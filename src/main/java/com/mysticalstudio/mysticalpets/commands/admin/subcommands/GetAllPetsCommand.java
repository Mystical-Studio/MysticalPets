package com.mysticalstudio.mysticalpets.commands.admin.subcommands;

import com.mysticalstudio.mysticalpets.commands.SubCommand;
import com.mysticalstudio.mysticalpets.managers.PetManager;
import com.mysticalstudio.mysticalpets.models.Pet;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.command.CommandSender;

import java.util.List;

public class GetAllPetsCommand implements SubCommand {

    private final PetManager petManager;

    public GetAllPetsCommand(PetManager petManager) {
        this.petManager = petManager;
    }

    @Override
    public String getName() {
        return "get";
    }

    @Override
    public void execute(CommandSourceStack source, String[] args) {

        if (args.length < 1) {
            source.getSender().sendMessage("Usage /petsadmin get all");
            return;
        }

        source.getSender().sendMessage("§6Available Pets:");

        for (Pet pet : petManager.getPets()) {
            source.getSender().sendMessage(
                    "§e- " + pet.getId()
            );
        }

    }

    @Override
    public List<String> tabComplete(CommandSourceStack source, String[] args) {
        return List.of("all");
    }

    @Override
    public String getPermission() {
        return SubCommand.super.getPermission();
    }

    @Override
    public boolean hasPermission(CommandSender sender) {
        return SubCommand.super.hasPermission(sender);
    }
}
