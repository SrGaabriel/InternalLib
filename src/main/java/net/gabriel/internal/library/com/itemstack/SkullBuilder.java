package net.gabriel.internal.library.com.itemstack;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;

public class SkullBuilder {

    ItemStack itemStack;
    SkullMeta itemMeta;

    public SkullBuilder(String displayname, String owner, int amount) {
        itemStack = new ItemStack(Material.SKULL_ITEM, amount, (short) 3);
        itemMeta = (SkullMeta) itemStack.getItemMeta();
        itemMeta.setOwner(owner);
        itemMeta.setDisplayName(displayname);
    }

    public SkullBuilder(String displayname, String owner, int amount, Enchantment enchantment) {
        itemStack = new ItemStack(Material.SKULL_ITEM, amount, (short) 3);
        itemMeta = (SkullMeta) itemStack.getItemMeta();
        itemMeta.setOwner(owner);
        itemMeta.setDisplayName(displayname);
        itemMeta.addEnchant(enchantment, 10, true);
    }

    public SkullBuilder(String displayname, String owner, int amount, ArrayList<String> lore) {
        itemStack = new ItemStack(Material.SKULL_ITEM, amount, (short) 3);
        itemMeta = (SkullMeta) itemStack.getItemMeta();
        itemMeta.setOwner(owner);
        itemMeta.setLore(lore);
        itemMeta.setDisplayName(displayname);
    }


    public ItemStack build() {
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

}
