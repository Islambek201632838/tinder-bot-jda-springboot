package org.main.listeners;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class ModalEventListener extends ListenerAdapter {
    private final String url = "http://127.0.0.1:8080/profiles";
    private final ObjectMapper objectMapper = new ObjectMapper();

    private static class Profile {
        public String id;
        public String name;
        public String degree;
        public String age;
        public String aboutMe;

        public Profile(String id, String name, String degree, String age, String aboutMe) {
            this.id = id;
            this.name = name;
            this.degree = degree;
            this.age = age;
            this.aboutMe = aboutMe;
        }
    }

    private String toJson(Profile profile) throws JsonProcessingException {
        return objectMapper.writeValueAsString(profile);
    }

    @Override
    public void onModalInteraction(@NotNull ModalInteractionEvent event) {
        if (!"create_profile_modal".equals(event.getModalId())) {
            return;
        }

        // Defer the reply. This acknowledges the interaction immediately and gives you time to process the request.
        event.deferReply(true).queue();

        // Extract the modal values.
        String id = event.getUser().getId();
        String name = getValue(event, "name_input");
        String degree = getValue(event, "degree_input");
        String age = getValue(event, "age_input");
        String aboutMe = getValue(event, "about_me_input");

        Profile profile = new Profile(id, name, degree, age, aboutMe);

        // Process the request asynchronously.
        CompletableFuture.runAsync(() -> {
            try {
                String jsonPayload = toJson(profile);
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
                        .build();

                HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

                // Respond to the interaction.
                event.getHook().editOriginal("Profile updated successfully!").queue();
            } catch (Exception e) {
                e.printStackTrace();
                event.getHook().editOriginal("Failed to update profile.").queue();
            }
        });
    }

    private String getValue(ModalInteractionEvent event, String key) {
        String parameter = event.getValue(key) != null ? event.getValue(key).getAsString() : "N/A";
        return parameter;
    }
}
