import java.awt.*; // Image
import java.awt.event.*; // event.KeyAdapter
import java.util.ArrayList;
import javax.swing.*;

@SuppressWarnings("serial")

public class Player extends GameObject {

	int tries = 0;
	boolean alive;
	double speed = 3;
	boolean jump = false;
	double jumping_time = 0;
	double jump_height;
	boolean falling = false;
	static boolean RIGHT, LEFT, UP;

	public Player() {
		super(100, 450, 20, 50);
		alive = true;
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
				//jump = false;

			} else {
				if ((this.box.x+this.box.width < i.box.x || this.box.x > i.box.x+i.box.width)
						&& (jumping_time > 95 || jumping_time < 4))
					this.box.y += 1.1;

			}
		}

	}

	public void displayTries(Graphics screen) {
		screen.setColor(Color.BLUE);
		screen.setFont(new Font("Sans Serif", Font.ITALIC, 36));
		screen.drawString("Tries: "+tries, 80, 50);
	}

	public void checkForFailure(ArrayList<GameObject> obstacles) {
		for(GameObject danger : obstacles) {
			if (danger.box.intersects(this.box.x, this.box.y + jump_height, this.box.width, this.box.height)) {
				alive = false;
				tries++;
				System.exit(0);
			}
		}
	}

	class KeyInput extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) RIGHT = true;
			else if (e.getKeyCode() == KeyEvent.VK_LEFT) LEFT = true;
			if (e.getKeyCode() == KeyEvent.VK_UP) UP = true;
		}

		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) RIGHT = false;
			else if (e.getKeyCode() == KeyEvent.VK_LEFT) LEFT = false;
			if (e.getKeyCode() == KeyEvent.VK_UP) UP = false;
		}
	}
}
