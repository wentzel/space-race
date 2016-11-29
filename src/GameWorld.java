import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
 * @author  Airaksinen, Tom & Wentzel, Jonatan
 *
 * The class for the world.
 *
 */
public class GameWorld {
	
	private GameLocation[][] worldGrid;
	private Dimension        worldSize;
	private String[]         stringWorld; // String representation of the world
										  // in order to provide easy editing
	
	private LinkedList<GameActor>       worldActors;
	private LinkedList<GameObject>       worldObjects;
	private LinkedList<Floor>       worldFloorTiles; // Used for random placement of
	               							  // objects and AI actors
	private Player           player1;
	private Player           player2;
	private GameLocation     player1StartingLocation;
	private GameLocation     player2StartingLocation;
	
	public static final int  WINNING_NUM_ROCKETS = 3; // Number of launches
													  // to win the game
	/**
	 * Creates a new Game World.
	 * 
	 * @param panel1 the panel for player 1
	 * @param panel2 the panel for player 2
	 */
	public GameWorld(GamePanel panel1, GamePanel panel2) {
		
		stringWorld = new String[] {
				// N.B. This is a representation of the left half of the
				// labyrinth. It will be mirrored automatically. 
				// Make sure that all rows in the matrix are of equal length!
				
				// 'X' = wall tile color 1
				// 'W' = wall tile color 2
				// ' ' = floor
				// '1' = player starting position
				
				"X X X X X X X X X X X",
				"X 1           X X X X",
				"X     X       X      ",
				"X   X X       X      ",
				"X   X X   X          ",
				"X         X     W W W",
				"X         X     W W W",
				"X         X     W W W",
				"X X     X X       W W",
				"X       X X         W",
				"X       X X X        ",
				"X X                  ",
				"X       X X X X   W W",
				"X       X         W W",
				"X     X X   X        ",
				"X     X X     X   W W",
				"X           X X   W W",
				"X X X     X X X      ",
				"X X X     X X X      ",
				"X                    ",
				"X X X X       X X X  ",
				"X X X X X X X X X X X" 
				
//				
//				"X X X X X X X X",
//				"X 1           X",
//				"X     X        ",
//				"X   X X        ",
//				"X X X X        ",
//				"X X X X X X X X" 
				
				
				};
		
		worldSize = new Dimension(
				(stringWorld[1].length() + 1), stringWorld.length);
		worldFloorTiles = new LinkedList<Floor>();	
		worldGrid = createLocationMatrix(stringWorld);
		
		// Create and add all players and AI actors
		player1 = new Player(1,
							 new ImageIcon("img/act/P1Up.gif"),
							 new ImageIcon("img/act/P1Right.gif"),
							 new ImageIcon("img/act/P1Down.gif"),
							 new ImageIcon("img/act/P1Left.gif"),
							 player1StartingLocation,
							 panel1);
		
		player2 = new Player(2,
							 new ImageIcon("img/act/P2Up.gif"),
							 new ImageIcon("img/act/P2Right.gif"),
							 new ImageIcon("img/act/P2Down.gif"),
							 new ImageIcon("img/act/P2Left.gif"),
							 player2StartingLocation,
							 panel2);
		
		worldActors = new LinkedList<GameActor>();
		worldActors.add(new SuperSucker(getRandomFreeLocation()));
		worldActors.add(new Ghost(getRandomFreeLocation()));
		worldActors.add(new Ghost(getRandomFreeLocation()));
		worldActors.add(player1);
		worldActors.add(player2);
		
		
		// Create and add all world objects: rocket parts and dust
		worldObjects = new LinkedList<GameObject>();
//		worldObjects.add(new RocketEngine(RocketModule.OLD, getLocation(2, 2)));
//		worldObjects.add(new RocketNozzle(RocketModule.OLD, getLocation(1, 3)));
//		worldObjects.add(new RocketFuselage(RocketModule.OLD, getLocation(2, 1)));
//		worldObjects.add(new RocketNoseCone(RocketModule.OLD, getLocation(1, 2)));
//		
//		worldObjects.add(new RocketEngine(RocketModule.NEW, getLocation(3, 1)));
//		worldObjects.add(new RocketNozzle(RocketModule.NEW, getLocation(5, 1)));
//		worldObjects.add(new RocketFuselage(RocketModule.NEW, getLocation(4, 1)));
//		worldObjects.add(new RocketNoseCone(RocketModule.NEW, getLocation(4, 2)));
		
		worldObjects.add(new RocketEngine(RocketModule.NEW,
										  getRandomFreeLocation()));
		worldObjects.add(new RocketNozzle(RocketModule.NEW,
										  getRandomFreeLocation()));
		worldObjects.add(new RocketFuselage(RocketModule.NEW,
											getRandomFreeLocation()));
		worldObjects.add(new RocketNoseCone(RocketModule.NEW,
											getRandomFreeLocation()));

		worldObjects.add(new RocketEngine(RocketModule.OLD,
										  getRandomFreeLocation()));
		worldObjects.add(new RocketNozzle(RocketModule.OLD,
										  getRandomFreeLocation()));
		worldObjects.add(new RocketFuselage(RocketModule.OLD,
											getRandomFreeLocation()));
		worldObjects.add(new RocketNoseCone(RocketModule.OLD,
											getRandomFreeLocation()));
		createNewDust();
		
		GameActor.setMainWorld(this);
	}
	
	/**
	 * Converts our string representation of the (left half of the) world into
	 * a symmetric matrix of GameLocations.
	 * 
	 * @param stringWorld string representation of left half of the world
	 * @return a symmetric matrix of GameLocations
	 */
	public GameLocation[][] createLocationMatrix(String[] stringWorld) {
		GameLocation[][] locationMatrix =
			new GameLocation[getHeight()][getWidth()];		
		
		// Delete every other character (should be spaces).
		for (int i = 0, h = stringWorld.length; i < h; i++) {
			String newString = "";
			for (int j = 0; j < stringWorld[i].length(); j += 2) {
				newString += stringWorld[i].charAt(j);
			}
			stringWorld[i] = newString;
		}

		int h = (int) getHeight();
		int w = (int) getWidth();
		int l = stringWorld[1].length();
		
		for (int y = 0 ; y < h; y++) {
			for (int x = 0; x < l; x++) {
				char locationType = stringWorld[y].charAt(x);
				if (locationType != ',') {
					if (locationType == 'X') {
						locationMatrix[y][x] =
							new Wall(Wall.WALL_TYPE_1, x, y);
						locationMatrix[y][w - x - 1] =
							new Wall(Wall.WALL_TYPE_1, w-x-1, y);
					}
					else if (locationType == 'W') {
						locationMatrix[y][x] = 
							new Wall(Wall.WALL_TYPE_2, x, y);
						locationMatrix[y][w - x - 1] =
							new Wall(Wall.WALL_TYPE_2, w-x-1, y);
					}
					else if (locationType == '1') {
						Floor floor =
							new Floor(Floor.FLOOR_TARGET_1, x, y);
						locationMatrix[y][x] = floor;
						worldFloorTiles.add(floor);
						player1StartingLocation = floor;
						
						floor =
							new Floor(Floor.FLOOR_TARGET_2, w-x-1, y);
						locationMatrix[y][w-x-1] = floor;
						worldFloorTiles.add(floor);
						player2StartingLocation = floor;
					}
					else {
						Floor floor;
						if (Math.random() < 0.8) {
							floor = new Floor(Floor.FLOOR_TYPE_1, x, y);
						}
						else if (Math.random() <0.5) {
							floor = new Floor(Floor.FLOOR_TYPE_2, x, y);
						}
						else {
							floor = new Floor(Floor.FLOOR_TYPE_3, x, y);
						}
						locationMatrix[y][x] = floor;
						worldFloorTiles.add(floor);
						
						if (Math.random() < 0.8) {
							floor = new Floor(Floor.FLOOR_TYPE_1, w-x-1, y);
						}
						else if (Math.random() <0.5) {
							floor = new Floor(Floor.FLOOR_TYPE_2, w-x-1, y);
						}
						else {
							floor = new Floor(Floor.FLOOR_TYPE_2, w-x-1, y);
						}
						locationMatrix[y][w-x-1] = floor;
						worldFloorTiles.add(floor);
					}
				}
			}
		}
		return locationMatrix;
	}
	
	public void updateIcons() {
		
	}
	
	/**
	 * Ticks the world. The method passes the tick() call along to all
	 * actors. If there is a rocket launching, the method also calls
	 * changeRocketClearing to update the vertical position of the rocket.
	 */
	public void tick() {
		Iterator<GameActor> i =  worldActors.iterator();
		while(i.hasNext()) {
			((GameActor) i.next()).tick();
		}
		player1.getPanel().getRocketPanel().repaint();
		player2.getPanel().getRocketPanel().repaint();

		if (player1.getPanel().getRocketPanel().getLaunchFinished()) {
			if (player1.getLaunchedRockets() == WINNING_NUM_ROCKETS) {
				((GameWindow)player1.getPanel().getTopLevelAncestor())
				.gameOver();
			}
			player1.returnRocketParts();
			player1.getPanel().getRocketPanel().setLaunchFinished(false);
		}
		if (player2.getPanel().getRocketPanel().getLaunchFinished()) {
			if (player2.getLaunchedRockets() == WINNING_NUM_ROCKETS) {
				((GameWindow)player2.getPanel().getTopLevelAncestor())
				.gameOver();
			}
			player2.returnRocketParts();
			player2.getPanel().getRocketPanel().setLaunchFinished(false);
		}
	}
	
	/**	
	 * Creates new dust on a random free location in the labyrinth.
	 */
	public void createNewDust() {
		worldObjects.add(new Dust(getRandomFreeLocation()));
	}
	
	
	//	 ------------------- Get&Sets of relevant variables -------------------
	
	/**
	 * Returns the height of the world.
	 * @return number of rows in the world matrix
	 */
	public int getHeight() {
		return (int) worldSize.getHeight();
	}
	
	/**
	 * Returns the width of the world.
	 * @return number of columns in the world matrix
	 */	
	public int getWidth() {
		return (int) worldSize.getWidth();
	}
	
	/**
	 * Returns all actors in the world.
	 * @return all actors in the world
	 */
	public LinkedList<GameActor> getWorldActors() {
		return worldActors;
	}
	
	/**
	 * Returns all objects in the world.
	 * @return all objects in the world
	 */
	public LinkedList<GameObject> getWorldObjects() {
		return worldObjects;
	}
	
	/**
	 * Returns the GameLocation in a specified matrix position.
	 * @param x the x coordinate of the desired location
	 * @param y the y coordinate of the desired location
	 * @return the GameLocation in a specified matrix position
	 */
	public GameLocation getLocation(int x, int y) {
		return worldGrid[y][x];
	}
	
	/**
	 * Returns a random item from a specified list.
	 * @param l the list to process
	 * @return a random list item
	 */
	public Object getRandomItem(LinkedList<?> l) {
		return l.get((int) (Math.random() * l.size()));
	}
	
	/**
	 * Returns a random floor tile where there are neither objects nor actors.
	 * @return a random floor tile where there are neither objects nor actors
	 */
	public GameLocation getRandomFreeLocation() {
		GameLocation freeFloorTile = null;
		boolean done = false;
		while (!done) {
			freeFloorTile = (GameLocation)getRandomItem(worldFloorTiles);
			if (freeFloorTile.getObjectOnLocation() == null
					&& freeFloorTile.getActorsOnLocation().isEmpty()) {
				done = true;
			}
		}
		return freeFloorTile;
	}
	
	/**
	 * Returns the specified player.
	 * @param i the number of the player to be returned
	 * @return player i
	 */
	public Player getPlayer(int i) {
		if (i == 1) {
			return player1;
		}
		else {
			return player2;
		}
	}
	
	/**
	 * Returns the location an actor is on his way to.
	 * 
	 * @param actor actual actor
	 * @return new actor location
	 */
	public GameLocation getNewLocation(GameActor actor) {
		int x = actor.getCoordinates().x;
		int y = actor.getCoordinates().y;		
		x = x + actor.getXSpeed();
		y = y + actor.getYSpeed();
		return getLocation(x, y);
	}
}

