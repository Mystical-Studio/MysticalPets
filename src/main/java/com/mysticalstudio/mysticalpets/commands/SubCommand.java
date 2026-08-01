package com.mysticalstudio.mysticalpets.commands;

import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.command.CommandSender;

import java.util.List;

public interface SubCommand {

    String getName();

    void execute(CommandSourceStack source, String[] args);

    default List<String> tabComplete(CommandSourceStack source, String[] args) {
        return List.of();
    }

    default String getPermission() {
        return "";
    }

    default boolean hasPermission(CommandSender sender) {

        String permission = getPermission();

        return permission == null || sender.hasPermission(permission);
    }
}
