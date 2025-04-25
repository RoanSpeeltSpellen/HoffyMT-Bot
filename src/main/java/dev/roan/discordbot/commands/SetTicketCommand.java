package dev.roan.discordbot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class SetTicketCommand extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (event.getName().equals("setticket")){
            if(event.getMember().isOwner()){
                EmbedBuilder embed = new EmbedBuilder();
                embed.setColor(Color.GREEN);
                embed.setTitle("Ticket openen");
                embed.setDescription("Klik op de knop om een ticket te openen");
                event.getChannel().sendMessageEmbeds(embed.build())
                        .setActionRow(
                                Button.success("openTicket", "Open Ticket")
                                        .withEmoji(Emoji.fromUnicode("U+1F3AB"))).queue();
                event.reply("Ticket embed gezet!").setEphemeral(true).queue();
            } else {
                event.reply("Je hebt geen permissie om dit te doen!").setEphemeral(true).queue();
            }
        }
    }
}
