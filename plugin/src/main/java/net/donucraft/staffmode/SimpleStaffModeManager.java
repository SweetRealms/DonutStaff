package net.donucraft.staffmode;

import net.donucraft.DonutStaff;
import net.donucraft.files.FileCreator;
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
    @Inject private DonutStaff donutStaff;
    @Inject @Named("staff-mode-cache") private Cache<UUID> staffModeCache;

    private final Map<UUID, ItemStack[]> playerItemsCache = new HashMap<>();
    private final Map<UUID, ItemStack[]> playerArmorCache = new HashMap<>();

    @Override
    public void enableStaffMode(Player player) {
        player.sendMessage(messages.getString("staff-mode.commands.mode.enabled"));
        toggleVanish(player);
        player.setAllowFlight(true);
        player.setFlying(true);
        savePlayerItems(player);
        giveStaffItemsToPlayer(player);
        staffModeCache.add(player.getUniqueId());
    }

    @Override
    public void disableStaffMode(Player player) {
        player.sendMessage(messages.getString("staff-mode.commands.mode.disabled"));
        player.setFlying(false);
        player.setAllowFlight(false);
        toggleVanish(player);
        givePlayerItems(player);
        staffModeCache.remove(player.getUniqueId());
    }

    @Override
    public void toggleVanish(Player player) {
        if (isOnStaffMode(player)) {
            for (Player player1 : Bukkit.getOnlinePlayers()) {
                player1.showPlayer(donutStaff, player);
            }
            return;
        }
        for (Player player1 : Bukkit.getOnlinePlayers()) {
            player1.hidePlayer(donutStaff, player);
        }

    }

    @Override
    public void savePlayerItems(Player player) {
        playerArmorCache.put(player.getUniqueId(), player.getInventory().getArmorContents());
        playerItemsCache.put(player.getUniqueId(), player.getInventory().getContents());
    }

    @Override
    public void giveStaffItemsToPlayer(Player player) {
        ItemStack compass = ItemBuilder.newBuilder(Material.COMPASS, 1)
                .setName(messages.getString("items.compass.name"))
                .setLore(messages.getStringList("items.compass.lore"))
                .build();

        ItemStack skull = ItemBuilder.newBuilder(Material.SKULL, 1)
                .setName(messages.getString("items.skull.name"))
                .setLore(messages.getStringList("items.skull.lore"))
                .build();

        ItemStack invsee = ItemBuilder.newBuilder(Material.STICK, 1)
                .setName(messages.getString("items.stick.name"))
                .setLore(messages.getStringList("items.stick.lore"))
                .build();

        player.getInventory().clear();
        player.getInventory().setItem(0, compass);
        player.getInventory().setItem(2, skull);
        player.getInventory().setItem(4, invsee);

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
