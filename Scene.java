public class Scene {

	static String scene = "HOME";
	static int DISPLAY_WIDTH = 800;
	static int DISPLAY_HEIGHT = 600;

	public static void updateScene() {
		if (scene == "HOME") {
			JButton start = new JButton("START");
			start.setFont(new Font ("Sans Serif", Font.PLAIN, 20));
			start.setBounds(DISPLAY_WIDTH/2 - 40, 450, 100, 40);
			add(start);
		}
	}
}
