package com.mysticalstudio.mysticalpets.models;

import java.util.UUID;

public class PlayerPet {

    private final int Id;
    private final UUID playerUuid;
    private final String petId;

    public PlayerPet(int Id, UUID playerUuid, String petId) {
        this.Id = Id;
        this.playerUuid = playerUuid;
        this.petId = petId;
    }

    public UUID getPlayerUuid() {
        return playerUuid;
    }

    public String getPetId() {
        return petId;
    }

    public int getDbId() {
        return Id;
    }
}
