package xyz.itwill.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SwingApp extends JFrame {

	private static final long serialVersionUID = 1L;

	JTextField jTextField;
	JTextArea jTextArea;

	public SwingApp(String title) {
		super(title);

		jTextField = new JTextField();
		jTextArea = new JTextArea("[홍길동]님이 입장하였습니다.\n");

		jTextField.setFont(new Font("굴림체", Font.BOLD, 20));
		jTextArea.setFont(new Font("굴림체", Font.BOLD, 20));

		jTextArea.setBackground(Color.lightGray);

		jTextArea.setFocusable(false);

		//Container container = getContentPane();

		JScrollPane jScrollPane = new JScrollPane(jTextArea);

		getContentPane().add(jTextField, BorderLayout.SOUTH);
		// container.add(jTextArea, BorderLayout.CENTER); - 굳이 객체생성할 필요가 없네?
		getContentPane().add(jScrollPane, BorderLayout.CENTER);

		jTextField.addActionListener(new TextEventHandle());

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(800, 200, 400, 500);
		setVisible(true);

	}

	public static void main(String[] args) {
		new SwingApp("Swing");
	}

	public class TextEventHandle implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String text = jTextField.getText();
			if (!text.equals("")) {
				jTextArea.append("[홍길동]" + text + "\n");

				jTextArea.setCaretPosition(jTextArea.getText().length());

				jTextField.setText("");
			}

		}
	}

}
