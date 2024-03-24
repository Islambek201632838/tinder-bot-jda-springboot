package org.main.commands;// CommandListener.java
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.main.commands.*;
import org.main.commands.*;

import java.util.HashMap;
import java.util.Map;

public class CommandListener extends ListenerAdapter {
    private final Map<String, CommandHandler> commandHandlers;

    public CommandListener() {
        commandHandlers = new HashMap<>();
        commandHandlers.put("search", new SearchCommand());
        commandHandlers.put("profile", new ProfileCommand());
        commandHandlers.put("likes", new LikesCommand());
        commandHandlers.put("help", new HelpCommand());
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        String commandName = event.getName();
        CommandHandler handler = commandHandlers.get(commandName);

        if (handler != null) {
            handler.execute(event);
        } else {
            event.reply("This command is not recognized.").setEphemeral(true).queue();
        }
    }
}
