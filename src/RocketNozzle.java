import javax.swing.*;

/**
 * @author  Airaksinen, Tom & Wentzel, Jonatan
 *
 * The class for the Rocket Nozzles.
 *
 */
public class RocketNozzle extends RocketModule {
	
	/**
	 * Creates a Rocket Nozzle.
	 * 
	 * @param condition condition of the nozzles (NEW or OLD)
	 * @param moduleLocation location of the nozzles
	 */
	public RocketNozzle(int condition, GameLocation moduleLocation) {
		super(condition,
		      new ImageIcon("img/roc/Nozzle.gif"),
			  new ImageIcon("img/roc/Nozzle.png"),
			  moduleLocation);
		if (condition == RocketModule.OLD) {
			setGameAreaImage(new ImageIcon("img/roc/NozzleOld.gif"));
			setPanelImage    (new ImageIcon("img/roc/NozzleOld.png"));
			setModuleScore(50);
		}
	}
}
