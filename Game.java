import java.awt.*;
import javax.swing.*;

public class Game {

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
