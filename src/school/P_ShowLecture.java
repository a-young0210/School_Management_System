package school;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;

//���� ���� ��ȸ
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;

//���� ���� ��ȸ
public class P_ShowLecture extends JFrame {

	private JPanel contentPane;
	private JTextField timeField1;
	private JTextField timeField2;
	private JTextField searchField;

	/**
	 * Launch the application.
	 */

	public static String id;
	public static String url = "jdbc:mariadb://localhost:3306/school";
	public static Connection con;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Class.forName("org.mariadb.jdbc.Driver");
					// sql �����ϱ� ���� ���̵�� ��й�ȣ �Է�
					url = "jdbc:mariadb://localhost:3306/school";

					con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/school", "root", "1234");

					P_Management mg = new P_Management(url, con);
					P_ShowLecture frame = new P_ShowLecture(id);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public P_ShowLecture(String id) throws SQLException {
		setTitle("�л���� ���α׷�");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.white);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// ���� �Լ��� ����
		P_Management mg = new P_Management(url, con);
		Functions func = new Functions(url, con);

		JPanel menuPanel = new JPanel();
		menuPanel.setBounds(0, 0, 205, 563);
		contentPane.add(menuPanel);
		menuPanel.setLayout(null);

		// �޴��ȳ� ��
		JButton LectureBtn = new JButton("���� �ý���");
		LectureBtn.setBounds(0, 0, 205, 50);
		menuPanel.add(LectureBtn);

		JButton showLectureLabel = new JButton("����������ȸ");
		showLectureLabel.setBounds(10, 63, 152, 18);
		showLectureLabel.setBorderPainted(false);
		showLectureLabel.setFocusPainted(false);
		showLectureLabel.setHorizontalAlignment(SwingConstants.LEFT);
		showLectureLabel.setEnabled(false);
		menuPanel.add(showLectureLabel);

		JButton AddMyLectureLabel = new JButton("���������Է�");
		AddMyLectureLabel.setBounds(10, 94, 152, 18);
		AddMyLectureLabel.setBorderPainted(false);
		AddMyLectureLabel.setFocusPainted(false);
		AddMyLectureLabel.setContentAreaFilled(false);
		AddMyLectureLabel.setHorizontalAlignment(SwingConstants.LEFT);
		menuPanel.add(AddMyLectureLabel);
		AddMyLectureLabel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				P_AddLecture pal;
				try {
					pal = new P_AddLecture(id);
					pal.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		JButton showMyLectureLabel = new JButton("�������񳻿�");
		showMyLectureLabel.setBounds(10, 125, 152, 18);
		menuPanel.add(showMyLectureLabel);
		showMyLectureLabel.setBorderPainted(false);
		showMyLectureLabel.setFocusPainted(false);
		showMyLectureLabel.setContentAreaFilled(false);
		showMyLectureLabel.setHorizontalAlignment(SwingConstants.LEFT);
		menuPanel.add(showMyLectureLabel);
		showMyLectureLabel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				P_ShowProLecture psl;
				try {
					psl = new P_ShowProLecture(id);
					psl.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

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

		JLabel titleLabel = new JLabel("����������ȸ");
		titleLabel.setBounds(219, 13, 109, 18);
		contentPane.add(titleLabel);

		JPanel selectPanel = new JPanel();
		selectPanel.setBounds(219, 44, 362, 28);
		contentPane.add(selectPanel);
		selectPanel.setLayout(null);
		selectPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, Color.black));

		JComboBox yearBox = new JComboBox();
		yearBox.setModel(new DefaultComboBoxModel(new String[] { "2021", "2020", "2019" }));
		yearBox.setBounds(90, 2, 85, 23);
		selectPanel.add(yearBox);

		JComboBox semesterBox = new JComboBox();
		semesterBox.setModel(new DefaultComboBoxModel(new String[] { "1", "2" }));
		semesterBox.setBounds(274, 2, 85, 23);
		selectPanel.add(semesterBox);

		JLabel yearLabel = new JLabel("�г⵵");
		yearLabel.setBounds(14, 5, 62, 18);
		selectPanel.add(yearLabel);

		JLabel semesterLabel = new JLabel("�б�");
		semesterLabel.setBounds(196, 5, 62, 18);
		selectPanel.add(semesterLabel);

		// ǥ ���� - �к�
		String colNames[] = { "�г�", "�м���ȣ", "�̼�����", "��������", "�����", "����", "���ǽð�", "��米��", "�����ο�", "�����ο�" };// �� ������
		Object data[][] = {};// �� ������
		DefaultTableModel model = new DefaultTableModel(data, colNames);

		// ǥ ���� - ������
		String colNames2[] = { "�г�", "�м���ȣ", "�̼�����", "��������", "�����", "����", "���ǽð�", "��米��", "�����ο�", "�����ο�" };// �� ������
		Object data2[][] = {};// �� ������
		DefaultTableModel model2 = new DefaultTableModel(data2, colNames2);

		// ǥ ���� - �ð�
		String colNames3[] = { "�г�", "�м���ȣ", "�̼�����", "��������", "�����", "����", "���ǽð�", "��米��", "�����ο�", "�����ο�" };// �� ������
		Object data3[][] = {};// �� ������
		DefaultTableModel model3 = new DefaultTableModel(data3, colNames3);

		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(219, 85, 936, 455);
		contentPane.add(mainPanel);
		mainPanel.setBackground(null);
		mainPanel.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 936, 455);
		mainPanel.add(tabbedPane);
		JTable table = new JTable(model);
		table.setBorder(null);

		// �к� �г�
		JPanel panelOne = new JPanel();
		panelOne.setBounds(104, 269, 544, 349);

		// ��ũ��
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 44, 931, 379);
		tabbedPane.addTab("�к�", null, panelOne, null);
		scrollPane.setBorder(null);
		JTable table3 = new JTable(model3);
		panelOne.setLayout(null);
		panelOne.add(scrollPane);

		JComboBox combo1 = new JComboBox();
		combo1.setModel(
				new DefaultComboBoxModel(new String[] {"\uC804\uACF5\uD544\uC218", "\uC804\uACF5\uC120\uD0DD", "\uAD50\uC591\uD544\uC218", "\uAD50\uC591\uC120\uD0DD"}));
		combo1.setBounds(73, 8, 85, 28);
		panelOne.add(combo1);

		JLabel orderLabel = new JLabel("����");
		orderLabel.setBounds(14, 13, 62, 18);
		panelOne.add(orderLabel);

		JLabel majorLabel = new JLabel("�а�");
		majorLabel.setBounds(195, 13, 62, 18);
		panelOne.add(majorLabel);

		JComboBox orderCombo = new JComboBox();
		orderCombo.setModel(
				new DefaultComboBoxModel(new String[] { "����Ʈ���������а�", "������а�", "������а�", "�����а�", "ȭ�а�", "�� ��" }));
		orderCombo.setBounds(245, 8, 139, 28);
		panelOne.add(orderCombo);

		JButton showBtn = new JButton("��ȸ");
		showBtn.setBounds(840, 8, 85, 28);
		panelOne.add(showBtn);
		JTable table2 = new JTable(model2);

		// ��ũ��
		JScrollPane scrollPane2 = new JScrollPane(table2);
		scrollPane2.setBounds(0, 44, 931, 379);

		JPanel panelTwo = new JPanel();
		panelTwo.setLayout(null);
		tabbedPane.addTab("�ð�", null, panelTwo, null);
		panelTwo.add(scrollPane2);

		JComboBox dayComobo = new JComboBox();
		dayComobo.setModel(new DefaultComboBoxModel(new String[] { "��", "ȭ", "��", "��", "��" }));
		dayComobo.setBounds(73, 8, 85, 28);
		panelTwo.add(dayComobo);

		// ���� ��
		JLabel dayLabel = new JLabel("����");
		dayLabel.setBounds(14, 13, 62, 18);
		panelTwo.add(dayLabel);

		JLabel timeLabel = new JLabel("�ð�");
		timeLabel.setBounds(195, 13, 62, 18);
		panelTwo.add(timeLabel);

		// ��ȸ ��ư
		JButton showBtn2 = new JButton("��ȸ");
		showBtn2.setBounds(840, 8, 85, 28);
		panelTwo.add(showBtn2);

		// �ð� �ʵ�(��)
		timeField1 = new JTextField();
		timeField1.setBounds(241, 10, 42, 24);
		panelTwo.add(timeField1);
		timeField1.setColumns(10);

		// �ð��ʵ�(��)
		timeField2 = new JTextField();
		timeField2.setColumns(10);
		timeField2.setBounds(321, 10, 42, 24);
		panelTwo.add(timeField2);

		JLabel wavePanel = new JLabel("~");
		wavePanel.setBounds(300, 12, 16, 18);
		panelTwo.add(wavePanel);

		JScrollPane scrollPane3 = new JScrollPane(table3);
		scrollPane3.setBounds(0, 44, 931, 379);

		JPanel panelThree = new JPanel();
		panelThree.setLayout(null);
		tabbedPane.addTab("������", null, panelThree, null);
		panelThree.add(scrollPane3);

		JLabel searchLabel = new JLabel("������˻�");
		searchLabel.setBounds(14, 13, 83, 18);
		panelThree.add(searchLabel);

		JButton showBtn3 = new JButton("��ȸ");
		showBtn3.setBounds(840, 8, 85, 28);
		panelThree.add(showBtn3);

		// ������ �˻� �ʵ�
		searchField = new JTextField();
		searchField.setBounds(92, 8, 145, 24);
		panelThree.add(searchField);
		searchField.setColumns(10);

		JRadioButton subNumRdo = new JRadioButton("�м���ȣ");
		subNumRdo.setBounds(249, 8, 85, 27);
		panelThree.add(subNumRdo);

		// ����� ����
		JRadioButton subNameRdo = new JRadioButton("�����");
		subNameRdo.setBounds(339, 8, 139, 27);
		panelThree.add(subNameRdo);

		//���� ��ư �� �� �� �ϳ��� ������ �� �ֵ��� �����ϱ�(�߿���)
		ButtonGroup bg = new ButtonGroup();
		bg.add(subNumRdo);
		bg.add(subNameRdo);
		
		
		// �к��ʵ忡�� �˻����� ���
		showBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultSet rs = null;
				// ���̺� �ο� �ʱ�ȭ ��Ű��
				if (table.getRowCount() > 0) {
					model.setNumRows(0);
				}

				// �ش� �б� ������ �޾ƿ���
				String Semester = yearBox.getSelectedItem().toString() + "-" + semesterBox.getSelectedItem().toString();

				// �̼����� ������ �޾ƿ���
				String Classification = combo1.getSelectedItem().toString();

				// �а� ������ �޾ƿ���
				String Major;

				// �а��� ���� ���� ���ڸ� �ٸ��� �ϱ�
				if (orderCombo.getSelectedItem().toString().equals("����Ʈ���������а�")) {
					Major = "MT";
				} else if (orderCombo.getSelectedItem().toString().equals("������а�")) {
					Major = "KL";
				} else if (orderCombo.getSelectedItem().toString().equals("������а�")) {
					Major = "EL";
				} else if (orderCombo.getSelectedItem().toString().equals("�����а�")) {
					Major = "EC";
				} else if (orderCombo.getSelectedItem().toString().equals("ȭ�а�")) {
					Major = "CH";
				} else {
					Major = "AA";
				}

				try {
					rs = mg.searchLecture("�к�", Classification, Major, Semester);
					if (rs.getRow() > -1) {
						while (rs.next()) {

							// ���� �޼ҵ� ���� �޾ƿͼ� �Ҵ���.
							String leName = rs.getString(1);
							String leNum = rs.getString(2);
							String semester = rs.getString(3);
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
							Lecture lec = new Lecture(leName, leNum, semester, limGrade, prName, type, time,
									credit, attMem, limMem, preLecture, division, "0");

							// GUI�� ����
							String[] rows = { Integer.toString(lec.getLimGrade()), lec.getleNum(), lec.getDivision(),
									lec.getType(), lec.getleName(), Integer.toString(lec.getCredit()), lec.getTime(),
									lec.getprName(), Integer.toString(lec.getLimMem()),
									Integer.toString(lec.getAttMem()), lec.getPreLecture() };

							model.addRow(rows);
						}
					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		//�ð��� ���� ��ȸ�ϱ�
		showBtn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultSet rs = null;
				// ���̺� �ο� �ʱ�ȭ ��Ű��
				if (table2.getRowCount() > 0) {
					model2.setNumRows(0);
				}
				// �ش� �б� ������ �޾ƿ���
				String Semester = yearBox.getSelectedItem().toString() + "-" + semesterBox.getSelectedItem().toString();
				// �ش� ���� ������ �޾ƿ���
				String Day = dayComobo.getSelectedItem().toString();
				// �ش� ���Ͽ� �ð� ������ �޾ƿ���
				String Time = timeField1.getText() + "-" + timeField2.getText();

				System.out.println(Day + Time);

				try {
					rs = mg.searchLecture("�ð�", Day, Time, Semester);
					if (rs.getRow() > -1) {
						while (rs.next()) {

							// ���� �޼ҵ� ���� �޾ƿͼ� �Ҵ���.
							String leName = rs.getString(1);
							String leNum = rs.getString(2);
							String semester = rs.getString(3);
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
							Lecture lec = new Lecture(leName, leNum, semester, limGrade, prName, type, time,
									credit, attMem, limMem, preLecture, division, "0");

							// GUI�� ����
							String[] rows = { Integer.toString(lec.getLimGrade()), lec.getleNum(), lec.getDivision(),
									lec.getType(), lec.getleName(), Integer.toString(lec.getCredit()), lec.getTime(),
									lec.getprName(), Integer.toString(lec.getLimMem()),
									Integer.toString(lec.getAttMem()), lec.getPreLecture() };

							model2.addRow(rows);
						}
					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		//�ð��� ���� ��ȸ�ϱ�
				showBtn3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ResultSet rs = null;
						// ���̺� �ο� �ʱ�ȭ ��Ű��
						if (table3.getRowCount() > 0) {
							model3.setNumRows(0);
						}
						// �ش� �б� ������ �޾ƿ���
						String Semester = yearBox.getSelectedItem().toString() + "-" + semesterBox.getSelectedItem().toString();
						// �ش� ���� ������ �޾ƿ���
						String Query = searchField.getText();
						
						//������� �м���ȣ �� � �� ���õǾ����� �޾ƿ���
						String which = null;
						if(subNameRdo.isSelected())
						{
							which = "�����";
						}
						else {
							which = "�м���ȣ";
						}
						
						
						try {
							rs = mg.searchLecture("������", which, Query, Semester);
							if (rs.getRow() > -1) {
								while (rs.next()) {
									
									// ���� �޼ҵ� ���� �޾ƿͼ� �Ҵ���.
									String leName = rs.getString(1);
									String leNum = rs.getString(2);
									String semester = rs.getString(3);
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
									Lecture lec = new Lecture(leName, leNum, semester, limGrade, prName, type, time,
											credit, attMem, limMem, preLecture, division, "0");

									// GUI�� ����
									String[] rows = { Integer.toString(lec.getLimGrade()), lec.getleNum(), lec.getDivision(),
											lec.getType(), lec.getleName(), Integer.toString(lec.getCredit()), lec.getTime(),
											lec.getprName(), Integer.toString(lec.getLimMem()),
											Integer.toString(lec.getAttMem()), lec.getPreLecture() };

									model3.addRow(rows);
								}
							}

						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});

	}

}
