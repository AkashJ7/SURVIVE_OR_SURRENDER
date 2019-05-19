import java.awt.*; // Image
import java.util.ArrayList;
import javax.swing.*;

@SuppressWarnings("serial")

public class Player extends GameObject {

	int attempts = 1;
	boolean alive = true;
	boolean succeeded = false;
	double speed = 3;
	boolean jump = false;
	double jumping_time = 0;
	double jump_height;
	boolean falling = false;
	boolean RIGHT, LEFT, UP;
	double sprite_counter = 0;
	boolean going_right = true;
	ArrayList<ImageIcon> runningRight = new ArrayList<ImageIcon>() {{
		for(int i = 0; i < 20; i++)
			add(new ImageIcon("Sprites/Running - Right/" + String.valueOf(i) + ".png"));
	}};
	ArrayList<ImageIcon> runningLeft = new ArrayList<ImageIcon>() {{
		for(int i = 0; i < 20; i++)
			add(new ImageIcon("Sprites/Running - Left/" + String.valueOf(i) + ".png"));
	}};

	public Player() {
		super(100, 500, 20, 50, 1);
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
		screen.drawRect((int) this.box.x, (int) (this.box.y + jump_height), (int) this.box.width, (int) this.box.height);
	}

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

			if ((this.box.x+this.box.width > i.box.x && this.box.x < i.box.x+i.box.width) && (this.box.y+this.box.height+jump_height > i.box.y
					&& this.box.y+this.box.height+jump_height < i.box.y+i.box.height)) {
				this.box.y = i.box.y - this.box.height - 5;           //above platform
				jumping_time = 116;
			} else {
				if ((this.box.x+this.box.width < i.box.x || this.box.x > i.box.x+i.box.width)
						&& (jump_height > -1))
					this.box.y += 0.75;
			}
		}
	}

	public void displayAttempts(Graphics screen) {
		screen.setColor(Color.DARK_GRAY);
		screen.fillRect(615, 70, 150, 35);
		screen.setColor(Color.WHITE);
		if (attempts < 10) screen.drawString("ATTEMPTS: " + attempts, 625, 95);
		else screen.drawString("ATTEMPTS: " + attempts, 619, 95);
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
