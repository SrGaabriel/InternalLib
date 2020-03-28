package net.gabriel.internal.library.com.storage.config;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class InternalConfig {

    private File file;

    private FileConfiguration fileConfig;
    private List<ConfigValue> configValues;

    public InternalConfig(String path, String fileName, Runnable callback, Plugin plugin) {
        if (!fileName.contains(".yml"))
            fileName = fileName + ".yml";
        this.file = new File(path, fileName);
        this.fileConfig = YamlConfiguration.loadConfiguration(this.file);
        if (!this.file.exists()) {
            this.fileConfig.options().copyDefaults(true);
            callback.run();
            try {
                this.fileConfig.save(this.file);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    public InternalConfig(String path, String fileName, Plugin plugin) {
        if (!fileName.contains(".yml"))
            fileName = fileName + ".yml";
        this.file = new File(path, fileName);
        this.fileConfig = YamlConfiguration.loadConfiguration(this.file);
        if (!this.file.exists()) {
            this.fileConfig.options().copyDefaults(true);
            try {
                this.fileConfig.save(this.file);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    public FileConfiguration getConfig() {
        return this.fileConfig;
    }

    public void saveConfig() {
        try {
            this.fileConfig.save(this.file);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void setLocation(String path, Location location) {
        this.fileConfig.set(path + ".World", location.getWorld().getName());
        this.fileConfig.set(path + ".X", location.getX());
        this.fileConfig.set(path + ".Y", location.getY());
        this.fileConfig.set(path + ".Z", location.getZ());
        this.fileConfig.set(path + ".Pitch", location.getPitch());
        this.fileConfig.set(path + ".Yaw", location.getYaw());
        saveConfig();
    }

    public Location getLocation(String path) {
        if (this.fileConfig.getString(path + ".World") == null)
            return null;
        return new Location(Bukkit.getWorld(this.fileConfig.getString(path + ".World")), this.fileConfig.getDouble(path + ".X"), this.fileConfig.getDouble(path + ".Y"), this.fileConfig.getDouble(path + ".Z"), (float)this.fileConfig.getDouble(path + ".Yaw"), (float)this.fileConfig.getDouble(path + ".Pitch"));

    }

    public void registerValue(ConfigValue arg0) {
        configValues.add(arg0);
    }

    public List<ConfigValue> getValues() {
        return configValues;
    }

}