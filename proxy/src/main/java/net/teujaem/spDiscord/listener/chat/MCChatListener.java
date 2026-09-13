package net.teujaem.spDiscord.listener.chat;

import com.velocitypowered.api.event.Subscribe;
import net.teujaem.spDiscord.SPDiscord;
import net.teujaem.spFramework.api.event.ProxyEvent;
import net.teujaem.spFramework.websoket.PluginMessage;

public class MCChatListener {

    private final SPDiscord plugin;

    public MCChatListener(SPDiscord plugin) {
        this.plugin = plugin;
    }

    @Subscribe
    public void onProxyEvent(ProxyEvent event) {

        PluginMessage message = event.getPluginMessage();

        if (message.name().equals("SO-Discord")) return;
        if (!message.data().get("eventname").equals("OnChat")) return;

        String playerName = message.username();
        String chatMessage = message.data().get("value").toString();
        String server = message.data().get("server").toString();

        if (chatMessage == null) return;
        if (server == null) return;
        if (playerName == null) return;

        plugin.getDiscord().sendToChat(
                "[" + server + "] " + playerName + ": " + chatMessage
        );
    }
}