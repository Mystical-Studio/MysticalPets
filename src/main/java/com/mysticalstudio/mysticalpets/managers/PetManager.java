package com.mysticalstudio.mysticalpets.managers;

import com.mysticalstudio.mysticalpets.database.DatabaseManager;
import com.mysticalstudio.mysticalpets.database.repositories.PlayerPetRepository;
import com.mysticalstudio.mysticalpets.models.Pet;
import com.mysticalstudio.mysticalpets.models.PlayerPet;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.*;

public class PetManager {

    private final JavaPlugin plugin;
    private final DatabaseManager databaseManager;
    private final File petsFolder;
    private PlayerPetRepository playerPetRepository;

    private final Map<String, Pet> pets = new HashMap<>();

    public PetManager(JavaPlugin plugin, DatabaseManager databaseManager) {
        this.plugin = plugin;
        this.databaseManager = databaseManager;
        this.petsFolder = new File(plugin.getDataFolder(), "pets");
        playerPetRepository = new PlayerPetRepository(databaseManager);
    }

    public void load() {

        createFolder();

        saveDefaultPets();

        loadPets();

        plugin.getLogger().info("Loaded " + pets.size() + " pets.");
    }

    public int reload() {

        pets.clear();

        loadPets();

        return pets.size();
    }

    public Pet getPet(String id) {
        return pets.get(id.toLowerCase());
    }

    public Collection<Pet> getPets() {
        return pets.values();
    }

    private void createFolder() {

        if (!petsFolder.exists()) {
            petsFolder.mkdirs();
        }
    }

    private void saveDefaultPets() {

        File dragon = new File(petsFolder, "dragon.yml");

        if (!dragon.exists()) {
            plugin.saveResource("pets/dragon.yml", false);
        }
    }

    private void loadPets() {

        File[] files = petsFolder.listFiles();

        if (files == null) {
            return;
        }

        for (File file : files) {

            if (!file.getName().endsWith(".yml")) {
                continue;
            }

            loadPet(file);
        }
    }

    private void loadPet(File file) {

        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

        String id = config.getString("id");
        String displayName = config.getString("display-name");
        String rarity = config.getString("rarity");

        if (id == null) {
            plugin.getLogger().warning(file.getName() + " is missing an id!");
            return;
        }

        Pet pet = new Pet(id, displayName, rarity);

        pets.put(id.toLowerCase(), pet);
    }

    public void givePet(UUID playerUuid, String petId) {

        Pet pet = getPet(petId);

        if (pet == null) {
            throw new IllegalArgumentException("Pet does not exist.");
        }

        playerPetRepository.addPet(playerUuid, petId);
    }

    public void removePet(UUID playerUuid, String petId) {

        Pet pet = getPet(petId);

        if (pet == null) {
            throw new IllegalArgumentException("Pet does not exist.");
        }

        playerPetRepository.removePet(playerUuid, petId);
    }

    public List<PlayerPet> getPlayerPets(UUID playerUuid) {

        return playerPetRepository.getPlayerPets(playerUuid);

    }

}