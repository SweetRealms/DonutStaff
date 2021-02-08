package net.donucraft.commands;

import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@Command(names = {"clearchat", "chatclear", "cc"}, desc = "Clear the chat", permission = "donucraft.clearchat")
public class ClearChatCommand implements CommandClass {

    @Command(names = "")
    public boolean onClearChatCommand(@Sender Player player) {

        for (int i = 0; i < 100; ++i) {
            Bukkit.broadcastMessage("");
        }

        return true;
    }
}
