import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")

/**
 * Manages GameObjects and other operations in the game/gameplay
 *
 * @author Akash Jagdeesh
 * @author Arjun Jagdeesh
 */

public class GameManagement {

	/**
	 * The obstacle order number, used along with the tag to determine
	 * which obstacles should be in the current current level
	 */
	static int obstacleCounter = 1;

	/** ArrayList holding all the obstacles which will be drawn at some point */
	static ArrayList<GameObject> obstacles = new ArrayList<GameObject>() {{
		add(new Spikes(350.0, 520.0, 2.0, 2));   //2
		add(new Spikes(460.0, 520.0, 2.0, 3));  //3
		add(new Crusher(480.0, 50.0, 4));    //4
		add(new Crusher(100.0, 50.0, 4));
		add(new Spikes(100.0, 440.0, 2.0, 5));
		add(new Spikes(340.0, 360.0, 1.0, 6));
		add(new Spikes(450.0, 360.0, 1.0, 6));
		add(new Spikes(510.0, 440.0, 4.0, 4));
		add(new Spikes(20.0, 220.0, 1.0, 7));
	}};
	/** The ArrayList holding the obstacles that are present (being drawn) */
	static ArrayList<GameObject> wallPlatform = new ArrayList<GameObject>() {{
		add(new Platform(480.0, 460.0, 7.0, 3));  //3
		add(new Platform(20.0, 460.0, 14.0, 4));   //5
		add(new Platform(240.0, 380.0, 12.0, 4));   //5
		add(new Wall(480.0, 380.0, 4.0, 4));       //5
		add(new Wall(240.0, 340.0, 2.0, 5));      //8
		add(new Platform(200.0, 340.0, 3.0, 5));
		add(new Platform(20.0, 400.0, 4.0, 5));
		add(new Wall(480.0, 230.0, 8.0, 6));
		add(new Platform(180.0, 340.0, 3.0, 7));
		add(new Platform(420.0, 300.0, 3.0, 6));
		add(new Platform(20.0, 240.0, 17.0, 7));
		add(new Platform(100.0, 160.0, 20.0, 7));
		add(new Wall(480.0, 170.0, 3.0, 7));
	}};

	/** The ArrayList holding all the walls/platforms that will be drawn at some point */
	static ArrayList<GameObject> currentObstacles = new ArrayList<GameObject>();
	/** The ArrayList holding the walls/platforms that are present (being drawn) */
	static ArrayList<GameObject> currentWallPlatform = new ArrayList<GameObject>() {{
		add(new Wall(0.0, 0.0, 27.0, 1));
		add(new Wall(765.0, 0.0, 27.0, 1));
		add(new Platform(0.0, 540.0, 40.0, 1));
		add(new Platform(0.0, 50.0, 40.0, 1));
		add(new Wall(695.0, 0.0, 3.0, 1));
		add(new Wall(595.0, 65.0, 2.0, 1));
		add(new Platform(595.0, 105.0, 12.0, 1));
	}};

   /**
    * Resets the player on each failure or successful completion of the level.
    * If the player completes the level, a new obstacles is added,
    * and if the player fails, the player is simply reset, with another attempt added to the player's attempts
    *
    * @param player The player to change the attributes/position of
    */

	public static void restartLevel(Player player) {
		if (!player.alive) { player.attempts += 1; }
		else if (player.succeeded && obstacleCounter < 7) {//Scene.scene != "COMPLETED") {
			obstacleCounter += 1;
			addObstacle(obstacleCounter);
			addWallPlatform(obstacleCounter);
		} else {
			Scene.ranIntro_COMPLETED = false;
			Scene.scene = "COMPLETED";
		}
		player.alive = true;
		player.succeeded = false;
		player.box.x = 100;
		player.box.y = 500;
		player.jumping_time = 0;
		player.jump_height = 0;
	}

   /**
    * Adds an obstacle to the level. Used in the restartLevel method.
    * @param obstacleTag The "stage" the level is on, used to decide which and how many obstacles to add
    */

	public static void addObstacle(int obstacleTag) {
		for (int i = 0; i < obstacles.size(); i++) {
			if (obstacles.get(i).tag == obstacleTag) {
				currentObstacles.add(obstacles.get(i));
			}
		}
	}

   /**
    * Adds a wall and/or platform to the level. Used in the restartLevel method.
    *
    * @param obstacleTag The "stage" the level is on, used to decide which and how many walls and/or platforms to add
    */

	public static void addWallPlatform(int obstacleTag) {
		for (int i = 0; i < wallPlatform.size(); i++) {
			if (wallPlatform.get(i).tag == obstacleTag) {
				currentWallPlatform.add(wallPlatform.get(i));
			}
		}
	}

   /**
    * Draws the obstacles, walls, and platforms onto the screen
    *
    * @param screen The graphics panel to draw the sprites on
    */

	public static void displayGameObjects(Graphics screen) {
		for (GameObject i : currentObstacles) { i.animate(screen); }
		for (GameObject a : currentWallPlatform) { a.animate(screen); }
	}

   /**
    * Used to bind a key to an ActionListener, to provide keyboard interaction
    *
    * @param panel The JPanel that the key will be binded to
    * @param key The key specified by KeyEvent
    * @param actionName The name of the Action that will be put on the panel's InputMap/ActionMap
    * @param action The result or Action that will occur when the key is pressed
    * @param onRelease Whether the action should be performed on the release or on the press of the key
    */

	public static void addKeyBinding(JPanel panel, int key, String actionName, ActionListener action, boolean onRelease) {
		InputMap inputMap = panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap actionMap = panel.getActionMap();

		inputMap.put(KeyStroke.getKeyStroke(key, 0, onRelease), actionName);
		actionMap.put(actionName, new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				action.actionPerformed(e);
			}
		});
	}
}
