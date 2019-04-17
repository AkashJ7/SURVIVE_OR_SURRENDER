import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")

public class Scene extends JPanel {

	static String scene = "HOME";
	static final int DISPLAY_WIDTH = 800;
	static final int DISPLAY_HEIGHT = 600;

	Player player = new Player();
	JButton start = new JButton("START");
	JButton back = new JButton("BACK");
	JButton pause = new JButton("||"); // genius setText

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		setFocusable(true);
		requestFocusInWindow();

		if (scene == "HOME") {
			setBackground(Color.BLACK);
			g.setColor(Color.BLUE);
			g.setFont(new Font("Sans Serif", Font.BOLD, 36));
			g.drawString("HOME", DISPLAY_WIDTH/2 - g.getFontMetrics().stringWidth("HOME")/2, 100);

			start.setFont(new Font("Sans Serif", Font.PLAIN, 20));
			start.setFocusPainted(false);
			start.setBounds(DISPLAY_WIDTH/2 - 100, 450, 200, 50);
			start.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					removeAll();
					scene = "NEXT";
				}
			});
			add(start);
			addKeyListener(new Player.KeyInput());

			player.move(g);
			player.checkForFailure(GameManagement.currentObstacles);
		}
		else if (scene == "NEXT") {
			setBackground(Color.BLUE.darker());
			g.setColor(Color.GREEN);
			g.setFont(new Font("Sans Serif", Font.PLAIN, 48));
			g.drawString("NEXT", DISPLAY_WIDTH/2 - g.getFontMetrics().stringWidth("NEXT")/2, 200);
			back.setFont(new Font("Sans Serif", Font.PLAIN, 20));
			back.setFocusPainted(false);
			back.setBounds(DISPLAY_WIDTH/2 - 75, 400, 150, 50);
			back.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					removeAll();
					scene = "HOME";
				}
			});
			add(back);
		}
		repaint();
	}
}
