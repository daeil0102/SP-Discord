package net.teujaem.spDiscord.boot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.middleman.GuildMessageChannel;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.teujaem.spDiscord.event.DiscordMessageEvent;
import net.teujaem.spDiscord.listener.DiscordListener;
import net.teujaem.spDiscord.model.DiscordModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DiscordBoot {

    private static final Logger logger = LoggerFactory.getLogger(DiscordBoot.class);

    private JDA jda;
    private final DiscordModel discordModel;
    private final DiscordMessageEvent discordMessageEvent;

    public DiscordBoot(DiscordModel discordModel, DiscordMessageEvent discordMessageEvent) {
        this.discordModel = discordModel;
        this.discordMessageEvent = discordMessageEvent;
    }

    public void startBot() {
        String token = discordModel.getToken();
        String serverId = discordModel.getServerId();

        if (token == null || token.isEmpty() || token.equals("token")) {
            logger.error("config.yaml에 토큰이 설정되지 않았습니다! 파일을 수정해주세요.");
            return;
        }

        new Thread(() -> {
            try {
                jda = JDABuilder.createDefault(
                                token,
                                GatewayIntent.GUILD_MESSAGES,
                                GatewayIntent.MESSAGE_CONTENT,
                                GatewayIntent.GUILD_MEMBERS
                        )
                        .addEventListeners(new DiscordListener(discordMessageEvent))
                        .build();

                jda.awaitReady();

                Guild guild = jda.getGuildById(serverId);

                if (guild != null) {
                    logger.info("명령어가 서버 [{}]에 등록되었습니다.", serverId);
                } else {
                    logger.error("서버 ID [{}]를 찾을 수 없습니다!", serverId);
                }

                logger.info("디스코드 봇이 정상적으로 실행되었습니다!");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.error("봇 실행 중 오류 발생", e);
            }
        }).start();
    }

    public void shutdownBot() {
        if (jda != null) {
            jda.shutdown();
            logger.info("디스코드 봇이 종료되었습니다.");
        }
    }

    public void sendConsoleMessage(String message) {
        if (jda == null) return;

        TextChannel consoleChannel =
                jda.getTextChannelById(discordModel.getConsoleChannelId());

        if (consoleChannel != null) {
            consoleChannel.sendMessage(message).queue();
        } else {
            logger.warn(
                    "콘솔 채널을 찾을 수 없습니다! (ID: {})",
                    discordModel.getConsoleChannelId()
            );
        }
    }

    public void sendChatMessage(String message) {
        if (jda == null) return;

        TextChannel consoleChannel =
                jda.getTextChannelById(discordModel.getChatChannelId());

        if (consoleChannel != null) {
            consoleChannel.sendMessage(message).queue();
        } else {
            logger.warn(
                    "채팅 채널을 찾을 수 없습니다! (ID: {})",
                    discordModel.getChatChannelId()
            );
        }
    }

    public String getConsoleId() {
        return discordModel.getConsoleChannelId();
    }

    public GuildMessageChannel getConsoleChannel() {
        if (jda == null) return null;

        return jda.getChannelById(
                GuildMessageChannel.class,
                discordModel.getConsoleChannelId()
        );
    }

    public JDA getJDA() {
        return jda;
    }

    public DiscordModel getDiscordModel() {
        return discordModel;
    }

    public DiscordMessageEvent getDiscordMessageEvent() {
        return discordMessageEvent;
    }
}