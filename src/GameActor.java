import java.awt.*;
import javax.swing.*;
import java.util.*;

/**
 * @author  Airaksinen, Tom & Wentzel, Jonatan
 *
 * The super class for all characters in the game, players as well as AI actors.
 * 
 */
public class GameActor {
	
	private        ImageIcon    currentImage;
	private        ImageIcon    upImage;
	private        ImageIcon    rightImage;
	private        ImageIcon    downImage;
	private        ImageIcon    leftImage;
	private        GameLocation location;
	//private        Point   	    coordinates;
	private        int 	        xSpeed, ySpeed, v;
	private        boolean      newMoveInitiated = false;
	private        boolean      passable;
	private        GameObject   carriedObject;
	private static GameWorld    mainWorld;
	
	/**
	 * Creates a Game Actor.
	 * 
	 * @param up image icon displayed when the actor is pointing upwards
	 * @param right image icon displayed when the actor is pointing right
	 * @param down image icon displayed when the actor is pointing downwards
	 * @param left image icon displayed when the actor is pointing left
	 * @param actorLocation location of the actor
	 * @param passable tells if another actor can pass this actor
	 */
	public GameActor(ImageIcon    up,
			         ImageIcon    right,
			         ImageIcon    down,
			         ImageIcon    left,
			         GameLocation actorLocation,
					 boolean      passable) {
		
		upImage       = up;
		rightImage    = right;
		downImage     = down;
		leftImage     = left;
		currentImage  = right;	
		location      = actorLocation;
		this.passable = passable;
		location.addActorOnLocation(this);
		//coordinates = new Point(actorLocation.getMatrixPosition().x,
		//						actorLocation.getMatrixPosition().y);
		v = 1;
	}
	
	/**
	 * Creates a game actor.
	 * 
	 * @param actorImage image icon that will be used for the actor no matter
	 * which direction it is pointing
	 * @param actorLocation location of the actor
	 * @param passable tells if another actor can pass this actor
	 */
	public GameActor(ImageIcon    actorImage,
			         GameLocation actorLocation,
					 boolean      passable) {
		currentImage = actorImage;
		upImage      = actorImage;
		rightImage   = actorImage;
		downImage    = actorImage;
		leftImage    = actorImage;
		location     = actorLocation;
		location.addActorOnLocation(this);
		//coordinates = new Point(actorLocation.getMatrixPosition().x,
		//		actorLocation.getMatrixPosition().y);
		v = 1;
		this.passable = passable;
	}
	
	/**
	 * Ticks the actor. This method will most likely need to be overridden by
	 * the sub class of the actor because this method does not do anything.
	 */
	public void tick() {
		;
	}
	
	/**
	 * Draws the actor.
	 * @param g graphics with which the actor will be drawn
	 */
	public void draw(Graphics g) {
		g.drawImage(currentImage.getImage(),
					getCoordinates().x * GameLocation.getSideLength(),
					getCoordinates().y * GameLocation.getSideLength(),
					null);
	}
	
	/**
	 * Updates the position of the actor. The actor will move only if it has
	 * a speed towards any direction and it is legal to move to the next 
	 * location in that direction.
	 */
	public void updatePosition() {
		if (getXSpeed() != 0 || getYSpeed() != 0) {
			GameLocation newLocation =
				mainWorld.getNewLocation(this);
			if (isLegalMove(newLocation)) {
				LinkedList<GameActor> actorsOnLocation = newLocation.getActorsOnLocation();
				if (!actorsOnLocation.isEmpty()) {
					for (int i = 0, l = actorsOnLocation.size(); i < l; i++) {
						GameActor actor = (GameActor) actorsOnLocation.get(i);
						// On collision both the colliding actors effects
						// each other
						this.handleCollision(actor);
						actor.handleCollision(this);
					}
				}
				moveTo(newLocation);
			}
		}
	}
	
	/**
	 * Checks whether it is legal to move to the given location or not. A move
	 * is legal if the location is a floor and if either the actor or all other
	 * actors on the location are passable.
	 * 
	 * @param newLocation location which is to be checked
	 * @return true if the location is a floor and if either the actor or all
	 * other actors on the location are passable
	 */
	public boolean isLegalMove(GameLocation newLocation) {
		if (newLocation instanceof Floor) {
			LinkedList<GameActor> actorsOnLocation = newLocation.getActorsOnLocation();
			if (!actorsOnLocation.isEmpty()) {
				for (int i = 0, l = actorsOnLocation.size(); i < l; i++) {
					GameActor actor = (GameActor) actorsOnLocation.get(i);
					if (!(this.passable || actor.passable)) {
						return false;
					}
				}
				return true;
			}
			else {
				return true;
			}
		}
		else {
			return false;
		}
	}
	
	/**
	 * Tells what the given actor will do when it collides.
	 * @param collidingActor actor whith which the caller of the method is
	 * colliding
	 */
	public void handleCollision(GameActor collidingActor) {
		;
	}
	
	/**
	 * Moves the  actor to the given location.
	 * @param newLocation location to which the actor will be moved
	 */
	public void moveTo(GameLocation newLocation) {
		newLocation.addActorOnLocation(this);
		location.removeActorOnLocation(this);
		//setCoordinates(newLocation.getMatrixPosition());
		location = newLocation;
	}
	
	/** 
	 * Makes the actor turn left.
	 * */
	public void goLeft() {
		xSpeed       = -v;
		ySpeed       = 0;
		currentImage = leftImage;
	}
	
	/** 
	 * Makes the actor turn right.
	 */
	public void goRight() {
		xSpeed       = v;
		ySpeed       = 0;
		currentImage = rightImage;
	}
	
	/**
	 * Makes the actor turn upwards.
	 */ 
	public void goUp() {
		xSpeed       = 0;
		ySpeed       = -v;
		currentImage = upImage;
	}
	
	/** 
	 * Makes the actor turn downwards.
	 */
	public void goDown() {
		xSpeed       = 0;
		ySpeed       = v;
		currentImage = downImage;
	}
	
	/**
	 * Makes the actor quit go left.
	 */
	public void quitGoLeft() {
		xSpeed = 0;
	}
	
	/**
	 * Makes the actor quit go right.
	 */
	public void quitGoRight() {
		xSpeed = 0;
	}
	
	/**
	 * Makes the actor quit go upwnwards.
	 */
	public void quitGoUp() {
		ySpeed = 0;
	}
	
	/**
	 * Makes the actor quit go downwards.
	 */
	public void quitGoDown() {
		ySpeed = 0;
	}
	
	/** 
	 * Makes the actor stop.
	 */
	public void stop() {
		xSpeed = 0;
		ySpeed = 0;
	}
	
	
	// ------------------- Get&Sets of relevant variables -------------------
	
	/**
	 * Returns the image of the actor.
	 * @return the image of the actor
	 */
	public ImageIcon getActorImage() {
		return currentImage;
	}
	
	/**
	 * Returns the image of the actor when facing left.
	 * @return the image of the actor
	 */
	public ImageIcon getLeftImage() {
		return leftImage;
	}
	
	/**
	 * Returns the image of the actor when facing right.
	 * @return the image of the actor when facing right
	 */
	public ImageIcon getRightImage() {
		return rightImage;
	}
	
	/**
	 * Returns the image of the actor when facing upwards.
	 * @return the image of the actor when facing upwards
	 */
	public ImageIcon getUpImage() {
		return upImage;
	}
	
	/**
	 * Returns the image of the actor when facing downwards.
	 * @return the image of the actor when facing downwards
	 */
	public ImageIcon getDownImage() {
		return downImage;
	}
	
	/**
	 * Sets the current actor image to the specified image.
	 * @param img image
	 */
	public void setCurrentImage(ImageIcon img) {
		currentImage = img;
	}
	
	/**
	 * Sets actor image (all directions) to the specified image.
	 * @param img image
	 */
	public void setActorImage(ImageIcon img) {
		upImage      = img;
		rightImage   = img;
		downImage    = img;
		leftImage    = img;
		currentImage = img;	
	}
	
	/**
	 * Returns the speed in the x-direction of the actor.
	 * @return the speed in the x-direction of the actor
	 */
	public int getXSpeed() {
		return xSpeed;
	}
	
	/**
	 * Returns the speed in the y-direction of the actor.
	 * @return the speed in the y-direction of the actor
	 */
	public int getYSpeed() {
		return ySpeed;
	}
	
	/**
	 * Sets the speed in the x-direction of the actor.
	 * @param xs speed in the x-direction of the actor
	 */
	public void setXSpeed(int xs) {
		xSpeed = xs;
	}
	
	/**
	 * Sets the speed in the y-direction of the actor.
	 * @param ys speed in the y-direction of the actor
	 */
	public void setYSpeed(int ys) {
		ySpeed = ys;
	}
	
	/**
	 * Returns the velocity of the actor.
	 * @return the velocity of the actor
	 */
	public int getV() {
		return v;
	}
	
	/**
	 * Sets the velocity of the actor.
	 * @param newV velocity
	 */
	public void setV(int newV) {
		v = newV;
	}
	
	/**
	 * Returns the coordinates of the position of the actor.
	 * @return the coordinates of the position of the actor
	 */
	public Point getCoordinates() {
		return location.getMatrixPosition();
	}
	
	/**
	 * Sets the Coordinates of the position of the actor.
	 * @param p coordinates of the position of the actor
	 */
	//public void setCoordinates(Point p) {
	//	coordinates = p;
	//}
	
	/**
	 * Returns the location of the actor.
	 * @return the location of the actor
	 */
	public GameLocation getLocation() {
		return location;
	}
	
	/**
	 * Returns whether the actor recently initiated a new move or not. This
	 * method is needed in order to avoid jumps over two locations.
	 * @return true if a new move was recently initiated
	 */
	public boolean getNewMoveInitiated() {
		return newMoveInitiated;
	}
	
	/**
	 * Sets the variable newMoveInitiated.
	 * @param b whether a new move recently was initiated or not
	 */
	public void setNewMoveInitiated(boolean b) {
		newMoveInitiated = b;
	}
	
	/**
	 * Whether the actor is passable or not.
	 * @return true if the actor is passable
	 */
	public boolean getPassable() {
		return passable;
	}
	
	/**
	 * Sets the variable passable.
	 * @param b wheter the actor is passable or not
	 */
	public void setPassable(boolean b) {
		passable = b;
	}
	
	/**
	 * Returns the carried object, if any.
	 * @return the carried object
	 */
	public GameObject getCarriedObject() {
		return carriedObject;
	}
	
	/**
	 * Sets the carried object.
	 * @param obj carried object
	 */
	public void setCarriedObject(GameObject obj) {
		carriedObject = obj;
	}
	
	/**
	 * Returns the main world which all actors are placed inside.
	 * @return the main world
	 */
	public static GameWorld getMainWorld() {
		return mainWorld;
	}

	/**
	 * Sets the main world which all actors are placed inside.
	 * @param world main world which all actors are placed inside
	 */
	public static void setMainWorld(GameWorld world) {
		mainWorld = world;
	}
}
