package com.mysticalstudio.mysticalpets.database.repositories;

import com.mysticalstudio.mysticalpets.database.DatabaseManager;
import com.mysticalstudio.mysticalpets.models.Pet;

import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerPetRepository {

    private final DatabaseManager databaseManager;

    public PlayerPetRepository(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }
}
