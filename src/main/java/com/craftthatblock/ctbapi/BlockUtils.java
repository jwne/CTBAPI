package com.craftthatblock.ctbapi;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

/**
 * @author CraftThatBlock
 */
public class BlockUtils {
	/**
	 * Apply Metadata to a block
	 *
	 * @param b     Block
	 * @param tag   Tag
	 * @param value Value
	 * @param host  Plugin
	 */
	public static void applyTag(Block b, String tag, String value, Plugin host) {
		b.setMetadata(tag, new FixedMetadataValue(host, value));
	}

	/**
	 * Get Metadata from a block
	 *
	 * @param b   Block
	 * @param tag Tag
	 */
	public static String getTag(Block b, String tag) {
		if (b.getMetadata(tag) != null) {
			for (MetadataValue val : b.getMetadata(tag)) return val.asString();
		}
		return null;
	}

	/**
	 * Remove Metadata from a block
	 *
	 * @param b    Block
	 * @param tag  Tag
	 * @param host Plugin
	 */
	public static void removeTag(Block b, String tag, Plugin host) {
		if (b.getMetadata(tag) != null)
			b.removeMetadata(tag, host);
	}

	/**
	 * Get a material from a string
	 *
	 * @param item Material name
	 * @return Material
	 */
	public static Material getMaterial(String item) {
		return Material.getMaterial(item.toUpperCase());
	}

	/**
	 * Get a item from a integer
	 *
	 * @param item Material ID
	 * @return Material
	 * @deprecated Use getMaterial(String)
	 */
	@Deprecated
	public static Material getMaterial(int item) {
		return Material.getMaterial(item);
	}

	/**
	 * Get a material from a integer/string
	 *
	 * @param item Material name Name/ID
	 * @return Material
	 * @deprecated Use getMaterial(String)
	 */
	@Deprecated
	public static Material getMixedMaterial(String item) {
		if (NumberUtils.isInteger(item))
			return getMaterial(Integer.parseInt(item));
		else
			return getMaterial(item);
	}
}
