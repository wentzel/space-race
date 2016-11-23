import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * @author Airaksinen, Tom & Wentzel, Jonatan
 *
 * The class for the About Window.
 * 
 */
public class AboutWindow extends JFrame {
	
	GameWindow mainWindow;
	
	/**
	 * Creates a new About Window.
	 * @param w Game Window which will be paused and
	 * over which the About Window will be centered 
	 */
	public AboutWindow(GameWindow w) {
		
		mainWindow = w;
	
		// Add the image to the window
		ImageIcon icon = new ImageIcon("img/About.gif");		
		JLabel aboutPicture = new JLabel(icon);
		Container c = getContentPane();
		c.add(aboutPicture);
		
		// Calculate size and position of the window
		Insets insets = getInsets();
		int x, y, aboutWindowWidth, aboutWindowHeight;
		aboutWindowWidth = icon.getIconWidth() + insets.left + insets.right;
		aboutWindowHeight = icon.getIconHeight() + insets.top + insets.bottom;
		Dimension mainWindowSize = mainWindow.getSize();
		Point mainWindowLocation = mainWindow.getLocation();
		x = mainWindowLocation.x
				+ ((mainWindowSize.width / 2)  - (aboutWindowWidth / 2));
		y = mainWindowLocation.y
				+ ((mainWindowSize.height / 2) - (aboutWindowHeight / 2));
			
		// Format the window
		setLocation(x, y);
		setTitle("About SpaceRace");		
		setSize(aboutWindowWidth, aboutWindowHeight);
		setResizable(false);
//		setUndecorated(true);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		addWindowListener(wl);		
	}

	private WindowListener wl =	 new WindowAdapter() {
		public void windowClosed(WindowEvent e) {
			mainWindow.resumeGame();
			mainWindow.getGameArea().requestFocus();
		}
	};
}
