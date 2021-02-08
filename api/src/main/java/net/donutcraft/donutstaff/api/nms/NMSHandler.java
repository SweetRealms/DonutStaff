package net.donutcraft.donutstaff.api.nms;

import org.bukkit.entity.Player;

public interface NMSHandler {
    void sendTitle(Player player, String title, String subTitle, int fadeIn, int fadeShow, int fadedOut);

    void sendActionBar(Player player, String message);

    void sendPacket(Player player, Object object);
}
