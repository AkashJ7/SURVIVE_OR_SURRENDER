import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Scene {

	static String scene = "HOME";
	static final int DISPLAY_WIDTH = 800;
	static final int DISPLAY_HEIGHT = 600;

	public static void updateScene(JFrame frame) {
		if (scene == "HOME") {
			JButton start = new JButton("START");
			start.setFocusPainted(false);
			start.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					scene = "NEXT";
				}
			});
			start.setFont(new Font ("Sans Serif", Font.PLAIN, 20));
			start.setBounds(DISPLAY_WIDTH/2 - 50, 450, 100, 40);
			frame.add(start);
		}
		else if (scene == "NEXT") {
			JButton cone = new JButton("CONTINUE");
			cone.setFocusPainted(false);
			cone.setFont(new Font("Sans Serif", Font.BOLD, 20));
			cone.setBounds(DISPLAY_WIDTH/2 - 75, 350, 150, 40);
			//JLabel text = new JLabel("NEXT", SwingConstants.LEFT);
			//text.setFont(new Font("Sans Serif", Font.PLAIN, 20));
			frame.add(cone);
		}
	}
}


/* FUNCTIONING COLLIDING CODE
	ArrayList<GameObject> obstacles = new ArrayList<GameObject>(0);
	Player a = new Player();
	GameObject b = new GameObject(100, 50, 50, 100);
	obstacles.add(b);
	a.checkForFailure(obstacles);
	System.out.println(a.alive);
*/
