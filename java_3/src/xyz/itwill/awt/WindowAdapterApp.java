package xyz.itwill.awt;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WindowAdapterApp extends Frame {

	private static final long serialVersionUID = 1L;

	public WindowAdapterApp(String title) {

		super(title);

		// addWindowListener(new WindowEventHandlwTwo());

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		setBounds(800, 200, 300, 300);
		setVisible(true);
	}

	public static void main(String[] args) {
		new WindowAdapterApp("윈도우 이벤트");
	}

	/*
	public class WindowEventHandlwTwo extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
	}
	*/

}
