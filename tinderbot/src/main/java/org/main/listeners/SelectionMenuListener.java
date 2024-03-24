package org.main.listeners;

import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SelectionMenuListener extends ListenerAdapter {

    private final String url = "http://127.0.0.1:8080/profiles/";
    @Override
    public void onStringSelectInteraction(@NotNull StringSelectInteractionEvent event) {
        if (event.getComponentId().equals("selected_buddy")) {
            String selectedBuddy = event.getValues().isEmpty() ? "None" : event.getValues().get(0);
            event.reply("Your buddy is " + selectedBuddy).queue();
            String userId = event.getInteraction().getUser().getId();

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url+"updateBuddy/"+ userId))
                    .header("Content-Type", "text/plain")
                    .PUT(HttpRequest.BodyPublishers.ofString(selectedBuddy))
                    .build();

            try {
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                System.out.println("Response status code: " + response.statusCode());
                System.out.println("Response body: " + response.body());
                event.reply("Response status code: " + response.statusCode() + "\nResponse body: " + response.body());
            }
            catch (Exception e){
                System.out.println(e);
                event.reply(String.valueOf(e));
            }
        }
        else if (event.getComponentId().equals("select_university")) {
            String selectedUniversity = event.getValues().isEmpty() ? "None" : event.getValues().get(0);
            String userId = event.getInteraction().getUser().getId();

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url+"updateUniversity/"+ userId))
                    .header("Content-Type", "text/plain")
                    .PUT(HttpRequest.BodyPublishers.ofString(selectedUniversity))
                    .build();

            try {
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                System.out.println("Response status code: " + response.statusCode());
                System.out.println("Response body: " + response.body());
            }
            catch (Exception e){
                System.out.println(e);
            }
            event.reply("Your university " + selectedUniversity).queue();
        }
    }
}
