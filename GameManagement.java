import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")

public class GameManagement {

	static ArrayList<GameObject> obstacles = new ArrayList<GameObject>() {{
		add(new Crusher(10.0, 50.0));
	}}; // list with all obstacles

	static ArrayList<GameObject> currentObstacles = new ArrayList<GameObject>();
	// creates list for current obstacles

	public static void restartLevel(Player player) {
		if (!player.alive) { player.attempts += 1; }
		else if (player.succeeded) { addObstacle(); }
		player.alive = true;
		player.succeeded = false;
		player.box.x = 100;
		player.box.y = 500;
	}

	public static void addObstacle() {
		currentObstacles.add(obstacles.get(currentObstacles.size()));
	}

	public static void displayObstacles(Graphics screen) {
		for(GameObject i : currentObstacles) {
			i.animate(screen);
		}
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
