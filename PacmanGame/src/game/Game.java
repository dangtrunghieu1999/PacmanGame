package game;

import java.awt.Graphics2D;
import java.util.ArrayList;

import character.Actor;
import character.Background;
import character.Ghost;
import character.Pacman;

public class Game {
	ArrayList<Actor> actors = new ArrayList<Actor>();
	Background background;

	public Game() {
		background = new Background();
		actors.add(new Pacman());
		actors.add(new Ghost("C:/Users/Admin/eclipse-workspace/PacmanGame/src/images/pinkGhost.png"));
		actors.add(new Ghost("C:/Users/Admin/eclipse-workspace/PacmanGame/src/images/redGhost.png"));
		actors.add(new Ghost("C:/Users/Admin/eclipse-workspace/PacmanGame/src/images/yellowGhost.png"));
		actors.add(new Ghost("C:/Users/Admin/eclipse-workspace/PacmanGame/src/images/cyanGhost.png"));
	}

	public boolean touching(Actor a, Actor b) {

		return Math.abs(a.getY() - b.getY()) + Math.abs(a.getX() - b.getX()) < 3;
	}

	public void setupPill() {
		Pacman pacman = (Pacman) actors.get(0);
		if (pacman.cells[pacman.getY()][pacman.getX()] == '2') {
			pacman.cells[pacman.getY()][pacman.getX()] = '1';
		} else if (pacman.cells[pacman.getY()][pacman.getX()] == '3') {
			pacman.cells[pacman.getY()][pacman.getX()] = '1';
			for (int i = 1; i <= 4; i++) {
				Ghost ghost = (Ghost) actors.get(i);
				ghost.edibleCountDown = 500;
			}
		}

		for (int i = 1; i <= 4; i++) {
			Ghost ghost = (Ghost) actors.get(i);
			if (ghost.edibleCountDown > 0) {
				if (touching(ghost, pacman)) {
					ghost.setX(114);
					ghost.setY(98);
				}
				ghost.edibleCountDown--;
			} else {
				if (touching(ghost, pacman)) {
					pacman.dead = true;
					ghost.dead = true;
				}
			}
		}
	}

	public void update() {
		for (Actor actor : actors) {
			actor.update();
		}
		setupPill();
	}

	public void draw(Graphics2D g) {
		background.draw(g);
		for (Actor actor : actors) {
			actor.draw(g);
		}
	}

}
