import java.awt.*;
import javax.swing.*;
import java.util.*;

/**
 * @author  Airaksinen, Tom & Wentzel, Jonatan
 *
 * The class for the Super Sucker. This is the famous AI search agent, always
 * looking for dust to suck up. Unfortunately, its video sensors is not too well
 * calibrated, so it devours any object that lies in its way. If it collides
 * with a player, it will suck up any object that he or she is carrying as well.
 * 
 * "Nothing sucks like an Electrolux!"
 *
 */
public class SuperSucker extends GameActor {
	
	private int        numTicks;
	private int        moveVelocity;
	private HashSet    visitedLocations;
	private Stack      locationsToVisit;
	private Stack      travelledPath;
	
	/**
	 * Creates a SuperSucker2005+.
	 * @param location starting location of the SuperSucker
	 */
	public SuperSucker(GameLocation location) {
		super(new ImageIcon("img/act/SuperSucker.gif"), location, true);
		numTicks           = (int)(Math.random() * 10);
		moveVelocity       = 2;
		visitedLocations   = new HashSet();
		locationsToVisit = new Stack();
		travelledPath      = new Stack();
	}
	
	/**
	 * Ticks the SuperSucker. The SuperSucker will move every time the numTicks
	 * exceeds its moveVelocity.
	 */
	public void tick() {
		numTicks++;
		if (numTicks > moveVelocity) {
			move();
			numTicks = 0;
		}
	}
	
	/**
	 * Draws the SuperSucker.
	 * @param g graphics with which the SuperSucker will be drawn
	 */
	public void draw(Graphics g) {
		g.drawImage(getActorImage().getImage(),
				getCoordinates().x * GameLocation.getSideLength(),
				getCoordinates().y * GameLocation.getSideLength(),
				null);
	}

	/**
	 * Tells what the given SuperSucker will do when it collides. If it
	 * collides with a Player the SuperSucker tries to suck any object that the
	 * Player might be carrying. If it collides with a Ghost nothing happens.
	 * @param collidingActor actor whith which the SuperSucker is colliding
	 */
	public void handleCollision(GameActor collidingActor) {
		if (collidingActor instanceof Player) {
			GameObject object = ((Player)collidingActor).getCarriedObject();
			if (object != null) {
				suck(object, (Player) collidingActor);
			}
		}
	}
	
	/**
	 * Makes the SuperSucker suck. If the object is dust the SuperSucker will
	 * be reset, and a new piece of dust will be generated in the world.
	 * 
	 * @param obj object the SuperSucker tries to suck
	 * @param player player the SuperSucker tries to clean (null if it cleans
	 * the ground and no player is present)
	 */
	public void suck(GameObject obj, Player player) {
		if (obj != null) {
			if (obj instanceof Dust) {
				removeObjectFromLocation(obj, player);
				if (player != null) {
					player.increaseScore(150);
				}
				reset();
				getMainWorld().createNewDust();
			}
			else if (getCarriedObject() == null) {
				removeObjectFromLocation(obj, player);
				setCarriedObject(obj);
			}
		}
	}
	
	/**
	 * Removes an object from the location it is placed or from the player that
	 * carries it
	 * @param obj object the SuperSucker tries to suck
	 * @param player player the SuperSucker tries to clean (null if it cleans
	 * the ground and no player is present)
	 */
	public void removeObjectFromLocation(GameObject obj, Player player) {
		if (obj.getLocation() != null) {
			getLocation().removeObjectOnLocation(obj);
		}
		else {
			player.loseObjectToSucker();
		}
	}
	
	/**
	 * Moves the SuperSucker to a new location if needed.
	 */
	public void move() {
		if (!visitedLocations.contains(getLocation())){
				visitedLocations.add(getLocation());
		}
		travelledPath.push(getLocation());
		addChildren();
		int direction = getNextDirection();
		if (direction == 1) {
			goUp();
		}
		else if (direction == 2) {
			goRight();
		}
		else if (direction == 3) {
			goDown();
		}
		else if (direction == 4) {
			goLeft();
		}
		
		// If the SuperSucker carries an object, should it be released and put
		// back into the labyrinth?
		if (getCarriedObject() != null) {
			if ((Math.random() > 0.92) 
					&& getLocation().getObjectOnLocation() == null) {
				releaseCarriedObject();
			}
		}
		
		updatePosition();
		suck(getLocation().getObjectOnLocation(), null);
	}
	
	/**
	 * Returns the direction the SuperSucker should move.
	 * @return 1 = up, 2 = right, 3 = down, 4 = left, 0 = stop
	 */
	public int getNextDirection() {
		try {
			GameLocation nextLoc = (GameLocation) locationsToVisit.pop();
			if (getCityBlockDistance(nextLoc) > 1) {
				// Next location to visit was too far away. Put it back to
				// locationsToVisit and go back in the travelledPath instead
				locationsToVisit.push(nextLoc);
				
				try {
					travelledPath.pop();
					return getDirection((GameLocation)travelledPath.pop());
				}
				catch (EmptyStackException ex) {
					// If for some mysterious reason the travelled path is empty
					// even though there is a location to visit the SuperSucker
					// is reset
					reset();
					return 0;
				}
			}
			else {
				return getDirection(nextLoc);
			}
		}
		catch (EmptyStackException ex) {
			// If there are no more locations to visit the SuperSucker is reset
			// and starts its search for the radioactive dust once more
			reset();
			return 0;
		}
	}
	
	/**
	 * Approximates the distance to the destination location.
	 * 
	 * @param destinationLocation destination location
	 * @return an approximate of the distance to the destination location
	 */
	public int getCityBlockDistance(GameLocation destinationLocation) {
		Point diff = getCoordinateDifference(destinationLocation);
		int xDistance = Math.abs(diff.x);
		int yDistance = Math.abs(diff.y);
		return xDistance + yDistance;
	}
	
	/**
	 * Returns a point with the difference in x-value and y-value between the
	 * SuperSucker's location and the destination location.
	 * 
	 * @param destinationLocation destination location
	 * @return a point with the difference in x-value and y-value between the
	 * SuperSucker's location and the destination location
	 */
	public Point getCoordinateDifference(GameLocation destinationLocation) {
		int xDifference= getLocation().getMatrixPosition().x
						- destinationLocation.getMatrixPosition().x;
		int yDifference = getLocation().getMatrixPosition().y
						- destinationLocation.getMatrixPosition().y;
		return (new Point(xDifference, yDifference));
	}
	
	/**
	 * Returns the direction the SuperSucker should go in order to reach the
	 * destination location.
	 * @param destinationLocation destination location
	 * @return the direction the SuperSucker should go in order to reach the
	 * destination location
	 */
	public int getDirection(GameLocation destinationLocation) {
		Point diff = getCoordinateDifference(destinationLocation);
		if (diff.y == 1) {
			return 1; // destination is above the current postition -> up
		}
		else if (diff.x == - 1) {
			return 2; // destination is right of the current postition -> right
		}
		else if (diff.y == - 1) {
			return 3; // destination is below the current postition -> down
		}
		else if (diff.x == 1) {
			return 4; // destination is left of the current postition -> left
		}
		else {
			return 0; // the destination location is not adjacent to the
			          // SuperSucker's location
		}
	}
	
	/**
	 * Adds all adjacent floors to the list of locations to visit. The locations
	 * are not added if they are already visited or if they are already on the
	 * list of locations to visit.
	 */
	public void addChildren() {
		Point position      = getLocation().getMatrixPosition();
		GameWorld mainWorld = getMainWorld();
		GameLocation north  = mainWorld.getLocation(position.x, position.y - 1);
		GameLocation east   = mainWorld.getLocation(position.x + 1, position.y);
		GameLocation south  = mainWorld.getLocation(position.x, position.y + 1);
		GameLocation west   = mainWorld.getLocation(position.x - 1, position.y);
		
		if (north instanceof Floor
				&& !(visitedLocations.contains(north))
				&& !(locationsToVisit.contains(north))) {
			locationsToVisit.push(north);
		}
		if (east instanceof Floor
				&& !(visitedLocations.contains(east))
				&& !(locationsToVisit.contains(east))) {
			locationsToVisit.push(east);
		}
		if (south instanceof Floor
				&& !(visitedLocations.contains(south))
				&& !(locationsToVisit.contains(south))) {
			locationsToVisit.push(south);
		}
		if (west instanceof Floor
				&& !(visitedLocations.contains(west))
				&& !(locationsToVisit.contains(west))) {
			locationsToVisit.push(west);
		}
	}
	
	/**
	 * Resets the SuperSucker.
	 */
	public void reset() {
		travelledPath.clear();
		locationsToVisit.clear();
		visitedLocations.clear();
		releaseCarriedObject();
	}
	
	/**
	 * Makes the SuperSucker spit out any object currently in its interior. 
	 */
	public void releaseCarriedObject() {
		if (getCarriedObject() != null) {
			GameObject obj = getCarriedObject();
			getLocation().setObjectOnLocation(obj);
			obj.setLocation(getLocation());
			setCarriedObject(null);
		}
	}
}
