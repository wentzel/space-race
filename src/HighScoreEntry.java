import java.io.*;

/**
 * @author  Airaksinen, Tom & Wentzel, Jonatan
 *
 * The class for the High Score Entries.
 *
 */
public class HighScoreEntry implements Comparable, Serializable {
	
	private String name;
	private int    score;
	
	/**
	 * Creates a new High Score Entry.
	 * 
	 * @param n player name
	 * @param s score
	 */
	public HighScoreEntry(String n, int s) {
		name = n;
		score = s;
	}
	
	/**
	 * Compares to high score entries.
	 * 
	 * @param o high score entry to compare with
	 * @return -1 if current score is less than the compared, 1 otherwise
	 */
	public int compareTo(Object o) {
		if (score < ((HighScoreEntry)o).getScore()) {
			return -1;
		}
		else {
			return 1;
		}
	}
	
	
	//	 ------------------- Get&Sets of relevant variables -------------------
	
	/**
	 * Returns the name.
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name.
	 * @param n name
	 */
	public void setName(String n) {
		name = n;
	}
	/**
	 * Returns the score.
	 * @return the score
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * Sets the score.
	 * @param s score
	 */
	public void setScore(int s) {
		score = s;
	}
}
