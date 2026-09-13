package net.teujaem.spDiscord.listener.discord;

import net.teujaem.spDiscord.SPDiscord;
import net.teujaem.spFramework.api.event.ProxyEvent;
import net.teujaem.spFramework.websoket.PluginMessage;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class SendConsoleListener implements Listener {

    private final SPDiscord plugin;

    public SendConsoleListener(SPDiscord plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onProxyEvent(ProxyEvent event) {

        if (!event.getPluginName().equals(plugin.getName())) return;
        if (!event.getEventName().equals("SendConsoleCommand")) return;

        PluginMessage pluginMessage = event.getRawMessage();

        if (!pluginMessage.data().get("server").toString().equals(plugin.getConfigManager().getName())) return;

        String command = event.getValue().toString();

        Bukkit.getScheduler().runTask(plugin, () -> {
            Bukkit.getServer().dispatchCommand(
                    Bukkit.getConsoleSender(), command
            );
        });

    }

}
