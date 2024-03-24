package org.main.commands;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.selections.SelectOption;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;
import net.dv8tion.jda.api.interactions.modals.Modal;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.components.text.TextInput;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class ProfileCommand implements CommandHandler {
    @Override
    public void execute(SlashCommandInteractionEvent event) {
        // Send a message with a button to create/edit profile
        Button editProfileButton = Button.primary("create_edit_profile", "Create/Edit Profile");
        Button viewProfileButton = Button.primary("view_profile", "View Profile");
        Button interestButton = Button.primary("select_buddy", "Select Buddy");
        Button selectUniversityButton = Button.primary("select_university", "Select University");
        event.reply("Click the buttons to edit your profile")
                .addActionRow(
                        viewProfileButton,
                        editProfileButton,
                        selectUniversityButton,
                        interestButton
                        )
                .queue();

    }
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static class Profile {
        public String id;
        public String name;
        public String age;
        public String university;
        public String buddy;
        public String degree;
        public String aboutMe;


        // Getters and setters if needed
    }
    public static void viewProfile(ButtonInteractionEvent event){


        String userId = event.getUser().getId();
        String backendUrl = "http://127.0.0.1:8080/profiles/" + userId;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(backendUrl))
                .GET()
                .build();

        HttpClient.newHttpClient()
                .sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(jsonBody -> {
                    try {
                        // Parse the JSON response into a Profile object
                        return objectMapper.readValue(jsonBody, Profile.class);
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new RuntimeException("Failed to parse JSON response");
                    }
                })
                .thenAccept(profile -> {
                    // Format the profile details into a String
                    String data = String.format("Name: %s\nUniversity: %s\nDegree: %s\nAge: %s\nSearching for: %s\nAbout Me: %s",
                            profile.name, profile.university, profile.degree, profile.age, profile.buddy, profile.aboutMe);

                    // Reply with the data
                    event.reply(data).setEphemeral(true).queue();
                })
                .exceptionally(e -> {
                    // Handle any exceptions here
                    e.printStackTrace();
                    event.reply("Failed to retrieve profile.").setEphemeral(true).queue();
                    return null;
                });
    }

    public static void editProfileModal(ButtonInteractionEvent event) {
        // Define text inputs for the modal
        TextInput nameInput = TextInput.create("name_input", "Name", TextInputStyle.SHORT).build();
        TextInput degreeInput = TextInput.create("degree_input", "Degree", TextInputStyle.SHORT).build();
        TextInput ageInput = TextInput.create("age_input", "Age", TextInputStyle.SHORT).build();
        TextInput aboutMeInput = TextInput.create("about_me_input", "About Me", TextInputStyle.PARAGRAPH).build();


        // Create the modal
        Modal createProfileModal = Modal.create("create_profile_modal", "Create/Edit Profile")
                .addActionRows(
                        ActionRow.of(nameInput),
                        ActionRow.of(degreeInput),
                        ActionRow.of(ageInput),
                        ActionRow.of(aboutMeInput)
                ).build();

        // Open the modal for the user
        event.replyModal(createProfileModal).queue();
    }

    public static void selectUniversity(ButtonInteractionEvent event) {
        StringSelectMenu universities = StringSelectMenu.create("select_university")
                .setPlaceholder("Choose your University")
                .addOptions(
                        SelectOption.of("Nazarbaev University", "nazarbaev"),
                        SelectOption.of("KBTU", "kbtu"),
                        SelectOption.of("Satpaev University", "satpaev"),
                        SelectOption.of("KazGU", "kazgu")
                )
                .build();

        // Correctly respond with a select menu
        event.reply("Please select your interest:")
                .addActionRow(universities)
                .setEphemeral(true)
                .queue();
    }
    public static void selectBuddy(ButtonInteractionEvent event) {
        StringSelectMenu menu = StringSelectMenu.create("selected_buddy")
                .setPlaceholder("Select your buddy")
                .addOptions(
                        SelectOption.of("Roommate", "roommate"),
                        SelectOption.of("Friend", "friend"),
                        SelectOption.of("Study Buddy", "study_buddy"),
                        SelectOption.of("Travel Buddy", "travel_buddy")
                )
                .build();

        // Correctly respond with a select menu
        event.reply("Please select your buddy: ")
                .addActionRow(menu)
                .setEphemeral(true)
                .queue();
    }

}

