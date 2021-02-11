package net.donutcraft.donutstaff.api.staffmode;

import org.bukkit.entity.Player;

public interface StaffModeHandler {

    void freezePlayer(Player target);

    void unFreezePlayer(Player target);

    boolean isPlayerFrozen(Player target);

    void toggleStaffChat(Player player);

    boolean isPlayerInStaffChat(Player player);

    void fakeLeave(Player player);

    void saveDeathPlayerInventory(Player player);

    void randomTp(Player player);

}