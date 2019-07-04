package character;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public abstract class Actor {
	public static final int MOVE_LEFT = KeyEvent.VK_LEFT;
	public static final int MOVE_RIGHT = KeyEvent.VK_RIGHT;
	public static final int MOVE_UP = KeyEvent.VK_UP;
	public static final int MOVE_DOWN = KeyEvent.VK_DOWN;
	public final int STEP = 2;
	public Maze[] mazes = new Maze[4];
	public int mazeNo = 0;

	public Actor() {
		mazes = new Maze[4];
		for (int m = 0; m < 4; m++) {
			mazes[m] = new Maze(m);
		}
	}

	public abstract void update();

	public abstract void draw(Graphics2D g);

	protected int x, y;

	BufferedImage image;

	protected int speed;

	protected int direction;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		if (x >= 0) {
			this.x = x;
		}
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		if (y >= 0) {
			this.y = y;
		}
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

}
