package net.teujaem.spDiscord.listener.console;

import net.teujaem.spDiscord.SPDiscord;
import net.teujaem.spDiscord.event.DiscordMessageEvent;
import net.teujaem.spDiscord.model.DiscordMessageModel;
import net.teujaem.spFramework.api.ProxyData;
import net.teujaem.spFramework.websoket.PluginMessage;

import java.util.HashMap;
import java.util.Map;

public class DiscordConsoleListener implements DiscordMessageEvent {

    @Override
    public void onMessage(DiscordMessageModel event) {
        if (!event.getChannel().equals(SPDiscord.getInstance().getConfigManager().getConsoleChannelId())) return;

        if (!event.getMessage().contains("|")) {
            help();
            return;
        }

        String[] messages = event.getMessage().split("\\s*\\|\\s*", 2);

        String server = messages[0];
        String command = messages[1];

        Map<String, Object> data = new HashMap<>();
        data.put("eventname", "SendConsoleCommand");
        data.put("value", command);
        data.put("server", server);

        PluginMessage pluginMessage = new PluginMessage(
                "server",
                "SP-Discord",
                null,
                null,
                data
        );

        ProxyData.sendToServer(pluginMessage);

    }

    private void help() {
        SPDiscord.getInstance().getDiscord().sendToConsole("커맨드 실행 방법 : <server> \\| <command>");
    }

}
