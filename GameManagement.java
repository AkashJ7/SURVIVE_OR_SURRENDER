import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")

public class GameManagement {
	static ArrayList<GameObject> obstacles = new ArrayList<GameObject>() {{

	}}; // list with all obstacles
	static ArrayList<GameObject> currentObstacles = new ArrayList<GameObject>() {{
		add(new Crusher(10.0, 50.0));
	}};
	// creates list for current obstacles

	public static void restartLevel() {
		// Player.failed = false; etc.
	}

	public static void addObstacle() {
		currentObstacles.add(obstacles.get(currentObstacles.size()));
	}
	public static void displayObstacles(boolean update, Graphics g) {
		for(GameObject i : currentObstacles) {
			i.animate(update, g);
		}
	}
}
