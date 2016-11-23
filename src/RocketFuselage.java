import javax.swing.*;

/**
 * @author  Airaksinen, Tom & Wentzel, Jonatan
 *
 * The class for the Rocket Fuselages.
 *
 */
public class RocketFuselage extends RocketModule {
	
	/**
	 * Creates a Rocket Fuselage.
	 * 
	 * @param condition condition of the fuselage (NEW or OLD)
	 * @param moduleLocation location of the fuselage
	 */
	public RocketFuselage(int condition, GameLocation moduleLocation) {
		super(condition,
			  new ImageIcon("img/roc/Fuselage.gif"),
			  new ImageIcon("img/roc/Fuselage.png"),
			  moduleLocation);
		if (condition == RocketModule.OLD) {
			setGameAreaImage(new ImageIcon("img/roc/FuselageOld.gif"));
			setPanelImage    (new ImageIcon("img/roc/FuselageOld.png"));
			setModuleScore(50);
		}
	}
}