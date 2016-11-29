import java.awt.*;

import javax.swing.*;

/**
 * @author  Airaksinen, Tom & Wentzel, Jonatan
 *
 * The class for the workshop. This is the part of the game panel that displays
 * the assembled rocket parts of a player.
 *
 */
public class GameWorkshop extends JComponent {
	
	private static final long serialVersionUID = 1L;
	
	private        JPanel  rocketPanel;
	private        JLabel  noseCone;
	private        JLabel  fuselage;
	private        JLabel  nozzles;
	private        JLabel  flames;

	private        boolean rocketComplete;
	private        boolean launchFinished;
	public  static int     totalRocketHeight;
	
    private float  xposX = 0;    // Used for calculating rocket position
    private double yposX = 0.0;  // Used for calculating rocket position
//	private float xVelocity = 1f;   // Used for calculating opacity change speed
	
	/**
	 * Creates a new workshop.
	 */
	public GameWorkshop() {
		
		//Create all rocket part placeholders
		noseCone = new JLabel();
		noseCone.setAlignmentX(CENTER_ALIGNMENT);
		noseCone.setAlignmentY(BOTTOM_ALIGNMENT);
		
		fuselage = new JLabel();
		fuselage.setAlignmentX(CENTER_ALIGNMENT);
		fuselage.setAlignmentY(BOTTOM_ALIGNMENT);
		
		nozzles = new JLabel(new ImageIcon("img/roc/NozzleContour.png"));
		nozzles.setAlignmentX(CENTER_ALIGNMENT);
		nozzles.setAlignmentY(BOTTOM_ALIGNMENT);
				
		flames = new JLabel(new ImageIcon(""));
		flames.setAlignmentX(CENTER_ALIGNMENT);
		flames.setAlignmentY(BOTTOM_ALIGNMENT);
		
		// Add placeholders to workshop
		rocketPanel = new JPanel();
		rocketPanel.setBackground(GameWindow.BACKGROUND_COLOR);
		rocketPanel.setLayout(new BoxLayout(rocketPanel, BoxLayout.Y_AXIS));
		rocketPanel.add(noseCone);
		rocketPanel.add(fuselage);
		rocketPanel.add(nozzles);
		rocketPanel.add(flames);
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(Box.createVerticalGlue());
		add(rocketPanel);
		
		totalRocketHeight = new ImageIcon("img/roc/NoseCone.png")
								.getIconHeight()
		   				  + new ImageIcon("img/roc/Fuselage.png")
						  		.getIconHeight()
						  + new ImageIcon("img/roc/Nozzle.png")
						  		.getIconHeight()
						  + new ImageIcon("img/roc/Flames.png")
						  		.getIconHeight();
	}
	
	/**
	 * Adds a new or old nozzle to the workshop.
	 * @param part nozzle to be added
	 */
	public void addNozzle(RocketNozzle part) {
		nozzles.setIcon(part.getPanelImage());
		fuselage.setIcon(new ImageIcon("img/roc/EngineContour.png"));
	}
	
	/**
	 * Adds a new or old engine to the workshop.
	 * @param part engine to be added
	 */
	public void addEngine(RocketEngine part) {
		if (part.getCondition() == RocketModule.NEW) {
			fuselage.setIcon(new ImageIcon("img/roc/FuselageContour.png"));
		}
		else {
			fuselage.setIcon(new ImageIcon("img/roc/FuselageContourOld.png"));
		}	
	}
	
	/**
	 * Adds a new or old fuselage to the workshop.
	 * @param part fuselage to be added
	 */
	public void addFuselage(RocketFuselage part) {
		fuselage.setIcon(part.getPanelImage());
		noseCone.setIcon(new ImageIcon("img/roc/NoseConeContour.png"));
	}
	
	/**
	 * Adds a new or old nose cone to the workshop.
	 * @param part nose cone to be added
	 */
	public void addNoseCone(RocketNoseCone part) {
		noseCone.setIcon(part.getPanelImage());
		rocketComplete = true;
	}
	
	/** 
	 * Clears the workshop.
	 */
	public void clearWorkshop() {
		yposX = 0.0;
		xposX = 0;
		noseCone.setIcon(new ImageIcon(""));
		fuselage.setIcon(new ImageIcon(""));
		nozzles.setIcon(new ImageIcon("img/roc/NozzleContour.png"));
		flames.setIcon(new ImageIcon(""));
		revalidate();
	}
	
	public void animateRocket() {
		addFlames(); // dumt att detta g�rs varje tick
		rocketPanel.setLocation(getNewRocketLocation());
		if (rocketPanel.getLocation().y < (0 - totalRocketHeight)) {
			launchFinished = true;
			rocketComplete = false;
			clearWorkshop();
		}
	}
	
	public Point getNewRocketLocation() {
	    int xpos = (int) (10f * (float) Math.sin(xposX) + 0.3f);
	    xposX += 1;
	    
	    
	    // N�gonstans h�r m�ste h�jden f�r Flames.png dras av
	    // - new ImageIcon("img/roc/Flames.png").getIconHeight()
	    int ypos = rocketPanel.getLocation().y;
	    ypos = ypos - (int) (Math.exp(yposX) - Math.exp(1));
	    yposX += 0.1;
	    return new Point(xpos, ypos);
	}
	
	public void addFlames() {
		flames.setIcon(new ImageIcon("img/roc/Flames.png"));
	}
	
	public void removeFlames() {
		flames.setIcon(new ImageIcon(""));
	}

	
	//	 ------------------- Get&Sets of relevant variables -------------------
	
	public boolean getLaunchFinished() {
		return launchFinished;
	}
	
	public void setLaunchFinished(boolean b) {
		launchFinished = b;
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
}
