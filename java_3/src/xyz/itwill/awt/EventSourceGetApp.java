package xyz.itwill.awt;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.Locale;

import javax.swing.border.Border;

public class EventSourceGetApp extends Frame {

	private static final long serialVersionUID = 1L;

	Button red, green, blue, white;
	Canvas canvas;

	public EventSourceGetApp(String title) {
		super(title);

		red = new Button("RED");
		green = new Button("GREEN");
		blue = new Button("BLUE");
		white = new Button("WHITE");

		canvas = new Canvas();

		Panel panel = new Panel();
		panel.setLayout(new GridLayout(1, 4));
		panel.add(red);
		panel.add(green);
		panel.add(blue);
		panel.add(white);

		add(panel, BorderLayout.NORTH);
		add(canvas, BorderLayout.CENTER);

		panel.setFont(new Font(Font.DIALOG, Font.BOLD, 20));

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}

		});

		
		/*
		red.addActionListener(new RedButtonHandle());
		green.addActionListener(new GreenButtonHandle());
		blue.addActionListener(new BlueButtonHandle());
		white.addActionListener(new WhiteButtonHandle());
		*/

		red.addActionListener(new ColorButtonHandler());
		green.addActionListener(new ColorButtonHandler());
		blue.addActionListener(new ColorButtonHandler());
		white.addActionListener(new ColorButtonHandler());

		setBounds(800, 200, 400, 400);
		setVisible(true);
	}

	public static void main(String[] args) {
		new EventSourceGetApp("캔버스");
	}

	/*
	public class RedButtonHandle implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			canvas.setBackground(Color.RED);

		}
	}

	public class GreenButtonHandle implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			canvas.setBackground(Color.GREEN);
		}
	}

	public class BlueButtonHandle implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			canvas.setBackground(Color.BLUE);
		}
	}

	public class WhiteButtonHandle implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			canvas.setBackground(Color.WHITE);
		}
	}
	*/

	public class ColorButtonHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			//Button eventSource = (Button) e.getSource();
			Object eventSource = e.getSource();
			
			if(eventSource == red) {
				canvas.setBackground(Color.RED);
			} else if(eventSource == green) {
				canvas.setBackground(Color.GREEN);
			} else if(eventSource == blue) {
				canvas.setBackground(Color.BLUE);
			} else if(eventSource == white) {
				canvas.setBackground(Color.WHITE);
			}
		}
	}
}
