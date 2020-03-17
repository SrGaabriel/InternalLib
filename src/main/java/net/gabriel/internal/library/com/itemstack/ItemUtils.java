package net.gabriel.internal.library.com.itemstack;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemUtils {

    public ItemUtils() {}

    public void addGlow(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        meta.addEnchant(new Glow(0), 1, true);
        item.setItemMeta(meta);
    }

    public void removeGlow(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        meta.removeEnchant(new Glow(0));
        item.setItemMeta(meta);
    }

}

class Glow extends Enchantment {

    public Glow(int id) {
        super(id);
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.ALL;
    }

    @Override
    public boolean conflictsWith(Enchantment other) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        return true;
    }
}
