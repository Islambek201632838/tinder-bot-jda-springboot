package org.main;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.main.commands.CommandListener;
import org.main.listeners.buttons.LikesButtonClickListener;
import org.main.listeners.buttons.ProfileButtonClickListener;
import org.main.listeners.ModalEventListener;
import org.main.listeners.SelectionMenuListener;
import org.main.listeners.buttons.SearchButtonClickListener;


public class Bot {

    public static void main(String[] args) {
        try {
            // Initialize the JDA builder with the bot token and necessary intents
            JDABuilder builder = JDABuilder.createDefault("");
            builder.enableIntents(GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES);
            builder.setActivity(Activity.playing("Type /help for commands"));

            // Add an event listener that will handle commands
            builder.addEventListeners(new CommandListener(),
                                      new ProfileButtonClickListener(),
                                      new SearchButtonClickListener(),
                                      new LikesButtonClickListener(),
                                      new ModalEventListener(),
                                      new SelectionMenuListener());

            // Build the JDA instance
            JDA jda = builder.build();

            // Await the JDA to be fully loaded
            jda.awaitReady();

            // Register commands after the bot is ready
            registerCommands(jda);

            System.out.println("Bot is up and running!");

        } catch (Exception e) {
            System.err.println("Error logging in: " + e.getMessage());
        }
    }

    private static void registerCommands(JDA jda) {
        jda.updateCommands()
                .addCommands(
                        Commands.slash("search", "Search for a roommate or study buddy"),
                        Commands.slash("profile", "View or edit your profile"),
                        Commands.slash("likes", "View your likes"),
                        Commands.slash("help", "Get help with the bot commands")
                        // Add more command registrations here as needed
                )
                .queue();
    }
}
