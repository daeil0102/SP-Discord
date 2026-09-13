package net.teujaem.spDiscord.listener.console;

import com.velocitypowered.api.event.Subscribe;
import net.teujaem.spDiscord.SPDiscord;
import net.teujaem.spFramework.api.event.ProxyEvent;
import net.teujaem.spFramework.websoket.PluginMessage;

public class MCConsoleListener {

    private final SPDiscord plugin;

    public MCConsoleListener(SPDiscord plugin) {
        this.plugin = plugin;
    }

    @Subscribe
    public void onProxyEvent(ProxyEvent event) {

        PluginMessage message = event.getPluginMessage();

        if (!message.name().equals("SP-Discord")) return;

        String eventName = message.data().get("eventname").toString();
        String server = message.data().get("server").toString();

        if (server == null) return;

        if (eventName.equals("OnConsoleLog")) {
            onLog(message, server);
        }

        if (eventName.equals("OnConsoleCommand")) {
            onConsoleCommand(message, server);
        }

        if (eventName.equals("OnPlayerCommand")) {
            onPlayerCommand(message, server);
        }
    }

    private void onLog(PluginMessage event, String server) {
        String log = event.data().get("value").toString();

        send("Log [" + server + "] " + log);
    }

    private void onPlayerCommand(PluginMessage event, String server) {
        String command = event.data().get("value").toString();
        String player = event.username();

        if (command == null) return;
        if (player == null) return;

        send("[" + server + "] " + player + ": " + command);
    }

    private void onConsoleCommand(PluginMessage event, String server) {
        String command = event.data().get("value").toString();

        send("Command [" + server + "] " + command);
    }

    private void send(String message) {
        plugin.getDiscord().sendToConsole(message);
    }
}