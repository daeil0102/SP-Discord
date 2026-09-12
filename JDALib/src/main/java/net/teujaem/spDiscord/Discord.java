package net.teujaem.spDiscord;

import net.teujaem.spDiscord.boot.DiscordBoot;
import net.teujaem.spDiscord.event.DiscordMessageEvent;
import net.teujaem.spDiscord.model.DiscordMessageModel;
import net.teujaem.spDiscord.model.DiscordModel;

import java.util.ArrayList;
import java.util.List;

public class Discord {

    private final DiscordModel discordModel;
    private final List<DiscordMessageEvent> discordMessageEvents = new ArrayList<>();

    private DiscordBoot discordBoot;

    public Discord(DiscordModel discordModel) {
        this.discordModel = discordModel;
    }

    public void addMessageEvent(DiscordMessageEvent event) {
        discordMessageEvents.add(event);
    }

    public void start() {

        discordBoot = new DiscordBoot(discordModel, new DiscordMessageEvent() {
            @Override
            public void onMessage(DiscordMessageModel model) {
                for (DiscordMessageEvent event : discordMessageEvents) {
                    event.onMessage(model);
                }
            }
        });

        discordBoot.startBot();
    }

    public void stop() {
        if (discordBoot != null) {
            discordBoot.shutdownBot();
        }
    }
}