package com.mysticalstudio.mysticalpets;

import com.mysticalstudio.mysticalpets.commands.CommandManager;
import com.mysticalstudio.mysticalpets.database.DatabaseInitializer;
import com.mysticalstudio.mysticalpets.database.DatabaseManager;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.plugin.java.JavaPlugin;

public final class MysticalPets extends JavaPlugin {

    DatabaseManager databaseManager;
    DatabaseInitializer initializer;

    @Override
    public void onEnable() {
        // Plugin startup logic

        fileSetup();

        databaseManager = new DatabaseManager(this);
        databaseManager.connect();

        initializer = new DatabaseInitializer(this, databaseManager);
        initializer.initialize();

        CommandManager commandManager = new CommandManager();

        getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, event -> commandManager.register(event.registrar()));


        System.out.println("I am alive!");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        if (databaseManager != null) {
            databaseManager.disconnect();
        }

        System.out.println("Goodbye world!");
    }

    private void fileSetup() {
        if (!this.getDataFolder().exists()) {
            this.getDataFolder().mkdirs();
        }
    }
}
