package character;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Background {
	BufferedImage[] mazeImages = new BufferedImage[4];
	int mazeNo = 0;

	public Background() {
		try {
			for (int m = 0; m < 4; m++) {
				mazeImages[m] = ImageIO.read(Pacman.class.getResource("/images/" + m + m + ".png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void draw(Graphics2D g) {
		g.drawImage(mazeImages[mazeNo], 0, 0, null);
	}
}
