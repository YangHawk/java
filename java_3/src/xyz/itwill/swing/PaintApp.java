package xyz.itwill.swing;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class PaintApp extends JFrame {
	private static final long serialVersionUID = 1L;

	private int x, y;// private Point point;

	public PaintApp(String title) {
		super(title);

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				x = e.getX();
				y = e.getY();
				
				repaint();
			}
		});

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(800, 200, 300, 400);
		setVisible(true);

	}

	public static void main(String[] args) {
		new PaintApp("Paint");
	}

	@Override
	public void paint(Graphics g) {
		
		super.paint(g);//=컨테이너 초기화이다.
		//System.out.println("이 메소드는 자동 호출이다.");
		/*
		g.setColor(Color.RED);
		g.drawRect(20, 40, 50, 50);
		g.fillRect(20, 100, 100, 50);

		g.setColor(Color.GREEN);
		g.drawOval(125, 175, 50, 50);
		g.fillOval(100, 250, 100, 50);

		g.setColor(Color.BLUE);
		g.drawLine(100, 320, 200, 320);
		g.drawString("홍길동", 100, 350);
		*/
		
		g.drawString("["+x+", "+y+"]", x, y);
		
	}
}
