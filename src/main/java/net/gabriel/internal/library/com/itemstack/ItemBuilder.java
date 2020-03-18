package net.gabriel.internal.library.com.itemstack;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ItemBuilder {

    ItemStack itemStack;
    ItemMeta itemMeta;

    public ItemBuilder(String displayname, Material material, byte subid, int amount) {
        itemStack = new ItemStack(material, amount, subid);
        itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(displayname);
    }

    public ItemBuilder(String displayname, Material material, byte subid, int amount, Enchantment enchantment) {
        itemStack = new ItemStack(material, amount, subid);
        itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(displayname);
        itemMeta.addEnchant(enchantment, 10, true);
    }

    public ItemBuilder(String displayname, Material material, byte subid, int amount, ArrayList<String> info) {
        itemStack = new ItemStack(material, amount, subid);
        itemMeta = itemStack.getItemMeta();
        itemMeta.setLore(info);
        itemMeta.setDisplayName(displayname);
    }

    public ItemStack build() {
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

}
