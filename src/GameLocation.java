import java.awt.*;
//import javax.swing.*;
import java.util.*;

/**
 * @author  Airaksinen, Tom & Wentzel, Jonatan
 *
 * The super class for all locations in the game.
 *
 */
public class GameLocation {
	
	private        Point      matrixPosition = new Point(1, 1);

	private        GameObject objectOnLocation;
	private        LinkedList actorsOnLocation;
	private static int        sideLength = 32;

	/**
	 * Creates a new Game Location.
	 * 
	 * @param image image for the location (floor, broken floor, wall...)
	 * @param x the x coordinate in the world matrix
	 * @param y the y coordinate in the world matrix
	 */
	public GameLocation(int x, int y) {
		matrixPosition.x = x;
		matrixPosition.y = y;
		actorsOnLocation = new LinkedList();
	}
	
	public void draw(Graphics g) {
//		g.drawImage(locationImage.getImage(),
//				matrixPosition.x * GameLocation.getSideLength(),
//				matrixPosition.y * GameLocation.getSideLength(),
//				null);
		;
	}
	
	
	//	 ------------------- Get&Sets of relevant variables -------------------
	
//	/**
//	 * Returns the location image.
//	 * @return the location image
//	 */
//	public ImageIcon getLocationImage() {
//		return locationImage;
//	}
//	
//	/**
//	 * Sets the location image.
//	 * @param img location image
//	 */
//	public void setLocationImage(ImageIcon img) {
//		locationImage = img;
//	}
	
	/**
	 * Returns the side length of a location square.
	 * @return the side length of a location square
	 */
	public static int getSideLength() {
		return sideLength;
	}
	
	/**
	 * Returns the location's position in the world matrix.
	 * @return the location's position in the world matrix
	 */
	public Point getMatrixPosition() {
		return matrixPosition;
	}
	
	/**
	 * Returns all actors on the location.
	 * @return all actors on the location
	 */
	public LinkedList getActorsOnLocation() {
		return actorsOnLocation;
	}
	
	/**
	 * Adds an actor to the location.
	 * @param actor actual actor
	 */
	public void addActorOnLocation(GameActor actor) {
		actorsOnLocation.add(actor);
	}
	
	/**
	 * Removes an actor from the location.
	 * @param actor actual actor
	 */
	public void removeActorOnLocation(GameActor actor) {
		actorsOnLocation.remove(actor);
	}
	
	/**
	 * Returns the object on the location.
	 * @return the object on the location
	 */
	public GameObject getObjectOnLocation() {
		return objectOnLocation;
	}
	
	/**
	 * Sets the object on the location.
	 * @param obj the object on the location
	 */
	public void setObjectOnLocation(GameObject obj) {
		objectOnLocation = obj;
	}
	
	/**
	 * Removes an object from the location.
	 * @param obj actual object
	 */
	public void removeObjectOnLocation(GameObject obj) {
		objectOnLocation = null;
		obj.setLocation(null);
	}
}
