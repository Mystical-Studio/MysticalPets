package com.mysticalstudio.mysticalpets.commands.pets.subcommands;

import com.mysticalstudio.mysticalpets.commands.SubCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

public class GiveCommand implements SubCommand {

    @Override
    public String getName() {
        return "give";
    }

    @Override
    public void execute(CommandSourceStack source, String[] args) {

        if (args.length < 3) {
            source.getSender().sendMessage("Usage /pets give <player> <pet> <amount>");
            return;
        }

        source.getSender().sendMessage("Pet has been added");
    }

    @Override
    public List<String> tabComplete(CommandSourceStack source, String[] args) {

        if (args.length == 1) {
            return Bukkit.getOnlinePlayers().stream().map(Player::getName).collect(Collectors.toList());
        }

        if (args.length == 2) {
            return List.of(
                    "bat",
                    "cow"
            );
        }

        if (args.length == 3) {
            return List.of(
                    "1",
                    "5",
                    "10",
                    "64"
            );
        }

        return List.of();
    }

    @Override
    public String getPermission() {
        return SubCommand.super.getPermission();
    }
}
