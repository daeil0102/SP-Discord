package net.teujaem.spDiscord.lisener;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.teujaem.spDiscord.event.DiscordMessageEvent;
import net.teujaem.spDiscord.model.DiscordMessageModel;

public class DiscordListener extends ListenerAdapter {

    private final DiscordMessageEvent discordMessageEvent;

    public DiscordListener(DiscordMessageEvent discordMessageEvent) {
        this.discordMessageEvent = discordMessageEvent;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;

        DiscordMessageModel model = new DiscordMessageModel();
        model.setMessage(event.getMessage().getContentRaw());
        model.setChannel(event.getChannel().getId());

        discordMessageEvent.onMessage(model);

    }
}
