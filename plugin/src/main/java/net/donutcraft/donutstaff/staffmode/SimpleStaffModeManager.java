package net.donutcraft.donutstaff.staffmode;

import net.donutcraft.donutstaff.files.FileCreator;
import net.donutcraft.donutstaff.util.nms.NMSManager;
import net.donutcraft.donutstaff.api.cache.SetCache;
import net.donutcraft.donutstaff.api.staffmode.StaffModeManager;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.*;

public class SimpleStaffModeManager implements StaffModeManager {

    @Inject @Named("messages") private FileCreator messages;
    @Inject @Named("items-file") private FileCreator items;
    @Inject @Named("staff-mode-cache") private SetCache<UUID> staffModeCache;
    @Inject @Named("vanish-cache") private SetCache<UUID> vanishCache;
    @Inject private NMSManager nmsManager;

    private final Map<UUID, ItemStack[]> playerItemsCache = new HashMap<>();
    private final Map<UUID, ItemStack[]> playerArmorCache = new HashMap<>();

    @Override
    public void enableStaffMode(Player player) {
        player.sendMessage(messages.getString("staff-mode.commands.mode.enabled")
                .replace("%prefix%", messages.getString("commons.global-prefix")));
        player.setAllowFlight(true);
        player.setFlying(true);
        enableVanish(player);
        savePlayerItems(player);
        giveStaffItemsToPlayer(player);
        nmsManager.getNMSHandler().sendActionBar(player,
                messages.getString("staff-mode.commands.mode.action-bar-enabled"));
        staffModeCache.get().add(player.getUniqueId());
    }

    @Override
    public void disableStaffMode(Player player) {
        player.sendMessage(messages.getString("staff-mode.commands.mode.disabled")
                .replace("%prefix%", messages.getString("commons.global-prefix")));
        player.setFlying(false);
        player.setAllowFlight(false);
        disableVanish(player);
        givePlayerItems(player);
        nmsManager.getNMSHandler().sendActionBar(player,
                messages.getString("staff-mode.commands.mode.action-bar-disabled"));
        staffModeCache.get().remove(player.getUniqueId());
    }

    @Override
    public void enableVanish(Player player) {
        player.sendMessage(messages.getString("staff-mode.vanish-enabled")
                .replace("%prefix%", messages.getString("commons.global-prefix")));
        vanishCache.get().add(player.getUniqueId());

        for (Player player1 : Bukkit.getOnlinePlayers()) {
            if (player == player1) {
                continue;
            }
            if (player1.hasPermission("donustaff.seestaff")) {
                continue;
            }
            nmsManager.getNMSHandler().hidePlayer(player1, player);
        }
    }

    @Override
    public void disableVanish(Player player) {
        player.sendMessage(messages.getString("staff-mode.vanish-disabled")
                .replace("%prefix%", messages.getString("commons.global-prefix")));
        vanishCache.get().remove(player.getUniqueId());

        for (Player player1 : Bukkit.getOnlinePlayers()) {
            if (player == player1) {
                continue;
            }
            if (player1.hasPermission("donustaff.seestaff")) {
                continue;
            }
            nmsManager.getNMSHandler().showPlayer(player1, player);
        }
    }

    @Override
    public void savePlayerItems(Player player) {
        playerArmorCache.put(player.getUniqueId(), player.getInventory().getArmorContents());
        playerItemsCache.put(player.getUniqueId(), player.getInventory().getContents());
    }

    @Override
    public void giveStaffItemsToPlayer(Player player) {
        ItemStack randomTp = new ItemStack(Material.COMPASS);
        ItemMeta randomTpMeta = randomTp.getItemMeta();
        randomTpMeta.setDisplayName(items.getString("items.random-tp.name"));
        randomTpMeta.setLore(items.getStringList("items.random-tp.lore"));
        randomTp.setItemMeta(randomTpMeta);

        ItemStack vanish = new ItemStack(Material.GLOWSTONE);
        ItemMeta vanishMeta = vanish.getItemMeta();
        vanishMeta.setDisplayName(items.getString("items.vanish-on.name"));
        vanishMeta.setLore(items.getStringList("items.vanish-on.lore"));
        vanish.setItemMeta(vanishMeta);

        ItemStack knockBack = new ItemStack(Material.STICK);
        ItemMeta knockBackMeta = knockBack.getItemMeta();
        knockBackMeta.setDisplayName(items.getString("items.knock-back.name"));
        knockBackMeta.setLore(items.getStringList("items.knock-back.lore"));
        knockBackMeta.addEnchant(Enchantment.KNOCKBACK, 10, true);
        knockBackMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        knockBack.setItemMeta(knockBackMeta);

        player.getInventory().clear();
        player.getInventory().setItem(0, randomTp);
        player.getInventory().setItem(2, vanish);
        player.getInventory().setItem(4, knockBack);

    }

    @Override
    public void givePlayerItems(Player player) {
        if (!playerItemsCache.containsKey(player.getUniqueId())) {
            return;
        }
        player.getInventory().clear();
        player.getInventory().setContents(playerItemsCache.get(player.getUniqueId()));
        player.getInventory().setArmorContents(playerArmorCache.get(player.getUniqueId()));
    }

    @Override
    public boolean isOnStaffMode(Player player) {
        return staffModeCache.get().contains(player.getUniqueId());
    }
}

