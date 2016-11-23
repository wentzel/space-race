import javax.swing.*;

/**
 * @author  Airaksinen, Tom & Wentzel, Jonatan
 *
 * The class for the Rocket Nose Cones.
 *
 */
public class RocketNoseCone extends RocketModule{
	
	/**
	 * Creates a Rocket Nose Cone.
	 * 
	 * @param condition condition of the nose cone (NEW or OLD)
	 * @param moduleLocation location of the nose cone
	 */
	public RocketNoseCone(int condition, GameLocation moduleLocation) {
		super(condition,
			  new ImageIcon("img/roc/NoseCone.gif"),
			  new ImageIcon("img/roc/NoseCone.png"),
			  moduleLocation);
		if (condition == RocketModule.OLD) {
			setGameAreaImage(new ImageIcon("img/roc/NoseConeOld.gif"));
			setPanelImage    (new ImageIcon("img/roc/NoseConeOld.png"));
			setModuleScore(50);
		}
	}
}
