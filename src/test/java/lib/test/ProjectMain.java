package lib.test;

import net.gabriel.internal.library.com.storage.mysql.InternalSQL;
import org.bukkit.plugin.java.JavaPlugin;

public class ProjectMain extends JavaPlugin {

    private static InternalSQL sql;

    public void onEnable() {
        sql = new InternalSQL("localhost", "3306", "core-db", "root", "");
        sql.connect();
    }

    public void onDisable() {
        sql.disconnect();
    }

}
