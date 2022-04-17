package school;

//��ü�б� ������ȸ
import java.awt.BorderLayout;
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
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.SwingConstants;

// ��ü�б� ������ȸ
import java.awt.BorderLayout;
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
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;

// ��ü�б� ������ȸ
public class S_ShowAllGrade extends JFrame {

	private JPanel contentPane;
	private JTextField totalCreField;
	private JTextField totalMGradeField;
	private JTextField totalAGradeField;
	private JTextField allMField;
	private JTextField allCreField;
	private JTextField allAField;
	
	public static String id;
	public static String url = "jdbc:mariadb://localhost:3306/school";
	public static Connection con;
	
	public S_ShowAllGrade(String id) throws Exception {
		setTitle("�л���� ���α׷�");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.white);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// �л� �Լ��� ����
		S_Management sg = new S_Management(url, con);
		
		//�л� ���� �̸� �޾ƿ���
		String Username = sg.getstName(id);
		//�л� ��ü �����
		Student s = sg.getStudent(id);
		
		JPanel menuPanel = new JPanel();
		menuPanel.setBounds(0, 0, 205, 563);
		contentPane.add(menuPanel);
		menuPanel.setLayout(null);
		
		JButton gradeBtn = new JButton("���� �ý���");
		gradeBtn.setBounds(0, 0, 205, 50);
		menuPanel.add(gradeBtn);
		
		JButton recentGradeLabel = new JButton("���б⼺����ȸ");
		recentGradeLabel.setBounds(10, 63, 152, 18);
		menuPanel.add(recentGradeLabel);
		recentGradeLabel.setBorderPainted(false);
		recentGradeLabel.setFocusPainted(false);
		recentGradeLabel.setContentAreaFilled(false);
		recentGradeLabel.setHorizontalAlignment(SwingConstants.LEFT);
		recentGradeLabel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				S_ShowRecentGrade srg;
				try {
					srg = new S_ShowRecentGrade(id);
					srg.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JButton allGradeLabel = new JButton("��ü������ȸ");
		allGradeLabel.setBounds(10, 94, 152, 18);
		allGradeLabel.setBorderPainted(false);
		allGradeLabel.setFocusPainted(false);
		allGradeLabel.setHorizontalAlignment(SwingConstants.LEFT);
		allGradeLabel.setEnabled(false);
		menuPanel.add(allGradeLabel);
		
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
		
		
		JLabel titleLabel = new JLabel("��ü������ȸ");
		titleLabel.setBounds(219, 13, 109, 18);
		contentPane.add(titleLabel);
		
		JPanel semesterPanel = new JPanel();
		semesterPanel.setBounds(910, 56, 245, 28);
		semesterPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED,null,Color.black));
		contentPane.add(semesterPanel);
		semesterPanel.setLayout(null);
		
		//�б� �޺��ڽ�
		String[] listSemester = { "����", "19-1","19-2","20-1","20-2", "21-1","21-2"};
		JComboBox semesterBox = new JComboBox(listSemester);
		semesterBox.setModel(new DefaultComboBoxModel(new String[] {"����", "2019-1", "2019-2", "2020-1", "2020-2", "2021-1", "2021-2"}));
		semesterBox.setBounds(65, 1, 100, 24);
		semesterPanel.add(semesterBox);
		
		//��ȸ ��ư
		JButton showBtn = new JButton("��ȸ");
		showBtn.setBounds(170, 1, 68, 24);
		semesterPanel.add(showBtn);

		//�б� ��
		JLabel semesterLabel = new JLabel("�б�");
		semesterLabel.setBounds(14, 5, 62, 18);
		semesterPanel.add(semesterLabel);
		
		// ǥ ���� - ��ü ���� ��ȸ
		String colNames[] = {"�м���ȣ", "�����", "�̼�����", "�̼�����","���ǽð�","������","����","����","���"};// �� ������
		Object data[][] = {};// �� ������
		DefaultTableModel model = new DefaultTableModel(data, colNames);		
		
		//��ȸ�ϱ� ����
		showBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//�б� �� �޾ƿ���
				String semester = (String) semesterBox.getSelectedItem();
				
				// �����б� �ҷ�����
				model.setNumRows(0); // ���� ǥ ������ �ʱ�ȭ 
				List<LectureScore> sc;
				try {
					sc = sg.showGrade(s.getstNum(), semester);
					int length = sc.size();
					for (int i=0; i< length; i++) {
						Object[] row;
						row = new Object[] { sc.get(i).getLeNum(), sc.get(i).getLeName(), sc.get(i).getDivision(),
								sc.get(i).getCredit(),sc.get(i).getTime(),sc.get(i).getprName(),
								sc.get(i).getTotalScore(),sc.get(i).getScoreCredit(),sc.get(i).getGrade() };
						model.addRow(row);	
					}
					
					// ���� �� ����
					int creditCnt = 0;
					for (int i=0; i<length; i++) {
						if(sc.get(i).getDivision().substring(0,1).equals("��"))
						creditCnt = creditCnt + sc.get(i).getCredit();
					}
					totalMGradeField.setText("���� �� ����:"+creditCnt);
					
					// �� �̼� ����
					int allCreditCnt = 0;
					for (int i=0; i<length; i++) {
						allCreditCnt = allCreditCnt + sc.get(i).getCredit();
					}
					totalCreField.setText("�� �̼� ����:"+ allCreditCnt);
				
					// �� ����
					float allScoreCredit = 0;
					for(int i=0; i<length; i++) {
						allScoreCredit += sc.get(i).getScoreCredit();
					}
					// �Ҽ� ��° ¥�� ���� ǥ�� (�� ��° �ڸ� �ݿø�)
					double recentScoreAvg = allScoreCredit/(length);
					totalAGradeField.setText("�� ����:" + String.format("%.2f", recentScoreAvg));
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
						
			}	
		});
				
		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED,null,Color.black));
		mainPanel.setBounds(219, 85, 936, 455);
		contentPane.add(mainPanel);
		mainPanel.setBackground(null);
		mainPanel.setLayout(null);
		JTable table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(14, 13, 908, 348);
		scrollPane.setBorder(null);
		mainPanel.add(scrollPane);
		
		JPanel panel = new JPanel();
		panel.setBounds(584, 360, 338, 36);
		mainPanel.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		totalMGradeField = new JTextField();
		totalMGradeField.setText("���� �� ����:");
		panel.add(totalMGradeField, BorderLayout.WEST);
		totalMGradeField.setColumns(10);
		
		totalCreField = new JTextField();
		totalCreField.setText("�� �̼� ����:");
		panel.add(totalCreField);
		totalCreField.setColumns(10);
		
		totalAGradeField = new JTextField();
		totalAGradeField.setText("�� ����:");
		panel.add(totalAGradeField, BorderLayout.EAST);
		totalAGradeField.setColumns(10);
		
		JLabel allTotal = new JLabel("��ü����");
		allTotal.setBounds(414, 374, 62, 36);
		mainPanel.add(allTotal);
		
		JPanel allTotalPanel = new JPanel();
		allTotalPanel.setBounds(14, 409, 908, 36);
		mainPanel.add(allTotalPanel);
		
		JPanel totalPanel = new JPanel();
		allTotalPanel.add(totalPanel);
	
		// ��ü �б⵿�� ������ ���� ���� ���ϱ�
		int allSubCnt = sg.getAllSubCnt(id);
		
		allMField = new JTextField();
		allMField.setColumns(10);
		int allMCredit = sg.getAllMCredit(id);
		totalPanel.add(allMField, BorderLayout.WEST);
		allMField.setText("���� �� ����:" + allMCredit);
	
		allCreField = new JTextField();
		allCreField.setColumns(10);
		int allCreditCnt = sg.getAllCreditCnt(id);
		totalPanel.add(allCreField, BorderLayout.CENTER);
		allCreField.setText("�� �̼� ����:"+allCreditCnt);
		
		allAField = new JTextField();
		allAField.setText("�� ����:");
		allAField.setColumns(10);
		double allScoreAvg = sg.getAllScoreAvg(id)/allSubCnt;
		totalPanel.add(allAField, BorderLayout.EAST);
		allAField.setText("�� ����:"+String.format("%.2f", allScoreAvg));
		sg.setstScore(id, allScoreAvg);
	}
}
