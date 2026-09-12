package net.teujaem.spDiscord.event;

import net.teujaem.spDiscord.model.DiscordMessageModel;

public interface DiscordMessageEvent {
    void onMessage(DiscordMessageModel discordMessageModel);
}
