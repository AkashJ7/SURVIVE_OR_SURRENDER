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
	static boolean ranIntro_CONTROLS = false;
	static boolean ranIntro_CREDITS = false;
	static boolean ranIntro_LEVEL = false;
	static boolean ranIntro_PAUSED = false;
	static boolean ranIntro_COMPLETED = false;
	static boolean ranIntro_SURRENDERED = false;

	Player player = new Player();
	GameObject door = new GameObject(720, 500, 50, 80);
	JButton start = new JButton("START");
	JButton returnHome = new JButton("RETURN TO MENU");
	JButton pause = new JButton("| |");
	JButton resume = new JButton("RESUME");
	JButton restartGame = new JButton("RESTART");
	JButton credits = new JButton("CREDITS");
	JButton controls = new JButton("CONTROLS");
	JButton backToHome = new JButton("BACK");

	BufferedImage buffer = new BufferedImage(DISPLAY_WIDTH, DISPLAY_HEIGHT, BufferedImage.TYPE_INT_RGB);
	Graphics screen = buffer.getGraphics();

	ImageIcon background = new ImageIcon("Sprites/Bricks.png");
	ImageIcon logo = new ImageIcon("Sprites/Logo.png");
	ImageIcon spikes_logo = new ImageIcon("Sprites/SPIKE.png");
	ImageIcon platform_logo = new ImageIcon("Sprites/Block.jpg");
	ImageIcon trophy = new ImageIcon("Sprites/Trophy.png");
	ImageIcon flag = new ImageIcon("Sprites/Surrender Flag.png");

	Font sans20 = new Font("Sans Serif", Font.PLAIN, 20);
	Font sans20b = new Font("Sans Serif", Font.BOLD, 20);
	Font sans30 = new Font("Sans Serif", Font.PLAIN, 30);
	Font sans30b = new Font("Sans Serif", Font.BOLD, 30);
	Font sans36 = new Font("Sans Serif", Font.PLAIN, 36);
	Font sans36i = new Font("Sans Serif", Font.ITALIC, 36);
	Font sans50bi = new Font("Sans Serif", Font.BOLD | Font.ITALIC, 50);
	Font sans50b = new Font("Sans Serif", Font.BOLD, 50);

	public Scene(int fps) {
		GameManagement.addKeyBinding(this, KeyEvent.VK_UP, "JUMP", (e) -> { player.UP = true; }, false);
		GameManagement.addKeyBinding(this, KeyEvent.VK_RIGHT, "RIGHT", (e) -> { player.RIGHT = true; }, false);
		GameManagement.addKeyBinding(this, KeyEvent.VK_LEFT, "LEFT", (e) -> { player.LEFT = true; }, false);

		GameManagement.addKeyBinding(this, KeyEvent.VK_UP, "!JUMP", (e) -> { player.UP = false; }, true);
		GameManagement.addKeyBinding(this, KeyEvent.VK_RIGHT, "!RIGHT", (e) -> { player.RIGHT = false; }, true);
		GameManagement.addKeyBinding(this, KeyEvent.VK_LEFT, "!LEFT", (e) -> { player.LEFT = false; }, true);

		setLayout(null);
		setDoubleBuffered(true);
		Toolkit.getDefaultToolkit().sync();

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
			screen.setColor(new Color(15, 15, 40));
			screen.fillRect(0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT);
			screen.setColor(Color.CYAN);
			screen.setFont(sans50bi);
			screen.drawString("SURVIVE OR SURRENDER", DISPLAY_WIDTH/2 - screen.getFontMetrics().stringWidth("SURVIVE OR SURRENDER")/2, 100);
			screen.drawImage(logo.getImage(), DISPLAY_WIDTH/2 - logo.getIconWidth()/4, 135, logo.getIconWidth()/2, logo.getIconHeight()/2, null);
			for (int i = 1; i <= 5; i++) {
				screen.drawImage(spikes_logo.getImage(), DISPLAY_WIDTH/2 + 97 - 38 * i, 275, (int) (spikes_logo.getIconWidth() * 1.5), (int) (spikes_logo.getIconHeight() * 1.5), null);
				screen.drawImage(platform_logo.getImage(), DISPLAY_WIDTH/2 + 107 - platform_logo.getIconWidth()/16 * i, 320, platform_logo.getIconWidth()/16, platform_logo.getIconHeight()/16, null);
				if(i < 4) screen.drawImage(platform_logo.getImage(), DISPLAY_WIDTH/2 + 107 - platform_logo.getIconWidth()/16 * (i + 5), 320, platform_logo.getIconWidth()/16, platform_logo.getIconHeight()/16, null);
			}
		}
		else if (scene == "CONTROLS") {
			screen.setColor(new Color(15, 15, 40));
			screen.fillRect(0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT);
			screen.setColor(Color.WHITE);
			screen.setFont(sans50b);
			screen.drawString("CONTROLS", DISPLAY_WIDTH/2 - screen.getFontMetrics().stringWidth("CONTROLS")/2, 100);
			screen.setFont(sans30);
			screen.drawString("Press LEFT or RIGHT to move", DISPLAY_WIDTH/2 - screen.getFontMetrics().stringWidth("Press LEFT or RIGHT to move")/2, 350);
			screen.setFont(sans30);
			screen.drawString("Press UP to jump", DISPLAY_WIDTH/2 - screen.getFontMetrics().stringWidth("Press UP to jump")/2, 400);
		}
		else if (scene == "CREDITS") {
			screen.setColor(new Color(15, 15, 40));
			screen.fillRect(0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT);
			screen.setColor(Color.WHITE);
			screen.setFont(sans50b);
			screen.drawString("CREDITS", DISPLAY_WIDTH/2 - screen.getFontMetrics().stringWidth("CREDITS")/2, 100);
			screen.setFont(sans30);
			screen.drawString("Game created and developed by", DISPLAY_WIDTH/2 - screen.getFontMetrics().stringWidth("Game created and developed by")/2, 350);
			screen.setFont(sans30);
			screen.drawString("Akash Jagdeesh and Arjun Jagdeesh", DISPLAY_WIDTH/2 - screen.getFontMetrics().stringWidth("Akash Jagdeesh and Arjun Jagdeesh")/2, 400);
		}
		else if (scene == "NEXT") {
			screen.setColor(Color.BLUE.darker());
			screen.drawImage(background.getImage(), 0, 0, null);

			screen.fillRect((int) door.box.x, (int) door.box.y, (int) door.box.width, (int) door.box.height);

			player.constrain();
			player.move(screen);
			GameManagement.displayGameObjects(screen);
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
			screen.setColor(Color.WHITE);
			screen.setFont(sans50b);
			screen.drawString("PAUSED", DISPLAY_WIDTH/2 - screen.getFontMetrics().stringWidth("PAUSED")/2, 100);
		}
		else if (scene == "COMPLETED") {
			screen.setColor(new Color(15, 70, 15));
			screen.fillRect(0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT);
			screen.setColor(Color.WHITE);
			screen.setFont(sans50bi);
			screen.drawString("CONGRATULATIONS!", DISPLAY_WIDTH/2 - screen.getFontMetrics().stringWidth("CONGRATULATIONS!")/2, 100);
			screen.drawImage(trophy.getImage(), DISPLAY_WIDTH/2 - trophy.getIconWidth()/40, 150, trophy.getIconWidth()/20, trophy.getIconHeight()/20, null);
			screen.setFont(sans30);
			screen.drawString("You beat the game in", DISPLAY_WIDTH/2 - screen.getFontMetrics().stringWidth("You beat the game in")/2, 375);
			screen.drawString(String.valueOf(player.attempts) + " attempts!", DISPLAY_WIDTH/2 - screen.getFontMetrics().stringWidth(String.valueOf(player.attempts) + " attempts!")/2, 425);
		}
		else if (scene == "SURRENDERED") {
			screen.setColor(new Color(70, 15, 15));
			screen.fillRect(0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT);
			screen.setColor(Color.WHITE);
			screen.setFont(sans50bi);
			screen.drawString("YOU GAVE UP!", DISPLAY_WIDTH/2 - screen.getFontMetrics().stringWidth("YOU GAVE UP!")/2, 100);
			screen.drawImage(flag.getImage(), DISPLAY_WIDTH/2 - flag.getIconWidth()/8, 125, flag.getIconWidth()/4, flag.getIconHeight()/4, null);
			screen.setFont(sans30);
			screen.drawString("Determination is the key to success...", DISPLAY_WIDTH/2 - screen.getFontMetrics().stringWidth("Determination is the key to success...")/2, 375);
			screen.drawString(String.valueOf(GameManagement.obstacles.size() - GameManagement.currentObstacles.size()) + " " + ((GameManagement.obstacles.size() - GameManagement.currentObstacles.size() == 1) ? "obstacle" : "obstacles") + " remained", DISPLAY_WIDTH/2 - screen.getFontMetrics().stringWidth(String.valueOf(GameManagement.obstacles.size() - GameManagement.currentObstacles.size()) + " " + ((GameManagement.obstacles.size() - GameManagement.currentObstacles.size() == 1) ? "obstacle" : "obstacles") + " remained")/2, 425);
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(buffer, 0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT, null);

		if (scene == "HOME") {
			if (!ranIntro_HOME) {
				start.setFont(sans20);
				start.setFocusPainted(false);
				start.setMargin(new Insets(0, 0, 0, 0));
				start.setBounds(DISPLAY_WIDTH/2 - 45, 370, 90, 40);
				start.setForeground(Color.WHITE);
				start.setBackground(Color.DARK_GRAY);
				start.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ranIntro_LEVEL = false;
						scene = "NEXT";
					}
				});

				credits.setFont(sans20);
				credits.setFocusPainted(false);
				credits.setMargin(new Insets(0, 0, 0, 0));
				credits.setBounds(DISPLAY_WIDTH/2 - 55, 435, 110, 40);
				credits.setForeground(Color.WHITE);
				credits.setBackground(Color.DARK_GRAY);
				credits.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ranIntro_CREDITS = false;
						scene = "CREDITS";
					}
				});

				controls.setFont(sans20);
				controls.setFocusPainted(false);
				controls.setMargin(new Insets(0, 0, 0, 0));
				controls.setBounds(DISPLAY_WIDTH/2 - 65, 500, 130, 40);
				controls.setForeground(Color.WHITE);
				controls.setBackground(Color.DARK_GRAY);
				controls.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ranIntro_CONTROLS = false;
						scene = "CONTROLS";
					}
				});

				ranIntro_HOME = true;
			}

			add(start);
			add(credits);
			add(controls);
		}
		else if (scene == "NEXT") {
			if (!ranIntro_LEVEL) {
				pause.setMargin(new Insets(0, 0, 4, 0));
				pause.setBackground(Color.GRAY.darker());
				pause.setForeground(Color.WHITE);
				pause.setFont(sans20b);
				pause.setFocusPainted(false);
				pause.setBounds(DISPLAY_WIDTH - 65, 10, 40, 40);
				pause.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ranIntro_PAUSED = false;
						scene = "PAUSED";
					}
				});

				ranIntro_LEVEL = true;
			}

			add(pause);
		}
		else if (scene == "PAUSED") {
			if (!ranIntro_PAUSED) {
				resume.setFont(sans20);
				resume.setFocusPainted(false);
				resume.setBounds(DISPLAY_WIDTH/2 - 55, 300, 110, 40);
				resume.setForeground(Color.WHITE);
				resume.setBackground(Color.DARK_GRAY);
				resume.setMargin(new Insets(0, 0, 0, 0));
				resume.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ranIntro_LEVEL = false;
						scene = "NEXT";
					}
				});

				restartGame.setFont(sans20);
				restartGame.setFocusPainted(false);
				restartGame.setBounds(DISPLAY_WIDTH/2 - 58, 365, 116, 40);
				restartGame.setForeground(Color.WHITE);
				restartGame.setBackground(Color.DARK_GRAY);
				restartGame.setMargin(new Insets(0, 0, 0, 0));
				restartGame.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						GameManagement.restartLevel(player);
						player.attempts = 0;
						GameManagement.currentObstacles.clear();
						ranIntro_LEVEL = false;
						scene = "NEXT";
					}
				});

				returnHome.setFont(sans20);
				returnHome.setFocusPainted(false);
				returnHome.setMargin(new Insets(0, 0, 0, 0));
				returnHome.setBounds(DISPLAY_WIDTH/2 - 100, 430, 200, 40);
				returnHome.setForeground(Color.WHITE);
				returnHome.setBackground(Color.DARK_GRAY);
				returnHome.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						GameManagement.restartLevel(player);
						player.attempts = 0;
						GameManagement.currentObstacles.clear();
						scene = "HOME";
					}
				});

				ranIntro_PAUSED = true;
			}

			add(resume);
			add(restartGame);
			add(returnHome);
		}
		else if (scene == "CONTROLS" || scene == "CREDITS") {
			if (!ranIntro_CONTROLS || !ranIntro_CREDITS) {
				backToHome.setFont(sans20);
				backToHome.setFocusPainted(false);
				backToHome.setMargin(new Insets(0, 0, 0, 0));
				backToHome.setBounds(20, 20, 66, 30);
				backToHome.setBackground(Color.DARK_GRAY);
				backToHome.setForeground(Color.WHITE);
				backToHome.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ranIntro_HOME = false;
						scene = "HOME";
					}
				});

				ranIntro_CONTROLS = true;
				ranIntro_CREDITS = true;
			}

			add(backToHome);
		}
		else if (scene == "COMPLETED" || scene == "SURRENDERED") {
			if (!ranIntro_COMPLETED || !ranIntro_SURRENDERED) {
				returnHome.setFont(sans20);
				returnHome.setFocusPainted(false);
				returnHome.setMargin(new Insets(0, 0, 0, 0));
				returnHome.setBounds(DISPLAY_WIDTH/2 - 100, 470, 200, 40);
				returnHome.setForeground(Color.WHITE);
				returnHome.setBackground(Color.DARK_GRAY);
				returnHome.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						GameManagement.restartLevel(player);
						player.attempts = 0;
						GameManagement.currentObstacles.clear();
						scene = "HOME";
					}
				});

				ranIntro_COMPLETED = true;
				ranIntro_SURRENDERED = true;
			}

			add(returnHome);
		}
	}
}
