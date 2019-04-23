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

	static boolean ranIntro_HOME = false;
	static boolean ranIntro_NEXT = false;
	static boolean ranIntro_PAUSED = false;

	Player player = new Player();
	GameObject door = new GameObject(720, 500, 50, 80);
	JButton start = new JButton("START");
	JButton back = new JButton("BACK");
	JButton pause = new JButton("| |"); // genius setText
	JButton resume = new JButton("RESUME");
	BufferedImage buffer = new BufferedImage(DISPLAY_WIDTH, DISPLAY_HEIGHT, BufferedImage.TYPE_INT_RGB);
	Graphics screen = buffer.getGraphics();

	Font sans36i = new Font("Sans Serif", Font.ITALIC, 36);
	Font sans48 = new Font("Sans Serif", Font.PLAIN, 48);
	Font sans20 = new Font("Sans Serif", Font.PLAIN, 20);
	Font sans20b = new Font("Sans Serif", Font.BOLD, 20);

	public Scene(int fps) {
		GameManagement.addKeyBinding(this, KeyEvent.VK_UP, "JUMP", (e) -> { player.UP = true; }, false);
		GameManagement.addKeyBinding(this, KeyEvent.VK_RIGHT, "RIGHT", (e) -> { player.RIGHT = true; }, false);
		GameManagement.addKeyBinding(this, KeyEvent.VK_LEFT, "LEFT", (e) -> { player.LEFT = true; }, false);

		GameManagement.addKeyBinding(this, KeyEvent.VK_UP, "!JUMP", (e) -> { player.UP = false; }, true);
		GameManagement.addKeyBinding(this, KeyEvent.VK_RIGHT, "!RIGHT", (e) -> { player.RIGHT = false; }, true);
		GameManagement.addKeyBinding(this, KeyEvent.VK_LEFT, "!LEFT", (e) -> { player.LEFT = false; }, true);

		Timer gameTimer = new Timer(1000/fps, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeAll();
				updateScene();
				revalidate();
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
			screen.setFont(sans36i);
			screen.drawString("SURVIVE OR SURRENDER", DISPLAY_WIDTH/2 - screen.getFontMetrics().stringWidth("SURVIVE OR SURRENDER")/2, 100);
		}
		else if (scene == "NEXT") {
			screen.setColor(Color.BLUE.darker());
			screen.fillRect(0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT);
			screen.setColor(Color.GREEN);
			screen.setFont(sans48);
			screen.drawString("NEXT", DISPLAY_WIDTH/2 - screen.getFontMetrics().stringWidth("NEXT")/2, 200);

			screen.fillRect((int) door.box.x, (int) door.box.y, (int) door.box.width, (int) door.box.height);

			player.move(screen);
			GameManagement.displayObstacles(screen);
			player.checkForFailure(GameManagement.currentObstacles);
			player.checkForSuccess(door);
			if (!player.alive || player.succeeded) {
				if (GameManagement.currentObstacles.size() != GameManagement.obstacles.size()) {
					GameManagement.restartLevel(player);
				}
				else if (player.succeeded) {
					scene =  "COMPLETED";
				}
			}
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

		g.drawImage(buffer, 0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT, null);

		if (scene == "HOME") {
			if (!ranIntro_HOME) {
				start.setFont(sans20);
				start.setFocusPainted(false);
				start.setBounds(DISPLAY_WIDTH/2 - 100, 450, 200, 50);
				start.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						scene = "NEXT";
					}
				});

				ranIntro_HOME = true;
			}

			add(start);
		}
		else if (scene == "NEXT") {
			if (!ranIntro_NEXT) {
				back.setFont(sans20);
				back.setFocusPainted(false);
				back.setBounds(DISPLAY_WIDTH/2 - 75, 400, 150, 50);
				back.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						scene = "HOME";
					}
				});

				pause.setMargin(new Insets(0, 0, 4, 0));
				pause.setBackground(Color.GRAY.darker());
				pause.setForeground(Color.WHITE);
				pause.setFont(sans20b);
				pause.setFocusPainted(false);
				pause.setBounds(DISPLAY_WIDTH - 65, 10, 40, 40);
				pause.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						scene = "PAUSED";
					}
				});

				ranIntro_NEXT = true;
			}

			add(back);
			add(pause);
		}
		else if (scene == "PAUSED") {
			if (!ranIntro_PAUSED) {
				resume.setFont(sans20);
				resume.setFocusPainted(false);
				resume.setBounds(DISPLAY_WIDTH/2 - 75, 400, 150, 50);
				resume.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						scene = "NEXT";
					}
				});

				ranIntro_PAUSED = true;
			}

			add(resume);
		}
	}
}
