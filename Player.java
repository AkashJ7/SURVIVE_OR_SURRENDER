public class Player extends GameObject {
	int tries = 0;
	boolean alive = true;

	public Player() {
		// Initialize this. variables
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
			if danger.intersects(this) // find how to refer to self inside parentheses
		}
	}
}
