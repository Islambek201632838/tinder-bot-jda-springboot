package org.main.listeners.buttons;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.main.commands.ProfileCommand;

public class LikesButtonClickListener extends ListenerAdapter {
    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
         if (event.getComponentId().equals("next_who_likes_me")) {
            event.reply("Next don't exists");
        } else if (event.getComponentId().equals("like_who_likes_me")) {
            event.reply("Like don't exists");
        }
    }
}
