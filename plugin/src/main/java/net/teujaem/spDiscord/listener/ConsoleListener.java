package net.teujaem.spDiscord.listener;

import net.teujaem.spDiscord.SPDiscord;
import net.teujaem.spFramework.api.ProxyData;
import net.teujaem.spFramework.websoket.PluginMessage;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class ConsoleListener extends Handler implements Listener {

    private final SPDiscord plugin;
    private final Logger logger;

    public ConsoleListener(SPDiscord plugin) {
        this.plugin = plugin;
        this.logger = Bukkit.getLogger();
        logger.addHandler(this);
    }

    @Override
    public void publish(LogRecord record) {
        if (!plugin.isEnabled()) return;
        if (record.getMessage() == null || record.getMessage().isEmpty()) return;

        String message = record.getMessage();
        if (message.contains("issued server command")) return;

        sendServer("OnConsoleLog", message);

    }

    @EventHandler
    public void onServerCommand(ServerCommandEvent event) {
        if (!plugin.isEnabled()) return;

        sendServer("OnConsoleCommand", event.getCommand());

    }

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        if (!plugin.isEnabled()) return;

        ProxyData.sendToPlayer(plugin.getName(),
                "OnPlayerCommand",
                event.getMessage(),
                event.getPlayer());

    }

    @Override
    public void flush() {

    }

    @Override
    public void close() throws SecurityException {
        logger.removeHandler(this);
    }

    private void sendServer(String eventName, String value) {
        Map<String, Object> data = new HashMap<>();
        data.put("eventname", eventName);
        data.put("value", value);
        data.put("server", plugin.getConfigManager().getName());

        PluginMessage pluginMessage = new PluginMessage(
                "server",
                plugin.getName(),
                null,
                null,
                data
        );

        ProxyData.sendToServer(pluginMessage);
    }
}
