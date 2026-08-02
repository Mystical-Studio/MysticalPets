package com.mysticalstudio.mysticalpets.database.repositories;

import com.mysticalstudio.mysticalpets.database.DatabaseManager;
import com.mysticalstudio.mysticalpets.models.Pet;
import com.mysticalstudio.mysticalpets.models.PlayerPet;

import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerPetRepository {

    private final DatabaseManager databaseManager;

    public PlayerPetRepository(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public void addPet(UUID playerUuid, String petId) {

        String sql = """
        INSERT INTO player_pets (
            owner_uuid,
            pet_id
        )
        VALUES (?, ?)
        """;

        try (PreparedStatement statement =
                     databaseManager.getConnection().prepareStatement(sql)) {

            statement.setString(1, playerUuid.toString());
            statement.setString(2, petId);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removePet(UUID playerUuid, String petDbId) {

        String sql = """
                DELETE FROM player_pets
                WHERE uuid = ? AND id = ?
                """;

        try (PreparedStatement statement =
                     databaseManager.getConnection().prepareStatement(sql)) {

            statement.setString(1, playerUuid.toString());
            statement.setString(2, petDbId);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<PlayerPet> getPlayerPets(UUID playerUuid) {

        List<PlayerPet> pets = new ArrayList<>();

        String sql = """
            SELECT id, owner_uuid, pet_id
            FROM player_pets
            WHERE owner_uuid = ?
            """;

        try (PreparedStatement statement = databaseManager.getConnection().prepareStatement(sql)) {

            statement.setString(1, playerUuid.toString());

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                pets.add(new PlayerPet(resultSet.getInt("id"), UUID.fromString(resultSet.getString("owner_uuid")), resultSet.getString("pet_id")));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pets;
    }
}
