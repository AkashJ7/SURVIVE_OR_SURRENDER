import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")

public class GameManagement {

	static ArrayList<GameObject> obstacles = new ArrayList<GameObject>() {{
		add(new Spikes(330.0, 510.0, 2.0));   //2
		add(new Spikes(460.0, 510.0, 2.0));  //3
		add(new Crusher(480.0, 50.0));    //4
		add(new Crusher(100.0, 50.0));
	}}; // list with all obstacles

	static ArrayList<GameObject> wallPlatform = new ArrayList<GameObject>() {{
		add(new Platform(480.0, 460.0, 10.0));  //3
		add(new Platform(20.0, 460.0, 14.0));   //5
		add(new Platform(240.0, 380.0, 12.0));   //5
		add(new Wall(480.0, 380.0, 4.0));       //5
		add(new Wall(580.0, 200.0, 10.0));     //7
		add(new Wall(240.0, 340.0, 2.0));      //8
		add(new Platform(20.0, 400.0, 4.0));
	}};

	static ArrayList<GameObject> currentObstacles = new ArrayList<GameObject>();
	static ArrayList<GameObject> currentWallPlatform = new ArrayList<GameObject>() {{
		add(new Wall(0.0, 0.0, 27.0));
		add(new Wall(765.0, 0.0, 27.0));
		add(new Platform(0.0, 540.0, 40.0));
	}};
	// creates list for current obstacles

	public static void restartLevel(Player player) {
		if (!player.alive) { player.attempts += 1; }
		else if (player.succeeded) {
			addObstacle();
			addWallPlatform();
		}
		player.alive = true;
		player.succeeded = false;
		player.box.x = 100;
		player.box.y = 500;
	}

	public static void addObstacle() {
		currentObstacles.add(obstacles.get(currentObstacles.size()));
	}

	public static void addWallPlatform() {
		currentWallPlatform.add(wallPlatform.get(currentWallPlatform.size()));
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
