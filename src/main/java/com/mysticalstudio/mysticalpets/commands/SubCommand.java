package com.mysticalstudio.mysticalpets.commands;

import io.papermc.paper.command.brigadier.CommandSourceStack;

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
}
