package com.mysticalstudio.mysticalpets.models;

public class Pet {

    private final String id;
    private final String displayName;
    private final String rarity;

    public Pet(String id, String displayName, String rarity) {
        this.id = id;
        this.displayName = displayName;
        this.rarity = rarity;
    }

    public String getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getRarity() {
        return rarity;
    }
}
