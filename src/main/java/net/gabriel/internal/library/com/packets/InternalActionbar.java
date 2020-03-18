package net.gabriel.internal.library.com.packets;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class InternalActionbar {
    private String title;

    public InternalActionbar(String title) {
        this.title = title;
    }

    public Packet buildPacket() {
        return new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + title + "\"}"), (byte) 2);
    }

    public void send(Player player) {
        CraftPlayer cp = (CraftPlayer)player;
        cp.getHandle().playerConnection.sendPacket(buildPacket());
    }

}
