package net.teujaem.spDiscord.listener;

import net.teujaem.spDiscord.SPDiscord;
import net.teujaem.spFramework.api.ProxyData;
import net.teujaem.spFramework.websoket.PluginMessage;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import java.util.HashMap;
import java.util.Map;

public class ChatListener implements Listener {

    private final SPDiscord plugin;

    public ChatListener(SPDiscord plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onChatEvent(PlayerChatEvent event) {

        Map<String, Object> data = new HashMap<>();
        data.put("eventname", "OnChat");
        data.put("value", event.getMessage());
        data.put("server", plugin.getConfigManager().getName());

        PluginMessage pluginMessage = new PluginMessage(
                "server",
                plugin.getName(),
                event.getPlayer().getUniqueId(),
                event.getPlayer().getName(),
                data
        );

        ProxyData.sendToServer(pluginMessage);

        event.setCancelled(true);
    }

}
