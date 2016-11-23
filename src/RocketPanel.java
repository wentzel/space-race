import java.awt.*;

import javax.swing.*;

/**
 * @author Jonatan
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RocketPanel extends JPanel{
	
	private RocketModule noseCone;
	private RocketModule fuselage;
	private RocketModule engine;
	private RocketModule nozzle;
	private boolean      flames;
	
	private int     noseConeHeight;
	private int     fuselageHeight;
	private int     engineHeight;
	private int     nozzleHeight;
	private int     flamesHeight;
	
	
	private ImageIcon nozzleContour;
	private ImageIcon engineContour;
	private ImageIcon fuselageContour;
	private ImageIcon noseConeContour;
	private ImageIcon flamesImage;
	
	private        boolean rocketComplete;
	private        boolean launchFinished;
	
    private float  xposX = -5;    // Used for calculating rocket position
    private double yposX = 0.0;  // Used for calculating rocket position
	
	public RocketPanel() {
		setOpaque(false);
		setBackground(GameWindow.BACKGROUND_COLOR);
		
		ImageIcon rocketModulePicture = new ImageIcon("img/roc/NoseCone.png");
		noseConeHeight = rocketModulePicture.getIconHeight();
		rocketModulePicture = new ImageIcon("img/roc/Fuselage.png");
		fuselageHeight = rocketModulePicture.getIconHeight();
		rocketModulePicture = new ImageIcon("img/roc/EngineOld.png");
		engineHeight = rocketModulePicture.getIconHeight();
		rocketModulePicture = new ImageIcon("img/roc/Nozzle.png");
		nozzleHeight = rocketModulePicture.getIconHeight();
		rocketModulePicture = new ImageIcon("img/roc/Flames.png");
		flamesHeight = rocketModulePicture.getIconHeight();
		
		flamesImage = new ImageIcon("img/roc/Flames.png");

		nozzleContour = new ImageIcon("img/roc/NozzleContour.png");
		engineContour = new ImageIcon("img/roc/engineContour.png");
		fuselageContour = new ImageIcon("img/roc/FuselageContour.png");
		noseConeContour = new ImageIcon("img/roc/NoseConeContour.png");
		
	}
	
	public void paintComponent(Graphics g) {
		Point pos = getNewRocketLocation();
		if (nozzle != null) {
			g.drawImage(nozzle.getPanelImage().getImage(),
						pos.x,
						pos.y - nozzleHeight,
						null);
			if (fuselage != null) {
				g.drawImage(fuselage.getPanelImage().getImage(),
						pos.x,
						pos.y - nozzleHeight - fuselageHeight,
						null);
				if (noseCone != null) {
					g.drawImage(noseCone.getPanelImage().getImage(),
							pos.x,
							pos.y - nozzleHeight - fuselageHeight
								  - noseConeHeight,
							null);
					if (flames) {
						g.drawImage(flamesImage.getImage(),
								pos.x,
								pos.y,
								null);
					}
				}
				else {
					g.drawImage(noseConeContour.getImage(),
							pos.x,
							pos.y - nozzleHeight - fuselageHeight
								  - noseConeHeight,
							null);
				}
			}
			else if (engine != null) {
				g.drawImage(engine.getPanelImage().getImage(),
						pos.x,
						pos.y - nozzleHeight - engineHeight,
						null);
				g.drawImage(fuselageContour.getImage(),
						pos.x,
						pos.y - nozzleHeight - fuselageHeight,
						null);
			}
			else {
				g.drawImage(engineContour.getImage(),
						pos.x,
						pos.y - nozzleHeight - engineHeight,
						null);
			}
		}
		else {
			g.drawImage(nozzleContour.getImage(),
					pos.x,
					pos.y - nozzleHeight,
					null);
		}
		
	}
	
	public Point getNewRocketLocation() {
		if (rocketComplete) {
			int xpos = (int) (6f * (float) Math.sin(Math.exp(0 - xposX)) + 0.3f);
			xposX += 0.02;
			
			int ypos = getHeight();
			ypos = ypos - (int) (Math.exp(yposX - 4) - 7);
			yposX += 0.2;
			
			if (ypos < 0) {
				launchFinished();
			}
			return new Point(xpos, ypos);
		}
		else {
			return new Point(0, getHeight());
		}
	}
	
	public void launchFinished() {
		launchFinished = true;
		rocketComplete = false;
		xposX = -5;
		yposX = 0.0;
		clear();
	}
	
	public void addFlames() {
		flames = true;
	}
	
	public void addNozzle(RocketModule part) {
		nozzle = part;
	}
	
	public void addEngine(RocketModule part) {
		engine = part;
	}
	
	public void addFuselage(RocketModule part) {
		fuselage = part;
	}
	
	public void addNoseCone(RocketModule part) {
		noseCone = part;
		rocketComplete = true;
		addFlames();
	}
	
	public void clear() {
		removeFlames();
		removeNozzle();
		removeEngine();
		removeFuselage();
		removeNoseCone();
	}
	
	public void removeFlames() {
		flames = false;
	}
	
	public void removeNozzle() {
		nozzle = null;
	}
	
	public void removeEngine() {
		engine = null;
	}
	
	public void removeFuselage() {
		fuselage = null;
	}
	
	public void removeNoseCone() {
		noseCone = null;
	}
	
	/**
	 * Returns the rocket complete status.
	 * @return the rocket complete status
	 */
	public boolean getRocketComplete() {
		return rocketComplete;
	}
	
	/**
	 * Sets the rocket complete status.
	 * @param b rocket completet status
	 */
	public void setRocketComplete(boolean b) {
		rocketComplete = b;
	}
	
	public boolean getLaunchFinished() {
		return launchFinished;
	}
	
	public void setLaunchFinished(boolean b) {
		launchFinished = b;
	}
	
}
