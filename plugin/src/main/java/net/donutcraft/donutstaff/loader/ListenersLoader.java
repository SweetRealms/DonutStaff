package net.donutcraft.donutstaff.loader;

import net.donutcraft.donutstaff.DonutStaff;
import net.donutcraft.donutstaff.listeners.KnockbackItemListener;
import net.donutcraft.donutstaff.listeners.RandomTpListener;
import net.donutcraft.donutstaff.listeners.VanishChangeStateListener;
import net.donutcraft.donutstaff.listeners.vanilla.*;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

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
    @Inject private BlockBreakListener blockBreakListener;
    @Inject private BlockPlaceListener blockPlaceListener;
    @Inject private KnockbackItemListener knockbackItemListener;
    @Inject private RandomTpListener randomTpListener;
    @Inject private VanishChangeStateListener vanishChangeStateListener;

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
            playerDamageListener,
            blockBreakListener,
            blockPlaceListener,
            knockbackItemListener,
            randomTpListener,
            vanishChangeStateListener
        );
    }

    private void registerListeners(Listener... listeners) {
        for (Listener listener : listeners) {
            Bukkit.getServer().getPluginManager().registerEvents(listener, donutStaff);
        }
    }
}
