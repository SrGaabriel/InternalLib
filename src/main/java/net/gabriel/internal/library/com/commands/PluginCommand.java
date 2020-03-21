package net.gabriel.internal.library.com.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class PluginCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (args.length != 1 || !args[0].equalsIgnoreCase("refresh")) {
            s.sendMessage("§9§lINTERNAL §fThe plugin is currently on, to refresh the config, use /internal-lib refresh");
            return false;
        }
        switch (args[0]) {
            case "refresh":
                s.sendMessage("§a§lSUCCESS §fThe plugin was refreshed sucessefuly!");
                return false;
        }
        s.sendMessage("§9§lINTERNAL §fThe plugin is currently on, to refresh the config, use /internal-lib refresh!");
        return false;
    }
}
