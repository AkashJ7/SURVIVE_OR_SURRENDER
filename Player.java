import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

@SuppressWarnings("serial")

/**
 * Player class to generate a playable character
 *
 * @author Akash Jagdeesh
 * @author Arjun Jagdeesh
 */

public class Player extends GameObject {

	/** Current number of attempts, starting on the first attempt */
	int attempts = 1;
	/** Whether the Player is alive */
	boolean alive = true;
	/** Whether the Player has completed the level (passed through the door) */
	boolean succeeded = false;
	/** Running speed */
	double speed = 3;
	/** Whether the Player can, or is in a position to, jump */
	boolean able_to_jump = false;
	/** Whether the Player is jumping */
	boolean jump = false;
	/** x coordinate of the parabola used to calculate the Player's height during a jump */
	double jumping_time = 0;
	/** Hheight above the platform during a jump (calculated using jumping_time) */
	double jump_height;
	/** Whether keyboard input has stated that the Player must run right or left, or jump */
	boolean RIGHT, LEFT, UP;
	/** Index of the sprites ArrayLists, used to display the correct sprite (either from runningRight or runningLeft) */
	double sprite_counter = 0;
	/** Whether the player is currently moving to the right (used to draw correct sprite) */
	boolean going_right = true;
	/** ArrayList containing all the sprites of the Player running toward the right */
	ArrayList<ImageIcon> runningRight = new ArrayList<ImageIcon>() {{
		for(int i = 0; i < 20; i++)
			add(new ImageIcon("Sprites/Running - Right/" + String.valueOf(i) + ".png"));
	}};
	/** ArrayList containing all the sprites of the Player running toward the left */
	ArrayList<ImageIcon> runningLeft = new ArrayList<ImageIcon>() {{
		for(int i = 0; i < 20; i++)
			add(new ImageIcon("Sprites/Running - Left/" + String.valueOf(i) + ".png"));
	}};

   /**
    * Creates a player GameObject at the default coordinates (100, 500),
    * with a collider box (width: 20, height: 50)
    */

	public Player() {
		super(100, 500, 20, 50, 1);
	}

   /**
    * Responds to input to move the player, and draw the correct sprites on the screen
    *
    * @param screen The graphics panel to draw the sprites on
    */

	public void move (Graphics screen) {
		if (jump_height > 0) { // If touched ground again
			jump = false; // Not jumping anymore
			jumping_time = 0; // Reset parabola
		}
		if (UP && able_to_jump) {
			jump = true;
			able_to_jump = false;
		}
		if (jump) {
			jumping_time += 2;
			jump_height = -(-0.03*jumping_time*jumping_time + 3.5*jumping_time);
			if (going_right) screen.drawImage(runningRight.get((int) (jumping_time / 20) + 4).getImage(), (int) (this.box.x - 15), (int) (this.box.y + jump_height - 5), null);
			else screen.drawImage(runningLeft.get((int) (jumping_time / 20) + 4).getImage(), (int) (this.box.x - 15), (int) (this.box.y + jump_height - 5), null);
		}
		if (RIGHT) {
			this.box.x += speed;
			going_right = true;
			if (!jump) {
				screen.drawImage(runningRight.get((int) sprite_counter % runningRight.size()).getImage(), (int) (this.box.x - 15), (int) (this.box.y + jump_height - 5), null);
				sprite_counter += 0.5;
			}
		} else if (LEFT) {
			going_right = false;
			this.box.x -= speed;
			if (!jump) {
				screen.drawImage(runningLeft.get((int) sprite_counter % runningRight.size()).getImage(), (int) (this.box.x - 15), (int) (this.box.y + jump_height - 5), null);
				sprite_counter += 0.5;
			}
		} else {
			sprite_counter = 0;
			if (!jump) {
				if (going_right) screen.drawImage(runningRight.get(12).getImage(), (int) (this.box.x - 15), (int) (this.box.y + jump_height - 5), null);
				else screen.drawImage(runningLeft.get(12).getImage(), (int) (this.box.x - 15), (int) (this.box.y + jump_height - 5), null);
			}
		}

		screen.setColor(Color.RED);
		//screen.drawRect((int) this.box.x, (int) (this.box.y + jump_height), (int) this.box.width, (int) this.box.height);
	      // To draw collider box
   }

   /**
    * Limits the movement of the player,
    * by responding to surrounding platforms/walls
    */

	public void constrain() {
		if (this.box.x > 760) this.box.x = 760;
		else if (this.box.x < 5) this.box.x = 5;

		for(GameObject i : GameManagement.currentWallPlatform) {
			if ((this.box.x+this.box.width > i.box.x && this.box.x < i.box.x+i.box.width)
					&& (this.box.y+jump_height <= i.box.y+i.box.height && this.box.y+jump_height >= i.box.y)
					&& jumping_time < 60) {
				jumping_time += 2*(Math.abs(59-jumping_time));  //under platform
			}

			if ((this.box.x+this.box.width > i.box.x && this.box.x < i.box.x)
					&& (this.box.y+jump_height < i.box.y+i.box.height+5 && this.box.y+this.box.height+jump_height > i.box.y+10))
				this.box.x = i.box.x - this.box.width-1;   //left of platform

			if ((this.box.x < i.box.x+i.box.width && this.box.x+this.box.width > i.box.x+i.box.width)
					&& (this.box.y+jump_height < i.box.y+i.box.height-5 && this.box.y+this.box.height+jump_height > i.box.y+10))
				this.box.x = i.box.x+i.box.width + 1;         //right of platform

			if ((this.box.x > i.box.x && this.box.x + this.box.width < i.box.x + i.box.width) && (this.box.y+this.box.height+jump_height > i.box.y
					&& this.box.y+this.box.height+jump_height < i.box.y+i.box.height)) {
				jumping_time = 116;
				this.box.y = i.box.y - this.box.height - 3;           //above platform
				if (jump_height > -5) able_to_jump = true;
			} else {
				if ((this.box.x+this.box.width < i.box.x || this.box.x > i.box.x+i.box.width)
						&& (jump_height > -1))
					this.box.y += 0.75;
			}
		}
	}

   /**
    * Displays the number of attempts the player has taken on the screen
    *
    * @param screen - The graphics panel to draw the text on
    */

	public void displayAttempts(Graphics screen) {
		screen.setColor(Color.DARK_GRAY);
		screen.fillRect(615, 70, 150, 35);
		screen.setColor(Color.WHITE);
		if (attempts < 10) screen.drawString("ATTEMPTS: " + attempts, 625, 95);
		else screen.drawString("ATTEMPTS: " + attempts, 619, 95);
	}

   /**
    * Checks for collision with surrounding obstacles, and fails the player accordingly
    *
    * @param obstacles The ArrayList containing the obstacle GameObjects to check for collision with
    */

	public void checkForFailure(ArrayList<GameObject> obstacles) {
		for(GameObject danger : obstacles) {
			if (danger.box.intersects(this.box.x, this.box.y + jump_height, this.box.width, this.box.height)) {
				alive = false;
			}
		}
	}

   /**
    * Checks for collision with the level's exit door, and succeeds the player accordingly
    *
    * @param door The door GameObject to check with collision with
    */

	public void checkForSuccess(GameObject door) {
		if(door.box.intersects(this.box.x, this.box.y + jump_height, this.box.width, this.box.height)) {
			succeeded = true;
		}
	}
}
