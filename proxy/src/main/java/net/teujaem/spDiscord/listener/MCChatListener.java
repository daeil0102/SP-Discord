package net.teujaem.spDiscord.listener;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.player.PlayerChatEvent;
import net.teujaem.spDiscord.SPDiscord;

public class MCChatListener {

    @Subscribe
    public void onPlayerChatEvent(PlayerChatEvent event) {
        SPDiscord.getInstance().getDiscord().sendToChat(event.getPlayer().getUsername() + ": " + event.getMessage());
    }

}
