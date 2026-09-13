package net.teujaem.spDiscord.listener.player;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.PostLoginEvent;
import net.teujaem.spDiscord.SPDiscord;

public class JoinListener {

    private final SPDiscord plugin;

    public JoinListener(SPDiscord plugin) {
        this.plugin = plugin;
    }

    @Subscribe
    public void onProxyEvent(PostLoginEvent event) {
        plugin.getDiscord().sendToChat("+ " + event.getPlayer().getUsername());
    }

}
