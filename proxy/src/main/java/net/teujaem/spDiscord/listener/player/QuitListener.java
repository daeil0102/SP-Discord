package net.teujaem.spDiscord.listener.player;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.DisconnectEvent;
import net.teujaem.spDiscord.SPDiscord;

public class QuitListener {

    private final SPDiscord plugin;

    public QuitListener(SPDiscord plugin) {
        this.plugin = plugin;
    }

    @Subscribe
    public void onProxyEvent(DisconnectEvent event) {
        plugin.getDiscord().sendToChat("- " + event.getPlayer().getUsername());
    }

}
