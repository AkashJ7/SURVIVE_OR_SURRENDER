import java.awt.*; // Image
import java.util.ArrayList;
import javax.swing.*;

@SuppressWarnings("serial")

public class Player extends GameObject {

	int attempts = 0;
	boolean alive = true;
	boolean succeeded = false;
	double speed = 3;
	boolean jump = false;
	double jumping_time = 0;
	double jump_height;
	boolean falling = false;
	boolean RIGHT, LEFT, UP;

	public Player() {
		super(100, 500, 20, 50);
		// Draw sprite 15 left and 5 up of rect
	}

	public void move (Graphics screen) {
		if (jump_height > 0) { // If touched ground again
			jump = false; // Not jumping anymore
			jumping_time = 0; // Reset parabola
		}
		if (UP && falling == false) {
			jump = true;
		}
		if (jump) {
			jumping_time += 2;
			jump_height = -(-0.03*jumping_time*jumping_time + 3.5*jumping_time);
			// jump math prob needs tweaking based on our sizes/game
		}
		if (RIGHT) {
			this.box.x += speed;
			// Sprite animation
		} else if (LEFT) {
			this.box.x -= speed;
			// Moving left, with opposite sprite animation
		}

		screen.setColor(Color.RED);
		screen.drawRect((int) this.box.x, (int) (this.box.y + jump_height), (int) this.box.width, (int) this.box.height);
	}

	public void constrain() {
		//
	}

	public void displayTries(Graphics screen) {
		//
	}

	public void checkForFailure(ArrayList<GameObject> obstacles) {
		for(GameObject danger : obstacles) {
			if (danger.box.intersects(this.box.x, this.box.y + jump_height, this.box.width, this.box.height)) {
				alive = false;
			}
		}
	}

	public void checkForSuccess(GameObject door) {
		if(door.box.intersects(this.box.x, this.box.y + jump_height, this.box.width, this.box.height)) {
			succeeded = true;
		}
	}
}
