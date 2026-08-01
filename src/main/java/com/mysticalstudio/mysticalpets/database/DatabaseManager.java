package com.mysticalstudio.mysticalpets.database;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    private final JavaPlugin plugin;

    private Connection connection;

    public DatabaseManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void connect() {

        File dbFile = new File(getDatabaseFolder(), "mysticalpets.db");
        String url = "jdbc:sqlite:" + dbFile.getAbsolutePath();

        try {
            connection = DriverManager.getConnection(url);
            plugin.getLogger().info("Connected to SQLite database.");
        } catch (SQLException e) {
            plugin.getLogger().severe("Failed to connect to SQLite database.");
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void disconnect() {

        if (connection == null) {
            return;
        }

        try {
            connection.close();
            plugin.getLogger().info("Disconnected from SQLite database.");
        } catch (SQLException e) {
            plugin.getLogger().severe("Failed to close SQLite connection.");
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