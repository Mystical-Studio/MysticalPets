package com.mysticalstudio.mysticalpets.commands.server.subcommands;

import com.mysticalstudio.mysticalpets.commands.SubCommand;
import com.mysticalstudio.mysticalpets.managers.PetManager;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.command.CommandSender;

import java.util.List;

public class ReloadPetsCommand implements SubCommand {

    private final PetManager petManager;

    public ReloadPetsCommand(PetManager petManager) {
        this.petManager = petManager;
    }

    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public void execute(CommandSourceStack source, String[] args) {

        CommandSender sender = source.getSender();

        int amountReloaded = petManager.reload();

        sender.sendMessage(String.valueOf(amountReloaded));

    }

    @Override
    public List<String> tabComplete(CommandSourceStack source, String[] args) {
        return SubCommand.super.tabComplete(source, args);
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
