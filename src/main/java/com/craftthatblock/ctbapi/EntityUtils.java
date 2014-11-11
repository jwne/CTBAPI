package com.craftthatblock.ctbapi;

import org.bukkit.entity.Entity;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

/**
 * @author CraftThatBlock
 */
public class EntityUtils {
	/**
	 * Apply Metadata to a entity
	 *
	 * @param e     Entity
	 * @param tag   Tag
	 * @param value Value
	 * @param host  Plugin
	 */
	public static void applyTag(Entity e, String tag, String value, Plugin host) {
		e.setMetadata(tag, new FixedMetadataValue(host, value));
	}

	/**
	 * Get Metadata from a entity
	 *
	 * @param e   Entity
	 * @param tag Tag
	 * @return Tag Data
	 */
	public static String getTag(Entity e, String tag) {
		if (e.getMetadata(tag) != null) {
			for (MetadataValue val : e.getMetadata(tag)) return val.asString();
		}
		return null;
	}

	/**
	 * Remove Metadata from a entity
	 *
	 * @param e    Entity
	 * @param tag  Tag
	 * @param host Plugin
	 */
	public static void removeTag(Entity e, String tag, Plugin host) {
		if (e.getMetadata(tag) != null)
			e.removeMetadata(tag, host);
	}
}
