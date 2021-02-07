package net.donutcraft.donutstaff.api.staffmode;

import org.bukkit.entity.Player;

public interface StaffModeManager {

    void enableStaffMode(Player player);

    void disableStaffMode(Player player);

    void toggleVanish(Player player);

    void savePlayerItems(Player player);

    void giveStaffItemsToPlayer(Player player);

    void givePlayerItems(Player player);

    boolean isOnStaffMode(Player player);

}