package xyz.itwill.net;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatClientApp extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	private JTextArea jTextArea;
	private JTextField jTextField;

	private Socket socket;

	private BufferedReader in;

	private PrintWriter out;

	private String aliasName;

	public ChatClientApp(String title) {
		super(title);

		jTextArea = new JTextArea();
		jTextField = new JTextField();

		jTextArea.setFont(new Font("굴림체", Font.BOLD, 20));
		jTextField.setFont(new Font("굴림체", Font.BOLD, 20));

		jTextArea.setFocusable(false);

		JScrollPane jScrollPane = new JScrollPane(jTextArea);

		getContentPane().add(jScrollPane, BorderLayout.CENTER);
		getContentPane().add(jTextField, BorderLayout.SOUTH);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(700, 200, 400, 500);
		setVisible(true);

		jTextField.addActionListener(this);

		try {
			socket = new Socket("192.168.13.14", 5000);

			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			out = new PrintWriter(socket.getOutputStream(), true);

		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "서버에 접속할 수 없습니다.", "접속오류", JOptionPane.ERROR_MESSAGE);

			System.exit(0);
		}

		while (true) {
			aliasName = JOptionPane.showInputDialog(this, "대화명을 입력해 주세요.", "대화명 입력", JOptionPane.QUESTION_MESSAGE);

			String regEx = "^[가-힣]{2,6}$";
			if (Pattern.matches(regEx, aliasName))
				break;
			JOptionPane.showMessageDialog(this, "정상적인 대화명을 입력해 주세요.", "입력오류", JOptionPane.ERROR_MESSAGE);

		}

		out.println(aliasName);

		while (true) {
			try {
				jTextArea.append(in.readLine() + "\n");

				jTextArea.setCaretPosition(jTextArea.getText().length());
			} catch (IOException e) {
				JOptionPane.showMessageDialog(this, "서버와 연결이 끊어졌습니다.", "접속오류", JOptionPane.ERROR_MESSAGE);
				System.exit(0);
			}
		}
	}

	public static void main(String[] args) {
		new ChatClientApp("자바채팅");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String message = jTextField.getText();

		if (!message.equals("")) {
			out.println(message);
			jTextField.setText("");
		}
	}
}
