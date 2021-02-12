package net.donutcraft.donutstaff.api.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class VanishChangeStateEvent extends Event {

    private static final HandlerList HANDLER_LIST = new HandlerList();
    private final Player player;

    public VanishChangeStateEvent(Player player) {
        this.player = player;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    public Player getPlayer() {
        return player;
    }
}
