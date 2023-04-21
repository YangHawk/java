package xyz.itwill.awt;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;

public class PanelApp extends Frame {

	private static final long serialVersionUID = 1L;

	public PanelApp(String title) {
		super(title);

		TextField textField = new TextField();
		TextArea textArea = new TextArea();

		Button red = new Button("RED");
		Button green = new Button("GREEN");
		Button blue = new Button("BLUE");

		/*
		add(red, BorderLayout.NORTH);
		add(green, BorderLayout.NORTH);
		add(blue, BorderLayout.NORTH);
		*/

		Panel panel = new Panel();

		panel.add(red);
		panel.add(green);
		panel.add(blue);

		add(panel, BorderLayout.NORTH);
		add(textArea, BorderLayout.CENTER);
		add(textField, BorderLayout.SOUTH);

		panel.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 16));
		textArea.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		textField.setFont(new Font(Font.SANS_SERIF, Font.BOLD + Font.ITALIC, 18));

		/*
		red.setForeground(new Color(255, 0, 0));
		green.setForeground(new Color(0, 255, 0));
		blue.setForeground(new Color(0, 0, 255));
		*/

		red.setForeground(Color.RED);
		green.setForeground(Color.GREEN);
		blue.setForeground(Color.BLUE);

		textArea.setBackground(Color.PINK);

		red.setEnabled(false);
		textArea.setFocusable(false);

		setBounds(600, 100, 300, 400);
		setVisible(true);

	}

	public static void main(String[] args) {
		new PanelApp("Panel");
	}
}
