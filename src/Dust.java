import javax.swing.*;

/**
 * @author Airaksinen, Tom & Wentzel, Jonatan
 *
 * The class for Dust. This is the type of object that the SuperSucker is
 * looking for.
 * 
 */
public class Dust extends GameObject {
	
	/**
	 * Creates a Dust object.
	 * @param location location on which the dust will be created
	 */
	public Dust(GameLocation location) {
		super(new ImageIcon("img/Dust.gif"),
			  new ImageIcon("img/Dust.gif"),
			  location);
	}
}
