package net.donutcraft.donutstaff.staffmode;

import net.donutcraft.donutstaff.files.FileCreator;
import net.donutcraft.donutstaff.util.nms.NMSManager;
import net.donutcraft.donutstaff.api.cache.Cache;
import net.donutcraft.donutstaff.api.staffmode.StaffModeManager;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import team.unnamed.gui.core.item.type.ItemBuilder;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.*;

public class SimpleStaffModeManager implements StaffModeManager {

    @Inject @Named("messages") private FileCreator messages;
    @Inject @Named("items-file") private FileCreator items;
    @Inject @Named("staff-mode-cache") private Cache<UUID> staffModeCache;
    @Inject @Named("vanish-cache") private Cache<UUID> vanishCache;
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
        staffModeCache.add(player.getUniqueId());
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
        staffModeCache.remove(player.getUniqueId());
    }

    @Override
    public void enableVanish(Player player) {
        player.sendMessage(messages.getString("staff-mode.vanish-enabled")
                .replace("%prefix%", messages.getString("commons.global-prefix")));
        vanishCache.add(player.getUniqueId());

        for (Player player1 : Bukkit.getOnlinePlayers()) {
            if (player1.hasPermission("donutstaff.seestaff")) {
                return;
            }
            nmsManager.getNMSHandler().hidePlayer(player1, player);
        }
    }

    @Override
    public void disableVanish(Player player) {
        player.sendMessage(messages.getString("staff-mode.vanish-disabled")
                .replace("%prefix%", messages.getString("commons.global-prefix")));
        vanishCache.remove(player.getUniqueId());

        for (Player player1 : Bukkit.getOnlinePlayers()) {
            if (player1.hasPermission("donutstaff.seestaff")) {
                return;
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
        ItemStack random_tp = ItemBuilder.newBuilder(Material.COMPASS, 1)
                .setName(items.getString("items.random-tp.name"))
                .setLore(items.getStringList("items.random-tp.lore"))
                .build();

        ItemStack vanish = ItemBuilder.newBuilder(Material.YELLOW_FLOWER, 1)
                .setName(items.getString("items.vanish-on.name"))
                .setLore(items.getStringList("items.vanish-on.lore"))
                .build();

        ItemStack knock_back = ItemBuilder.newBuilder(Material.STICK, 1)
                .setName(items.getString("items.knock-back.name"))
                .setLore(items.getStringList("items.knock-back.lore"))
                .build();

        player.getInventory().clear();
        player.getInventory().setItem(0, random_tp);
        player.getInventory().setItem(2, vanish);
        player.getInventory().setItem(4, knock_back);

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
        return staffModeCache.exists(player.getUniqueId());
    }
}

