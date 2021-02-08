package net.donucraft.loader;

import net.donucraft.DonutStaff;
import net.donucraft.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import javax.inject.Inject;

public class ListenersLoader implements Loader {

    @Inject private DonutStaff donutStaff;
    @Inject private PlayerChatListener playerChatListener;
    @Inject private PlayerMoveListener playerMoveListener;
    @Inject private PlayerJoinListener playerJoinListener;
    @Inject private InventoryClickListener inventoryClickListener;
    @Inject private InventoryOpenListener inventoryOpenListener;
    @Inject private PlayerItemDropListener playerItemDropListener;
    @Inject private PlayerPickupItemListener playerPickupItemListener;
    @Inject private PlayerDamageListener playerDamageListener;

    @Override
    public void load() {
        registerListeners(
            playerChatListener,
            playerMoveListener,
            playerJoinListener,
            inventoryOpenListener,
            inventoryClickListener,
            playerItemDropListener,
            playerPickupItemListener,
            playerDamageListener
        );
    }

    private void registerListeners(Listener... listeners) {
        for (Listener listener : listeners) {
            Bukkit.getServer().getPluginManager().registerEvents(listener, donutStaff);
        }
    }
}
