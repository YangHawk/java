package xyz.itwill.io;

import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

public class NotepadApp extends JFrame {
	private static final long serialVersionUID = 1L;

	JTextArea jTextArea;
	JMenuItem init, open, save, exit;

	FileDialog openDialog, saveDialog;

	private String filepath;

	public NotepadApp(String title) {
		super(title);

		JMenuBar jMenuBar = new JMenuBar();

		JMenu jMenu = new JMenu("파일(F)");
		jMenu.setMnemonic('F');

		init = new JMenuItem("새로 만들기(N)", 'N');
		open = new JMenuItem("열기(O)", 'O');
		save = new JMenuItem("저장(S)", 'S');
		exit = new JMenuItem("끝내기(X)", 'X');

		init.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
		open.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.ALT_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK));
		save.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK));

		jMenu.add(init);
		jMenu.add(open);
		jMenu.add(save);
		jMenu.addSeparator();
		jMenu.add(exit);

		jMenuBar.add(jMenu);

		setJMenuBar(jMenuBar);

		jTextArea = new JTextArea();
		jTextArea.setFont(new Font("굴림체", Font.BOLD, 30));
		JScrollPane jScrollPane = new JScrollPane(jTextArea);

		getContentPane().add(jScrollPane, BorderLayout.CENTER);

		openDialog = new FileDialog(this, "열기", FileDialog.LOAD);
		saveDialog = new FileDialog(this, "저장", FileDialog.SAVE);

		init.addActionListener(new NotepadEventHandle());
		open.addActionListener(new NotepadEventHandle());
		save.addActionListener(new NotepadEventHandle());
		exit.addActionListener(new NotepadEventHandle());

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(450, 150, 1000, 600);
		setVisible(true);

	}

	public static void main(String[] args) {
		new NotepadApp("제목 없음 - Java 메모장");
	}

	public class NotepadEventHandle implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Object eventSource = e.getSource();

			if (eventSource == init) {
				jTextArea.setText("");
				filepath = "";
				setTitle("제목 없음 - Java 메모장");
			} else if (eventSource == open) {
				openDialog.setVisible(true);

				if (openDialog.getFile() == null)
					return;

				filepath = openDialog.getDirectory() + openDialog.getFile();

				try {
					BufferedReader in = new BufferedReader(new FileReader((filepath)));

					jTextArea.setText("");

					while (true) {
						String text = in.readLine();
						if (text == null)
							break;
						jTextArea.append(text + "\n");
					}
					in.close();

					setTitle(openDialog.getFile() + " - Java 메모장");
				} catch (FileNotFoundException exception) {
					JOptionPane.showMessageDialog(null, "파일을 찾을 수 없습니다.");
				} catch (IOException exception) {
					JOptionPane.showMessageDialog(null, "프로그램에 문제가 발생하였습니다.");
				}

			} else if (eventSource == save) {

				if (filepath == null || filepath.equals("")) {

					saveDialog.setVisible(true);

					if (saveDialog.getFile() == null)
						return;

					filepath = saveDialog.getDirectory() + saveDialog.getFile();

					setTitle(saveDialog.getFile() + " - Java 메모장");
				}
				try {
					BufferedWriter out = new BufferedWriter(new FileWriter(filepath));

					String text = jTextArea.getText();

					out.write(text);

					out.close();

				} catch (IOException exception) {
					JOptionPane.showMessageDialog(null, "프로그램에 문제가 발생하였습니다.");
				}

			} else if (eventSource == exit) {
				System.exit(0);
			}
		}
	}
}
