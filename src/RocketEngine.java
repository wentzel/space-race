import javax.swing.*;

/**
 * @author  Airaksinen, Tom & Wentzel, Jonatan
 *
 * The class for the Rocket Engines.
 *
 */
public class RocketEngine extends RocketModule {
	
	/**
	 * Creates a Rocket Engine.
	 * 
	 * @param condition condition of the engine (NEW or OLD)
	 * @param moduleLocation location of the engine
	 */
	public RocketEngine(int condition, GameLocation moduleLocation) {
		super(condition,
			  new ImageIcon("img/roc/Engine.gif"),
			  new ImageIcon("img/roc/Engine.png"),
			  moduleLocation);
		if (condition == RocketModule.OLD) {
			setGameAreaImage(new ImageIcon("img/roc/EngineOld.gif"));
			setPanelImage    (new ImageIcon("img/roc/EngineOld.png"));
			setModuleScore(50);
		}
	}
}
