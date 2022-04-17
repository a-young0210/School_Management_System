package school;

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
import java.sql.SQLException;
import java.util.List;
import javax.swing.SwingConstants;

// ��������
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

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
import java.awt.font.TextAttribute;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;

// ��������
public class P_Grade extends JFrame {
	public static String id;
	public static String url = "jdbc:mariadb://localhost:3306/school";
	public static Connection con;
	private JPanel contentPane;
	private JTextField totalNumField;
	private JTextField avgField;
	public Lecture lec = null;

	public String semester;
	
	public P_Grade(String id) throws Exception {
		setTitle("�л���� ���α׷�");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.white);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Class.forName("org.mariadb.jdbc.Driver");
		// sql �����ϱ� ���� ���̵�� ��й�ȣ �Է�

		con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/school", "root", "1234");

		// ���� �Լ��� ����
		P_Management pmg = new P_Management(url, con);
		Functions func = new Functions(url, con);

		// �ش� ������ �̸� �ҷ�����
		String prName = pmg.checkNamePr(id);

		JPanel menuPanel = new JPanel();
		menuPanel.setBounds(0, 0, 205, 563);
		contentPane.add(menuPanel);
		menuPanel.setLayout(null);

		JButton GradeAdBtn = new JButton("���� �ý���");
		GradeAdBtn.setBounds(0, 0, 205, 50);
		menuPanel.add(GradeAdBtn);

		JButton gradeMenuLabel = new JButton("��������");
		gradeMenuLabel.setBounds(10, 63, 152, 18);
		gradeMenuLabel.setBorderPainted(false);
		gradeMenuLabel.setFocusPainted(false);
		gradeMenuLabel.setContentAreaFilled(false);
		gradeMenuLabel.setHorizontalAlignment(SwingConstants.LEFT);
		gradeMenuLabel.setEnabled(false);
		menuPanel.add(gradeMenuLabel);

		JLabel titleLabel = new JLabel("��������");
		titleLabel.setBounds(219, 13, 109, 18);
		contentPane.add(titleLabel);

		// Ȩ ȭ��
		JButton homeBtn = new JButton("Ȩ ȭ��");
		homeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				P_Home phome;
				try {
					phome = new P_Home(id);
					phome.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		homeBtn.setBounds(45, 500, 105, 27);
		menuPanel.add(homeBtn);

		JPanel selectPanel = new JPanel();
		selectPanel.setBounds(219, 38, 936, 35);
		contentPane.add(selectPanel);
		selectPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, Color.black));
		selectPanel.setLayout(null);

		// �г⵵ �޺��ڽ�
		String[] listYear = { "����", "2019", "2020", "2021" };
		JComboBox yearBox = new JComboBox(listYear);
		yearBox.setModel(new DefaultComboBoxModel(new String[] { "\uC120\uD0DD", "2019", "2020", "2021" }));
		yearBox.setBounds(70, 3, 111, 28);
		selectPanel.add(yearBox);

		// �б� �޺��ڽ�
		String[] listSemester = { "����", "1", "2" };
		JComboBox semesterBox = new JComboBox(listSemester);
		semesterBox.setBounds(253, 3, 111, 28);
		selectPanel.add(semesterBox);

		// �г⵵ ��
		JLabel yearLabel = new JLabel("�г⵵");
		yearLabel.setBounds(14, 6, 62, 18);
		selectPanel.add(yearLabel);

		// �б� ��
		JLabel semesterLabel = new JLabel("�б�");
		semesterLabel.setBounds(206, 6, 45, 18);
		selectPanel.add(semesterLabel);

		// ����� ��
		JLabel nameLabel = new JLabel("�����");
		nameLabel.setBounds(617, 6, 45, 18);
		selectPanel.add(nameLabel);

		// ǥ ���� (������ȸ)
		String colNames[] = { "�����", "�̸�", "�й�", "�߰����", "�⸻���", "����", "�⼮", "����", "����" };// �� ������
		Object data[][] = {};// �� ������
		DefaultTableModel model = new DefaultTableModel(data, colNames);
		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, Color.black));
		mainPanel.setBounds(219, 85, 936, 455);
		contentPane.add(mainPanel);
		mainPanel.setBackground(null);
		mainPanel.setLayout(null);
		JTable table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(14, 37, 908, 371);
		scrollPane.setBorder(null);
		mainPanel.add(scrollPane);

		// �����ο�, ��� panel
		JPanel panel = new JPanel();
		panel.setBounds(682, 407, 240, 35);
		mainPanel.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		// �����ο� �ʵ� - ������ �Է¹޾ƾ���
		totalNumField = new JTextField();
		totalNumField.setText("�����ο�:");
		panel.add(totalNumField, BorderLayout.WEST);
		totalNumField.setColumns(10);

		// ��� �� - ������ �Է� �޾ƾ���
		avgField = new JTextField();
		avgField.setText("���:");
		panel.add(avgField, BorderLayout.CENTER);
		avgField.setColumns(10);

		// ���� ��ȸ
		// �б�,�г⵵ �˻� ��ư
		JLabel subNameLabel = new JLabel();
		JButton findBtn = new JButton("������ȸ");
		findBtn.setBounds(385, 3, 95, 27);
		selectPanel.add(findBtn);

		
		//���� ��ȸ��ư
		findBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String YY = (String) yearBox.getSelectedItem();
				String SS = (String) semesterBox.getSelectedItem();
				semester = YY + "-" + SS;

				// ��ȸ ��ư
				JButton showBtn = new JButton("��ȸ");
				selectPanel.add(showBtn);
				showBtn.setBounds(838, 3, 93, 27);

				try {
					List<String> leName = pmg.getLeInSem(prName, semester);

					leName.add(0, "������");
					// ����� �޺��ڽ�
					JComboBox nameBox = new JComboBox(leName.toArray());
					selectPanel.add(nameBox);
					nameBox.setBounds(676, 3, 150, 28);

					// ��ȸ ��ư - ����� �̿��ϱ�
					showBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							// ���õ� ���� �޾ƿ���
							String selectName = (String) nameBox.getSelectedItem();
							System.out.println(selectName);
							pmg.setLeName(selectName);

							// ����� �� - ������ �Է¹޾ƾ� ��
							subNameLabel.setText(selectName);
							mainPanel.add(subNameLabel);
							subNameLabel.setBounds(414, 13, 89, 18);

							// �г⵵, �б� �޾ƿ���
							String YY = (String) yearBox.getSelectedItem();
							String SS = (String) semesterBox.getSelectedItem();
							String semester = YY + "-" + SS;

							// ����ڰ� ������ ���� ��ü�� �޾ƿ���
							try {
								ResultSet rs = pmg.searchLecture("������", "�����", selectName, semester);

								while (rs.next()) {

									// ���� �޼ҵ� ���� �޾ƿͼ� �Ҵ���.
									String leName1 = rs.getString(1);
									String leNum = rs.getString(2);
									String semester1 = rs.getString(3);
									int limGrade = rs.getInt(4);
									String prName = rs.getString(5);
									String type = rs.getString(6);
									String time = rs.getString(7);
									int credit = rs.getInt(8);
									int attMem = rs.getInt(9);
									int limMem = rs.getInt(10);
									String preLecture = rs.getString(11);
									String division = rs.getString(12);
									// ���� ��ü ����
									lec = new Lecture(leName1, leNum, semester1, limGrade, prName, type, time, credit,
											attMem, limMem, preLecture, division, "0");
								}

							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

							// ��ȸ Ŭ���� ���̺� ���� �ʱ�ȭ
							model.setNumRows(0);
							List<LectureScore> sc;
							try {
								sc = pmg.showGrade(semester, lec.getleNum());

								int length = sc.size();

								for (int i = 0; i < length; i++) {
									Object[] row;
									try {
										row = new Object[] { sc.get(i).getLeName(), sc.get(i).getstName(),
												sc.get(i).getstNum(), sc.get(i).getMidScore(), sc.get(i).getFinScore(),
												sc.get(i).getAssiScore(), sc.get(i).getAttendScore(),
												sc.get(i).getTotalScore(), sc.get(i).getGrade()};
										model.addRow(row);
										showBtn.hide();

									} catch (Exception e1) { // getPerson() �ͼ���
										e1.printStackTrace();
									}
								}

							} catch (Exception e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}

							// �����ο� üũ
							try {
								int cnt;
								cnt = pmg.getCount(lec.getleNum());
								String toInt = Integer.toString(cnt);
								totalNumField.setText("�����ο�: " + toInt);

								// ��� ���ϱ�
								double avg;
								try {
									avg = pmg.stLecture(semester, lec.getleNum());
									avgField.setText("���: " + avg / cnt);
								} catch (Exception e1) {
									e1.printStackTrace();
								}

							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							nameBox.hide();
						}
					});
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});

		// �����Է� ��ư
		JButton putGradeBtn = new JButton("�����Է�");
		putGradeBtn.setBounds(829, 6, 93, 27);
		mainPanel.add(putGradeBtn);
		
		JLabel ExplainLabel = new JLabel("");
		ExplainLabel.setBounds(430, 380, 50, 15);
		mainPanel.add(ExplainLabel);
		
		
		// ���� �Լ�
		 Font font = ExplainLabel.getFont();
		 ExplainLabel.setForeground(Color.GRAY);
		 Map attributes = font.getAttributes();
		 attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		 ExplainLabel.setFont(font.deriveFont(attributes));
		 

		// �Է�ư�� ������ �� -> ������ �Է��ϰ� �Ѵ�
		putGradeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExplainLabel.setText("���� ���� �������Դϴ�.");
				// �����Է� ��ư �����
				if (e.getSource() == putGradeBtn) {
					putGradeBtn.hide();
				}

				// �Է� ��ư ����
				JButton inputGradeBtn = new JButton("Ȯ��");
				inputGradeBtn.setBounds(829, 6, 93, 27);
				mainPanel.add(inputGradeBtn);

				// �Է� ��ư�� ������ �� -> �������� ���� ����� �ٽ� �����Է� ��ư �߰� ��
				inputGradeBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ExplainLabel.setText("���� ���� �Ϸ�Ǿ����ϴ�.");
						// ���� ǥ�� �ִ� ���� �̸� �޾ƿ���
						String leName = model.getValueAt(0, 0).toString();

						// ������(upadate��) ǥ���� �޾ƿ� �� DB�� ������Ʈ �ϱ�
						int rCnt = model.getRowCount();
						for (int j = 0; j < rCnt; j++) {
							try {
								int midScore = Integer.parseInt(model.getValueAt(j, 3).toString()); // �߰����
								int finalScore = Integer.parseInt(model.getValueAt(j, 4).toString());// �⸻���
								int assiScore = Integer.parseInt(model.getValueAt(j, 5).toString());// ����
								int attendScore = Integer.parseInt(model.getValueAt(j, 6).toString());// �⼮
								int totalScore = Integer.parseInt(model.getValueAt(j, 7).toString()); // ����
								String grade = model.getValueAt(j, 8).toString();// ����

								// ������ ���� ��ü�� ����
								LectureScore S = new LectureScore(midScore, finalScore, assiScore, attendScore,
										totalScore, grade);

								// ���� ���� �����ϱ�
								pmg.modifyScore(model.getValueAt(j, 2).toString(), lec.getleNum(), S);
								// ��� ���ϱ�
								double avg;
								
								try {
									int cnt;
									cnt = pmg.getCount(lec.getleNum());
									avg = pmg.stLecture(semester, lec.getleNum());
									avgField.setText("���: " + avg / cnt);
								} catch (Exception e1) {
									e1.printStackTrace();
								}
							} catch (Exception e1) {
								e1.printStackTrace();
							}

						}
						putGradeBtn.show(); // ���� �Է� ��ư ��Ÿ����
						inputGradeBtn.hide();
					}
				});
			}

		});

	}
}
