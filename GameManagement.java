import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")

public class GameManagement {
	static ArrayList<GameObject> obstacles = new ArrayList<GameObject>() {{

	}}; // list with all obstacles
	static ArrayList<GameObject> currentObstacles = new ArrayList<GameObject>() {{
		add(new Spikes(330.0, 510.0, 2.0));   //2
		add(new Spikes(460.0, 510.0, 6.0));  //3
		add(new Crusher(480.0, 50.0));    //4
		add(new Crusher(100.0, 50.0));
	}};

	static ArrayList<GameObject> wallPlatform = new ArrayList<GameObject>() {{

		//add(new Wall(400.0, 80.0, 20.0));
	}};

	static ArrayList<GameObject> currentWallPlatform = new ArrayList<GameObject>() {{
		add(new Wall(0.0, 0.0, 27.0));
		add(new Wall(765.0, 0.0, 27.0));
		add(new Platform(0.0, 540.0, 40.0));

		add(new Platform(480.0, 460.0, 10.0));  //3
		add(new Platform(20.0, 460.0, 14.0));   //5
		add(new Platform(240.0, 380.0, 12.0));   //5
		add(new Wall(480.0, 380.0, 4.0));       //5
		add(new Wall(580.0, 200.0, 10.0));     //7
		add(new Wall(240.0, 340.0, 2.0));      //8
		add(new Platform(20.0, 400.0, 4.0));
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
			i.animate(screen);
		}
		for(GameObject a : currentWallPlatform) {
			a.animate(screen);
		}
	}
}
