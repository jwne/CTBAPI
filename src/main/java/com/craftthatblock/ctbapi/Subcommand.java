package com.craftthatblock.ctbapi;

import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * The Subcommand class provides a nice way to make "subcommands" for tab completion.
 *
 * @author CraftThatBlock
 */
public class Subcommand {

	private final List<Subcommand> subcommands = new ArrayList<>();
	private final List<String> text = new ArrayList<>();

	public static List<String> calculate(List<Subcommand> subcommands, String[] args) {
		List<Subcommand> temp = new ArrayList<>();
		List<Subcommand> found = new ArrayList<>();

		// Use base subcommands for suggestions at the start
		for (Subcommand subcommand : subcommands) {
			temp.add(subcommand);
		}

		// To up the args
		for (String arg : args) {
			List<Subcommand> tempList = new ArrayList<>();

			// If already have "founds" command, move them to temp.
			if (found.size() > 0)
				for (Subcommand sub : found)
					for (Subcommand subsub : sub.subcommands)
						temp.add(subsub);

			found.clear();

			for (Subcommand subcommand : temp) {
				List<String> sub = new ArrayList<>();
				// Get partial matches (Possible "suggest")
				for (String check : subcommand.getPartialMatches(arg)) {
					// Make sure it doesn't suggest non-matching queries
					sub.add(check);
					if (check.equalsIgnoreCase(arg) && !found.contains(subcommand)) {
						// If perfect match, suggest next next row ("found")
						found.add(subcommand);
					} else if (!tempList.contains(subcommand)) {
						// Add to suggestions
						tempList.add(subcommand);
					}

				}

				// Move "sub" (the matching ones) to the suggestions
				subcommand.text.clear();
				for (String check : sub)
					subcommand.text.add(check);
			}

			// Move list over
			temp.clear();
			for (Subcommand sub : tempList)
				temp.add(sub);
			tempList.clear();
		}

		// Make string list from "temp"
		List<String> command = new ArrayList<>();
		for (Subcommand subcommand : temp)
			for (String subcommandText : subcommand.text)
				command.add(subcommandText);

		return command;
	}

	/**
	 * Create a Subcommand
	 *
	 * @param text        List of possibilities of matches
	 * @param subcommands Daisy-chain subcommands
	 */
	public Subcommand(Iterable<String> text, Subcommand... subcommands) {
		for (String add : text)
			this.text.add(add);
		Collections.addAll(this.subcommands, subcommands);
	}

	/**
	 * Create a Subcommand
	 *
	 * @param text        Subcommand text
	 * @param subcommands Daisy-chain subcommands
	 */
	public Subcommand(String text, Subcommand... subcommands) {
		this(Arrays.asList(text), subcommands);
	}

	/**
	 * Add Subcommands to this Subcommand
	 *
	 * @param subcommands The Subcommands to add
	 * @return Subcommand
	 */
	public Subcommand addSubcommands(Subcommand... subcommands) {
		Collections.addAll(this.subcommands, subcommands);
		return this;
	}

	/**
	 * Add texts to this Subcommand
	 *
	 * @param text The texts to add
	 * @return Subcommand
	 */
	public Subcommand addTexts(String... text) {
		Collections.addAll(this.text, text);
		return this;
	}

	/**
	 * Get texts that partly match
	 *
	 * @param prefix Prefix to check
	 * @return List of matches
	 */
	private List<String> getPartialMatches(String prefix) {
		List<String> matches = new ArrayList<>();
		for (String match : text)
			if (!matches.contains(match)/* No duplicate */ && StringUtil.startsWithIgnoreCase(match, prefix))
				matches.add(match);

		return matches;
	}

}