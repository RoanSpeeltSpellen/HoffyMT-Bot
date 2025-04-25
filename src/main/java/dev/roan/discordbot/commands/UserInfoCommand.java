package dev.roan.discordbot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Random;

public class UserInfoCommand extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (event.getName().equals("userinfo")){

            OptionMapping option = event.getOption("gebruiker");

            if (option == null){
                event.reply("Je hebt geen gebruiker meegegeven!").queue();
                return;
            }

            Member member = event.getMember();
            Member target = option.getAsMember();

            String accountCreated = target.getTimeCreated().getDayOfMonth() + "/" + target.getTimeCreated().getMonthValue() + "/" + target.getTimeCreated().getYear();
            String joinedDate = target.getTimeJoined().getDayOfMonth() + "/" + target.getTimeJoined().getMonthValue() + "/" + target.getTimeJoined().getYear();

            EmbedBuilder embed = new EmbedBuilder();
            embed.setTitle("Informatie over " + target.getEffectiveName());
            embed.setDescription("Hier wat informatie over deze gebruiker:");
            embed.addField("Id:", target.getId(), false);
            embed.addField("Gejoind op:", joinedDate, true);
            embed.addField("Account gemaakt op: ", accountCreated, true);
            embed.setThumbnail(target.getEffectiveAvatarUrl());
            embed.setFooter("HoffyMT | Official Discord" + event.getGuild().getIconUrl());

            Random random = new Random();
            float r = random.nextFloat();
            float g = random.nextFloat();
            float b = random.nextFloat();
            embed.setColor(new Color(r, g, b));

            event.replyEmbeds(embed.build()).queue();
        }
    }
}
