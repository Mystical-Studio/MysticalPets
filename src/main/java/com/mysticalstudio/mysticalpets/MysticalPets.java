package com.mysticalstudio.mysticalpets;

import com.mysticalstudio.mysticalpets.database.DatabaseManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class MysticalPets extends JavaPlugin {

    DatabaseManager databaseManager;

    @Override
    public void onEnable() {
        // Plugin startup logic

        fileSetup();

        databaseManager = new DatabaseManager(this);
        databaseManager.setupDatabase();

        System.out.println("I am alive!");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        System.out.println("Goodbye world!");
    }

    private void fileSetup() {
        if (!this.getDataFolder().exists()) {
            this.getDataFolder().mkdirs();
        }
    }
}
