package xyz.itwill.awt;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;

public class FlowLayoutApp extends Frame {

	private static final long serialVersionUID = 1L;

	public FlowLayoutApp(String title) {
		super(title);

		//setLayout(new FlowLayout());

		Button button1 = new Button("BUTTON-1");
		Button button2 = new Button("BUTTON-2");
		Button button3 = new Button("BUTTON-3");
		Button button4 = new Button("BUTTON-4");
		Button button5 = new Button("BUTTON-5");
		
		add(button1);
		add(button2);
		add(button3);
		add(button4);
		add(button5);

		setBounds(600, 100, 400, 400);
		setVisible(true);

	}

	public static void main(String[] args) {
		new FlowLayoutApp("FlowLayout");
	}
}
