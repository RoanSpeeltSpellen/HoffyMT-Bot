package dev.roan.discordbot.listeners;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class MessageReceivedListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {

        String memberMention = event.getMember().getAsMention();

        if (event.getChannel().equals(event.getGuild().getTextChannelById("1337085879306424330"))){
            if (!event.getMember().getUser().isBot()){
                event.getMessage().delete().queue();
                event.getChannel().sendMessage(memberMention + " hier kan je geen berichten plaatsen").queue();
            }
        }

    }
}
