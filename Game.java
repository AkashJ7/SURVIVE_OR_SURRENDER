import java.awt.*;
import javax.swing.*;

public class Game {

	public static void main(String[] args) {
		JFrame window = new JFrame("SURVIVE OR SURRENDER!");
		window.setSize(Scene.DISPLAYWIDTH, Scene.DISPLAYHEIGHT);
		window.setLocation(1368/2 - Scene.DISPLAYWIDTH/2, 768/2 - Scene.DISPLAYHEIGHT/2);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setVisible(true);

		while(true) { Scene.updateScene(); }
	}
}

//final code for game
