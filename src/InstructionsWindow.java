import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author Airaksinen, Tom & Wentzel, Jonatan
 *
 * The class for the Instructions Window.
 * 
 */
public class InstructionsWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	GameWindow mainWindow;
	
	/**
	 * Creates a new Instructions Window.
	 * 
	 * @param w Game Window which will be paused and
	 * over which the Instructions Window will be centered 
	 */
	public InstructionsWindow(GameWindow w) {
		
		mainWindow = w;
	
		// Add the image to the window
		ImageIcon icon = new ImageIcon("img/Instructions.png");		
		JLabel aboutPicture = new JLabel(icon);
		Container c = getContentPane();
		c.add(aboutPicture);
		pack();
		
		// Calculate size and position of the window
		Insets insets = getInsets();
		int x, y, instrWindowWidth, instrWindowHeight;
		instrWindowWidth = icon.getIconWidth() + insets.left + insets.right;
		instrWindowHeight = icon.getIconHeight() + insets.top + insets.bottom;
		Dimension mainWindowSize = mainWindow.getSize();
		Point mainWindowLocation = mainWindow.getLocation();
		x = mainWindowLocation.x
				+ ((mainWindowSize.width / 2)  - (instrWindowWidth / 2));
		y = mainWindowLocation.y
				+ ((mainWindowSize.height / 2) - (instrWindowHeight / 2));
			
		// Format the window
		setLocation(x, y);
		setTitle("SpaceRace Instructions");		
		setSize(instrWindowWidth, instrWindowHeight);
		setResizable(false);
//		setUndecorated(true);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		addWindowListener(wl);
	}

	private WindowListener wl = new WindowAdapter() {
		public void windowClosed(WindowEvent e) {
			mainWindow.resumeGame();
			mainWindow.getGameArea().requestFocus();
		}
	};
}
