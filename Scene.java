import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")

public class Scene extends JPanel implements ActionListener {

	static String scene = "HOME";
	static final int DISPLAY_WIDTH = 800;
	static final int DISPLAY_HEIGHT = 600;
	static boolean update = false;

	Player player = new Player();
	JButton start = new JButton("START");
	JButton back = new JButton("BACK");
	JButton pause = new JButton("| |"); // genius setText
	JButton resume = new JButton("RESUME");
	GameObject crusher = new Crusher(10, 500);

	public Scene(int fps) {
		Timer gameTimer = new Timer(1000/fps, this);
		gameTimer.start();
	}

	public void actionPerformed(ActionEvent e) { update = true; }

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		setFocusable(true);
		requestFocusInWindow();

		if (scene == "HOME") {
			setBackground(Color.BLACK);
			g.setColor(Color.BLUE);
			g.setFont(new Font("Sans Serif", Font.ITALIC, 36));
			g.drawString("SURVIVE OR SURRENDER", DISPLAY_WIDTH/2 - g.getFontMetrics().stringWidth("SURVIVE OR SURRENDER")/2, 100);

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

			pause.setMargin(new Insets(0, 0, 4, 0));
			pause.setBackground(Color.GRAY.darker());
			pause.setForeground(Color.WHITE);
			pause.setFont(new Font("Sans Serif", Font.BOLD, 20));
			pause.setFocusPainted(false);
			pause.setBounds(DISPLAY_WIDTH - 65, 10, 40, 40);
			pause.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					removeAll();
					scene = "PAUSED";
				}
			});

			add(back);
			add(pause);
			addKeyListener(new Player.KeyInput());

			player.move(update, g);
			GameManagement.displayObstacles(update, g);
			player.checkForFailure(GameManagement.currentObstacles);
		}
		else if (scene == "PAUSED") {
			setBackground(Color.BLACK);

			resume.setFont(new Font("Sans Serif", Font.PLAIN, 20));
			resume.setFocusPainted(false);
			resume.setBounds(DISPLAY_WIDTH/2 - 75, 400, 150, 50);
			resume.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					removeAll();
					scene = "NEXT";
				}
			});

			add(resume);
		}
		repaint();
		update = false;
	}
}
