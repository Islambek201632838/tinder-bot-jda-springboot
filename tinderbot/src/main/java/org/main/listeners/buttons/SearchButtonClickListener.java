package org.main.listeners.buttons;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.jetbrains.annotations.NotNull;
import org.main.commands.SearchCommand;

public class SearchButtonClickListener extends ListenerAdapter {
    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        if (event.getComponentId().equals("friend")) {
            SearchCommand.showFriends(event);
        }
        else if (event.getComponentId().equals("roommate")) {
            SearchCommand.showRoommates(event);
        }
        else if(event.getComponentId().equals("study_buddy")) {
            SearchCommand.showStudyBuddies(event);
        }
        else if(event.getComponentId().equals("travel_buddy")){
            SearchCommand.showTravelBuddies(event);
        }
        if (event.getComponentId().equals("next_friend")) {
            event.reply("Next don't exists");
        } else if (event.getComponentId().equals("like_friend")) {
            event.reply("Like don't exists");
        }
        if (event.getComponentId().equals("next_roommate")) {
            event.reply("Next don't exists");
        } else if (event.getComponentId().equals("like_roommate")) {
            event.reply("Like don't exists");
        }
        if (event.getComponentId().equals("next_study_buddy")) {
            event.reply("Next don't exists");
        } else if (event.getComponentId().equals("like_study_buddy")) {
            event.reply("Like don't exists");
        }
        if (event.getComponentId().equals("next_travel_buddy")) {
            event.reply("Next don't exists");
        } else if (event.getComponentId().equals("like_travel_buddy")) {
            event.reply("Like don't exists");
        }

    }


}
