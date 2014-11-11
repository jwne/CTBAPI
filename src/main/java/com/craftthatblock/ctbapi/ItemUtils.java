package com.craftthatblock.ctbapi;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * @author CraftThatBlock
 */
public class ItemUtils {

	/**
	 * Check if a item is a tool.
	 * (See http://minecraft.gamepedia.com/Enchanting#Enchantments. Items under 'Unbreaking' is a 'tool')
	 *
	 * @param item Item
	 * @return Is tool
	 */
	public static boolean isTool(ItemStack item) {
		return Enchantment.DURABILITY.canEnchantItem(item);
	}

	/**
	 * Get the display name of an item.
	 *
	 * @param item Item
	 * @return Display name
	 */
	public static String getDisplayName(ItemStack item) {
		if (item.hasItemMeta() && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName() != null && item.getItemMeta().getDisplayName() != "") {
			return item.getItemMeta().getDisplayName();
		} else {
			return null;
		}
	}

	/**
	 * Set the display name of an item.
	 *
	 * @param item Item
	 * @param name Name
	 * @return Display name
	 */
	public static ItemStack setDisplayName(ItemStack item, String name) {
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		item.setItemMeta(meta);
		return item;
	}
}
