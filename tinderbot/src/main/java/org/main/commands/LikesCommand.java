package org.main.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

public class LikesCommand implements CommandHandler {
    @Override
    public void execute(SlashCommandInteractionEvent event) {
        String name = "Uchiha Itachi";
        int age = 15;
        String degree = "MIT";
        String aboutMe = "Anbu and Akatsuki Member";
        String university = "Konoha Ninja Academy";

        Button nextButton = Button.primary("next_who_likes_me", "Next");
        Button likeButton = Button.primary("like_who_likes_me", "Like");

        String data = String.format("Name: %s\nUniversity: %s\nDegree: %s\nAge: %s\nAbout Me: %s", name, university, degree, age, aboutMe);

        // Reply with the data
        event.reply(data)
                .addActionRow(
                        likeButton,
                        nextButton
                )
                .queue();
    }
}