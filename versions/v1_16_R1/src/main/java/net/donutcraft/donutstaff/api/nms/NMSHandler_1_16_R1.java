package net.donutcraft.donutstaff.api.nms;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class NMSHandler_1_16_R1 implements NMSHandler {

    private final Plugin plugin;

    public NMSHandler_1_16_R1(Plugin plugin) {
        this.plugin = plugin;
    }


    @Override
    public void sendTitle(Player player, String title, String subTitle, int fadeIn, int fadeShow, int fadedOut) {
        player.sendTitle(title, subTitle, fadeIn, fadeShow, fadedOut);
    }

    @Override
    public void sendActionBar(Player player, String message) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
    }

    @Override
    public void sendPacket(Player player, Object object) {}

    @Override
    public void hidePlayer(Player user, Player staff) {
        user.hidePlayer(plugin, staff);
    }

    @Override
    public void showPlayer(Player user, Player staff) {
        user.showPlayer(plugin, staff);
    }
}
