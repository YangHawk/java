package xyz.itwill.awt;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;

public class BorderLayoutApp extends Frame {

	private static final long serialVersionUID = 1L;

	public BorderLayoutApp(String title) {
		super(title);

		// setLayout(new BorderLayout());

		Button button1 = new Button("Center");
		Button button2 = new Button("East");
		Button button3 = new Button("West");
		Button button4 = new Button("South");
		Button button5 = new Button("North");

		add(button1, BorderLayout.CENTER);
		add(button2, BorderLayout.EAST);
		add(button3, BorderLayout.WEST);
		add(button4, BorderLayout.SOUTH);
		add(button5, BorderLayout.NORTH);

		setBounds(600, 100, 400, 400);
		setVisible(true);

	}

	public static void main(String[] args) {
		new BorderLayoutApp("BorderLayout");
	}
}
