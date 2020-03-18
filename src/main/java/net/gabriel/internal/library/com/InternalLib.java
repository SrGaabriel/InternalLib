package net.gabriel.internal.library.com;

import org.bukkit.plugin.java.JavaPlugin;

public class InternalLib extends JavaPlugin {

    public void onEnable() {
        print("§9[InternalLib] §aThe plugin has been successfully enabled!");
    }

    public void onDisable() {
        print("§9[InternalLib] §aThe plugin has been successfully disabled!");
    }

    public static InternalLib getInstance() {
        return InternalLib.getPlugin(InternalLib.class);
    }

    private void print(String s){
        getInstance().getLogger().info(s);
    }

}
