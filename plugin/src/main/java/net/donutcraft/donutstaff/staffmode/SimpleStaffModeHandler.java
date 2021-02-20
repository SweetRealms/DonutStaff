package net.donutcraft.donutstaff.staffmode;

import net.donutcraft.donutstaff.api.cache.MapCache;
import net.donutcraft.donutstaff.files.FileCreator;
import net.donutcraft.donutstaff.util.InventoryUtils;
import net.donutcraft.donutstaff.util.nms.NMSManager;
import net.donutcraft.donutstaff.api.cache.SetCache;
import net.donutcraft.donutstaff.api.staffmode.StaffModeHandler;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.*;

public class SimpleStaffModeHandler implements StaffModeHandler {

    @Inject @Named("messages") private FileCreator messages;
    @Inject @Named("freeze-cache") private SetCache<UUID> freezeCache;
    @Inject @Named("staff-chat-cache") private SetCache<UUID> staffChatCache;
    @Inject @Named("death-inventories-cache") private MapCache<UUID, List<ItemStack>> deathInventoriesCache;
    @Inject @Named("death-armor-cache") private MapCache<UUID, List<ItemStack>> deathArmorCache;
    @Inject private NMSManager nmsManager;
    private Random random;

    @Override
    public void freezePlayer(Player target) {
        for (String line : messages.getStringList("player.frozen-enabled.target")) {
            target.sendMessage(line);
        }
        nmsManager.getNMSHandler().sendTitle(target, messages.getString("player.frozen-enabled.title"),
                messages.getString("player.frozen-enabled.subtitle"),5, 1000000, 5);
        freezeCache.get().add(target.getUniqueId());
    }

    @Override
    public void unFreezePlayer(Player target) {
        for (String line : messages.getStringList("player.frozen-disabled.target")) {
            target.sendMessage(line);
        }
        nmsManager.getNMSHandler().sendTitle(target, "", "", 1, 1, 1);
        nmsManager.getNMSHandler().sendTitle(target, messages.getString("player.frozen-disabled.title"),
                messages.getString("player.frozen-disabled.subtitle"), 5, 20, 5);
        freezeCache.get().remove(target.getUniqueId());
    }

    @Override
    public boolean isPlayerFrozen(Player target) {
        return freezeCache.get().contains(target.getUniqueId());
    }

    @Override
    public void toggleStaffChat(Player player) {
        if (!isPlayerInStaffChat(player)) {
            player.sendMessage(messages.getString("staff-mode.staff-chat.enabled")
                    .replace("%prefix%", messages.getString("commons.global-prefix")));
            staffChatCache.get().add(player.getUniqueId());
            return;
        }
        staffChatCache.get().remove(player.getUniqueId());
        player.sendMessage(messages.getString("staff-mode.staff-chat.disabled")
                .replace("%prefix%", messages.getString("commons.global-prefix")));
    }

    @Override
    public boolean isPlayerInStaffChat(Player player) {
        return staffChatCache.get().contains(player.getUniqueId());
    }

    @Override
    public void fakeLeave(Player player) {
        Bukkit.broadcastMessage(messages.getString("staff-mode.commands.fake-leave")
                .replace("%player_name%", player.getName()));
    }

    @Override
    public void saveDeathPlayerInventory(Player player) {
        Inventory inventory = player.getInventory();

        List<ItemStack> content = InventoryUtils.getAvailableItems(inventory, 0, 27);
        List<ItemStack> armor = InventoryUtils.getAvailableItems(inventory, 36, 40);

        if (hasPlayerSavedInventory(player)) {
            deathInventoriesCache.get().remove(player.getUniqueId());
            deathArmorCache.get().remove(player.getUniqueId());
        }

        deathInventoriesCache.get().put(player.getUniqueId(), content);
        deathArmorCache.get().put(player.getUniqueId(), armor);
    }

    @Override
    public void returnPlayerInventory(Player player, Player sender) {
        if (!hasPlayerSavedInventory(player)) {
            sender.sendMessage(messages.getString("staff-mode.commands.revive.sender-unavailable")
                    .replace("%player_name%", player.getName())
                    .replace("%prefix%", messages.getString("commons.global-prefix")));
            return;
        }
        sender.sendMessage(messages.getString("staff-mode.commands.revive.sender-available")
                .replace("%player_name%", player.getName())
                .replace("%prefix%", messages.getString("commons.global-prefix")));
        player.sendMessage(messages.getString("staff-mode.commands.revive.target-available")
                .replace("%player_name%", player.getName())
                .replace("%prefix%", messages.getString("commons.global-prefix")));
        InventoryUtils.addItemsToPlayer(player, deathInventoriesCache.get().get(player.getUniqueId()), true);
        InventoryUtils.addArmorToPlayer(player, deathInventoriesCache.get().get(player.getUniqueId()), true);
        deathInventoriesCache.get().remove(player.getUniqueId());
        deathArmorCache.get().remove(player.getUniqueId());
    }

    @Override
    public boolean hasPlayerSavedInventory(Player player) {
        return deathInventoriesCache.get().containsKey(player.getUniqueId())
                && deathInventoriesCache.get().containsKey(player.getUniqueId());
    }

    @Override
    public void randomTp(Player player) {
        List<UUID> playerList = new ArrayList<>();

        for (Player player1 : Bukkit.getOnlinePlayers()) {
            playerList.add(player1.getUniqueId());
        }
        playerList.remove(player.getUniqueId());
        if (playerList.size() < 1) {
            player.sendMessage(messages.getString("staff-mode.not-enough-players")
                    .replace("%prefix%", messages.getString("commons.global-prefix")));
            return;
        }
        int playerNumber = random.nextInt(playerList.size());
        Player target = Bukkit.getPlayer(playerList.get(playerNumber));
        player.teleport(target);
        player.sendMessage(messages.getString("staff-mode.random-tp")
                .replace("%player_name%", target.getName())
                .replace("%prefix%", messages.getString("commons.global-prefix")));
    }
}
