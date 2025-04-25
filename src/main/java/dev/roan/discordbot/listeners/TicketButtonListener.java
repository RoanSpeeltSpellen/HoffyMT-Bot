package dev.roan.discordbot.listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.EnumSet;

public class TicketButtonListener extends ListenerAdapter {

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        if (event.getButton().getId().equals("openTicket")){

            Guild guild = event.getGuild();
            Member member = event.getMember();

            EmbedBuilder embed = new EmbedBuilder();
            embed.setColor(Color.GREEN);
            embed.setTitle(member.getEffectiveName() + "'s Ticket");
            embed.setDescription("Een staff-lid helpt je zo snel mogelijk.");

            guild.createTextChannel("ticket-" + member.getEffectiveName().toLowerCase(), guild.getCategoryById("1365000163067957311"))
                    .addPermissionOverride(member, EnumSet.of(Permission.VIEW_CHANNEL), null)
                    .addPermissionOverride(guild.getRoleById("1365004150085259376" ), EnumSet.of(Permission.VIEW_CHANNEL), null)
                    .addPermissionOverride(guild.getPublicRole(), null, EnumSet.of(Permission.VIEW_CHANNEL))
                    .complete().sendMessageEmbeds(embed.build()).setActionRow(closeButton()).queue();

                     event.reply("Je ticket is aangemaakt!").setEphemeral(true).queue();
        } else if (event.getButton().getId().equals("closeButton")){
            event.getInteraction().getChannel().delete().queue();
        }
    }

    private Button closeButton(){
        return Button.danger("closeButton", "Sluiten");
    }

}
