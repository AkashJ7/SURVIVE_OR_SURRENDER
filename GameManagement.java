import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

public class GameManagement {

	static int numObstacles = 1;
	ArrayList<GameObject> obstacles = new ArrayList<GameObject>(); // creates list with all obstacles
	ArrayList<GameObject> currentObstacles = new ArrayList<GameObject>(); // creates list for current obstacles

	public void pause() { scene = "PAUSE"; }

	public void restartLevel() {
		// Player.failed = false; etc.
	}

	public void addObstacle() {
		currentObstacles = obstacles.subList(0, currentObstacles.size() + 1);
		// check for whether higher interval marking is exclusive or inclusive
	}
}
