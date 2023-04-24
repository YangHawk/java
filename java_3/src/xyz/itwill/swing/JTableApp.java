package xyz.itwill.swing;

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class JTableApp extends JFrame {
	private static final long serialVersionUID = 1L;

	public JTableApp(String title) {
		super(title);

		String[] columnName = { "학번", "이름", "전화번호" };
		String[][] rowData = { { "1000", "홍길동", "010-1234-5678" }
		, { "2000", "임꺽정", "010-4812-4812" }, { "3000", "전우치", "010-1532-9842" }
		, { "4000", "일지매", "010-6821-2128" }, { "5000", "장길산", "010-0546-9874" } };

		DefaultTableModel defaultTableModel = new DefaultTableModel(rowData, columnName);

		//String[] row = { "6000", "홍경래", "010-3198-5869" };
		//defaultTableModel.addRow(row);

		Vector<String> vector = new Vector<>();
		vector.add("6000");
		vector.add("홍경래");
		vector.add("010-3198-5869");
		defaultTableModel.addRow(vector);

		// JTable jTable = new JTable(rowData, columnName);
		JTable jTable = new JTable(defaultTableModel);

		JScrollPane jScrollPane = new JScrollPane(jTable);

		getContentPane().add(jScrollPane, BorderLayout.CENTER);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(700, 200, 500, 300);
		setVisible(true);

	}

	public static void main(String[] args) {
		new JTableApp("JTable 컴포넌트");
	}
}
