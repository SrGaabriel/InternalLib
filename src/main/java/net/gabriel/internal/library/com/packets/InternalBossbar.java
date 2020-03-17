package net.gabriel.internal.library.com.packets;

import net.minecraft.server.v1_8_R3.EntityEnderDragon;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityLiving;
import net.minecraft.server.v1_8_R3.WorldServer;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class InternalBossbar {

    private String text;

    public InternalBossbar(String text) {
        this.text = text;
    }

    public void showToPlayer(Player player) {
        WorldServer world = ((CraftWorld) player.getLocation().getWorld()).getHandle();
        EntityEnderDragon dragon = new EntityEnderDragon(world);
        dragon.setLocation(player.getLocation().getX(), player.getLocation().getY() - 30, player.getLocation().getZ(), 0, 0);
        dragon.setCustomName(text);
        dragon.setCustomNameVisible(true);
        dragon.setInvisible(true);

        Packet<?> packet = new PacketPlayOutSpawnEntityLiving(dragon);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }

}
