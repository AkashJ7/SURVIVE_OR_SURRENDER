import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")

public class GameManagement {
	static ArrayList<GameObject> obstacles = new ArrayList<GameObject>() {{
		add(new Spikes(170.0, 525.0, 2.0));
		add(new Platform(400.0, 480.0, 10.0, "platform"));
		add(new Spikes(340.0, 525, 10.0));
		add(new Crusher(410.0, 50.0));
		add(new Crusher(150.0, 50.0));
	}}; // list with all obstacles
	static ArrayList<GameObject> currentObstacles = new ArrayList<GameObject>() {{
		add(new Spikes(170.0, 525.0, 2.0));
		add(new Platform(400.0, 480.0, 10.0, "platform"));
		add(new Spikes(340.0, 525, 10.0));
		add(new Crusher(410.0, 50.0));
		add(new Crusher(50.0, 50.0));
	}};
	// creates list for current obstacles

	public static void restartLevel() {
		// Player.failed = false; etc.
	}

	public static void addObstacle() {
		currentObstacles.add(obstacles.get(currentObstacles.size()));
	}
	public static void displayObstacles(Graphics screen) {
		for(GameObject i : currentObstacles) {
			screen.setColor(Color.RED);
			i.animate(screen);
		}
	}
}
