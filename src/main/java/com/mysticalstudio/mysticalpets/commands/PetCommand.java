package com.mysticalstudio.mysticalpets.commands;

import com.mysticalstudio.mysticalpets.commands.subcommands.GiveCommand;

import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.command.CommandSender;
import org.jspecify.annotations.NonNull;

import java.util.*;

public class PetCommand implements BasicCommand {

    private final Map<String, SubCommand> subCommands = new LinkedHashMap<>();

    public PetCommand() {
        register(new GiveCommand());
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

        String[] subArgs = Arrays.copyOfRange(args, 1, args.length);

        command.execute(source, subArgs);
    }

    @Override
    public @NonNull Collection<String> suggest(@NonNull CommandSourceStack source, String[] args) {

        if (args.length == 0) {
            return subCommands.keySet();
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
            sender.sendMessage("§e/pets " + command.getName());
        }
    }
}
