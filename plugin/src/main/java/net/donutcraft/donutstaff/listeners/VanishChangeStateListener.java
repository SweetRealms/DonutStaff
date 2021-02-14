package net.donutcraft.donutstaff.listeners;

import net.donutcraft.donutstaff.api.cache.Cache;
import net.donutcraft.donutstaff.api.event.VanishChangeStateEvent;
import net.donutcraft.donutstaff.api.staffmode.StaffModeManager;
import net.donutcraft.donutstaff.files.FileCreator;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import team.unnamed.gui.core.item.type.ItemBuilder;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.UUID;

public class VanishChangeStateListener implements Listener {

    @Inject private StaffModeManager staffModeManager;
    @Inject @Named("vanish-cache") private Cache<UUID> vanishCache;
    @Inject @Named("items-file") private FileCreator items;

    @EventHandler
    public void onVanishChangeStateEvent(VanishChangeStateEvent event) {
        Player player = event.getPlayer();

        if (vanishCache.exists(player.getUniqueId())) {
            ItemStack vanish_disabled = ItemBuilder.newBuilder(Material.RED_ROSE, 1)
                    .setName(items.getString("items.vanish-off.name"))
                    .setLore(items.getStringList("items.vanish-off.lore"))
                    .build();

            staffModeManager.disableVanish(player);
            player.getInventory().setItem(2, vanish_disabled);
            return;
        }

        ItemStack vanish_enabled = ItemBuilder.newBuilder(Material.YELLOW_FLOWER, 1)
                .setName(items.getString("items.vanish-on.name"))
                .setLore(items.getStringList("items.vanish-on.lore"))
                .build();

        player.getInventory().setItem(2, vanish_enabled);
        staffModeManager.enableVanish(player);
    }
}
