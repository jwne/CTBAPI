package com.craftthatblock.ctbapi;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author CraftThatBlock
 */
public class CTBAPI extends JavaPlugin {

	// Unused, leaving for safety
	public void onEnable() {
	}

	/**
	 * Get potion from string
	 * Format: POTION LEVEL, ex: SPEED 0
	 * Level starts at 0, but is I in-game. Potion is **:** (Infinite)
	 *
	 * @param potion String
	 * @return potion
	 */
	public static PotionEffect getInfinitePotionFromString(String potion) {
		String[] ar = potion.split(" ");
		return new PotionEffect(PotionEffectType.getByName(ar[0]), 72000, Integer.parseInt(ar[1]));
	}

	/**
	 * Get potion from string
	 * Format: POTION LEVEL SECOND, ex: SPEED 0 30
	 * Level starts at 0, but is I in-game.
	 *
	 * @param potion String
	 * @return potion
	 */
	public static PotionEffect getPotionFromString(String potion) {
		String[] ar = potion.split(" ");
		return new PotionEffect(PotionEffectType.getByName(ar[0]), Integer.parseInt(ar[2]) * 20, Integer.parseInt(ar[1]));
	}

	/**
	 * Clears most things of a player (inv, food/health, XP, potion, etc)
	 *
	 * @param player Player to reset
	 */
	public static void resetPlayer(Player player) {
		player.getInventory().clear();
		player.getInventory().setHelmet(null);
		player.getInventory().setChestplate(null);
		player.getInventory().setLeggings(null);
		player.getInventory().setBoots(null);

		player.setExp(0);
		player.setLevel(0);
		player.setCompassTarget(player.getLocation());
		player.setFoodLevel(20);
		player.setHealth(player.getMaxHealth());
		player.setSaturation(100);
		player.setExhaustion(0);
		for (PotionEffect potion : player.getActivePotionEffects())
			player.removePotionEffect(potion.getType());
	}

	/**
	 * Transfer a inventory into a string
	 *
	 * @param inventory Inventory
	 * @return String
	 */
	@Deprecated
	public static String inventoryToString(Inventory inventory) {
		try {
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);

			// Write the size of the inventory
			dataOutput.writeInt(inventory.getSize());

			// Save every element in the list
			for (int i = 0; i < inventory.getSize(); i++) {
				dataOutput.writeObject(inventory.getItem(i));
			}

			// Serialize that array
			dataOutput.close();
			return Base64Coder.encodeLines(outputStream.toByteArray());
		} catch (Exception e) {
			throw new IllegalStateException("Unable to save item stacks.", e);
		}
	}

	/**
	 * Transfer a string into an inventory
	 *
	 * @param data String
	 * @return Inventory
	 * @throws IOException Failed.
	 */
	@Deprecated
	public static Inventory stringToInventory(String data) throws IOException {
		try {
			ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
			BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
			Inventory inventory = Bukkit.getServer().createInventory(null, dataInput.readInt());

			// Read the serialized inventory
			for (int i = 0; i < inventory.getSize(); i++) {
				inventory.setItem(i, (ItemStack) dataInput.readObject());
			}
			dataInput.close();
			return inventory;
		} catch (ClassNotFoundException e) {
			throw new IOException("Unable to decode class type.", e);
		}
	}


}
