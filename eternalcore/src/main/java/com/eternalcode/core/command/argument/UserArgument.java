package com.eternalcode.core.command.argument;

import com.eternalcode.core.chat.notification.Notification;
import com.eternalcode.core.language.LanguageManager;
import com.eternalcode.core.language.Messages;
import com.eternalcode.core.user.User;
import com.eternalcode.core.user.UserManager;
import com.eternalcode.core.viewer.BukkitViewerProvider;
import com.eternalcode.core.viewer.Viewer;
import dev.rollczi.litecommands.argument.ArgumentName;
import dev.rollczi.litecommands.argument.simple.OneArgument;
import dev.rollczi.litecommands.command.LiteInvocation;
import dev.rollczi.litecommands.suggestion.Suggestion;
import org.bukkit.Server;
import org.bukkit.entity.HumanEntity;
import panda.std.Result;

import java.util.List;

@ArgumentName("player")
public class UserArgument extends AbstractViewerArgument<User> {

    private final Server server;
    private final UserManager userManager;

    public UserArgument(BukkitViewerProvider viewerProvider, LanguageManager languageManager, Server server, UserManager userManager) {
        super(viewerProvider, languageManager);
        this.server = server;
        this.userManager = userManager;
    }

    @Override
    public List<Suggestion> suggest(LiteInvocation invocation) {
        return this.server.getOnlinePlayers().stream()
            .map(HumanEntity::getName)
            .map(Suggestion::of)
            .toList();
    }

    @Override
    public Result<User, Notification> parse(LiteInvocation invocation, String argument, Messages messages) {
        return this.userManager.getUser(argument).toResult(() -> messages.argument().offlinePlayer());
    }

}
