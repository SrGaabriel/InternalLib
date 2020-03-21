package lib.test;

import net.gabriel.internal.library.com.itemstack.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ItemTester {

    public void generateApple(Location location) {
        ItemStack apple = new ItemBuilder(
                "§cI'm a Apple!", Material.APPLE, (byte) 0, 1).build();

        location.getWorld().dropItem(location, apple);
    }

}
