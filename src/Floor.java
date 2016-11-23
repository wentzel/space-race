import java.awt.*;

import javax.swing.*;

/**
 * @author Airaksinen, Tom & Wentzel, Jonatan
 *
 * The class for Floor tiles.
 * 
 */
public class Floor extends GameLocation {

	public static final int FLOOR_TYPE_1 = 1;
	public static final int FLOOR_TYPE_2 = 2;
	public static final int FLOOR_TYPE_3 = 3;
	public static final int FLOOR_TARGET_1 = -1;
	public static final int FLOOR_TARGET_2 = -2;
	
	private static ImageIcon floorType1Icon = 
		new ImageIcon("img/FloorTile.gif");
	private static ImageIcon floorType2Icon = 
		new ImageIcon("img/FloorTile_Broken.gif");
	private static ImageIcon floorType3Icon = 
		new ImageIcon("img/FloorTile_Broken2.gif");
	private static ImageIcon target1Icon = 
		new ImageIcon("img/FloorTileTarget.gif");
	private static ImageIcon target2Icon = 
		new ImageIcon("img/FloorTileTarget2.gif");
	
	private int type;
	
	/**
	 * Creates a Floor.
	 * 
	 * @param img image icon used for the floor
	 * @param x x-value for the position of the floor
	 * @param y y-value for the position of the floor
	 */
	public Floor(int type, int x, int y) {
		super(x, y);
		this.type = type;
	}
	
	public Floor(int x, int y) {
		super(x, y);
		type = FLOOR_TYPE_1;
	}
	public Floor() {
		super(0, 0);
		type = FLOOR_TYPE_1;
	}
	
	public void draw(Graphics g) {
		g.drawImage(getImage(),
				getMatrixPosition().x * GameLocation.getSideLength(),
				getMatrixPosition().y * GameLocation.getSideLength(),
				null);
	}
	
	public Image getImage() {
		if (type == FLOOR_TYPE_1) {
			return floorType1Icon.getImage();
		}
		else if (type == FLOOR_TYPE_2) {
			return floorType2Icon.getImage();
		}
		else if (type == FLOOR_TYPE_3) {
			return floorType3Icon.getImage();
		}
		else if (type == FLOOR_TARGET_1) {
			return target1Icon.getImage();
		}
		else if (type == FLOOR_TARGET_2) {
			return target2Icon.getImage();
		}
		else {
			return null;
		}
	}
								
	
	public static Icon getIcon(int type) {
		if (type == FLOOR_TYPE_1) {
			return floorType1Icon;
		}
		else if (type == FLOOR_TYPE_2) {
			return floorType2Icon;
		}
		else if (type == FLOOR_TYPE_3) {
			return floorType3Icon;
		}
		else if (type == FLOOR_TARGET_1) {
			return target1Icon;
		}
		else if (type == FLOOR_TARGET_2) {
			return target2Icon;
		}
		else {
			return null;
		}

	}
	
	public static void setIcon(int type, Icon icon) {
		if (type == FLOOR_TYPE_1) {
			floorType1Icon = (ImageIcon) icon;
		}
		else if (type == FLOOR_TYPE_2) {
			floorType2Icon = (ImageIcon) icon;
		}
		else if (type == FLOOR_TYPE_3) {
			floorType3Icon = (ImageIcon) icon;
		}
		else if (type == FLOOR_TARGET_1) {
			target1Icon = (ImageIcon) icon;
		}
		else if (type == FLOOR_TARGET_2) {
			target2Icon = (ImageIcon) icon;
		}
	}
}
