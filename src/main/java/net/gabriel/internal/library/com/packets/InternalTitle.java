package net.gabriel.internal.library.com.packets;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class InternalTitle {
    private String title;
    private String subtitle;

    private PacketPlayOutTitle.EnumTitleAction action;

    public InternalTitle(String title, String subtitle) {
        this.title = title;
        this.subtitle = subtitle;
    }

    public Packet[] buildPackets() {
        PacketPlayOutTitle packetPlayOutTitle1 = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + this.title +"\"}"));
        PacketPlayOutTitle packetPlayOutTitle2 = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + this.subtitle +"\"}"));
        return new Packet[] { packetPlayOutTitle1, packetPlayOutTitle2 };
    }

    public void send(Player player) {
        CraftPlayer cp = (CraftPlayer)player;
        cp.getHandle().playerConnection.sendPacket(buildPackets()[0]);
        cp.getHandle().playerConnection.sendPacket(buildPackets()[1]);
    }

}
