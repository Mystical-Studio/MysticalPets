package com.mysticalstudio.mysticalpets.database;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    private final JavaPlugin plugin;

    public DatabaseManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }


    public void setupDatabase() {

        File dbFile = new File(getDatabaseFolder(), "playerpets.db");
        String url = "jdbc:sqlite:" + dbFile.getAbsolutePath();

        try (var conn = DriverManager.getConnection(url)) {
            System.out.println("Connected to database!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private File getDatabaseFolder() {
        File databaseFolder = new File(plugin.getDataFolder(), "database");

        if (!databaseFolder.exists()) {
            databaseFolder.mkdirs();
        }

        return databaseFolder;
    }
}
