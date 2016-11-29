import java.io.*;
import java.util.*;

/**
 * @author  Airaksinen, Tom & Wentzel, Jonatan
 *
 * The class for the High Score List.
 *
 */
public class HighScoreList extends LinkedList<HighScoreEntry> implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a High Score List with default high score entries.
	 */
	public HighScoreList() {
		super();
		this.add(new HighScoreEntry("Space Ace",  2000));
		this.add(new HighScoreEntry("Rocket Man", 1990));
		this.add(new HighScoreEntry("Launcher Gal", 1983));
		this.add(new HighScoreEntry("Iceman", 1700));
		this.add(new HighScoreEntry("Rocker Girl", 1600));
		this.add(new HighScoreEntry("C3PO", 1500));
		this.add(new HighScoreEntry("Dunder Dude", 1400));
		this.add(new HighScoreEntry("Mars Attack", 1300));
		this.add(new HighScoreEntry("Fly Hy", 1200));
		this.add(new HighScoreEntry("Spock", 1100));
	}
}