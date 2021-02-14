package net.donutcraft.donutstaff.listeners.vanilla;

import net.donutcraft.donutstaff.api.cache.Cache;
import net.donutcraft.donutstaff.api.event.KnockbackItemEvent;
import net.donutcraft.donutstaff.api.event.RandomTpEvent;
import net.donutcraft.donutstaff.api.event.VanishChangeStateEvent;
import net.donutcraft.donutstaff.api.staffmode.StaffModeManager;
import net.donutcraft.donutstaff.files.FileCreator;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.UUID;

public class PlayerInteractListener implements Listener {

    @Inject @Named("freeze-cache") private Cache<UUID> freezeCache;
    @Inject private StaffModeManager staffModeManager;
    @Inject @Named("items-file") private FileCreator items;

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (freezeCache.exists(player.getUniqueId())) {
            event.setCancelled(true);
            return;
        }

        if (!staffModeManager.isOnStaffMode(player)) {
            return;
        }

        ItemStack itemStack = player.getInventory().getItemInHand();
        if (!itemStack.hasItemMeta()) {
            return;
        }
        String itemDisplayName = itemStack.getItemMeta().getDisplayName();

        if (itemDisplayName.equals(items.getString("items.random-tp.name"))) {
            Bukkit.getPluginManager().callEvent(new RandomTpEvent(player));
            event.setCancelled(true);
            return;
        }
        if (itemDisplayName.equals(items.getString("items.knock-back.name"))) {
            Bukkit.getPluginManager().callEvent(new KnockbackItemEvent(player));
            return;
        }
        if (itemDisplayName.equals(items.getString("items.vanish-on.name")) ||
                itemDisplayName.equals(items.getString("items.vanish-off.name"))) {
            Bukkit.getPluginManager().callEvent(new VanishChangeStateEvent(player));
            event.setCancelled(true);
        }

    }
}
