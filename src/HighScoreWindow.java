import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


import java.util.*;

/**
 * @author  Airaksinen, Tom & Wentzel, Jonatan
 *
 * The class for the High Score Window.
 *
 */
public class HighScoreWindow extends JFrame {
	

	private static final long serialVersionUID = 1L;
	
	private        LinkedList<HighScoreEntry> highScoreList;
	private        GameWindow mainWindow;
	private        boolean    gameOver;
	public  static int        GAME_OVER    = 0;
	public  static int        NOT_GAME_OVER = 1;
	
	/**
	 * Creates a new High Score Window.
	 * 
	 * @param w Game Window which will be paused and
	 * over which the Instructions Window will be centered 
	 */
	public HighScoreWindow(GameWindow w) {
		mainWindow = w;
	}
	
	private WindowListener wl = new WindowAdapter() {
		public void windowClosed(WindowEvent e) {
			if (gameOver == true) {
				mainWindow.showGameAlternatives();
			}
			else {
				mainWindow.resumeGame();
				mainWindow.getGameArea().requestFocus();
			}
		}
	};
	
	/**
	 * Creates a new High Score Window and displays (or not) the game
	 * alternatives on close
	 * 
	 * @param w Game Window which will be paused and
	 * over which the Instructions Window will be centered 
	 * @param option 0 if game alternatives is to be shown, otherwise 1
	 */
	public HighScoreWindow(GameWindow w, int option) {
		this(w);
		if (option == 0) {
			gameOver = true;
		}
		else {
			gameOver = false;
		}
	}
	
	public void setupAndDisplay() {
		highScoreList = mainWindow.getHighScore();
		
		Container c = getContentPane();
		c.setLayout(new GridLayout(11, 3, 10, 5));
		c.setBackground(GameWindow.BACKGROUND_COLOR);
		
		JLabel l1 = new JLabel("Rank", JLabel.RIGHT);
		JLabel l2 = new JLabel("Name", JLabel.CENTER);
		JLabel l3 = new JLabel("Score", JLabel.LEFT);
		
		l1.setForeground(GameWindow.FOREGROUND_COLOR);
//		l1.setBorder(new LineBorder(GameWindow.FOREGROUND_COLOR, 3, true));
		l2.setForeground(GameWindow.FOREGROUND_COLOR);
//		l2.setBorder(new LineBorder(GameWindow.FOREGROUND_COLOR, 3, true));
		l3.setForeground(GameWindow.FOREGROUND_COLOR);
//		l3.setBorder(new LineBorder(GameWindow.FOREGROUND_COLOR, 3, true));
		
		c.add(l1);
		c.add(l2);
		c.add(l3);
		
		Iterator<HighScoreEntry> highScoreIterator = highScoreList.iterator();
		int n = 0;
		while (highScoreIterator.hasNext() && n < 10) {
			HighScoreEntry e = highScoreIterator.next();
			
			JLabel rank  = new JLabel("" + (n + 1), JLabel.RIGHT);
			JLabel name  = new JLabel(e.getName(), JLabel.CENTER);
			JLabel score = new JLabel("" + e.getScore(), JLabel.LEFT);
			
			rank.setForeground(GameWindow.FOREGROUND_COLOR);
			name.setForeground(GameWindow.FOREGROUND_COLOR);
			score.setForeground(GameWindow.FOREGROUND_COLOR);
			
			c.add(rank);
			c.add(name);
			c.add(score);
			
			n += 1;
		}
		
		pack();
		
		// Calculate size and position of the window
		Dimension mainWindowSize = mainWindow.getSize();
		int highScoreWindowWidth = getSize().width;
		int highScoreWindowHeight = getSize().height;
//		Toolkit t = Toolkit.getDefaultToolkit();
		Insets insets = getInsets();
		Point mainWindowLocation = mainWindow.getLocation();
		int x = mainWindowLocation.x
				+ (mainWindowSize.width / 2)  - (highScoreWindowWidth / 2);
		int y = mainWindowLocation.y
				+(mainWindowSize.height / 2) - (highScoreWindowHeight / 2);

		// Format the window
		setLocation(x, y);
		setTitle("High Score");
		setSize(highScoreWindowWidth + insets.left + insets.right,
				highScoreWindowHeight + insets.top + insets.bottom);
		setResizable(false);
//		setUndecorated(true);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		addWindowListener(wl);
	}
}

