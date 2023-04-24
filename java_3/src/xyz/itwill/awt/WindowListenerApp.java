package xyz.itwill.awt;

import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class WindowListenerApp extends Frame {

	private static final long serialVersionUID = 1L;

	public WindowListenerApp(String title) {
		super(title);

		addWindowListener(new WindowEventHandleOne());

		setBounds(800, 200, 300, 300);
		setVisible(true);
	}

	public static void main(String[] args) {
		new WindowListenerApp("윈도우 이벤트");
	}

	public class WindowEventHandleOne implements WindowListener {
		@Override
		public void windowOpened(WindowEvent e) {
			//System.out.println("windowOpened 메소드 호출");
		
		}

		@Override
		public void windowClosing(WindowEvent e) {
			//System.out.println("windowClosing 메소드 호출");
			System.exit(0);

		}

		@Override
		public void windowClosed(WindowEvent e) {
			System.out.println("windowClosed 메소드 호출");

		}

		@Override
		public void windowIconified(WindowEvent e) {
			System.out.println("windowIconified 메소드 호출");

		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			System.out.println("windowDeiconified 메소드 호출");

		}

		@Override
		public void windowActivated(WindowEvent e) {
			System.out.println("windowActivated 메소드 호출");

		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			System.out.println("windowDeactivated 메소드 호출");

		}
	}
}
