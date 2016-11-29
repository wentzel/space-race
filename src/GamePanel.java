import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author  Airaksinen, Tom & Wentzel, Jonatan
 *
 * The class for the Game Panels, which contains status information for a
 * player.
 *
 */
public class GamePanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private RocketPanel  rocketPanel;
	private JLabel		 carriedObjectLabel;
	private JPanel       statusPanel;
	private JLabel       scoreLabel;
	private JLabel       launchedRocketsLabel;
	private JLabel		 nameLabel;
	
	/**
	 * Creates a new Game Panel.
	 */
	public GamePanel() {
		
		// Initialize the label for carried object
		carriedObjectLabel = new JLabel("Now carrying:",
										new ImageIcon(""),
										JLabel.CENTER);
		carriedObjectLabel.setVerticalTextPosition(JLabel.TOP);
		carriedObjectLabel.setVerticalAlignment(JLabel.TOP);
		carriedObjectLabel.setHorizontalTextPosition(JLabel.CENTER);
		carriedObjectLabel.setAlignmentX(CENTER_ALIGNMENT);
		carriedObjectLabel.setForeground(GameWindow.FOREGROUND_COLOR);
		carriedObjectLabel.setPreferredSize(new Dimension(300, 70));
		carriedObjectLabel.setBorder(new LineBorder(
				GameWindow.FOREGROUND_COLOR, 3, true));		
		carriedObjectLabel.setFont(GameWindow.LABEL_FONT);
		
		// Initialize the label for the rocket panel
		rocketPanel = new RocketPanel();
		
		// Initialize the status panel
		scoreLabel = new JLabel("Score: 0");
		scoreLabel.setHorizontalAlignment(JLabel.CENTER);
		scoreLabel.setForeground(GameWindow.FOREGROUND_COLOR);
		scoreLabel.setFont(GameWindow.LABEL_FONT);
		
		launchedRocketsLabel = new JLabel("Launched Rockets: 0");
		launchedRocketsLabel.setHorizontalAlignment(JLabel.CENTER);
		launchedRocketsLabel.setForeground(GameWindow.FOREGROUND_COLOR);
		launchedRocketsLabel.setFont(GameWindow.LABEL_FONT);
		
		nameLabel = new JLabel();
		nameLabel.setHorizontalAlignment(JLabel.CENTER);
		nameLabel.setForeground(GameWindow.FOREGROUND_COLOR);
		nameLabel.setFont(GameWindow.LABEL_FONT);
		
		statusPanel = new JPanel(new GridLayout(3,1));
		statusPanel.setOpaque(false);
		statusPanel.add(scoreLabel);
		statusPanel.add(launchedRocketsLabel);
		statusPanel.add(nameLabel);
		
		// Add the labels
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(135, 200));
		setBackground(GameWindow.BACKGROUND_COLOR);
		
		add(carriedObjectLabel, BorderLayout.NORTH);
		add(rocketPanel, BorderLayout.CENTER);
		add(statusPanel, BorderLayout.SOUTH);
	}
	
	/**
	 * Resets the workshop. Used when a new game is initiated.
	 */
	public void newWorkshop() {
		rocketPanel.clear();
	}
	
	public void updateForeground() {
		carriedObjectLabel.setForeground(GameWindow.FOREGROUND_COLOR);
		carriedObjectLabel.setBorder(new LineBorder(
				GameWindow.FOREGROUND_COLOR, 3, true));
		scoreLabel.setForeground(GameWindow.FOREGROUND_COLOR);
		launchedRocketsLabel.setForeground(GameWindow.FOREGROUND_COLOR);
		nameLabel.setForeground(GameWindow.FOREGROUND_COLOR);
	}
	
	public void updateBackground() {
//		statusPanel.setBackground(GameWindow.BACKGROUND_COLOR);
		setBackground(GameWindow.BACKGROUND_COLOR);
	}
	
	/**
	 * Updates the name label.
	 * @param name new name
	 */
	public void updateNameLabel(String name) {
		nameLabel.setText(name);
	}

	/**
	 * Updates the score label.
	 * @param score new score
	 */
	public void updateScoreLabel(int score) {
		scoreLabel.setText("Score: " + score);
	}
	
	/**
	 * Updates the launched rockets label.
	 * @param launchedRockets new number of launched rockets
	 */
	public void updateLaunchedRocketsLabel(int launchedRockets) {
		launchedRocketsLabel.setText("Launched Rockets: " + launchedRockets);
	}
	
	/**
	 * Updates the carried object label.
	 * @param carriedObject the new carried object
	 */
	public void updateCarriedObjectLabel(GameObject carriedObject) {
		GameObject obj = carriedObject;
		if (obj == null) {
			carriedObjectLabel.setIcon(new ImageIcon(""));
		}
		else {
			carriedObjectLabel.setIcon(obj.getGameAreaImage());
		}
	}
	
	
	//	 ------------------- Get&Sets of relevant variables -------------------
	
	/**
	 * Returns the workshop associated with the game panel.
	 * @return the workshop associated with the game panel
	 */
	public RocketPanel getRocketPanel() {
		return rocketPanel;
	}
}
