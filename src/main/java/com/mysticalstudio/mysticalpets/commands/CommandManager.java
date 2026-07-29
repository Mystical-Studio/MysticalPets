package com.mysticalstudio.mysticalpets.commands;

import io.papermc.paper.command.brigadier.Commands;

public class CommandManager {

    public void register(Commands commands) {

        commands.register("pets", new PetCommand());

    }
}
