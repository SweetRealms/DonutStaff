package net.donutcraft.donutstaff.util;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class InventoryUtils {

    private static final Predicate<ItemStack> IS_FIT_TO_ADD = itemStack -> itemStack != null && itemStack.getType() != Material.AIR;

    public static boolean hasSpace(Player player, int spaces) {
        int i = 0;

        for (ItemStack item : player.getInventory().getContents()) {
            if (item != null) {
                continue;
            }

            i++;
        }

        return i >= spaces;
    }

    public static boolean hasItem(Player player, ItemStack itemStack){
        return player.getInventory().contains(itemStack);
    }

    public static void removeItemsFromPlayer(Player player, List<ItemStack> items){
        boolean hasItem = false;
        for (ItemStack item : items) {
            hasItem = hasItem(player, item);
        }

        if(hasItem) {
            for (ItemStack item : items){
                player.getInventory().removeItem(item);
            }
        }
    }

    public static void addItemsToPlayer(Player player, List<ItemStack> items, boolean dropIfNoSpace) {
        int index = items.size();

        for (ItemStack item : items) {
            index -= 1;

            if (hasSpace(player, index)) {
                player.getInventory().addItem(item);
            } else {
                if (dropIfNoSpace) {
                    player.getWorld().dropItemNaturally(player.getLocation(), item);
                }
            }
        }
    }

    public static void addItemToPlayer(Player player, ItemStack itemStack, boolean dropIfNoSpace) {
        if (hasSpace(player, 1)) {
            player.getInventory().addItem(itemStack);
        } else {
            if (dropIfNoSpace) {
                player.getWorld().dropItemNaturally(player.getLocation(), itemStack);
            }
        }
    }

    public static void addArmorToPlayer(Player player, Iterable<ItemStack> armors, boolean dropIfNoSpace) {
        for (ItemStack armor : armors) {
            if (!isArmor(armor)) {
                continue;
            }

            if (hasSpace(player, 1)) {
                if (hasAlreadyArmor(player, armor)) {
                    player.getInventory().addItem(armor);

                    continue;
                }
            } else if (dropIfNoSpace) {
                player.getWorld().dropItemNaturally(player.getLocation(), armor);
            }

            if (isHelmet(armor)) {
                player.getInventory().setHelmet(armor);
            } else if (isChestPlate(armor)) {
                player.getInventory().setChestplate(armor);
            } else if (isLegging(armor)) {
                player.getInventory().setLeggings(armor);
            } else if (isBoot(armor)) {
                player.getInventory().setBoots(armor);
            }
        }
    }

    private static boolean hasAlreadyArmor(Player player, ItemStack itemStack) {
        if (isHelmet(itemStack)) {
            return player.getInventory().getHelmet() != null;
        } else if (isChestPlate(itemStack)) {
            return player.getInventory().getChestplate() != null;
        } else if (isLegging(itemStack)) {
            return player.getInventory().getLeggings() != null;
        } else if (isBoot(itemStack)) {
            return player.getInventory().getBoots() != null;
        }

        return false;
    }

    private static boolean isArmor(ItemStack itemStack) {
        return isHelmet(itemStack) || isChestPlate(itemStack) || isLegging(itemStack) || isBoot(itemStack);
    }

    private static boolean isHelmet(ItemStack itemStack) {
        return itemStack.getType().name().contains("HELMET");
    }

    private static boolean isChestPlate(ItemStack itemStack) {
        return itemStack.getType().name().contains("CHESTPLATE");
    }

    private static boolean isLegging(ItemStack itemStack) {
        return itemStack.getType().name().contains("LEGGINGS");
    }

    private static boolean isBoot(ItemStack itemStack) {
        return itemStack.getType().name().contains("BOOTS");
    }

    public static List<ItemStack> getAvailableItems(Inventory inventory, int from, int to) {
        List<ItemStack> items = new ArrayList<>();

        ItemStack[] contents = inventory.getContents();
        for (int i = from; i < to; i++) {
            ItemStack item = contents[i];

            if (!IS_FIT_TO_ADD.test(item)) {
                continue;
            }

            items.add(item);
        }

        return items;
    }

}

