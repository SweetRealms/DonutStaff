package net.donutcraft.donutstaff.api.nms;

import net.minecraft.server.v1_8_R3.*;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class NMSHandler_1_8_R3 implements NMSHandler {
    @Override
    public void sendTitle(Player player, String title, String subTitle, int fadeIn, int fadeShow, int fadedOut) {
        IChatBaseComponent titleText = new ChatMessage(title);
        IChatBaseComponent subtitleText = new ChatMessage(subTitle);
        PacketPlayOutTitle titlePacket = new PacketPlayOutTitle(
                PacketPlayOutTitle.EnumTitleAction.TITLE, titleText,fadeIn,fadeShow,fadedOut);

        PacketPlayOutTitle subtitlePacket = new PacketPlayOutTitle(
                PacketPlayOutTitle.EnumTitleAction.SUBTITLE, subtitleText,fadeIn,fadeShow,fadedOut);

        sendPacket(player,titlePacket);
        sendPacket(player,subtitlePacket);
    }

    @Override
    public void sendActionBar(Player player, String message) {
        IChatBaseComponent icbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + message + "\"}");
        PacketPlayOutChat packetPlayOutChat = new PacketPlayOutChat(icbc, (byte) 2);
        sendPacket(player, packetPlayOutChat);
    }

    @Override
    public void sendPacket(Player player, Object object) {
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket((Packet) object);
    }

    @Override
    public void hidePlayer(Player user, Player staff) {
        user.hidePlayer(staff);
    }

    @Override
    public void showPlayer(Player user, Player staff) {
        user.showPlayer(staff);
    }
}
