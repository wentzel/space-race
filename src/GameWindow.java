import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

import java.util.*;
import java.io.*;

/**
 * @author  Airaksinen, Tom & Wentzel, Jonatan
 *
 * The class for the main window of the game. It is used for handling of all
 * major game operations, e.g. new game and pause game.
 * 
 */
public class GameWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private GameArea      labyrinth;
	private GamePanel     panel1;
	private GamePanel     panel2;
	private GameButtons   buttonPanel;
	
	private Timer         timer;
	private boolean       paused;
	private boolean       gameOver;
	private Font          messageFont;
	private FontMetrics   messageMetrics;
	private HighScoreList highScore;
	
	// Dessa beh�ver egentligen inte initieras h�r eftersom loadSettings(true)
	// k�rs i b�rjan
	public static Color FOREGROUND_COLOR = new Color(120,  50, 150);
	public static Color BACKGROUND_COLOR = new Color(140, 124, 194);
	public static Color BUTTON_COLOR = new Color(160, 150, 210);
	public static LineBorder BUTTON_BORDER = new LineBorder(
			GameWindow.FOREGROUND_COLOR, 3, true);
	public static Font  LABEL_FONT =
		new Font("Gill Sans MT", Font.BOLD, 14);

	
	/**
	 * Creates a new GameWindow.
	 */
	public GameWindow() {
		
	}
	
	public void setupAndDisplay() {
			
		loadSettings(true);
		
		panel1    = new GamePanel();
		panel2    = new GamePanel();
		labyrinth = new GameArea(panel1, panel2);
		enterPlayerNames();
		labyrinth.getWorld().getPlayer(1).updatePanel();
		labyrinth.getWorld().getPlayer(2).updatePanel();
		
		
	
		// Add components to window
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		c.setBackground(BACKGROUND_COLOR);
		c.add(panel1, BorderLayout.WEST);
		c.add(labyrinth, BorderLayout.CENTER);
		c.add(panel2, BorderLayout.EAST);
		pack();
		
		buttonPanel = new GameButtons(panel1.getWidth());
		c.add(buttonPanel, BorderLayout.SOUTH);
		pack();
		
		// Set correct window size
		Insets insets = getInsets();
		setSize((GameLocation.getSideLength()
				* labyrinth.getWorld().getWidth())
				+ panel1.getWidth() * 2
				- 2 // Kompensation f�r mystisk bredd�kning...
				+ insets.left + insets.right,
				(GameLocation.getSideLength()
						* labyrinth.getWorld().getHeight())
						+ buttonPanel.getHeight()
						+ insets.top + insets.bottom);
		
		// Center window on screen
		int x, y;
		Toolkit t = Toolkit.getDefaultToolkit();
		Dimension screenSize = t.getScreenSize();
		x = screenSize.width / 2 - getSize().width / 2;
		y = screenSize.height / 2 - getSize().height / 2;
		setLocation(x, y);
		
		setTitle("SpaceRace");
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		labyrinth.requestFocus();
		
		// Initialize various auxiliary variables
		messageFont = new Font("SansSerif", Font.PLAIN, 24);
		messageMetrics = this.getFontMetrics(messageFont);
		loadHighScore();
		paused   = false;
		gameOver = false;
		
		// Initialize and start timer
		timer = new Timer(
				50,
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						labyrinth.getWorld().tick();
						labyrinth.repaint();
					}
				}		
		);
		timer.start();
	}
	
	/**
	 * Pauses the game.
	 */
	public void pauseGame() {
		paused = true;
		labyrinth.repaint();
		timer.stop();
	}
	
	/**
	 * Resumes the game.
	 */
	public void resumeGame() {
		paused = false;
		timer.restart();
	}
	
	/**
	 * Returns the game paused status.
	 * @return the game paused status
	 */
	public boolean gamePaused() {
		return paused;
	}
	
	/**
	 * Sets the game paused status.
	 * @param b whether the game is paused or not
	 */
	public void setPaused(boolean b) {
		paused = b;
	}
	
	/**
	 * Shows the quit confirmation dialog box.
	 */
	public void quitGame() {
		pauseGame();
		int i = JOptionPane.showConfirmDialog(this,
				"Are you sure want to quit the game?",
				"Quit Game",
				JOptionPane.YES_NO_OPTION);
		if (i == JOptionPane.YES_OPTION) {
			saveHighScore();
			System.exit(0);
		}
		if (gameOver) {
			showGameAlternatives();
		}
		else {
			resumeGame();
			labyrinth.requestFocus();
		}
	}
	
	/**
	 * Restarts the game.
	 */
	public void restartGame() {
		gameOver = false;
		String player1Name = labyrinth.getWorld().getPlayer(1).getPlayerName();
		String player2Name = labyrinth.getWorld().getPlayer(2).getPlayerName();
		panel1.newWorkshop();
		panel2.newWorkshop();
		labyrinth.setWorld(new GameWorld(panel1, panel2));
		enterPlayerNames(player1Name, player2Name);
		labyrinth.getWorld().getPlayer(1).updatePanel();
		labyrinth.getWorld().getPlayer(2).updatePanel();
		if (!gameOver) {
			resumeGame();
		}
	}
	
	/** 
	 * Handles everything when a player has won the game.
	 */
	public void gameOver() {
		gameOver = true;
		int winningPlayerNumber;
		Player player1 = labyrinth.getWorld().getPlayer(1);
		Player player2 = labyrinth.getWorld().getPlayer(2);
		int    player1Score = player1.getScore();
		int    player2Score = player2.getScore();
		if (player1Score > player2Score) {
			winningPlayerNumber = 1;
		}
		else {
			winningPlayerNumber = 2;
		}
		
		// Congratulate player
		JOptionPane.showMessageDialog(this,
				"Congratulations " +
				labyrinth.getWorld().getPlayer(winningPlayerNumber)
					.getPlayerName()
				+"! You won \n"
				+ "with " + Math.max(player1Score, player2Score) + " points"
				+ " over " + Math.min(player1Score, player2Score) + " points",
				"Game Over",
				JOptionPane.PLAIN_MESSAGE,
				new ImageIcon("img/Flower.gif"));
		
		// Enter new highscore
		addHighScores(new HighScoreEntry(
							player1.getPlayerName(), player1Score),
					   new HighScoreEntry(
					   		player2.getPlayerName(), player2Score));
		
		showGameAlternatives();
	}
	
	/** 
	 * Shows alternatives after a player has won. New Game/High Score/Quit.
	 */
	public void showGameAlternatives() {
		String[] answerAlternatives = {"New Game",
				"High Score", "Quit"};
		int alt = JOptionPane.showOptionDialog(
					this,
					"What do you want do to?",
					"Game Over",
					JOptionPane.DEFAULT_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null,
					answerAlternatives,
					answerAlternatives[0]);
		
		if (alt == 0) {
			newGameRequested();
		}
		if (alt == 1) {
			newHighScoreWindow(HighScoreWindow.GAME_OVER);
		}
		else if (alt == 2) {
			quitGame();
		}
	}
	
	/** 
	 * Shows the restart confirmation dialog box.
	 */
	public void newGameRequested() {
		pauseGame();
		int i = JOptionPane.showConfirmDialog(this,
				"Are you sure want to restart the game?",
				"Restart Game",
				JOptionPane.YES_NO_OPTION);
		if (i == JOptionPane.YES_OPTION) {
			restartGame();
		}
		if (gameOver) {
			showGameAlternatives();
		}
		else {
			resumeGame();
			labyrinth.requestFocus();
		}
	}
	
	/**
	 * Shows the dialog boxes for entering player names. This method displays
	 * the default names "Player 1" and "Player 2" in the text fields.
	 */
	public void enterPlayerNames() {
		enterPlayerNames("Player 1", "Player 2");
	}
	
	/**
	 * Shows the dialog boxes for entering player names. This method displays
	 * the player names in the text fields.
	 * 
	 * @param name1 name of player 1
	 * @param name2 name of player 2
	 */
	public void enterPlayerNames(String name1, String name2) {
		labyrinth.getWorld().getPlayer(1)
		.setPlayerName(JOptionPane.showInputDialog(
			"What is the name of player 1?",
			name1));
		labyrinth.getWorld().getPlayer(2)
		.setPlayerName(JOptionPane.showInputDialog(
			"What is the name of player 2?",
			name2));
	}
	
	/**
	 * Shows the Instructions window.
	 */
	public void newInstructionsWindow() {
		pauseGame();
		InstructionsWindow w =	new InstructionsWindow(this);
		w.setupAndDisplay();
	}
	
	/**
	 *  Shows the About window.
	 */
	public void newAboutWindow() {
		pauseGame();
		AboutWindow w =	new AboutWindow(this);
		w.setupAndDisplay();
	}
	
	/**
	 * Shows the High score window.
	 * 
	 * @param option decides whether the high score window should resume the
	 * game on close, or show the game alternatives dialog box
	 */
	public void newHighScoreWindow(int option) {
		pauseGame();
		HighScoreWindow w =	new HighScoreWindow(this, option);
		w.setupAndDisplay();
	}
	
	/**
	 * Shows the Settings window.
	 * 
	 * @param option decides whether the settings window should resume the
	 * game on close, or show the game alternatives dialog box
	 */
	public void newSettingsWindow(int option) {
		pauseGame();
		SettingsWindow w = new SettingsWindow(this, option);
		w.setupAndDisplay();
	}
	
	@SuppressWarnings("resource")
	public void loadSettings(boolean defaultSettings) {
		try {
			ObjectInputStream in = new ObjectInputStream
						(new FileInputStream("set/default.set"));
			if (!defaultSettings) {
				String workingDirectory = System.getProperty("user.dir");
				JFileChooser fc = new JFileChooser(workingDirectory + "/set");
				int result = fc.showOpenDialog(this);
				if (result == JFileChooser.APPROVE_OPTION) {
					String fileName = fc.getSelectedFile().getAbsolutePath();
					System.out.println(fileName);
					if (fileName.startsWith(workingDirectory)) {
						fileName = fileName.substring(workingDirectory.length()+1);
						fileName = fileName.replace('\\','/');
						System.out.println(workingDirectory.length());
						System.out.println(fileName);
					}
					in = new ObjectInputStream(new FileInputStream(fileName));
				}
			}

			FOREGROUND_COLOR = (Color) in.readObject();
			BACKGROUND_COLOR = (Color) in.readObject();
			BUTTON_COLOR = (Color) in.readObject();
			BUTTON_BORDER = (LineBorder) in.readObject();
			LABEL_FONT = (Font) in.readObject();
			Floor.setIcon(Floor.FLOOR_TYPE_1, (Icon) in.readObject());
			Floor.setIcon(Floor.FLOOR_TYPE_2, (Icon) in.readObject());
			Floor.setIcon(Floor.FLOOR_TYPE_3, (Icon) in.readObject());
			Floor.setIcon(Floor.FLOOR_TARGET_1, (Icon) in.readObject());
			Floor.setIcon(Floor.FLOOR_TARGET_2, (Icon) in.readObject());
			Wall.setIcon(Wall.WALL_TYPE_1, (Icon) in.readObject());
			Wall.setIcon(Wall.WALL_TYPE_2, (Icon) in.readObject());
			
			System.out.println("Closing in-file");
			System.out.println(FOREGROUND_COLOR.getRed());
			System.out.println(FOREGROUND_COLOR.getGreen());
			System.out.println(FOREGROUND_COLOR.getBlue());
			in.close();
		}
		catch (ClassNotFoundException ex) {
			System.out.println("ClassNotFoundException while loading Settings");
		}
		catch (IOException ex) {
			System.out.println("IOException while loading Settings");
			// No default settings found, use hard coded settings.
		}
	}
	
	@SuppressWarnings("resource")
	public void saveSettings(boolean defaultSettings) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(
										new FileOutputStream(
												"set/settings.set"));
			if (defaultSettings) {
				out = new ObjectOutputStream
										(new FileOutputStream(
												"set/default.set"));
			}
			else if (!defaultSettings) {
				String workingDirectory = System.getProperty("user.dir");
				JFileChooser fc = new JFileChooser(workingDirectory + "/set");
				int result = fc.showSaveDialog(this);
				if (result == JFileChooser.APPROVE_OPTION) {
					String fileName = fc.getSelectedFile().getAbsolutePath();
					System.out.println(fileName);
					if (fileName.startsWith(workingDirectory)) {
						fileName = fileName.substring(workingDirectory.length()+1);
						fileName = fileName.replace('\\','/');
						System.out.println(workingDirectory.length());
						System.out.println(fileName);
					}
					out = new ObjectOutputStream(
									new FileOutputStream(fileName));
				}
			}
			out.writeObject(FOREGROUND_COLOR);
			out.writeObject(BACKGROUND_COLOR);
			out.writeObject(BUTTON_COLOR);
			out.writeObject(BUTTON_BORDER);
			out.writeObject(LABEL_FONT);
			out.writeObject(Floor.getIcon(Floor.FLOOR_TYPE_1));
			out.writeObject(Floor.getIcon(Floor.FLOOR_TYPE_2));
			out.writeObject(Floor.getIcon(Floor.FLOOR_TYPE_3));
			out.writeObject(Floor.getIcon(Floor.FLOOR_TARGET_1));
			out.writeObject(Floor.getIcon(Floor.FLOOR_TARGET_2));
			out.writeObject(Wall.getIcon(Wall.WALL_TYPE_1));
			out.writeObject(Wall.getIcon(Wall.WALL_TYPE_2));
			
			out.close();
		}
		catch (IOException ex) {
			System.out.println("IOException while saving Settings");
		}
	}
	
	/** 
	 * Loads the High Score List from the file highscore.hsl.
	 */
	public void loadHighScore() {
		highScore = null;
		try {
			ObjectInputStream in = new ObjectInputStream
								   (new FileInputStream("highscore.hsl"));
			highScore = (HighScoreList) in.readObject();
			in.close();
		}
		catch (ClassNotFoundException ex) {
			System.out.println("ClassNotFoundException while loading "
							 + "High Score");
		}
		catch (IOException ex) {
			System.out.println("IOException while loading High Score");
			// No highscore.hsl found -> Create a new default HighScoreList
			highScore = new HighScoreList();
		}
	}
	
	/** 
	 * Saves the High Score List to the file highscore.hsl.
	 */
	public void saveHighScore() {
		try {
			ObjectOutputStream out = new ObjectOutputStream
									(new FileOutputStream("highscore.hsl"));
			out.writeObject(highScore);
			out.close();
		}
		catch (IOException ex) {
			System.out.println("IOException while saving High Score");
		}
	}
	
	/**
	 * Adds scores to the high score list if they are high enough, then
	 * sorts the list in falling order.
	 */
	public void addHighScores(HighScoreEntry e1, HighScoreEntry e2) {
		
		int lowestScore = ((HighScoreEntry)highScore.getLast()).getScore();
		if (e1.getScore() >= lowestScore) {
			highScore.removeLast();
			highScore.addFirst(e1);
			Collections.sort(highScore);
			Collections.reverse(highScore); // Should be sorted in falling order
		}
		if (e2.getScore() >= lowestScore) {
			highScore.removeLast();
			highScore.addFirst(e2);
		}
		Collections.sort(highScore);
		Collections.reverse(highScore); // Should be sorted in falling order
	}
	
	public void updateForeground(Color color) {
		FOREGROUND_COLOR = color;
		BUTTON_BORDER = new LineBorder(
				GameWindow.FOREGROUND_COLOR, 3, true);
		panel1.updateForeground();
		panel2.updateForeground();
		buttonPanel.updateForeground();
	}
	
	public void updateBackground(Color color) {
		BACKGROUND_COLOR = color;
		panel1.updateBackground();
		panel2.updateBackground();
		buttonPanel.updateBackground();
		Container c = getContentPane();
		c.setBackground(BACKGROUND_COLOR);
	}
	
	public void updateButtonColor(Color color) {
		BUTTON_COLOR = color;
		buttonPanel.updateButtonColor();
	}
	
	//	 ------------------- Get&Sets of relevant variables -------------------
	
	public static Color getForegroundColor() {
		return FOREGROUND_COLOR;
	}
	
	public static void setForegroundColor(Color color) {
		FOREGROUND_COLOR = color;
	}
	
	public static Color getBackgroundColor() {
		return BACKGROUND_COLOR;
	}
	
	public static void setBackgroundColor(Color color) {
		BACKGROUND_COLOR = color;
	}
	
	public static Color getButtonColor() {
		return BUTTON_COLOR;
	}
	
	public static void setButtonColor(Color color) {
		BUTTON_COLOR = color;
	}
	
	/**
	 * Returns the high score list.
	 * @return the high score list
	 */
	public LinkedList<HighScoreEntry> getHighScore() {
		return highScore;
	}
	
	/**
	 * Returns the game over status.
	 * @return the game over status
	 */
	public boolean getGameOver() {
		return gameOver;
	}
	
	/**
	 * Sets the game over status
	 * @param b whether the game is over or not
	 */
	public void setGameOver(boolean b) {
		gameOver = b;
	}
	
	/**
	 * Returns the message font.
	 * @return the message font 
	 */
	public Font getMessageFont() {
		return messageFont;
	}

	/**
	 * Returns the message metrics.
	 * @return the message metrics
	 */
	public FontMetrics getMessageMetrics() {
		return messageMetrics;
	}
	
	/**
	 * Returns the panel for a player.
	 * 
	 * @param i player number
	 * @return the panel for the player
	 */
	public JPanel getPanel(int i) {
		if (i == 1) {
			return panel1;
		}
		else {
			return panel2;
		}
	}
	
	/**
	 * Returns the labyrinth.
	 * @return the labyrinth
	 */
	public GameArea getGameArea() {
		return labyrinth;
	}
}
