package org.main.listeners.buttons;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.main.commands.ProfileCommand;

public class ProfileButtonClickListener extends ListenerAdapter {

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        if (event.getComponentId().equals("create_edit_profile")) {
            ProfileCommand.editProfileModal(event);
        }
        else if (event.getComponentId().equals("view_profile")) {
            ProfileCommand.viewProfile(event);
        }
        else if(event.getComponentId().equals("select_buddy")) {
            ProfileCommand.selectBuddy(event);
        }
        else if(event.getComponentId().equals("select_university")){
            ProfileCommand.selectUniversity(event);
        }

    }
}
