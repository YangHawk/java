package xyz.itwill.awt;

import java.awt.Frame;

public class FrameOneApp {
	public static void main(String[] args) {
		Frame frame = new Frame("프레임 연습");

		frame.setVisible(true);

		frame.setSize(400, 300);

		frame.setLocation(600, 100);
	}

}
