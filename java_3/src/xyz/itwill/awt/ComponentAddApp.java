package xyz.itwill.awt;

import java.awt.Button;
import java.awt.Frame;

public class ComponentAddApp extends Frame {

	private static final long serialVersionUID = 1L;

	public ComponentAddApp(String title) {
		super(title);

		Button button = new Button("BUTTON");

		add(button);

		setBounds(600, 100, 300, 400);
		setVisible(true);
	}

	public static void main(String[] args) {
		new ComponentAddApp("컴포넌트 배치");
	}
}
