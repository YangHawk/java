package xyz.itwill.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DigitalClockApp extends JFrame {
	private static final long serialVersionUID = 1L;

	JLabel clockLabel;
	JButton startButton, stopButton;

	private boolean isRun;

	public DigitalClockApp(String title) {
		super(title);

		isRun = true;

		/*
		 * Date now = new Date();
		 * SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
		 * String printDate = dateFormat.format(now);
		 * 
		 * clockLabel = new JLabel(printDate, JLabel.CENTER);
		 */

		clockLabel = new JLabel("", JLabel.CENTER);
		clockLabel.setFont(new Font("굴림체", Font.BOLD, 30));
		clockLabel.setForeground(Color.DARK_GRAY);

		startButton = new JButton("다시 실행");
		stopButton = new JButton("일시 중지");
		startButton.setFont(new Font("굴림체", Font.BOLD, 20));
		stopButton.setFont(new Font("굴림체", Font.BOLD, 20));
		JPanel jPanel = new JPanel();
		jPanel.add(startButton);
		jPanel.add(stopButton);

		startButton.setEnabled(false);

		getContentPane().add(clockLabel, BorderLayout.CENTER);
		getContentPane().add(jPanel, BorderLayout.SOUTH);

		new ClockThread().start();

		startButton.addActionListener(new ClockEventHandle());
		stopButton.addActionListener(new ClockEventHandle());

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(700, 200, 600, 200);
		setVisible(true);
	}

	public static void main(String[] args) {
		new DigitalClockApp("디지털 시계");

	}

	public class ClockThread extends Thread {
		@Override
		public void run() {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");

			while (true) {
				if (isRun) {

					/*
					 * Date now = new Date();
					 * String printDate = dateFormat.format(now);
					 * 
					 * clockLabel.setText(printDate);
					 */

					clockLabel.setText(dateFormat.format(new Date()));
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO: handle exception
					e.printStackTrace();

				}
			}

		}
	}

	public class ClockEventHandle implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Object eventSource = e.getSource();

			if (eventSource == startButton) {
				startButton.setEnabled(false);
				stopButton.setEnabled(true);

				isRun = true;
			} else if (eventSource == stopButton) {
				startButton.setEnabled(true);
				stopButton.setEnabled(false);

				isRun = false;
			}
		}
	}
}
