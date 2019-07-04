package game;

import javax.swing.JFrame;

public class Windows extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GameScreen game;

	public Windows() {
		init();
	}

	public void start() {
		game.start();
	}

	private void init() {
		setTitle("Pacman");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		game = new GameScreen();
		getContentPane().add(game);
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
}
