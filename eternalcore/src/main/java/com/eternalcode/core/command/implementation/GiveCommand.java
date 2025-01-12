package com.eternalcode.core.command.implementation;

import com.eternalcode.core.util.MaterialUtil;
import com.eternalcode.core.viewer.Viewer;
import com.eternalcode.core.chat.notification.NoticeService;

import dev.rollczi.litecommands.argument.Arg;
import dev.rollczi.litecommands.argument.By;
import dev.rollczi.litecommands.argument.Name;
import dev.rollczi.litecommands.command.amount.Between;
import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.route.Route;
import dev.rollczi.litecommands.command.permission.Permission;
import dev.triumphteam.gui.builder.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Route(name = "give", aliases = {"i", "item"})
@Permission("eternalcore.give")
public class GiveCommand {

    private final NoticeService noticeService;

    public GiveCommand(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @Execute
    @Between(min = 1, max = 2)
    void execute(Viewer audience, CommandSender sender, @Arg @Name("material") Material material, @Arg @By("or_sender") Player player) {
        String formattedMaterial = MaterialUtil.format(material);

        this.giveItem(player, material);

        this.noticeService.create()
            .placeholder("{ITEM}", formattedMaterial)
            .notice(messages -> messages.other().giveReceived())
            .player(player.getUniqueId())
            .send();

        if (sender.equals(player)) {
            return;
        }

        this.noticeService.create()
            .placeholder("{ITEM}", formattedMaterial)
            .placeholder("{PLAYER}", player.getName())
            .notice(messages -> messages.other().giveGiven())
            .viewer(audience)
            .send();
    }

    private void giveItem(Player player, Material material) {
        int amount = 64;

        if (material.isItem()) {
            amount = 1;
        }

        ItemStack item = ItemBuilder.from(material)
            .amount(amount)
            .build();

        player.getInventory().addItem(item);
    }

}
