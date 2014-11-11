package com.craftthatblock.ctbapi;

import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.List;

/**
 * The ScoreboardBuilder class is a class to create scoreboard very easily.
 * WARNING: Untested and not fully documented yet.
 * <pre>
 *  new ScoreboardBuilder()
 *      .addObjective(
 *          new ObjectiveStorage("test", DisplaySlot.SIDEBAR, "Test")
 *         .addScore("Hey", 2)
 *         .addScore("Sup?", 1)
 *      )
 *  .toScoreboard();
 * </pre>
 *
 * @author CraftThatBlock
 */

@Data
public class ScoreboardBuilder {

	private List<ObjectiveStorage> objectiveStorage = new ArrayList<ObjectiveStorage>();

	/**
	 * Add a objective
	 *
	 * @param objective Objective
	 * @return ScoreboardBuilder
	 */
	public ScoreboardBuilder addObjective(ObjectiveStorage objective) {
		objectiveStorage.add(objective);
		return this;
	}

	/**
	 * Create the Scoreboard!
	 *
	 * @return Scoreboard
	 */
	public Scoreboard toScoreboard() {
		Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		for (ObjectiveStorage objectiveS : objectiveStorage) {
			Objective objective = scoreboard.registerNewObjective(objectiveS.getName(), "dummy");
			objective.setDisplaySlot(objectiveS.getDisplaySlot());
			for (ObjectiveStorage.ScoreStorage score : objectiveS.getScores()) {
				objective.getScore(Bukkit.getOfflinePlayer(score.getKey())).setScore(score.getValue());
			}
			objective.setDisplayName(objectiveS.getDisplayName());
		}


		return scoreboard;
	}

	/**
	 * ObjectiveStorage to store information.
	 */
	@Data
	public static class ObjectiveStorage {
		final private String name;
		final private DisplaySlot displaySlot;
		final private String displayName;
		private List<ScoreStorage> scores = new ArrayList<ScoreStorage>();

		public ObjectiveStorage addScore(String key, int value) {
			scores.add(new ScoreStorage(key, value));
			return this;
		}

		@Data
		public static class ScoreStorage {
			final private String key;
			final private int value;
		}

	}
}
