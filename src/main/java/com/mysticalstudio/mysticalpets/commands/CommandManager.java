package com.mysticalstudio.mysticalpets.commands;

import com.mysticalstudio.mysticalpets.commands.admin.PetsAdminCommand;
import com.mysticalstudio.mysticalpets.commands.pets.PetsCommand;
import com.mysticalstudio.mysticalpets.commands.server.PetsServerCommand;
import com.mysticalstudio.mysticalpets.database.DatabaseManager;
import com.mysticalstudio.mysticalpets.managers.PetManager;
import io.papermc.paper.command.brigadier.Commands;

public class CommandManager {

    private DatabaseManager databaseManager;
    private PetManager petManager;

    public CommandManager(DatabaseManager databaseManager, PetManager petManager) {
        this.databaseManager = databaseManager;
        this.petManager = petManager;
    }

    public void register(Commands commands) {

        commands.register(
                "pets",
                new PetsCommand(databaseManager, petManager)
        );

        commands.register(
                "petsadmin",
                new PetsAdminCommand(databaseManager, petManager)
        );

        commands.register(
                "petsserver",
                new PetsServerCommand(databaseManager, petManager)
        );

    }
}
