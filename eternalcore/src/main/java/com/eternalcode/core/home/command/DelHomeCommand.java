package com.eternalcode.core.home.command;

import com.eternalcode.core.chat.notification.NoticeService;
import com.eternalcode.core.home.Home;
import com.eternalcode.core.home.HomeManager;
import com.eternalcode.core.user.User;
import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.route.Route;

@Route(name = "delhome")
public class DelHomeCommand {

    private final HomeManager homeManager;
    private final NoticeService noticeService;

    public DelHomeCommand(HomeManager homeManager, NoticeService noticeService) {
        this.homeManager = homeManager;
        this.noticeService = noticeService;
    }

    @Execute
    void execute(User user, @ArgHome Home home) {
        this.homeManager.deleteHome(user, home.getName());
        this.noticeService.create()
            .user(user)
            .notice(messages -> messages.home().delete())
            .placeholder("{HOME}", home.getName())
            .send();
    }

}
