package net.donutcraft.donutstaff.commands;

import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import net.donutcraft.donutstaff.api.staffmode.StaffModeHandler;
import org.bukkit.entity.Player;

import javax.inject.Inject;

@Command(names = {"staffchat", "sc"}, desc = "Enable staff chat.", permission = "donutstaff.staffchat")
public class StaffChatCommand implements CommandClass {

    @Inject private StaffModeHandler staffModeHandler;

    @Command(names = "")
    public boolean onStaffChatCommand(@Sender Player player) {

        staffModeHandler.toggleStaffChat(player);

        return true;
    }
}
