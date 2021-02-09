package net.donutcraft.donutstaff.api.nms;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.server.v1_10_R1.ChatMessage;
import net.minecraft.server.v1_10_R1.IChatBaseComponent;
import net.minecraft.server.v1_10_R1.Packet;
import net.minecraft.server.v1_10_R1.PacketPlayOutTitle;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class NMSHandler_1_10_R1 implements NMSHandler {
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
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
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
