import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

@SuppressWarnings("serial")

public class Scene extends JPanel {

	static String scene = "HOME";
	static final int DISPLAY_WIDTH = 800;
	static final int DISPLAY_HEIGHT = 600;
	static boolean update = false;

	Player player = new Player();
	JButton start = new JButton("START");
	JButton back = new JButton("BACK");
	JButton pause = new JButton("| |"); // genius setText
	JButton resume = new JButton("RESUME");
	ImageIcon bckgrnd = new ImageIcon("Sprites/Bricks.png");
	BufferedImage buffer = new BufferedImage(DISPLAY_WIDTH, DISPLAY_HEIGHT, BufferedImage.TYPE_INT_RGB);
	Graphics screen = buffer.getGraphics();

	public Scene(int fps) {
		Timer gameTimer = new Timer(1000/fps, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateScene();
				repaint();
			}
		});
		gameTimer.start();
	}

	public void updateScene() {
		if (scene == "HOME") {
			screen.setColor(Color.BLACK);
			screen.fillRect(0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT);
			screen.setColor(Color.BLUE);
			screen.setFont(new Font("Sans Serif", Font.ITALIC, 36));
			screen.drawString("SURVIVE OR SURRENDER", DISPLAY_WIDTH/2 - screen.getFontMetrics().stringWidth("SURVIVE OR SURRENDER")/2, 100);
		}
		else if (scene == "NEXT") {
			//screen.setColor(Color.BLUE.darker());
			//screen.fillRect(0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT);
			screen.setColor(Color.GREEN);
			screen.setFont(new Font("Sans Serif", Font.PLAIN, 48));
			screen.drawImage(bckgrnd.getImage(), 0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT, null);
			screen.drawString("NEXT", DISPLAY_WIDTH/2 - screen.getFontMetrics().stringWidth("NEXT")/2, 200);

			addKeyListener(new Player.KeyInput());

			player.move(screen);
			player.constrain();
			player.displayTries(screen);
			GameManagement.displayObstacles(screen);
			player.checkForFailure(GameManagement.currentObstacles);
		}
		else if (scene == "PAUSED") {
			screen.setColor(Color.BLACK);
			screen.fillRect(0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT);
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		setFocusable(true);
		requestFocusInWindow();

		g.drawImage(bckgrnd.getImage(), 0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT, null);
		g.drawImage(buffer, 0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT, null);

		if (scene == "HOME") {
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
		}
		else if (scene == "PAUSED") {
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
	}
}
