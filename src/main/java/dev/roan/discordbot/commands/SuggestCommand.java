package dev.roan.discordbot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Random;

public class SuggestCommand extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (event.getName().equals("suggest")){

            OptionMapping option = event.getOption("suggestie");

            if(option == null){
                event.reply("Je hebt geen suggestie meegegeven!").setEphemeral(true).queue();
                return;
            }

            String suggestion = option.getAsString();
            TextChannel suggestChannel = event.getGuild().getTextChannelById("1365251426934329364");

            if (suggestChannel == null){
                event.reply("Er is geen suggestie kanaal!").setEphemeral(true).queue();
                return;
            }

            Random random = new Random();
            float r = random.nextFloat();
            float g = random.nextFloat();
            float b = random.nextFloat();
            Color randomColor = new Color(r, g, b);

            EmbedBuilder embed = new EmbedBuilder();
            embed.setColor(randomColor);
            embed.setAuthor(event.getMember().getEffectiveName(), null, event.getMember().getEffectiveAvatarUrl());
            embed.setDescription(suggestion);
            embed.setFooter("Plaats een suggestie met /suggest");

            suggestChannel.sendMessageEmbeds(embed.build()).queue(message -> {
                message.addReaction(Emoji.fromUnicode("U+1F44D")).queue();
                message.addReaction(Emoji.fromUnicode("U+1F44E")).queue();
            });

            event.reply("Suggestie is geplaats in " + suggestChannel.getAsMention()).queue();

        }
    }
}
