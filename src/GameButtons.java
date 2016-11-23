import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author  Airaksinen, Tom & Wentzel, Jonatan
 *
 * The class for the main button panel containing e.g. a quit button and a
 * new game button.
 *
 */
public class GameButtons extends JPanel implements ActionListener {
	private JButton newGameButton;
	private JButton instructionsButton;
	private JButton highScoreButton;
	private JButton aboutButton;
	private JButton quitButton;
	private JButton settingsButton;
	private JPanel  buttonPanel;
	
	
	/**
	 * Creates a new panel with game buttons.
	 * @param panelWidth the width of the GamePanel (same for both players'
	 * panels)
	 */
	public GameButtons(int panelWidth) {
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		buttonPanel = new JPanel(new GridLayout(1,5,2,0));
		
		// Create buttons
		newGameButton = new JButton("New Game");
		newGameButton.setAlignmentX(CENTER_ALIGNMENT);
		newGameButton.addActionListener(this);
		newGameButton.setToolTipText("Click to start a new game");
		newGameButton.setMnemonic(KeyEvent.VK_N);
		newGameButton.setBorder(GameWindow.BUTTON_BORDER);
		newGameButton.setFont(GameWindow.LABEL_FONT);
		newGameButton.setBackground(GameWindow.BUTTON_COLOR);
		newGameButton.setForeground(GameWindow.FOREGROUND_COLOR);
		
		instructionsButton = new JButton("Instructions");
		instructionsButton.setAlignmentX(CENTER_ALIGNMENT);
		instructionsButton.addActionListener(this);
		instructionsButton.setToolTipText("Click to see instructions");
		instructionsButton.setMnemonic(KeyEvent.VK_I);
		instructionsButton.setBorder(GameWindow.BUTTON_BORDER);
		instructionsButton.setFont(GameWindow.LABEL_FONT);
		instructionsButton.setBackground(GameWindow.BUTTON_COLOR);
		instructionsButton.setForeground(GameWindow.FOREGROUND_COLOR);
		
		highScoreButton = new JButton("High Score");
		highScoreButton.setAlignmentX(CENTER_ALIGNMENT);
		highScoreButton.addActionListener(this);
		highScoreButton.setToolTipText("Click to see the high score list");
		highScoreButton.setMnemonic(KeyEvent.VK_H);
		highScoreButton.setBorder(GameWindow.BUTTON_BORDER);
		highScoreButton.setFont(GameWindow.LABEL_FONT);
		highScoreButton.setBackground(GameWindow.BUTTON_COLOR);
		highScoreButton.setForeground(GameWindow.FOREGROUND_COLOR);
		
		aboutButton = new JButton("About");
		aboutButton.setAlignmentX(CENTER_ALIGNMENT);
		aboutButton.addActionListener(this);
		aboutButton.setToolTipText("Click to find out who made this "
				+ "awesome game");
		aboutButton.setMnemonic(KeyEvent.VK_A);
		aboutButton.setBorder(GameWindow.BUTTON_BORDER);
		aboutButton.setFont(GameWindow.LABEL_FONT);
		aboutButton.setBackground(GameWindow.BUTTON_COLOR);
		aboutButton.setForeground(GameWindow.FOREGROUND_COLOR);
		
		quitButton = new JButton("Quit");
		quitButton.setAlignmentX(CENTER_ALIGNMENT);
		quitButton.addActionListener(this);
		quitButton.setToolTipText("Click to exit game");
		quitButton.setMnemonic(KeyEvent.VK_Q);
		quitButton.setBorder(GameWindow.BUTTON_BORDER);
		quitButton.setFont(GameWindow.LABEL_FONT);
		quitButton.setBackground(GameWindow.BUTTON_COLOR);
		quitButton.setForeground(GameWindow.FOREGROUND_COLOR);
		
		settingsButton = new JButton("Settings");
		settingsButton.setAlignmentX(CENTER_ALIGNMENT);
		settingsButton.addActionListener(this);
		settingsButton.setToolTipText("Click to change the settings of the game");
		settingsButton.setMnemonic(KeyEvent.VK_S);
		settingsButton.setBorder(GameWindow.BUTTON_BORDER);
		settingsButton.setFont(GameWindow.LABEL_FONT);
		settingsButton.setBackground(GameWindow.BUTTON_COLOR);
		settingsButton.setForeground(GameWindow.FOREGROUND_COLOR);
		
		// Add buttons to panel
		buttonPanel.add(newGameButton);
		buttonPanel.add(instructionsButton);
		buttonPanel.add(highScoreButton);
		buttonPanel.add(aboutButton);
		buttonPanel.add(settingsButton);
		buttonPanel.add(quitButton);
		buttonPanel.setOpaque(false);

		// Set background and create rigid areas around the buttons, so that
		// they are centered below the labyrinth
		setBackground(GameWindow.BACKGROUND_COLOR);	
		add(Box.createRigidArea(new Dimension(panelWidth, 1)));
		add(buttonPanel);
		add(Box.createRigidArea(new Dimension(panelWidth, 1)));
		
		// Add enterListener to the buttons
		KeyListener enterListener = new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if ((e.getKeyCode() == KeyEvent.VK_ENTER) &&
						(e.getSource() instanceof AbstractButton)) {
					((AbstractButton) e.getSource()).doClick();	
				}
			}
		};
		newGameButton.addKeyListener(enterListener);
		instructionsButton.addKeyListener(enterListener);
		highScoreButton.addKeyListener(enterListener);
		aboutButton.addKeyListener(enterListener);
		settingsButton.addKeyListener(enterListener);
		quitButton.addKeyListener(enterListener);
	}
	
	public void updateForeground() {
		newGameButton.setBorder(GameWindow.BUTTON_BORDER);
		newGameButton.setForeground(GameWindow.FOREGROUND_COLOR);
		
		instructionsButton.setBorder(GameWindow.BUTTON_BORDER);
		instructionsButton.setForeground(GameWindow.FOREGROUND_COLOR);
		
		highScoreButton.setBorder(GameWindow.BUTTON_BORDER);
		highScoreButton.setForeground(GameWindow.FOREGROUND_COLOR);
		
		aboutButton.setBorder(GameWindow.BUTTON_BORDER);
		aboutButton.setForeground(GameWindow.FOREGROUND_COLOR);
		
		quitButton.setBorder(GameWindow.BUTTON_BORDER);
		quitButton.setForeground(GameWindow.FOREGROUND_COLOR);
		
		settingsButton.setBorder(GameWindow.BUTTON_BORDER);
		settingsButton.setForeground(GameWindow.FOREGROUND_COLOR);
		
	}
	
	public void updateBackground() {
//		buttonPanel.setBackground(GameWindow.BACKGROUND_COLOR);
		setBackground(GameWindow.BACKGROUND_COLOR);
	}
	
	public void updateButtonColor() {
		newGameButton.setBackground(GameWindow.BUTTON_COLOR);
		instructionsButton.setBackground(GameWindow.BUTTON_COLOR);
		highScoreButton.setBackground(GameWindow.BUTTON_COLOR);
		aboutButton.setBackground(GameWindow.BUTTON_COLOR);
		quitButton.setBackground(GameWindow.BUTTON_COLOR);
		settingsButton.setBackground(GameWindow.BUTTON_COLOR);
	}
	
	/**
	 * Handles button events.
	 * @param e actual ActionEvent
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == newGameButton) {
			((GameWindow)getTopLevelAncestor()).newGameRequested();
		}
		else if (e.getSource() == instructionsButton) {
			((GameWindow)getTopLevelAncestor()).newInstructionsWindow();
		}
		else if (e.getSource() == highScoreButton) {
			((GameWindow)getTopLevelAncestor()).newHighScoreWindow(
					                             HighScoreWindow.NOT_GAME_OVER);
		}
		else if (e.getSource() == aboutButton) {
			((GameWindow)getTopLevelAncestor()).newAboutWindow();
		}
		else if (e.getSource() == quitButton) {
			((GameWindow)getTopLevelAncestor()).quitGame();
		}
		else if (e.getSource() == settingsButton) {
			((GameWindow)getTopLevelAncestor()).newSettingsWindow(
												 HighScoreWindow.NOT_GAME_OVER);
		}
	}	
}
