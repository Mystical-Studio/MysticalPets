package com.mysticalstudio.mysticalpets.database;

import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {

    private final DatabaseManager databaseManager;
    private final JavaPlugin plugin;

    public DatabaseInitializer(JavaPlugin plugin, DatabaseManager databaseManager) {
        this.plugin = plugin;
        this.databaseManager = databaseManager;
    }

    public void initialize() {

        try (Statement statement = databaseManager.getConnection().createStatement()) {

            statement.execute("""
                CREATE TABLE IF NOT EXISTS player_pets (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    owner_uuid NOT NULL,
                    pet_id INTEGER NOT NULL
                )
            """);

            plugin.getLogger().info("Database tables initialized.");

        } catch (SQLException e) {
            plugin.getLogger().severe("Failed to initialize database tables.");
            e.printStackTrace();
        }
    }
}