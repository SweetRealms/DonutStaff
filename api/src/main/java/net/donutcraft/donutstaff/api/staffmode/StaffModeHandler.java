package net.donutcraft.donutstaff.api.staffmode;

import org.bukkit.entity.Player;

public interface StaffModeHandler {

    void freezePlayer(Player target);

    boolean isPlayerFrozen(Player target);

    void enableOrDisableStaffChat(Player player);

    boolean isPlayerInStaffChat(Player player);

    void fakeLeave(Player player);

    void randomTp(Player player);

}