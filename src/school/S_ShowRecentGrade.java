package school;

//���б� ������ȸ
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

// ���б� ������ȸ
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

// ���б� ������ȸ
public class S_ShowRecentGrade extends JFrame {
	private JPanel contentPane;
	
	public static String id;
	public static String url = "jdbc:mariadb://localhost:3306/school";
	public static Connection con;

	public S_ShowRecentGrade(String id) throws Exception {
		setTitle("�л���� ���α׷�");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.white);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// �л� �Լ��� ����
		S_Management smg = new S_Management(url, con);
		
		//�л� ���� �̸� �޾ƿ���
		String Username = smg.getstName(id);
		
		JPanel menuPanel = new JPanel();
		menuPanel.setBounds(0, 0, 205, 563);
		contentPane.add(menuPanel);
		menuPanel.setLayout(null);

		JButton gradeBtn = new JButton("���� �ý���");
		gradeBtn.setBounds(0, 0, 205, 50);
		menuPanel.add(gradeBtn);

		JButton recentGradeLabel = new JButton("���б⼺����ȸ");
		recentGradeLabel.setBounds(10, 63, 152, 18);
		recentGradeLabel.setBorderPainted(false);
		recentGradeLabel.setFocusPainted(false);
		recentGradeLabel.setHorizontalAlignment(SwingConstants.LEFT);
		recentGradeLabel.setEnabled(false);
		menuPanel.add(recentGradeLabel);

		JButton allGradeLabel = new JButton("��ü������ȸ");
		allGradeLabel.setBounds(10, 94, 152, 18);
		menuPanel.add(allGradeLabel);
		allGradeLabel.setBorderPainted(false);
		allGradeLabel.setFocusPainted(false);
		allGradeLabel.setContentAreaFilled(false);
		allGradeLabel.setHorizontalAlignment(SwingConstants.LEFT);
		allGradeLabel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				S_ShowAllGrade sag;
				try {
					sag = new S_ShowAllGrade(id); // ��ü ������ȸ
					sag.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		JButton homeBtn = new JButton("Ȩ ȭ��");
		homeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				S_Home sHome;
				try {
					sHome = new S_Home(id);
					sHome.setVisible(true);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} // Ȩ ȭ�� ����(�л�)
			}
		});
		homeBtn.setBounds(45, 500, 105, 27);
		menuPanel.add(homeBtn);

		JLabel titleLabel = new JLabel("���б⼺����ȸ");
		titleLabel.setBounds(219, 13, 109, 18);
		contentPane.add(titleLabel);

		JPanel selectPanel = new JPanel();
		selectPanel.setBounds(988, 56, 167, 28);
		contentPane.add(selectPanel);
		selectPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, Color.black));
		selectPanel.setLayout(null);

		// �г⵵�б� ��
		JLabel semesterLabel = new JLabel();
		semesterLabel.setBounds(40, 5, 174, 18);
		selectPanel.add(semesterLabel);

		// ǥ ����
		String colNames[] = { "�����", "�̼�����", "�߰����", "�⸻���", "����", "�⼮", "����", "����" };// �� ������
		Object data[][] = {};// �� ������
		DefaultTableModel model = new DefaultTableModel(data, colNames);
		List<LectureScore> sc;

		// �ֱ� �б� �ҷ�����
		String maxYear = smg.getMaxYear(id);

		// �ֱ� ���� �ҷ�����
		sc = smg.showRecentGrade(id, maxYear);

		// �г⵵ �б� �� �ҷ�����
		//String yearText = maxYear.substring(1, 3);
		//String semesterText = maxYear.substring(4, 5);
		//String labelText = maxYear;
		semesterLabel.setText(maxYear);

		// ���� �ҷ����� - ���б� ����
		int length = sc.size();
		for (int i = 0; i < length; i++) {
			Object[] row;
			try {
				row = new Object[] { sc.get(i).getLeName(), sc.get(i).getCredit(), sc.get(i).getMidScore(),
						sc.get(i).getFinScore(), sc.get(i).getAssiScore(), sc.get(i).getAttendScore(),
						sc.get(i).getTotalScore(), sc.get(i).getGrade() };
				model.addRow(row);
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}

		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, Color.black));
		mainPanel.setBounds(219, 85, 936, 455);
		contentPane.add(mainPanel);
		mainPanel.setBackground(null);
		mainPanel.setLayout(null);
		JTable table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(14, 13, 908, 429);
		scrollPane.setBorder(null);
		mainPanel.add(scrollPane);

	}
}
