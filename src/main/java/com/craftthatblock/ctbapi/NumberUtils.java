package com.craftthatblock.ctbapi;

/**
 * @author CraftThatBlock
 */
public class NumberUtils {
	/**
	 * Check if string is integer
	 *
	 * @param s Input
	 * @return boolean
	 */
	public static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

}
