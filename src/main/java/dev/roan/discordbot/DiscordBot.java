package dev.roan.discordbot;

import dev.roan.discordbot.commands.*;
import dev.roan.discordbot.listeners.JoinListener;
import dev.roan.discordbot.listeners.MessageReceivedListener;
import dev.roan.discordbot.listeners.TicketButtonListener;
import dev.roan.discordbot.managers.SlashCommandManager;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;

public class DiscordBot {

    private final String youtubeApiKey;
    private final String youtubeChannelId;

    public DiscordBot() throws LoginException {
        Dotenv dotenv = Dotenv.configure().load();
        String token = dotenv.get("TOKEN");
        youtubeApiKey = dotenv.get("YOUTUBE-API-KEY");
        youtubeChannelId = dotenv.get("YOUTUBE-CHANNEL-ID");

        DefaultShardManagerBuilder builder= DefaultShardManagerBuilder.createDefault(token);
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.playing("op play.hoffymt.nl"));
        builder.enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_MESSAGES);
        ShardManager shardManager = builder.build();
        shardManager.addEventListener(
                // Managers
                new SlashCommandManager(),
                // Listeners
                new JoinListener(),
                new MessageReceivedListener(),
                new TicketButtonListener(),
                // Commands
                new TestCommand(),
                new UserInfoCommand(),
                new SetTicketCommand(),
                new KickCommand(),
                new BanCommand(),
                new YoutubeCommand(this),
                new SuggestCommand()
        );
    }

    public static void main(String[] args) {
        try {
            DiscordBot bot = new DiscordBot();
        } catch (LoginException e) {
            System.out.println("Bot token niet gevonden!");
        }
    }

    public String getYoutubeApiKey(){
        return youtubeApiKey;
    }

    public String getYoutubeChannelId(){
        return youtubeChannelId;
    }

}
