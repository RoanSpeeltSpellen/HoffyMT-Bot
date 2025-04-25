package dev.roan.discordbot.listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class JoinListener extends ListenerAdapter {

    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
        // Verkrijg het nieuwe lid dat zich heeft aangemeld
        Member member = (Member) event.getMember();

        // Maak een EmbedBuilder aan
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setAuthor("HoffyMT | OPMT");
        embedBuilder.setTitle(member.getEffectiveName() + " is gejoined!");
        embedBuilder.setColor(Color.BLUE);
        embedBuilder.addField("Aantal leden:", String.valueOf(event.getGuild().getMemberCount()), false);
        embedBuilder.addField("Server IP:", "play.hoffymt.nl", false);
        embedBuilder.setThumbnail("https://cdn.discordapp.com/attachments/1327290995804475404/1365233864078786613/TIJDELIJKLOGOHOFFYMT.png?ex=680c909e&is=680b3f1e&hm=91e4b567b68bff2d311313435b086a61f82060a7c6e43442b4c58e3995e2297b&");
        embedBuilder.setFooter("HoffyMT | Official Discord");

        event.getGuild().getTextChannelById("1337085879306424330").sendMessageEmbeds(embedBuilder.build()).queue();
        };
    }

