package com.mysticalstudio.mysticalpets.commands.admin.subcommands;

import com.mysticalstudio.mysticalpets.commands.SubCommand;
import com.mysticalstudio.mysticalpets.managers.PetManager;
import com.mysticalstudio.mysticalpets.models.Pet;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AddPetCommand implements SubCommand {

    private final Map<String, SubCommand> subCommands = new LinkedHashMap<>();

    private final PetManager petManager;

    public AddPetCommand(PetManager petManager) {
        this.petManager = petManager;
    }

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public void execute(CommandSourceStack source, String[] args) {

        CommandSender sender = source.getSender();

        if (args.length < 2) {
            sender.sendMessage("Usage: /petsadmin add <player> <pet>");
            return;
        }

        Player target = Bukkit.getPlayerExact(args[0]);

        if (target == null) {
            sender.sendMessage("§cPlayer '" + args[0] + "' is not online.");
            return;
        }

        Pet pet = petManager.getPet(args[1]);

        if (pet == null) {
            sender.sendMessage("§cUnknown pet '" + args[1] + "'.");
            return;
        }

        petManager.givePet(target.getUniqueId(), pet.getId());

        sender.sendMessage("§aGave " + target.getName() + " a " + pet.getDisplayName() + ".");

    }

    @Override
    public List<String> tabComplete(CommandSourceStack source, String[] args) {

        if (args.length == 1) {
            return Bukkit.getOnlinePlayers().stream().map(Player::getName).collect(Collectors.toList());
        }

        if (args.length == 2) {
            for (Pet pet : petManager.getPets()) {
                return Collections.singletonList(pet.getId());
            }
        }

        return List.of();
    }

    @Override
    public String getPermission() {
        return SubCommand.super.getPermission();
    }

    @Override
    public boolean hasPermission(CommandSender sender) {
        return SubCommand.super.hasPermission(sender);
    }

    @Override
    public void sendHelp(CommandSender sender) {
        SubCommand.super.sendHelp(sender);

        for (SubCommand command : subCommands.values()) {
            if (command.hasPermission(sender)) {
                sender.sendMessage("§e/petsadmin " + command.getName());
            }
        }
    }
}
