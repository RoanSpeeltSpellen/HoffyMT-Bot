package dev.roan.discordbot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class BanCommand extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (event.getName().equals("ban")){

            OptionMapping option1 = event.getOption("gebruiker");
            OptionMapping option2 = event.getOption("reden");
            OptionMapping option3 = event.getOption("verwijder-berichten");

            if (event.getMember().hasPermission(Permission.BAN_MEMBERS)){

                if (option1 == null || option2 == null || option3 == null){
                    event.reply("Er is een optie niet meegegeven@").setEphemeral(true).queue();
                    return;
                }

                User user = option1.getAsUser();
                String reason = option2.getAsString();
                int delete_messages = option3.getAsInt();

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                LocalDateTime now = LocalDateTime.now();

                EmbedBuilder embed = new EmbedBuilder();
                embed.setColor(new Color(255, 186, 57));
                embed.setTitle("Gebruiker verbannen!");
                embed.addField("Verbannen", user.getAsMention(), false);
                embed.addField("Verbannen door", event.getMember().getAsMention(), false);
                embed.addField("Reden", reason, false);
                embed.addField("Datum", dtf.format(now), false);
                embed.setThumbnail(user.getEffectiveAvatarUrl());

                event.getGuild().ban(user, delete_messages, TimeUnit.valueOf(reason));

                event.replyEmbeds(embed.build()).queue();

            } else {
                event.reply("Je hebt geen permissie voor dit command!").setEphemeral(true).queue();
            }

        }
    }
}
