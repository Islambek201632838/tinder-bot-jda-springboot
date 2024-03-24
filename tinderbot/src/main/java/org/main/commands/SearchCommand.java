package org.main.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

public class SearchCommand implements CommandHandler {
    @Override
    public void execute(SlashCommandInteractionEvent event) {
        Button friend = Button.primary("friend","Friend");
        Button roomate = Button.primary("roommate", "Roommate");
        Button studyBuddy = Button.primary("study_buddy", "Study Buddy");
        Button travelBuddy = Button.primary("travel_buddy", "Travel Buddy");
        event.reply("Select your buddy you searching for")
                .addActionRow(friend, roomate, studyBuddy, travelBuddy)
                .queue();
    }

    public static void showFriends(ButtonInteractionEvent event) {
        String name = "Friend";
        int age = 15;
        String degree = "MIT";
        String aboutMe = "Anbu and Akatsuki Member";
        String university = "Konoha Ninja Academy";

        Button nextButton = Button.primary("next_friend", "Next");
        Button likeButton = Button.primary("like_friend", "Like");

        String data = String.format("Name: %s\nUniversity: %s\nDegree: %s\nAge: %s\nAbout Me: %s", name, university, degree, age, aboutMe);

        // Reply with the data
        event.reply(data)
                .addActionRow(
                        likeButton,
                        nextButton
                )
                .queue();
    }

    public static void showRoommates(ButtonInteractionEvent event) {
        String name = "Roomate";
        int age = 15;
        String degree = "MIT";
        String aboutMe = "Anbu and Akatsuki Member";
        String university = "Konoha Ninja Academy";

        Button nextButton = Button.primary("next_roommate", "Next");
        Button likeButton = Button.primary("like_roommate", "Like");

        String data = String.format("Name: %s\nUniversity: %s\nDegree: %s\nAge: %s\nAbout Me: %s", name, university, degree, age, aboutMe);

        // Reply with the data
        event.reply(data)
                .addActionRow(
                        likeButton,
                        nextButton
                )
                .queue();
    }

    public static void showStudyBuddies(ButtonInteractionEvent event) {
        String name = "Study Buddy";
        int age = 15;
        String degree = "MIT";
        String aboutMe = "Anbu and Akatsuki Member";
        String university = "Konoha Ninja Academy";

        Button nextButton = Button.primary("next_study_buddy", "Next");
        Button likeButton = Button.primary("like_study_buddy", "Like");

        String data = String.format("Name: %s\nUniversity: %s\nDegree: %s\nAge: %s\nAbout Me: %s", name, university, degree, age, aboutMe);

        // Reply with the data
        event.reply(data)
                .addActionRow(
                        likeButton,
                        nextButton
                )
                .queue();
    }

    public static void showTravelBuddies(ButtonInteractionEvent event) {
        String name = "Travel Buddy";
        int age = 15;
        String degree = "MIT";
        String aboutMe = "Anbu and Akatsuki Member";
        String university = "Konoha Ninja Academy";

        Button nextButton = Button.primary("next_travel_buddy", "Next");
        Button likeButton = Button.primary("like_travel_buddy", "Like");

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
