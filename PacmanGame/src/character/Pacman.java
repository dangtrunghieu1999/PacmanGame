package character;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import game.Keyboard;

public class Pacman extends Actor {

	BufferedImage packman;
	BufferedImage pills;
	int frame;
	int reqDir, curDir;

	public int columns, rows;
	ArrayList<String> lines = new ArrayList<String>();
	int mazeNo = 0;
	public char[][] cells;
	public boolean dead;
	public int totalPill;
	public int countPill;
	public boolean over;


	public Pacman() {
		over = false;
		dead = false;
		cells = mazes[mazeNo].getCells();
		rows = mazes[mazeNo].rows;// 260
		columns = mazes[mazeNo].columns;// 228

		y = mazes[mazeNo].row;
		x = mazes[mazeNo].column;

		frame = 0;
		totalPill = mazes[mazeNo].countPill;
		countPill = 0;
		curDir = reqDir = MOVE_RIGHT;

		try {
			packman = ImageIO.read(Pacman.class.getResource("/images/packman.png"));
			pills = ImageIO.read(Pacman.class.getResource("/images/packman_sheet.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void updatePlaying() {

		if (Keyboard.keys[KeyEvent.VK_LEFT]) {

			reqDir = KeyEvent.VK_LEFT;
		}
		if (Keyboard.keys[KeyEvent.VK_RIGHT]) {
			reqDir = KeyEvent.VK_RIGHT;

		}
		if (Keyboard.keys[KeyEvent.VK_DOWN]) {

			reqDir = KeyEvent.VK_DOWN;
		}
		if (Keyboard.keys[KeyEvent.VK_UP]) {
			reqDir = KeyEvent.VK_UP;
		}
	}

	public void update() {
		if (!dead) {
			updatePlaying();
			frame++;
			if (frame > 5) {
				frame = 0;
			}
			if (move(reqDir) == SUCCESS) {
				curDir = reqDir;

			} else {
				move(curDir);
			}

		}
	}

	static final int SUCCESS = 1, FAIL = 0;

	private int move(int reqDir) {

		switch (reqDir) {

		case KeyEvent.VK_LEFT: // 37
			if (x > 0 && mazes[mazeNo].charAt(y, x - 1) != WALL) {
				x -= 1;

				return SUCCESS;
			}
			if (x == 0 && cells[y][columns - 1] == STREET) {
				x = columns - 1;
				return SUCCESS;
			}

			break;
		case KeyEvent.VK_UP: // 38
			if (y > 0 && mazes[mazeNo].charAt(y - 1, x) != WALL) {
				y -= 1;
				return SUCCESS;
			}
			break;
		case KeyEvent.VK_RIGHT: // 39
			if (x < columns - 1 && mazes[mazeNo].charAt(y, x + 1) != WALL) {
				x += 1;
				return SUCCESS;
			}
			break;
		case KeyEvent.VK_DOWN: // 40
			if (y < rows - 1 && mazes[mazeNo].charAt(y + 1, x) != WALL) {
				y += 1;
				return SUCCESS;
			}
			break;
		}
		return FAIL;

	}

	final int radius = 14;
	final int widthPacman = 28;
	final int heightPacman = 28;
	final int distanceImage = 30;
	final int alignPill = 5;
	final int alignPower = 8;
	final char PILL = '2';
	final char POWER_PILL = '3';
	final char STREET = '1';
	final char WALL = '0';

	public void draw(Graphics2D g) {

		g.setColor(Color.WHITE);
		for (int r = 0; r < mazes[mazeNo].rows; r++) {
			for (int c = 0; c < mazes[mazeNo].columns; c++) {
				if (cells[r][c] == PILL) {
					g.drawImage(pills.getSubimage(46, 4, 12, 12), c * STEP - alignPill, r * STEP - alignPill, null);
				} else if (cells[r][c] == POWER_PILL) {
					g.drawImage(pills.getSubimage(3, 0, 20, 20), c * STEP - alignPower, r * STEP - alignPower, null);
				}
			}
		}
		g.drawImage(packman.getSubimage((frame / 2) * distanceImage, (curDir - MOVE_LEFT) * distanceImage, widthPacman,
				heightPacman), x * STEP - radius, y * STEP - radius, null);
		
	

	}

}
