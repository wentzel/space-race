import javax.swing.*;

/**
 * @author  Airaksinen, Tom & Wentzel, Jonatan
 *
 * The super class for all Rocket Modules.
 *
 */
public class RocketModule extends GameObject{
    
	public static final int OLD = 0;
	public static final int NEW = 1;
    private int condition;
    private int moduleScore = 300;

	/**
	 * Creates a Rocket Module.
	 * 
	 * @param condition condition of the module (NEW or OLD)
	 * @param gameAreaIcon icon to be used in the game area
	 * @param panelIcon icon to be used in the panel
	 * @param moduleLocation location of the module
	 */
	public RocketModule(int condition,
						ImageIcon gameAreaIcon,
						ImageIcon panelIcon,
						GameLocation moduleLocation) {
		super(gameAreaIcon, panelIcon, moduleLocation);
		this.condition = condition;
	}
	
	
	//	 ------------------- Get&Sets of relevant variables -------------------
	
	/**
	 * Returns the condition of the rocket module.
	 * @return the condition of the rocket module (0 = OLD, 1 = NEW)
	 */
	public int getCondition() {
		return condition;
	}
	
	/**
	 * Sets the condition of the rocket module.
	 * @param cond 0 = OLD, 1 = NEW
	 */
	public void setCondition(int cond) {
		condition = cond;
	}
	
	/**
	 * Returns the score the module will add to the space ship.
	 * @return the score the module will add to the space ship
	 */
	public int getModuleScore() {
		return moduleScore;
	}
	
	/**
	 * Sets the score the module will add to the space ship
	 * @param score score the module will add to the space ship
	 */
	public void setModuleScore(int score) {
		moduleScore = score;
	}
}
