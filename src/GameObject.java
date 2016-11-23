import java.awt.*;
import javax.swing.*;

/**
 * @author  Airaksinen, Tom & Wentzel, Jonatan
 *
 * The super class for all objects in the game.
 *
 */
public class GameObject {
	private ImageIcon    gameAreaImage;
	private ImageIcon    panelImage;
	private GameLocation location;
	
	
	/**
	 * Creates a new Game Object.
	 * 
	 * @param gAImage small image of the object, for drawing in the game area
	 * @param pImage bigger image of the object, for drawing in the panel
	 * @param objectLocation the location where the object should be created
	 */
	public GameObject(ImageIcon gAImage,
					  ImageIcon pImage,
					  GameLocation objectLocation) {
		gameAreaImage = gAImage;
		panelImage     = pImage;
		location       = objectLocation;
		location.setObjectOnLocation(this);
	}
	
	/**
	 * Draws the game object.
	 * @param g graphics with which the object will be drawn
	 */
	public void draw(Graphics g) {
		if (location != null) {
			g.drawImage(gameAreaImage.getImage(),
				location.getMatrixPosition().x * GameLocation.getSideLength(),
				location.getMatrixPosition().y * GameLocation.getSideLength(),
				null);
		}
	}

	
//	 ------------------- Get&Sets of relevant variables -------------------
	
	/**
	 * Returns the small image used for drawing in the game area.
	 * @return the small image used for drawing in the game area
	 */
	public ImageIcon getGameAreaImage() {
		return gameAreaImage;
	}
	
	/**
	 * Sets the small image used for drawing in the game area.
	 * @param i small image used for drawing in the game area
	 */
	public void setGameAreaImage(ImageIcon i) {
		gameAreaImage = i;
	}
	
	/**
	 * Returns the big image used for drawing in the panel.
	 * @return the big image used for drawing in the panel
	 */
	public ImageIcon getPanelImage() {
		return panelImage;
	}
	
	/**
	 * Sets the big image used for drawing in the panel.
	 * @param i big image used for drawing in the panel
	 */
	public void setPanelImage(ImageIcon i) {
		panelImage = i;
	}
	
	/**
	 * Returns the matrix coordinates for the object.
	 * @return the matrix coordinates for the object
	 */
	public Point getCoordinates() {
		return location.getMatrixPosition();
	}
	
	/**
	 * Returns the location where the object is.
	 * @return the location where the object is
	 */
	public GameLocation getLocation() {
		return location;
	}
	
	/**
	 * Sets the location of the object
	 * @param loc location of the object
	 */
	public void setLocation(GameLocation loc) {
		location = loc;
	}
}
