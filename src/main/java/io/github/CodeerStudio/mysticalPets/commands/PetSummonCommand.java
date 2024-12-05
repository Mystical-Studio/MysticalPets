package io.github.CodeerStudio.mysticalPets.commands;

import io.github.CodeerStudio.mysticalPets.managers.PetManager;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Handles the "summon" subcommand for pets.
 * Allows players to summon a specific pet by name, which appears near them in the game world.
 */
public class PetSummonCommand implements PetSubCommand{

    private final PetManager petManager;


    /**
     * Constructs a PetSummonCommand with the specified PetManager.
     *
     * @param petManager the PetManager instance used to manage pets
     */
    public PetSummonCommand(PetManager petManager) {
        this.petManager = petManager;
    }


    /**
     * Executes the "summon" subcommand.
     * Summons a pet for the player by name if they own the pet, and it can be successfully spawned.
     *
     * @param commandSender the sender of the command, must be a player
     * @param args the command arguments; expects the pet name as the first argument
     * @return true to indicate the command has been handled
     */
    @Override
    public boolean execute(CommandSender commandSender, String[] args) {
        // Check if the command sender is a player
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ChatColor.RED + "Only players can use this command.");
            return true;
        }

        Player player = (Player) commandSender;

        // Ensure a pet name is provided
        if (args.length < 1) {
            player.sendMessage(ChatColor.RED + "Usage: /pet summon <petName>");
            return true;
        }

        String petName = args[0];
        String response = petManager.summonPet(player, petName);

        player.sendMessage(response);

        return true;
    }
}
