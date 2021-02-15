package net.donutcraft.donutstaff.flow;

import me.fixeddev.commandflow.Authorizer;
import me.fixeddev.commandflow.Namespace;
import me.fixeddev.commandflow.bukkit.BukkitCommandManager;
import org.bukkit.command.CommandSender;

public class CustomAuthorizer implements Authorizer {
    @Override
    public boolean isAuthorized(Namespace namespace, String permission) {
        if (permission.isEmpty()) {
            return true;
        }
        CommandSender sender = namespace.getObject(CommandSender.class, BukkitCommandManager.SENDER_NAMESPACE);

        return sender.hasPermission(permission) || sender.hasPermission("donutstaff.*");
    }
}
