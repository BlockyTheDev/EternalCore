package com.eternalcode.core.chat.notification;

import com.eternalcode.core.language.LanguageManager;
import com.eternalcode.core.language.MessageExtractor;
import com.eternalcode.core.language.NotificationExtractor;
import com.eternalcode.core.placeholder.PlaceholderRegistry;
import com.eternalcode.core.user.User;
import com.eternalcode.core.viewer.Viewer;
import com.eternalcode.core.viewer.ViewerProvider;
import panda.utilities.text.Formatter;

import javax.annotation.CheckReturnValue;
import java.util.UUID;

public class NoticeService {

    private final LanguageManager languageManager;
    private final ViewerProvider viewerProvider;
    private final NotificationAnnouncer announcer;
    private final PlaceholderRegistry placeholderRegistry;

    public NoticeService(LanguageManager languageManager, ViewerProvider viewerProvider, NotificationAnnouncer announcer, PlaceholderRegistry placeholderRegistry) {
        this.languageManager = languageManager;
        this.viewerProvider = viewerProvider;
        this.announcer = announcer;
        this.placeholderRegistry = placeholderRegistry;
    }

    @CheckReturnValue
    public Notice create() {
        return new Notice(this.languageManager, this.viewerProvider, this.announcer, this.placeholderRegistry);
    }

    public void player(UUID player, NotificationExtractor extractor, Formatter... formatters) {
        this.create()
            .player(player)
            .message(extractor)
            .formatter(formatters)
            .send();
    }

    public void players(Iterable<UUID> players, NotificationExtractor extractor, Formatter... formatters) {
        this.create()
            .players(players)
            .message(extractor)
            .formatter(formatters)
            .send();
    }

    public void user(User user, NotificationExtractor extractor, Formatter... formatters) {
        this.create()
            .user(user)
            .message(extractor)
            .formatter(formatters)
            .send();
    }

    public void viewer(Viewer viewer, NotificationExtractor extractor, Formatter... formatters) {
        this.create()
            .viewer(viewer)
            .message(extractor)
            .formatter(formatters)
            .send();
    }


    public void console(NotificationExtractor extractor, Formatter... formatters) {
        this.create()
            .console()
            .message(extractor)
            .formatter(formatters)
            .send();
    }

    public void all(NotificationExtractor extractor, Formatter... formatters) {
        this.create()
            .all()
            .message(extractor)
            .formatter(formatters)
            .send();
    }

}
