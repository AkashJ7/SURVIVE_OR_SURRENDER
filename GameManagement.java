import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")

public class GameManagement {

	static int obstacleCounter = 1;

	static ArrayList<GameObject> obstacles = new ArrayList<GameObject>() {{
		add(new Spikes(330.0, 510.0, 2.0, 2));   //2
		add(new Spikes(460.0, 510.0, 2.0, 3));  //3
		add(new Crusher(480.0, 50.0, 4));    //4
		add(new Crusher(100.0, 50.0, 4));
	}}; // list with all obstacles

	static ArrayList<GameObject> wallPlatform = new ArrayList<GameObject>() {{
		add(new Platform(480.0, 460.0, 10.0, 3));  //3
		add(new Platform(20.0, 460.0, 14.0, 5));   //5
		add(new Platform(240.0, 380.0, 12.0, 5));   //5
		add(new Wall(480.0, 380.0, 4.0, 5));       //5
		add(new Wall(580.0, 200.0, 10.0, 7));     //7
		add(new Wall(240.0, 340.0, 2.0, 8));      //8
		add(new Platform(20.0, 400.0, 4.0, 8));
	}};

	static ArrayList<GameObject> currentObstacles = new ArrayList<GameObject>();
	static ArrayList<GameObject> currentWallPlatform = new ArrayList<GameObject>() {{
		add(new Wall(0.0, 0.0, 27.0, 1));
		add(new Wall(765.0, 0.0, 27.0, 1));
		add(new Platform(0.0, 540.0, 40.0, 1));
		add(new Platform(0.0, 50.0, 40.0, 1));
		add(new Wall(695.0, 0.0, 3.0, 1));
		add(new Wall(595.0, 65.0, 2.0, 1));
		add(new Platform(595.0, 105.0, 12.0, 1));
	}};
	// creates list for current obstacles

	public static void restartLevel(Player player) {
		if (!player.alive) { player.attempts += 1; }
		else if (player.succeeded && Scene.scene != "COMPLETED") {
			obstacleCounter += 1;
			addObstacle(obstacleCounter);
			addWallPlatform(obstacleCounter);
		}
		player.alive = true;
		player.succeeded = false;
		player.box.x = 100;
		player.box.y = 500;
		player.jumping_time = 0;
	}

	public static void addObstacle(int obstacleTag) {
		for (int i = 0; i < obstacles.size(); i++) {
			if (obstacles.get(i).tag == obstacleTag) {
				currentObstacles.add(obstacles.get(i));
			}
		}
	}

	public static void addWallPlatform(int obstacleTag) {
		for (int i = 0; i < wallPlatform.size(); i++) {
			if (wallPlatform.get(i).tag == obstacleTag) {
				currentWallPlatform.add(wallPlatform.get(i));
			}
		}
	}

	public static void displayGameObjects(Graphics screen) {
		for (GameObject i : currentObstacles) { i.animate(screen); }
		for (GameObject a : currentWallPlatform) { a.animate(screen); }
	}

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
