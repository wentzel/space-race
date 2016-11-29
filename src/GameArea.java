import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

/**
 * @author  Airaksinen, Tom & Wentzel, Jonatan
 *
 * The class for the main Game Area. It paints the game area and also contains
 * the key listener for player control.
 *
 */
public class GameArea extends JComponent {
	
	private static final long serialVersionUID = 1L;
	
	private GameWorld mainWorld;
	
	/**
	 * Creates a Game Area.
	 * 
	 * @param panel1 panel for player 1
	 * @param panel2 panel for player 2
	 */
	public GameArea(GamePanel panel1, GamePanel panel2) {
		mainWorld = new GameWorld(panel1, panel2);
		
		addKeyListener(new GameKeyListener());
		setFocusable(true);		
	}
	
	/**
	 * Draws the labyrinth on screen.
	 * @param g g graphics with which the labyrinth will be drawn
	 */
	public void paintComponent(Graphics g) {
		
		int h = mainWorld.getHeight();
		int w = mainWorld.getWidth();
		
		// Draw the labyrinth
		for (int y = 0 ; y < h; y++) {
			for (int x = 0; x < w; x++) {
				mainWorld.getLocation(x, y).draw(g);
			}
		}

		// Draw all objects
		Iterator<GameObject> objectIterator =  mainWorld.getWorldObjects().iterator();
		while(objectIterator.hasNext()) {
			((GameObject) objectIterator.next()).draw(g);
		}

		// Draw all actors
		Iterator<GameActor> actorIterator =  mainWorld.getWorldActors().iterator();
		while(actorIterator.hasNext()) {
			((GameActor) actorIterator.next()).draw(g);
		}
		
		if (((GameWindow) getTopLevelAncestor()).gamePaused()) {
			g.setColor(new Color(190, 0, 150));
			displayMessage(g, "Game Paused. Press p to resume.");
		}
	}
	
	/**
	 * Displays a message.
	 * 
	 * @param g g graphics with which the message will be drawn
	 * @param message message to display
	 */
	public void displayMessage(Graphics g, String message) {
		Dimension labyrinthSize = getSize();
		GameWindow mainWindow = ((GameWindow) getTopLevelAncestor());
		FontMetrics messageMetrics =
			mainWindow.getMessageMetrics();
		
		int x = (labyrinthSize.width - messageMetrics.stringWidth(message)) /2;
		int y = (labyrinthSize.height - messageMetrics.getHeight())	/2;
		
		g.setFont(mainWindow.getMessageFont());
		g.setColor(GameWindow.FOREGROUND_COLOR);
		g.drawString(message, x, y);
	}
	
	/**
	 * Returns the world which is associated with the game area.
	 * @return the world which is associated with the game area
	 */
	public GameWorld getWorld() {
		return mainWorld;
	}
	
	/**
	 * Sets the world which is associated with the game area.
	 * @param world associated with the game area
	 */
	public void setWorld(GameWorld world) {
		mainWorld = world;
	}
	
	/**
	 * The class for our key listener. Handles both player control and
	 * UI actions (shortcut keys for New Game, Quit etc.)
	 * 
	 * @author  Airaksinen, Tom & Wentzel, Jonatan
	 */
	class GameKeyListener extends KeyAdapter {
		
		/**
		 * Handles keyPress events.
		 * @param e keyEvent
		 */
		public void keyPressed(KeyEvent e) {
			
			GameWindow mainWindow = ((GameWindow)getTopLevelAncestor());
			Player player1 = mainWorld.getPlayer(1);
			Player player2 = mainWorld.getPlayer(2);
			int keyCode = e.getKeyCode();

			if (keyCode == KeyEvent.VK_LEFT) {
				player2.setNewMoveInitiated(true);
				player2.goLeft();
			}
			else if (keyCode == KeyEvent.VK_A) {
				player1.setNewMoveInitiated(true);
				player1.goLeft();
			}
			else if (keyCode == KeyEvent.VK_RIGHT) {
				player2.setNewMoveInitiated(true);
				player2.goRight();
			}
			else if (keyCode == KeyEvent.VK_D) {
				player1.setNewMoveInitiated(true);
				player1.goRight();
			}
			else if (keyCode == KeyEvent.VK_UP) {
				player2.setNewMoveInitiated(true);
				player2.goUp();
			}
			else if (keyCode == KeyEvent.VK_W) {
				player1.setNewMoveInitiated(true);
				player1.goUp();
			}
			else if (keyCode == KeyEvent.VK_DOWN) {
				player2.setNewMoveInitiated(true);
				player2.goDown();
			}
			else if (keyCode == KeyEvent.VK_S) {
				player1.setNewMoveInitiated(true);
				player1.goDown();
			}
			else if (keyCode == KeyEvent.VK_SPACE) {
				player1.pickUpReleaseObject();
			}
			else if (keyCode == KeyEvent.VK_ENTER) {
				player2.pickUpReleaseObject();
			}
			
			// Universal commands
			else if (keyCode == KeyEvent.VK_P) {
				if (mainWindow.gamePaused()) {
					mainWindow.resumeGame();
				}
				else {
					mainWindow.pauseGame();
				}
			}
			else if (keyCode == KeyEvent.VK_N && e.isControlDown()) {
				mainWindow.newGameRequested();
			}
			else if (keyCode == KeyEvent.VK_I && e.isControlDown()) {
				mainWindow.newInstructionsWindow();
			}
			else if (keyCode == KeyEvent.VK_B && e.isControlDown()) {
				mainWindow.newAboutWindow();
			}
			else if (keyCode == KeyEvent.VK_H && e.isControlDown()) {
				mainWindow.newHighScoreWindow(HighScoreWindow.NOT_GAME_OVER);
			}
			else if (keyCode == KeyEvent.VK_Q && e.isControlDown()) {
				mainWindow.quitGame();
			}

			player1.updatePosition();
			player2.updatePosition();
		}
		
		/**
		 * Handles keyRelease events.
		 * @param e keyEvent
		 */
		public void keyReleased(KeyEvent e) {
			int keyCode = e.getKeyCode();
			Player player1 = mainWorld.getPlayer(1);
			Player player2 = mainWorld.getPlayer(2);
			
			if (keyCode == KeyEvent.VK_A) {
				player1.quitGoLeft();
				mainWorld.getPlayer(1).setNewMoveInitiated(false);
			}
			else if (keyCode == KeyEvent.VK_D) {
				player1.quitGoRight();
				mainWorld.getPlayer(1).setNewMoveInitiated(false);
			}
			else if (keyCode == KeyEvent.VK_W) {
				player1.quitGoUp();
				mainWorld.getPlayer(1).setNewMoveInitiated(false);
			}
			else if (keyCode == KeyEvent.VK_S) {
				player1.quitGoDown();
				mainWorld.getPlayer(1).setNewMoveInitiated(false);
			}
//					mainWorld.getPlayer(1).setNewMoveInitiated(false);
					
			
			if (keyCode == KeyEvent.VK_LEFT) {
				player2.quitGoLeft();
			}
			else if (keyCode == KeyEvent.VK_RIGHT) {
				player2.quitGoRight();
			}
			else if (keyCode == KeyEvent.VK_UP) {
				player2.quitGoUp();
			}
			else if (keyCode == KeyEvent.VK_DOWN) {
				player2.quitGoDown();
			}
			//						mainWorld.getPlayer(2).setNewMoveInitiated(false);
		}
	}
}

