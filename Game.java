import java.awt.*;
import javax.swing.*;

/**
 * The main Driver class to run the game
 *
 * @author Akash Jagdeesh
 * @author Arjun Jagdeesh
 */

public class Game {

   /**
    * Creates a JFrame, and displays the Scene panel
    */

	public static void main(String[] args) {
		JFrame window = new JFrame("SURVIVE OR SURRENDER!");
		window.setSize(Scene.DISPLAY_WIDTH, Scene.DISPLAY_HEIGHT);
		window.setLocation(1368/2 - Scene.DISPLAY_WIDTH/2, 768/2 - Scene.DISPLAY_HEIGHT/2);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setContentPane(new Scene(120));
		window.setVisible(true);
	}
}
