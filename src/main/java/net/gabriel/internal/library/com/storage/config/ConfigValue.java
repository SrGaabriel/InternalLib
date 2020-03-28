package net.gabriel.internal.library.com.storage.config;


import org.bukkit.Location;

import java.util.List;

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

    private void setIfNotSet() {
        if (!isSet()) {
            setValue(defaultValue);
        }
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
        setIfNotSet();
        return config.getConfig().get(path);
    }

    public String getString() {
        setIfNotSet();
        return config.getConfig().getString(path);
    }

    public List<?> getObjectList() {
        setIfNotSet();
        return config.getConfig().getList(path);
    }

    public List<Double> getDoubleList() {
        setIfNotSet();
        return config.getConfig().getDoubleList(path);
    }

    public List<Integer> getIntegerList() {
        setIfNotSet();
        return config.getConfig().getIntegerList(path);
    }

    public List<Long> getLongList() {
        setIfNotSet();
        return config.getConfig().getLongList(path);
    }

    public List<Boolean> getBooleanList() {
        setIfNotSet();
        return config.getConfig().getBooleanList(path);
    }

    public List<Byte> getByteList() {
        setIfNotSet();
        return config.getConfig().getByteList(path);
    }

    public List<Short> getShortList() {
        setIfNotSet();
        return config.getConfig().getShortList(path);
    }

    public List<Float> getFloatList() {
        setIfNotSet();
        return config.getConfig().getFloatList(path);
    }


    public Double getDouble() {
        setIfNotSet();
        return config.getConfig().getDouble(path);
    }

    public Long getLong() {
        setIfNotSet();
        return config.getConfig().getLong(path);
    }

    public boolean getBoolean() {
        setIfNotSet();
        return config.getConfig().getBoolean(path);
    }

    public int getInteger() {
        setIfNotSet();
        return config.getConfig().getInt(path);
    }

    public Location getLocation() {
        setIfNotSet();
        return config.getLocation(path);
    }

    public List<String> getStringList() {
        setIfNotSet();
        return config.getConfig().getStringList(path);
    }

}
