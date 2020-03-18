package net.gabriel.internal.library.com;

import net.gabriel.internal.library.com.commands.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class InternalLib extends JavaPlugin {

    public void onEnable() {
        getCommand("internal-lib").setExecutor(new PluginCommand());
        print("§9[InternalLib] §aThe plugin has been successfully enabled!");
    }

    public void onDisable() {
        print("§9[InternalLib] §aThe plugin has been successfully disabled!");
    }

    public static InternalLib getInstance() {
        return InternalLib.getPlugin(InternalLib.class);
    }

    public void refreshConfig() {
        // TODO I'm currently working on this.
    }

    private void print(String s){
        getInstance().getLogger().info(s);
    }

}
