import java.awt.*; // Image, event.KeyEvent

public class Player extends GameObject {

	public Player() {
		this.tries = 0;
		this.alive = true;
	}

	public void move() {
		// If statements for keyboard go here
	}

	public boolean constrain() {
		//
	}

	public void displayTries() {
		// Stuff from Graphics?
	}

	public boolean checkForFailure(ArrayList<GameObject> list) {
		for(danger : list) {
			if danger.intersects(this) { this.alive = false; }
		}
	}
}
