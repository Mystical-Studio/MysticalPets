package com.mysticalstudio.mysticalpets.commands.server;

import com.mysticalstudio.mysticalpets.commands.SubCommand;
import com.mysticalstudio.mysticalpets.commands.server.subcommands.ReloadPetsCommand;
import com.mysticalstudio.mysticalpets.database.DatabaseManager;
import com.mysticalstudio.mysticalpets.managers.PetManager;
import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.command.CommandSender;
import org.jspecify.annotations.NonNull;

import java.util.*;

public class PetsServerCommand implements BasicCommand {
    private final Map<String, SubCommand> subCommands = new LinkedHashMap<>();

    private final DatabaseManager databaseManager;
    private final PetManager petManager;

    public PetsServerCommand(DatabaseManager databaseManager, PetManager petManager) {
        this.databaseManager = databaseManager;
        this.petManager = petManager;

        register(new ReloadPetsCommand(petManager));
    }

    @Override
    public void execute(CommandSourceStack source, String[] args) {

        CommandSender sender = source.getSender();

        if (args.length == 0) {
            sendHelp(sender);
            return;
        }

        String subCommandName = args[0].toLowerCase();

        SubCommand command = subCommands.get(subCommandName);

        if (command == null) {
            sender.sendMessage("Unknown subcommand.");
            sendHelp(sender);
            return;
        }

        if (!command.hasPermission(sender)) {
            sender.sendMessage("You do not have permission to use this command.");
            return;
        }

        String[] subArgs = Arrays.copyOfRange(args, 1, args.length);

        command.execute(source, subArgs);
    }

    @Override
    public Collection<String> suggest(CommandSourceStack source, String[] args) {

        if (args.length == 0) {

            return subCommands.values()
                    .stream()
                    .filter(command -> command.hasPermission(source.getSender()))
                    .map(SubCommand::getName)
                    .toList();

        }

        SubCommand command = subCommands.get(args[0].toLowerCase());

        if (command != null) {

            String[] subArgs = Arrays.copyOfRange(args, 1, args.length);

            return command.tabComplete(source, subArgs);

        }

        return List.of();
    }

    private void register(SubCommand command) {
        subCommands.put(command.getName().toLowerCase(), command);
    }


    private void sendHelp(CommandSender sender) {

        sender.sendMessage("§6MysticalPets Commands:");

        for (SubCommand command : subCommands.values()) {
            if (command.hasPermission(sender)) {
                sender.sendMessage("§e/petsserver " + command.getName());
            }
        }
    }
}
