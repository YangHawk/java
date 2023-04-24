package xyz.itwill.swing;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class PenguinMoveApp extends JFrame {

	private static final long serialVersionUID = 1L;

	private static final int JFRAME_WIDTH = 646;
	private static final int JFRAME_HEIGHT = 461;

	private static final int PENGUIN_SIZE = 60;

	private Image backImage;

	private Image[] penguins;

	private int penguinNo;

	private int penguinX, penguinY;

	public PenguinMoveApp(String title) {
		super(title);

		backImage = new ImageIcon(getClass().getResource("/images/back.jpg")).getImage();

		penguins = new Image[3];
		for (int i = 0; i < penguins.length; i++) {
			penguins[i] = new ImageIcon(getClass().getResource("/images/penguin" + (i + 1) + ".gif")).getImage();
		}

		penguinX = JFRAME_WIDTH / 2 - PENGUIN_SIZE / 2;
		penguinY = JFRAME_HEIGHT - PENGUIN_SIZE;

		setResizable(false);

		addKeyListener(new PenguinMoveHandle());

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// setBounds(700, 200, 646, 461);
		setBounds(700, 200, JFRAME_WIDTH, JFRAME_HEIGHT);
		setVisible(true);

	}

	public static void main(String[] args) {
		new PenguinMoveApp("펭귄 이동");

	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(backImage, 0, 0, JFRAME_WIDTH, JFRAME_HEIGHT, this);

		g.drawImage(penguins[penguinNo], penguinX, penguinY, PENGUIN_SIZE, PENGUIN_SIZE, this);

	}

	public class PenguinMoveHandle extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();

			switch (keyCode) {
			case KeyEvent.VK_LEFT:
				penguinX -= 10;
				if (penguinX <= 0) {
					penguinX = 0;
				}
				penguinNo++;
				penguinNo %= 3;
				repaint();
				break;
			case KeyEvent.VK_RIGHT:
				penguinX += 10;
				if (penguinX >= JFRAME_WIDTH - PENGUIN_SIZE) {
					penguinX = JFRAME_WIDTH - PENGUIN_SIZE;
				}
				penguinNo++;
				penguinNo %= 3;
				repaint();
				break;
			}

		}
	}
}
