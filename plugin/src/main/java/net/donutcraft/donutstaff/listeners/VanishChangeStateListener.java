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

import org.bukkit.inventory.meta.ItemMeta;
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
            ItemStack vanish_disabled = new ItemStack(Material.SEA_LANTERN);
            ItemMeta vanish_meta_disabled = vanish_disabled.getItemMeta();
            vanish_meta_disabled.setDisplayName(items.getString("items.vanish-off.name"));
            vanish_meta_disabled.setLore(items.getStringList("items.vanish-off.lore"));
            vanish_disabled.setItemMeta(vanish_meta_disabled);

            staffModeManager.disableVanish(player);
            player.getInventory().setItem(2, vanish_disabled);
            return;
        }

        ItemStack vanish_enabled = new ItemStack(Material.GLOWSTONE);
        ItemMeta vanish_meta_enabled = vanish_enabled.getItemMeta();
        vanish_meta_enabled.setDisplayName(items.getString("items.vanish-on.name"));
        vanish_meta_enabled.setLore(items.getStringList("items.vanish-on.lore"));
        vanish_enabled.setItemMeta(vanish_meta_enabled);

        player.getInventory().setItem(2, vanish_enabled);
        staffModeManager.enableVanish(player);
    }
}
