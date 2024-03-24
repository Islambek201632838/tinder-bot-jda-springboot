package org.main.commands;


import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public interface CommandHandler {
    void execute(SlashCommandInteractionEvent event);
}
