import java.awt.*;
import javax.swing.*;
import java.util.*;

/**
 * @author  Airaksinen, Tom & Wentzel, Jonatan
 *
 * The class for the Players.
 *
 */
public class Player extends GameActor {

	private String		 playerName;
	private int          playerNumber;
	private GameLocation targetLocation;   // The location of the player's base
	private LinkedList   retrievedObjects; // The so far collected parts of the
										   // space ship
	private GamePanel    panel;
	private int          score;
	private int			 launchedRockets;
	private int			 moveInitiatedCounter = 4;
	
	/**
	 * Creates a Player.
	 * 
	 * @param n player number
	 * @param up image icon displayed when the player is pointing upwards
	 * @param right image icon displayed when the player is pointing right
	 * @param down image icon displayed when the player is pointing downwards
	 * @param left image icon displayed when the player is pointing left
	 * @param location location of the player
	 * @param p panel of the player
	 */
	public Player(int          n,
			      ImageIcon    up,
			      ImageIcon    right,
			      ImageIcon    down,
			      ImageIcon    left,
			      GameLocation location,
				  GamePanel    p) {
		super(up, right, down, left, location, false);
		playerNumber = n;
		playerName = "Player " + playerNumber;
		targetLocation   = location;
		retrievedObjects = new LinkedList();
		panel = p;
	}

	/**
	 * Ticks the player.
	 */
	public void tick() {
		if (getNewMoveInitiated()) {
			moveInitiatedCounter -= 1;
			if (moveInitiatedCounter <= 0) {
				setNewMoveInitiated(false);
				moveInitiatedCounter = 4;
			}
		}
		else {
			updatePosition();
		}
	}
	
	/**
	 * Picks up or releases an object depending on whether the player carries
	 * anything at the moment or not.
	 */
	public void pickUpReleaseObject() {
		if (getCarriedObject() == null) {
			pickUpObject();
		}
		else {
			releaseObject();
		}
	}
	
	/**
	 * Picks up any object on the current location
	 */
	public void pickUpObject() {
		GameLocation location = getLocation();
		GameObject object = location.getObjectOnLocation();
		if (object != null) {
			setCarriedObject(object);
			panel.updateCarriedObjectLabel(object);
			location.removeObjectOnLocation(object);
		}
	}
	
	/**
	 * Releases any carried object as long as the current location does not
	 * contain another object. If the correct rocket module is released on the
	 * correct target location it will be added to the player's space ship.
	 */
	public void releaseObject() {
		GameObject obj = getCarriedObject();
		if (obj != null 
				&& (getLocation().getObjectOnLocation() == null)) {
			if (obj instanceof RocketModule
					&& getLocation() == targetLocation) {
				if (correctPart(obj)) {
					retrievedObjects.add(obj);
					obj.setLocation(null);
					increaseScore(50);
					if (panel.getRocketPanel().getRocketComplete()) {
						increaseScore(getRocketScore());
						increaseLaunchedRockets();
					}
				}
				else {
					Toolkit.getDefaultToolkit().beep();
					getLocation().setObjectOnLocation(obj);
					obj.setLocation(getLocation());
				}
				
			}
			else {
				// Ordinary release of object
				getLocation().setObjectOnLocation(obj);
				obj.setLocation(getLocation());
			}
			setCarriedObject(null);
			panel.updateCarriedObjectLabel(null);
		}
	}
	
	/**
	 * The player looses the carried object to a SuperSucker
	 */
	public void loseObjectToSucker() {
		setCarriedObject(null);
		panel.updateCarriedObjectLabel(null);
	}
	
	/**
	 * Returns whether the correct rocket module is added to the target
	 * location or not
	 * 
	 * @param obj object to be added to the space ship
	 * @return whether the correct rocket module is added to the target
	 * location or not
	 */
	public boolean correctPart(GameObject obj) {
		try {
			GameObject last = (GameObject)(retrievedObjects.getLast());
			if (last instanceof RocketNozzle
					&& obj instanceof RocketEngine) {
				panel.getRocketPanel().addEngine((RocketEngine)obj);
				return true;
			}
			else if (last instanceof RocketEngine
					&& obj instanceof RocketFuselage) {
				panel.getRocketPanel().addFuselage((RocketFuselage)obj);
				return true;
			}
			else if (last instanceof RocketFuselage
					&& obj instanceof RocketNoseCone) {
				panel.getRocketPanel().addNoseCone((RocketNoseCone)obj);
				return true;
			}
		}
		catch (NoSuchElementException ex) {
			// No parts are in the list, thus only the nozzles can be added
			if (obj instanceof RocketNozzle) {
				panel.getRocketPanel().addNozzle((RocketNozzle)obj);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns the parts from a launched rocket to random new locations
	 * in the labyrinth.
	 */
	public void returnRocketParts() {
		Iterator i = retrievedObjects.iterator();
		while (i.hasNext()) {
			RocketModule mod = ((RocketModule)i.next());
			GameLocation loc = (getMainWorld().getRandomFreeLocation());
			mod.setLocation(loc);
			loc.setObjectOnLocation(mod);
			i.remove();
		}
		panel.getRocketPanel().clear();
	}
	
	/**
	 * Updates the panel with the current status.
	 */
	public void updatePanel() {		
		panel.updateNameLabel(playerName);
		panel.updateScoreLabel(score);
		panel.updateLaunchedRocketsLabel(launchedRockets);
		panel.updateCarriedObjectLabel(getCarriedObject());
	}
	
	/**
	 * Increases the score.
	 * @param numPoints number of points with which the score should increase
	 */
	public void increaseScore(int numPoints) {
		score += numPoints;
		panel.updateScoreLabel(score);
	}
	
	/**
	 * Decreases the score.
	 * @param numPoints number of points with which the score should decrease
	 */
	public void decreaseScore(int numPoints) {
		score -= numPoints;
		panel.updateScoreLabel(score);
	}
	
	/**
	 * Increases the number of launched rockets by one.
	 */
	public void increaseLaunchedRockets() {
		launchedRockets += 1;
		panel.updateLaunchedRocketsLabel(launchedRockets);
	}
	
	// These are the same as for all game actors except that players can go
	// diagonal.
	/** 
	 * Makes the actor turn left.
	 * */
	public void goLeft() {
		setXSpeed(-getV());
		if ((getYSpeed() == 0) || getActorImage() == getRightImage()) {
			setCurrentImage(getLeftImage());
		}
	}
	
	/** 
	 * Makes the actor turn right.
	 */
	public void goRight() {
		setXSpeed(getV());
		if ((getYSpeed() == 0) || getActorImage() == getLeftImage()) {
			setCurrentImage(getRightImage());
		}
	}
	
	/**
	 * Makes the actor turn upwards.
	 */ 
	public void goUp() {
		setYSpeed(-getV());
		if ((getXSpeed() == 0) || getActorImage() == getDownImage()) {
			setCurrentImage(getUpImage());
		}
	}
	
	/** 
	 * Makes the actor turn downwards.
	 */
	public void goDown() {
		setYSpeed(getV());
		if ((getXSpeed() == 0) || getActorImage() == getUpImage()) {
			setCurrentImage(getDownImage());
		}
	}
	
	
	//	 ------------------- Get&Sets of relevant variables -------------------
	
	/**
	 * Returns the score.
	 * @return the score
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * Returns the total score the current rocket is worth.
	 * @return the total score the current rocket is worth
	 */
	public int getRocketScore() {
		int rocketScore = 0;
		Iterator i = retrievedObjects.iterator();
		while (i.hasNext()) {
			rocketScore += ((RocketModule)i.next()).getModuleScore();
		}
		return rocketScore;
	}
	
	/**
	 * Returns the number of launched rockets.
	 * @return the number of launched rockets
	 */
	public int getLaunchedRockets() {
		return launchedRockets;
	}
	
	/**
	 * Returns the target location.
	 * @return the target location
	 */
	public GameLocation getTargetLocation() {
		return targetLocation;
	}
	
	/**
	 * Returns the panel
	 * @return the panel
	 */
	public GamePanel getPanel() {
		return panel;
	}
	
	/**
	 * Sets the panel.
	 * @param p panel to be set
	 */
	public void setPanel(GamePanel p) {
		panel = p;
	}
	
	/**
	 * Returns the name.
	 * @return the name
	 */
	public String getPlayerName() {
		return playerName;
	}
	
	/**
	 * Sets the name
	 * @param name name to be set
	 */
	public void setPlayerName(String name) {
		if (name != null && !name.equals("")) {
			playerName = name;	
		}
		if (name.equals("Mario")) {
			System.out.println("M A R I O - M A N I A !");
			setActorImage(new ImageIcon("img/Mario.gif"));
		}
	}
}
