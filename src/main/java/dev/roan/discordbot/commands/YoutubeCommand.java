package dev.roan.discordbot.commands;

import dev.roan.discordbot.DiscordBot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class YoutubeCommand extends ListenerAdapter {

    private DiscordBot discordBot;

    public YoutubeCommand(DiscordBot discordBot){
        this.discordBot = discordBot;
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (event.getName().equals("youtube")){

            JSONObject object = null;

            try {

                URL url = new URL("https://www.googleapis.com/youtube/v3/channels?part=statistics&id=" + discordBot.getYoutubeChannelId() + "&key=" + discordBot.getYoutubeApiKey());
                URLConnection connection = url.openConnection();
                connection.connect();

                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder sb = new StringBuilder();

                String line;
                while ((line = br.readLine()) != null){
                    sb.append(line + "\n");
                }
                br.close();

                JSONObject jsonObject = new JSONObject(sb.toString());
                object = jsonObject.getJSONArray("items").getJSONObject(0).getJSONObject("statistics");

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            EmbedBuilder embed = new EmbedBuilder();
            embed.setColor(new Color(255, 0, 0));
            embed.setTitle("Youtube RoanSpeeltSpellen", "https://www.youtube.com/@RoanSpeeltSpellen122");
            embed.setDescription("informatie over het Youtube kanaal RoanSpeeltSpellen");
            embed.addField("Abbonnees", object.get("subscriberCount").toString(), false);
            embed.addField("Views", object.get("viewCount").toString(), false);
            embed.addField("Videos", object.get("videoCount").toString(), false);
            embed.setThumbnail(event.getGuild().getIconUrl());

            event.replyEmbeds(embed.build()).queue();
        }
    }
}
