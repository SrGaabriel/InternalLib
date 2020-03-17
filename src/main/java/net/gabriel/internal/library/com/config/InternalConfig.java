package net.gabriel.internal.library.com.config;

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

    public InternalConfig(String path, String fileName, Runnable callback, Plugin plugin) {
        if (!fileName.contains(".yml"))
            fileName = String.valueOf(fileName) + ".yml";
        this.file = new File(path, fileName);
        this.fileConfig = (FileConfiguration) YamlConfiguration.loadConfiguration(this.file);
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
            fileName = String.valueOf(fileName) + ".yml";
        this.file = new File(path, fileName);
        this.fileConfig = (FileConfiguration)YamlConfiguration.loadConfiguration(this.file);
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
        this.fileConfig.set(String.valueOf(path) + ".World", location.getWorld().getName());
        this.fileConfig.set(String.valueOf(path) + ".X", Double.valueOf(location.getX()));
        this.fileConfig.set(String.valueOf(path) + ".Y", Double.valueOf(location.getY()));
        this.fileConfig.set(String.valueOf(path) + ".Z", Double.valueOf(location.getZ()));
        this.fileConfig.set(String.valueOf(path) + ".Pitch", Float.valueOf(location.getPitch()));
        this.fileConfig.set(String.valueOf(path) + ".Yaw", Float.valueOf(location.getYaw()));
        saveConfig();
    }

    public Location getLocation(String path) {
        if (this.fileConfig.getString(String.valueOf(path) + ".World") == null)
            return null;
        Location location = new Location(Bukkit.getWorld(this.fileConfig.getString(String.valueOf(path) + ".World")), this.fileConfig.getDouble(String.valueOf(path) + ".X"), this.fileConfig.getDouble(String.valueOf(path) + ".Y"), this.fileConfig.getDouble(String.valueOf(path) + ".Z"), (float)this.fileConfig.getDouble(String.valueOf(path) + ".Yaw"), (float)this.fileConfig.getDouble(String.valueOf(path) + ".Pitch"));
        return location;
    }

}

class ConfigValue {

    private InternalConfig config;
    private String path;
    private Object defaultValue;

    public ConfigValue(InternalConfig config, String path, Object defaultValue) {
        this.config = config;
        this.path = path;
        this.defaultValue = defaultValue;
        mkdirs();
    }

    public void setValue(Object newValue) {
        if (!(newValue instanceof Location)) {
            config.getConfig().set(this.path, newValue);
        } else {
            config.setLocation(this.path, (Location) newValue);
        }
        config.saveConfig();
    }

    public boolean isSet() {
        return config.getConfig().contains(path);
    }

    private void mkdirs() {
        if (!(config.getConfig().contains(path))) {
            setValue(this.defaultValue);
        }
    }


    public Object get() {
        if (!(isSet())) {
            setValue(defaultValue);
        }
        return config.getConfig().get(path);
    }

    public String getString() {
        if (!(isSet())) {
            setValue(defaultValue);
        }
        return config.getConfig().getString(path).replace("&", "§");
    }

    public boolean getBoolean() {
        if (!(isSet())) {
            setValue(defaultValue);
        }
        return config.getConfig().getBoolean(path);
    }

    public int getInteger() {
        if (!(isSet())) {
            setValue(defaultValue);
        }
        return config.getConfig().getInt(path);
    }

    public Location getLocation() {
        if (!(isSet())) {
            setValue(defaultValue);
        }
        return config.getLocation(path);
    }

    public List<String> getStringList() {
        if (!(isSet())) {
            setValue(defaultValue);
        }
        List<String> returnValue = config.getConfig().getStringList(path);
        for (String i : returnValue) {
            i.replace("&", "§");
        }
        return returnValue;
    }

}
