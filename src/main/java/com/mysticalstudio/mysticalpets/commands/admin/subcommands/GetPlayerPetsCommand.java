package com.mysticalstudio.mysticalpets.commands.admin.subcommands;

import com.mysticalstudio.mysticalpets.commands.SubCommand;
import com.mysticalstudio.mysticalpets.managers.PetManager;
import com.mysticalstudio.mysticalpets.models.Pet;
import com.mysticalstudio.mysticalpets.models.PlayerPet;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class GetPlayerPetsCommand implements SubCommand {

    private final PetManager petManager;

    public GetPlayerPetsCommand(PetManager petManager) {
        this.petManager = petManager;
    }

    @Override
    public String getName() {
        return "pets";
    }

    @Override
    public void execute(CommandSourceStack source, String[] args) {

        CommandSender sender = source.getSender();

        if (args.length < 1) {
            sender.sendMessage("Usage: /petsadmin pets <player>");
            return;
        }

        Player player = Bukkit.getPlayerExact(args[0]);

        List<PlayerPet> playerPets = petManager.getPlayerPets(player.getUniqueId());

        if (playerPets.isEmpty()) {
            sender.sendMessage("§e" + player.getName() + " does not own any pets.");
            return;
        }

        sender.sendMessage("§6" + player.getName() + "'s Pets:");

        for (PlayerPet playerPet : playerPets) {

            Pet pet = petManager.getPet(playerPet.getPetId());

            if (pet == null) {
                sender.sendMessage("§c- Unknown Pet (" + playerPet.getPetId() + ")");
                continue;
            }

            sender.sendMessage("§e- " + playerPet.getDbId() + "  " + pet.getDisplayName()
            );
        }

    }

    @Override
    public List<String> tabComplete(CommandSourceStack source, String[] args) {

        if (args.length == 1) {
            return Bukkit.getOnlinePlayers().stream().map(Player::getName).collect(Collectors.toList());
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
    }
}
