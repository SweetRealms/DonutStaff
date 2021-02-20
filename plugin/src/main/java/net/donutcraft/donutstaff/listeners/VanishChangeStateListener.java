package net.donutcraft.donutstaff.listeners;

import net.donutcraft.donutstaff.api.cache.SetCache;
import net.donutcraft.donutstaff.api.event.VanishChangeStateEvent;
import net.donutcraft.donutstaff.api.staffmode.StaffModeManager;
import net.donutcraft.donutstaff.files.FileCreator;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import org.bukkit.inventory.meta.ItemMeta;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.UUID;

public class VanishChangeStateListener implements Listener {

    @Inject private StaffModeManager staffModeManager;
    @Inject @Named("vanish-cache") private SetCache<UUID> vanishCache;
    @Inject @Named("items-file") private FileCreator items;

    @EventHandler
    public void onVanishChangeStateEvent(VanishChangeStateEvent event) {
        Player player = event.getPlayer();

        if (vanishCache.get().contains(player.getUniqueId())) {
            ItemStack vanishDisabled = new ItemStack(Material.SEA_LANTERN);
            ItemMeta vanishMetaDisabled = vanishDisabled.getItemMeta();
            vanishMetaDisabled.setDisplayName(items.getString("items.vanish-off.name"));
            vanishMetaDisabled.setLore(items.getStringList("items.vanish-off.lore"));
            vanishDisabled.setItemMeta(vanishMetaDisabled);

            staffModeManager.disableVanish(player);
            player.getInventory().setItem(2, vanishDisabled);
            return;
        }

        ItemStack vanishEnabled = new ItemStack(Material.GLOWSTONE);
        ItemMeta vanishMetaEnabled = vanishEnabled.getItemMeta();
        vanishMetaEnabled.setDisplayName(items.getString("items.vanish-on.name"));
        vanishMetaEnabled.setLore(items.getStringList("items.vanish-on.lore"));
        vanishEnabled.setItemMeta(vanishMetaEnabled);

        player.getInventory().setItem(2, vanishEnabled);
        staffModeManager.enableVanish(player);
    }
}
