import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

@SuppressWarnings("serial")

/**
 * The JPanel which contains all the JComponents and graphics of the game.
 * It takes in keyboard input to control the player,
 * and contains the logic for scene transitions, gameplay, etc.
 *
 * @author Akash Jagdeesh
 * @author Arjun Jagdeesh
 */

public class Scene extends JPanel {

	/** Current scene name, used to switch scenes */
	static String scene = "HOME";
	/** Width of display panel */
	static final int DISPLAY_WIDTH = 800;
	/** Height of display panel */
	static final int DISPLAY_HEIGHT = 600;
	/** Whether the scene has just been switched to "HOME" (used to create and add JComponents to panel) */
	static boolean ranIntro_HOME = false;
	/** Whether the scene has just been switched to "CONTROLS" (used to create and add JComponents to panel) */
	static boolean ranIntro_CONTROLS = false;
	/** Whether the scene has just been switched to "CREDITS" (used to create and add JComponents to panel) */
	static boolean ranIntro_CREDITS = false;
	/** Whether the scene has just been switched to "LEVEL" (used to create and add JComponents to panel) */
	static boolean ranIntro_LEVEL = false;
	/** Whether the scene has just been switched to "PAUSED" (used to create and add JComponents to panel) */
	static boolean ranIntro_PAUSED = false;
	/** Whether the scene has just been switched to "COMPLETED" (used to create and add JComponents to panel) */
	static boolean ranIntro_COMPLETED = false;
	/** Whether the scene has just been switched to "SURRENDERED" (used to create and add JComponents to panel) */
	static boolean ranIntro_SURRENDERED = false;

	/** Player used in this game */
	Player player = new Player();
	/** Exit door at the end of the level */
	GameObject door = new GameObject(700, 480, 50, 80, 1);

	/** Button to start level (in "HOME") */
	JButton start = new JButton("START");
	/** Button to return to home screen (while "PAUSED") */
	JButton returnHome = new JButton("RETURN TO MENU");
	/** Button to surrender while playing the game (in "LEVEL") */
	JButton surrender = new JButton("SURRENDER");
	/** Button to pause game (in "LEVEL") */
	JButton pause = new JButton("| |");
	/** Button to resume game (while "PAUSED") */
	JButton resume = new JButton("RESUME");
	/** Button to restart the game (while "PAUSED") */
	JButton restartGame = new JButton("RESTART");
	/** Button to view game credits (in "HOME") */
	JButton credits = new JButton("CREDITS");
	/** Button to view controls (in "HOME") */
	JButton controls = new JButton("CONTROLS");
	/** Button to return to home screen (in "CONTROLS" and "CREDITS") */
	JButton backToHome = new JButton("BACK");

	/** Image buffer that graphics are drawn on */
	BufferedImage buffer = new BufferedImage(DISPLAY_WIDTH, DISPLAY_HEIGHT, BufferedImage.TYPE_INT_RGB);
	/** Graphics object of the buffer, used to draw on the buffer */
	Graphics screen = buffer.getGraphics();

	/** Background image of the level */
	ImageIcon background = new ImageIcon("Sprites/Bricks.png");
	/** Home screen's player image */
	ImageIcon logo = new ImageIcon("Sprites/Logo.png");
	/** Home screen's spikes image */
	ImageIcon spikes_logo = new ImageIcon("Sprites/SPIKE.png");
	/** Home screen's platform image */
	ImageIcon platform_logo = new ImageIcon("Sprites/Block.jpg");
	/** Completed screen's trophy image */
	ImageIcon trophy = new ImageIcon("Sprites/Trophy.png");
	/** Surrendered screen's white flag (surrender) image */
	ImageIcon flag = new ImageIcon("Sprites/Surrender Flag.png");

	/** 20 point Sans Serif font */
	Font sans20 = new Font("Sans Serif", Font.PLAIN, 20);
	/** 20 point bolded Sans Serif font */
	Font sans20b = new Font("Sans Serif", Font.BOLD, 20);
	/** 30 point Sans Serif font */
	Font sans30 = new Font("Sans Serif", Font.PLAIN, 30);
	/** 30 point bolded Sans Serif font */
	Font sans30b = new Font("Sans Serif", Font.BOLD, 30);
	/** 30 point italicized Sans Serif font */
	Font sans30i = new Font("Sans Serif", Font.ITALIC, 30);
	/** 36 point Sans Serif font */
	Font sans36 = new Font("Sans Serif", Font.PLAIN, 36);
	/** 36 point bolded Sans Serif font */
	Font sans36b = new Font("Sans Serif", Font.BOLD, 36);
	/** 36 point italicized Sans Serif font */
	Font sans36i = new Font("Sans Serif", Font.ITALIC, 36);
	/** 50 point bolded and italicized Sans Serif font */
	Font sans50bi = new Font("Sans Serif", Font.BOLD | Font.ITALIC, 50);
	/** 50 point bolded Sans Serif font */
	Font sans50b = new Font("Sans Serif", Font.BOLD, 50);

   /**
    * Instantiates the JPanel, and is used as a content pane that a JFrame can use
    *
    * @param fps The number of frames per second that the game will run at
    */

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
				updateScene();
				revalidate();
				if (scene == "NEXT") {
					repaint((int) player.box.x - 2, (int) (player.box.y + player.jump_height - 10), (int) (player.box.width + 5), (int) (player.box.height + 22));
					for (GameObject i : GameManagement.currentObstacles) {
						repaint((int) i.box.x - 1, (int) i.box.y - 10, (int) i.box.width + 2, (int) i.box.height + 20);
					}
					for (GameObject i : GameManagement.currentWallPlatform) {
						repaint((int) i.box.x - 1, (int) i.box.y - 1, (int) i.box.width + 2, (int) i.box.height + 2);
					}
				}
				else { repaint(); }
			}
		});
		gameTimer.start();
	}

   /**
    * Draws the graphics of each scene to a buffer image, and calls all action methods in the game (of Player, GameManagement, etc.)
    */

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
			screen.drawString("Inspired by the online game", DISPLAY_WIDTH/2 - screen.getFontMetrics().stringWidth("Inspired by the online game")/2, 250);
			screen.setFont(sans30i);
			screen.drawString("GIVE UP", DISPLAY_WIDTH/2 - screen.getFontMetrics().stringWidth("GIVE UP")/2, 300);
			screen.setFont(sans30);
			screen.drawString("Game created and developed by", DISPLAY_WIDTH/2 - screen.getFontMetrics().stringWidth("Game created and developed by")/2, 400);
			screen.drawString("Akash Jagdeesh and Arjun Jagdeesh", DISPLAY_WIDTH/2 - screen.getFontMetrics().stringWidth("Akash Jagdeesh and Arjun Jagdeesh")/2, 450);
		}
		else if (scene == "NEXT") {
			screen.drawImage(background.getImage(), 0, 0, null);
			screen.setFont(sans20b);

			screen.setColor(Color.BLACK);
			screen.fillRect((int) door.box.x, (int) door.box.y, (int) door.box.width, (int) door.box.height);

			player.constrain();
			player.move(screen);
			GameManagement.displayGameObjects(screen);
			player.displayAttempts(screen);
			player.checkForFailure(GameManagement.currentObstacles);
			player.checkForSuccess(door);
			if (!player.alive || player.succeeded) {
				if (GameManagement.obstacleCounter == 7 && player.succeeded) {
					removeAll();
				}
				GameManagement.restartLevel(player);
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
			screen.drawString(String.valueOf(player.attempts) + " attempt" + ((player.attempts == 1) ? "!" : "s!"), DISPLAY_WIDTH/2 - screen.getFontMetrics().stringWidth(String.valueOf(player.attempts) + " attempt" + ((player.attempts == 1) ? "!" : "s!"))/2, 425);
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

   /**
    * Draws the image buffer to the main JPanel, and displays all the JComponents (mainly JButtons)
    *
    * @param g An internal variable required by paintComponent to draw to the JPanel
    */

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
						removeAll();
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
						removeAll();
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
						removeAll();
						ranIntro_CONTROLS = false;
						scene = "CONTROLS";
					}
				});

				ranIntro_HOME = true;

				add(start);
				add(credits);
				add(controls);
			}
		}
		else if (scene == "NEXT") {
			if (!ranIntro_LEVEL) {
				repaint();
				pause.setMargin(new Insets(0, 0, 4, 0));
				pause.setBackground(Color.DARK_GRAY);
				pause.setForeground(Color.WHITE);
				pause.setFont(sans20b);
				pause.setFocusPainted(false);
				pause.setBounds(DISPLAY_WIDTH - 85, 0, 50, 50);
				pause.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						removeAll();
						ranIntro_PAUSED = false;
						scene = "PAUSED";
					}
				});

				surrender.setBackground(new Color(50, 150, 255));
	            surrender.setForeground(Color.BLACK);
	            surrender.setFont(sans36b);
	            surrender.setFocusPainted(false);
	            surrender.setBounds(20, 0, 675, 50);
	            surrender.addActionListener(new ActionListener() {
	               public void actionPerformed(ActionEvent e) {
	                  removeAll();
	                  ranIntro_SURRENDERED = false;
	                  scene = "SURRENDERED";
	               }
	            });

				ranIntro_LEVEL = true;

				add(pause);
				add(surrender);
			}
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
						removeAll();
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
						player.attempts = 1;
						GameManagement.obstacleCounter = 1;
						GameManagement.currentObstacles.clear();
						GameManagement.currentWallPlatform.subList(7, GameManagement.currentWallPlatform.size()).clear();
						removeAll();
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
						player.attempts = 1;
						GameManagement.obstacleCounter = 1;
						GameManagement.currentObstacles.clear();
						GameManagement.currentWallPlatform.subList(7, GameManagement.currentWallPlatform.size()).clear();
						removeAll();
						ranIntro_HOME = false;
						scene = "HOME";
					}
				});

				ranIntro_PAUSED = true;

				add(resume);
				add(restartGame);
				add(returnHome);
			}
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
						removeAll();
						ranIntro_HOME = false;
						scene = "HOME";
					}
				});

				ranIntro_CONTROLS = true;
				ranIntro_CREDITS = true;

				add(backToHome);
			}
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
						player.attempts = 1;
						GameManagement.obstacleCounter = 1;
						GameManagement.currentObstacles.clear();
						GameManagement.currentWallPlatform.subList(7, GameManagement.currentWallPlatform.size()).clear();
						removeAll();
						ranIntro_HOME = false;
						scene = "HOME";
					}
				});

				ranIntro_COMPLETED = true;
				ranIntro_SURRENDERED = true;

				add(returnHome);
			}
		}
	}
}
