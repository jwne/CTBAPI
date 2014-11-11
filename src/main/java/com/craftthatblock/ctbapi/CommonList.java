package com.craftthatblock.ctbapi;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CraftThatBlock
 */
public class CommonList {
	public static List<String> playerList() {
		List<String> playerList = new ArrayList<>();
		for (Player player : Bukkit.getOnlinePlayers())
			playerList.add(player.getName());
		return playerList;
	}
}
