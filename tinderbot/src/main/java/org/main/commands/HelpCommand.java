package org.main.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class HelpCommand implements CommandHandler {
    @Override
    public void execute(SlashCommandInteractionEvent event) {
        // Logic for handling the search command
        event.reply("Help functionality is not yet implemented!").queue();
    }
}