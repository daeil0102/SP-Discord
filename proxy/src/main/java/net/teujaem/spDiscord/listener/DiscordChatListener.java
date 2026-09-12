package net.teujaem.spDiscord.listener;

import net.teujaem.spDiscord.SPDiscord;
import net.teujaem.spDiscord.event.DiscordMessageEvent;
import net.teujaem.spDiscord.model.DiscordMessageModel;
import net.teujaem.spFramework.api.ProxyData;
import net.teujaem.spFramework.websoket.PluginMessage;

import java.util.HashMap;
import java.util.Map;

public class DiscordChatListener implements DiscordMessageEvent {

    @Override
    public void onMessage(DiscordMessageModel event) {
        if (!event.getChannel().equals(SPDiscord.getInstance().getConfigManager().getChatChannelId())) return;

        Map<String, Object> data = new HashMap<>();
        data.put("eventname", "OnChat");
        data.put("value", event.getMessage());
        data.put("server", "Discord");

        PluginMessage pluginMessage = new PluginMessage(
                "server",
                "SP-ProxyData",
                null,
                event.getUserName(),
                data
        );

        ProxyData.sendToServer(pluginMessage);
    }

}
