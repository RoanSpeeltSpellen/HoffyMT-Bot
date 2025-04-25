package dev.roan.discordbot.managers;

import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SlashCommandManager extends ListenerAdapter {

    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        List<CommandData> commands = new ArrayList<>();

        commands.add(Commands.slash("test", "een leuke test command"));
        commands.add(Commands.slash("userinfo", "Krijg informatie over een gebruiker")
                .addOption(OptionType.USER, "gebruiker", "De gebruiker waarvan je informatie wilt", true));
        commands.add(Commands.slash("setticket", "Zet de ticket embed"));
        commands.add(Commands.slash("kick", "Kick een gebruiker")
                .addOption(OptionType.USER, "gebruiker", "De gebruiker die je wil kicken", true)
                .addOption(OptionType.STRING, "reden", "De reden van de kick", true));
        commands.add(Commands.slash("ban", "verban een gebruiker")
                .addOption(OptionType.USER, "gebruiker", "De gebruiker die je wil bannen", true)
                .addOption(OptionType.STRING, "reden", "De reden van de ban", true)
                .addOptions(new OptionData(OptionType.INTEGER, "verwijder-berichten", "Hoeveel van z'n berichtengeschiedenis wordt verwijderd", true)
                        .setRequiredRange(0, 7)));
        commands.add(Commands.slash("youtube", "Krijg informatie over het youtube kanaal RoanSpeeltSpellen"));
        commands.add(Commands.slash("suggest", "Plaats een suggestie")
                .addOption(OptionType.STRING, "suggestie", "Het suggestie bericht"));

        event.getGuild().updateCommands().addCommands(commands).queue();
    }
}
