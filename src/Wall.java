import java.awt.*;

import javax.swing.*;

/**
 * @author Airaksinen, Tom & Wentzel, Jonatan
 *
 * The class for Wall tiles.
 * 
 */
public class Wall extends GameLocation {

	public static final int WALL_TYPE_1 = 1;
	public static final int WALL_TYPE_2 = 2;
	
	private static ImageIcon floorType1Icon = 
		new ImageIcon("img/WallTile.gif");
	private static ImageIcon floorType2Icon = 
		new ImageIcon("img/WallTile2.gif");
	
	private int type;
	
	/**
	 * Creates a Wall.
	 * 
	 * @param img image icon used for the floor
	 * @param x x-value for the position of the floor
	 * @param y y-value for the position of the floor
	 */
	public Wall(int type, int x, int y) {
		super(x, y);
		this.type = type;
	}
	
	public Wall(int x, int y) {
		super(x, y);
		type = WALL_TYPE_1;
	}
	public Wall() {
		super(0, 0);
		type = WALL_TYPE_1;
	}
	
	public void draw(Graphics g) {
		g.drawImage(getImage(),
				getMatrixPosition().x * GameLocation.getSideLength(),
				getMatrixPosition().y * GameLocation.getSideLength(),
				null);
	}
	
	public Image getImage() {
		if (type == WALL_TYPE_1) {
			return floorType1Icon.getImage();
		}
		else if (type == WALL_TYPE_2) {
			return floorType2Icon.getImage();
		}
		else {
			return null;
		}
	}
								
	
	public static Icon getIcon(int type) {
		if (type == WALL_TYPE_1) {
			return floorType1Icon;
		}
		else if (type == WALL_TYPE_2) {
			return floorType2Icon;
		}
		else {
			return null;
		}

	}
	
	public static void setIcon(int type, Icon icon) {
		if (type == WALL_TYPE_1) {
			floorType1Icon = (ImageIcon) icon;
		}
		else if (type == WALL_TYPE_2) {
			floorType2Icon = (ImageIcon) icon;
		}
	}
}

