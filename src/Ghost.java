import java.awt.*;
import javax.swing.*;

/**
 * @author  Airaksinen, Tom & Wentzel, Jonatan
 *
 * The class for the Ghosts. They float around randomly in the labyrinth. Their
 * opacity varies, and can be passed by players when they are transparent.
 *
 */
public class Ghost extends GameActor {
	
	private float opacity;
    private float opacityX;     // Used for calculating opacity with sinus fn
	private float opVelocity;   // Used for calculating opacity change speed
	private int   numTicks;     // Used for controlling ghost movement speed
	private int   moveVelocity; // Used for controlling ghost movement speed
	
	
	/**
	 * Creates a new Ghost.
	 * @param location location of the ghost
	 */
	public Ghost(GameLocation location) {
		super(new ImageIcon("img/act/Ghost.gif"), location, true);
		opacity = 0.0f;
		opacityX = (float) (Math.random() * 2 * Math.PI);
		opVelocity = 0.2f;
		numTicks = (int)(Math.random() * 10);
		moveVelocity = (int)(Math.random() * 20 + 1);
	}
	
	/**
	 * Draws the ghost.
	 * @param g graphics with which the ghost will be drawn
	 */
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		
		Composite c = g2d.getComposite();
		Composite alphaComp = AlphaComposite.getInstance(
				AlphaComposite.SRC_OVER, opacity);
		g2d.setComposite(alphaComp);
		
		g2d.drawImage(getActorImage().getImage(),
				getCoordinates().x * GameLocation.getSideLength(),
				getCoordinates().y * GameLocation.getSideLength(),
				null);
		g2d.setComposite(c);
	}
	
	/**
	 * Ticks the ghost. Updates the opacity and moves the ghost if necessary.
	 * (The Ghost will move every time numTicks exceeds its moveVelocity.)
	 */
	public void tick() {
		updateOpacity();
		numTicks++;
		if (numTicks > moveVelocity) {
			move();
			numTicks = 0;
		}
	}
	
	/**
	 * Moves the ghost by initiating a move in random direction.
	 */
	public void move() {
		int direction = (int) (Math.random() * 4);
		if (direction == 0) {
			goLeft();
		}
		else if (direction == 1) {
			goRight();
		}
		else if (direction == 2) {
			goUp();
		}
		else if (direction == 3) {
			goDown();
		}
		updatePosition();
	}
	
	/**
	 * Handles collisions between the ghost and another actor (possibly ghost).
	 * @param collidingActor the actor with whom the ghost is colliding
	 */
	public void handleCollision(GameActor collidingActor) {
		if (collidingActor instanceof Player) {
			((Player)collidingActor).decreaseScore(100);
		}
	}
	
	/**
	 * Updates the ghost's opacity according to a Nobel prize-winning sinus
	 * algorithm. If necessary, it also changes the "passable" value.
	 *
	 */
	public void updateOpacity() {
	    opacity = 0.2f * (float) Math.sin(opacityX) + 0.3f;
	    opacityX += opVelocity;
	    if (opacity < 0.35f && !getPassable()) {
	    	setPassable(true);
	    }
	    else if (opacity >= 0.35f && getPassable()) {
	    	setPassable(false);
	    }
	}
	
	
	//	 ------------------- Get&Sets of relevant variables -------------------
	
	/**
	 * Returns the opacity of the ghost.
	 * @return the opacity of the ghost
	 */
	public float getOpacity() {
		return opacity;
	}
	
	/**
	 * Sets the opacity of the ghost.
	 * @param o opacity of the ghost
	 */
	public void setOpacity(float o) {
		opacity = o;
	}
}
