package xyz.itwill.awt;

import java.awt.Frame;

public class FrameTwoApp extends Frame {

	private static final long serialVersionUID = 1L;

	public FrameTwoApp(String title) {
		super(title);

		setBounds(600, 100, 400, 300);

		setVisible(true);

		setResizable(true);
	}

	public static void main(String[] args) {
		new FrameTwoApp("프레임 연습");
	}
}
