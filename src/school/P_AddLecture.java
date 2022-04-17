package school;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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


//���������Է�
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;

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
import javax.swing.DefaultComboBoxModel;

//���������Է�
public class P_AddLecture extends JFrame {
	private JPanel contentPane;
	private JTextField belongTxt;
	private JTextField allLecTxt;
	private JTextField allCreTxt;
	private JTextField gradeField;
	private JTextField creditField;
	private JTextField gradeTxt;
	private JTextField creditTxt;
	private JTextField nameField;
	private JTextField nameTxt;
	private JTextField timeField;
	private JTextField timeTxt;
	private JTextField classificationField;
	private JTextField classificationTxt;
	private JTextField belongTxt3;
	private JTextField belongTxt4;
	private JTextField courseField;
	private JTextField courseTxt;
	private JTextField limitNumField;
	private JTextField limitTxt;

	public int SubNum = 0;
	public static String id;
	public static String url = "jdbc:mariadb://localhost:3306/school";
	public static Connection con;
	static P_Management mg;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				// ����̹� �ε�
				try {
					Class.forName("org.mariadb.jdbc.Driver");
					// sql �����ϱ� ���� ���̵�� ��й�ȣ �Է�

					con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/school", "root", "1234");

					mg = new P_Management(url, con);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				Scanner scan = new Scanner(System.in);
				try {
					P_AddLecture frame = new P_AddLecture(id);
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
	 * @param con2
	 * @param url2
	 * @throws Exception
	 */

	public P_AddLecture(String id) throws Exception {

		// �⺻ �г� ����
		setTitle("�л���� ���α׷�");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.white);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// ���� ��ü �����ϱ�
		// ������ ���� DB�� ã�Ƽ� �ű⿡ ����� ���� ���� �� �ҷ�����

		// ���� �Լ��� ����
		P_Management mg = new P_Management(url, con);
		Functions func = new Functions(url, con);

		// �ش� ������ �̸� �ҷ�����
		String Username = mg.checkNamePr(id);

		JPanel menuPanel = new JPanel();
		menuPanel.setBounds(0, 0, 205, 563);
		contentPane.add(menuPanel);
		menuPanel.setLayout(null);

		JButton LectureBtn = new JButton("���� �ý���");
		LectureBtn.setBounds(0, 0, 205, 50);
		menuPanel.add(LectureBtn);

		// �޴��ȳ� ��
		JButton showLectureLabel = new JButton("����������ȸ");
		showLectureLabel.setBounds(10, 63, 152, 18);
		showLectureLabel.setBorderPainted(false);
		showLectureLabel.setFocusPainted(false);
		showLectureLabel.setContentAreaFilled(false);
		showLectureLabel.setHorizontalAlignment(SwingConstants.LEFT);
		menuPanel.add(showLectureLabel);
		showLectureLabel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				P_ShowLecture psl;
				try {
					psl = new P_ShowLecture(id);
					psl.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		JButton AddLectureLabel = new JButton("���������Է�");
		AddLectureLabel.setBounds(10, 94, 182, 18);
		AddLectureLabel.setBorderPainted(false);
		AddLectureLabel.setFocusPainted(false);
		AddLectureLabel.setHorizontalAlignment(SwingConstants.LEFT);
		// AddLectureLabel.setContentAreaFilled(false);
		AddLectureLabel.setEnabled(false);
		menuPanel.add(AddLectureLabel);

		JButton showProLectureLabel = new JButton("�������񳻿�");
		showProLectureLabel.setBounds(10, 125, 152, 18);
		menuPanel.add(showProLectureLabel);
		showProLectureLabel.setBorderPainted(false);
		showProLectureLabel.setFocusPainted(false);
		showProLectureLabel.setContentAreaFilled(false);
		showProLectureLabel.setHorizontalAlignment(SwingConstants.LEFT);
		showProLectureLabel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				try {
					P_ShowProLecture pspl = new P_ShowProLecture(id);
					pspl.setVisible(true);
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

		JLabel titleLabel = new JLabel("���������Է�");
		titleLabel.setBounds(219, 13, 109, 18);
		contentPane.add(titleLabel);

		JPanel selectPanel = new JPanel();
		selectPanel.setBounds(219, 38, 936, 35);
		contentPane.add(selectPanel);
		selectPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, Color.black));
		selectPanel.setLayout(null);

		// �޺��ڽ�
		JComboBox yearBox = new JComboBox();
		yearBox.setModel(new DefaultComboBoxModel(new String[] { "", "2021", "2020", "2019" }));
		yearBox.setBounds(90, 5, 146, 24);
		selectPanel.add(yearBox);

		JComboBox semesterBox = new JComboBox();
		semesterBox.setModel(new DefaultComboBoxModel(new String[] { "", "1", "2" }));
		semesterBox.setBounds(375, 5, 146, 24);
		selectPanel.add(semesterBox);

		// �ȳ� ��
		JLabel yearLabel = new JLabel("�г⵵");
		yearLabel.setBounds(14, 7, 62, 18);
		selectPanel.add(yearLabel);

		JLabel semesterLabel = new JLabel("�б�");
		semesterLabel.setBounds(287, 7, 62, 18);
		selectPanel.add(semesterLabel);

		JButton showBtn = new JButton("��ȸ");
		showBtn.setBounds(834, 3, 93, 27);
		selectPanel.add(showBtn);

		/*
		 * JLabel belongLabel = new JLabel("�Ҽ�"); belongLabel.setBounds(559, 7, 62, 18);
		 * selectPanel.add(belongLabel);
		 * 
		 * //�Ҽ� �ؽ�Ʈ �ʵ�(���⼭ �Ҽ� ������ �Է¹��� ����) belongTxt = new JTextField();
		 * belongTxt.setBounds(622, 5, 169, 24); selectPanel.add(belongTxt);
		 * belongTxt.setColumns(10);
		 */

		// ǥ ���� - �Է¸��
		String colNames[] = { "�г�", "�м���ȣ", "�̼�����", "��������", "�����", "����", "���ǽð�", "��米��", "�����ο�", "�����ο�", "��������",
				"�������" };// �� ������
		Object data[][] = {};// �� ������
		DefaultTableModel model = new DefaultTableModel(data, colNames);

		// ���� �г� : �� ���� �Է� ��ϰ� ���� �Է� ȭ���� ������
		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, Color.black));
		mainPanel.setBounds(219, 85, 936, 455);
		contentPane.add(mainPanel);
		mainPanel.setBackground(null);
		mainPanel.setLayout(null);

		// �Է� ��� ��
		JLabel addListLabel = new JLabel("�Է� ���");
		addListLabel.setBounds(14, 13, 62, 18);
		mainPanel.add(addListLabel);
		JTable table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(14, 44, 908, 181);
		scrollPane.setBorder(null);
		mainPanel.add(scrollPane);

		// �� ���� ���� ��
		JLabel allLecLabel = new JLabel("�� ���� ���� ��");
		allLecLabel.setBounds(617, 239, 110, 18);
		mainPanel.add(allLecLabel);

		// �� ���� ���� ��
		JLabel allCreLabel = new JLabel("�� ���� ����");
		allCreLabel.setBounds(780, 239, 110, 18);
		mainPanel.add(allCreLabel);

		// �� ���� ���� �ؽ�Ʈ�ʵ�(�������Է�)
		allLecTxt = new JTextField();
		allLecTxt.setEditable(false);
		allLecTxt.setBounds(705, 238, 62, 24);
		mainPanel.add(allLecTxt);
		allLecTxt.setColumns(10);

		// �� ���� ���� �ؽ�Ʈ�ʵ�(�������Է�)
		allCreTxt = new JTextField();
		allCreTxt.setEditable(false);
		allCreTxt.setColumns(10);
		allCreTxt.setBounds(855, 238, 62, 24);
		mainPanel.add(allCreTxt);

		// ���� �Է� ��
		JLabel addSubLabel = new JLabel("���� �Է�");
		addSubLabel.setBounds(14, 244, 62, 18);
		mainPanel.add(addSubLabel);

		// ù ��° �� �г� - �����Է�
		JPanel panel = new JPanel();
		panel.setBounds(14, 275, 908, 31);
		mainPanel.add(panel);
		panel.setLayout(null);

		// �г� �ʵ�
		gradeField = new JTextField();
		gradeField.setHorizontalAlignment(SwingConstants.CENTER);
		gradeField.setText("�г�");
		gradeField.setBounds(0, 0, 116, 31);
		panel.add(gradeField);
		gradeField.setColumns(10);

		// �г� �Է¶�
		gradeTxt = new JTextField();
		gradeTxt.setBounds(115, 0, 283, 31);
		panel.add(gradeTxt);
		gradeTxt.setColumns(10);

		// ���� �ʵ�
		creditField = new JTextField();
		creditField.setText("����");
		creditField.setHorizontalAlignment(SwingConstants.CENTER);
		creditField.setBounds(398, 0, 116, 31);
		panel.add(creditField);
		creditField.setColumns(10);

		// ���� �Է¶�
		creditTxt = new JTextField();
		creditTxt.setBounds(514, 0, 394, 31);
		panel.add(creditTxt);
		creditTxt.setColumns(10);

		// �� ��° �� �г� - �����Է�
		JPanel panel2 = new JPanel();
		panel2.setLayout(null);
		panel2.setBounds(14, 307, 908, 31);
		mainPanel.add(panel2);

		// ����� �ʵ�
		nameField = new JTextField();
		nameField.setText("�����");
		nameField.setHorizontalAlignment(SwingConstants.CENTER);
		nameField.setColumns(10);
		nameField.setBounds(0, 0, 116, 31);
		panel2.add(nameField);

		// ����� �Է¶�
		nameTxt = new JTextField();
		nameTxt.setColumns(10);
		nameTxt.setBounds(115, 0, 283, 31);
		panel2.add(nameTxt);

		// ���ǽð� �ʵ�
		timeField = new JTextField();
		timeField.setText("���ǽð�");
		timeField.setHorizontalAlignment(SwingConstants.CENTER);
		timeField.setColumns(10);
		timeField.setBounds(398, 0, 116, 31);
		panel2.add(timeField);

		// ���ǽð� �Է¶�
		timeTxt = new JTextField();
		timeTxt.setColumns(10);
		timeTxt.setBounds(514, 0, 394, 31);
		panel2.add(timeTxt);

		// �� ��° �г� - �����Է�
		JPanel panel3 = new JPanel();
		panel3.setLayout(null);
		panel3.setBounds(14, 339, 908, 31);
		mainPanel.add(panel3);

		// �̼����� �ʵ�
		classificationField = new JTextField();
		classificationField.setText("�̼�����");
		classificationField.setHorizontalAlignment(SwingConstants.CENTER);
		classificationField.setColumns(10);
		classificationField.setBounds(0, 0, 116, 31);
		panel3.add(classificationField);

		// �̼����� �Է¶�
		classificationTxt = new JTextField();
		classificationTxt.setColumns(10);
		classificationTxt.setBounds(115, 0, 283, 31);
		panel3.add(classificationTxt);

		// �������� �ʵ�
		belongTxt3 = new JTextField();
		belongTxt3.setText("��������");
		belongTxt3.setHorizontalAlignment(SwingConstants.CENTER);
		belongTxt3.setColumns(10);
		belongTxt3.setBounds(398, 0, 116, 31);
		panel3.add(belongTxt3);

		// �������� �Է¶�
		belongTxt4 = new JTextField();
		belongTxt4.setColumns(10);
		belongTxt4.setBounds(514, 0, 394, 31);
		panel3.add(belongTxt4);

		// �� ��° �г� - �����Է�
		JPanel panel4 = new JPanel();
		panel4.setLayout(null);
		panel4.setBounds(14, 372, 908, 31);
		mainPanel.add(panel4);

		// �������� �ʵ�
		courseField = new JTextField();
		courseField.setText("��������");
		courseField.setHorizontalAlignment(SwingConstants.CENTER);
		courseField.setColumns(10);
		courseField.setBounds(0, 0, 116, 31);
		panel4.add(courseField);

		// �������� �Է¶�
		courseTxt = new JTextField();
		courseTxt.setColumns(10);
		courseTxt.setBounds(115, 0, 283, 31);
		panel4.add(courseTxt);

		// �����ο� �ʵ�
		limitNumField = new JTextField();
		limitNumField.setText("�����ο�");
		limitNumField.setHorizontalAlignment(SwingConstants.CENTER);
		limitNumField.setColumns(10);
		limitNumField.setBounds(398, 0, 116, 31);
		panel4.add(limitNumField);

		// �����ο� �Է¶�
		limitTxt = new JTextField();
		limitTxt.setColumns(10);
		limitTxt.setBounds(514, 0, 394, 31);
		panel4.add(limitTxt);

		// �Է� ��ư
		JButton inputBtn = new JButton("�Է�");
		inputBtn.setBounds(817, 416, 105, 27);
		mainPanel.add(inputBtn);

		// ��ȸ�ϱ� ���
		showBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// ����ڽ��� �ִ� ������ �ҷ�����
				String InputSemester = null;
				InputSemester = yearBox.getSelectedItem().toString() + "-" + semesterBox.getSelectedItem().toString();
				System.out.println(InputSemester);

				// ���̺� ��� �� �ʱ�ȭ �ϱ� - �ƿ� ���� �����̱� ����
				if (table.getRowCount() > 0) {
					model.setNumRows(0);
				}

				// ������ ���� �ش� ũ���� ������� ����
				int sumcre = 0;

				try {
					ResultSet rs = null;
					if (!InputSemester.equals("-")) {
						// ����ڰ� ����� ���� �ҷ�����
						rs = mg.getClasses(InputSemester, Username);
					} else {
						// �б⸦ �������� �ʾ��� ��� �ش� ����ڰ� ���ݱ��� ������ ���� ��ü��� �޾ƿ���
						rs = mg.getLecture(Username);
					}

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
						Lecture lec = new Lecture(leName, leNum, semester, limGrade, prName, type, time, credit,
								attMem, limMem, preLecture, division, "0");

						// GUI�� ����
						String[] rows = { Integer.toString(lec.getLimGrade()), lec.getleNum(), lec.getDivision(),
								lec.getType(), lec.getleName(), Integer.toString(lec.getCredit()), lec.getTime(),
								lec.getprName(), Integer.toString(lec.getLimMem()), Integer.toString(lec.getAttMem()),
								lec.getPreLecture() };

						model.addRow(rows);

						sumcre += lec.getCredit();

					}
					allLecTxt.setText(Integer.toString(table.getRowCount()));
					allCreTxt.setText(Integer.toString(sumcre));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		// �Է� ��ư ������?
		inputBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String grade = gradeTxt.getText();
				String Credit = creditTxt.getText();
				String Name = nameTxt.getText();
				String Time = timeTxt.getText();
				String Classification = classificationTxt.getText();
				String Belong = belongTxt4.getText();
				String Course = courseTxt.getText();
				String Limitmem = limitTxt.getText();

				if (!Name.equals("") && !grade.equals("") && !Credit.equals("") && !Time.equals("")
						&& !Classification.equals("") && !Belong.equals("") && !Limitmem.equals("")) {
					Lecture lec = new Lecture(Name, "", "2021-1", Integer.parseInt(grade), Username, Belong, Time,
							Integer.parseInt(Credit), 0, Integer.parseInt(Limitmem), Course, Classification, "0");
					try {
						// SQL�� �Է��ϱ�
						// ���� ��ü�� �޾Ƽ� �м���ȣ �����(�̶� �м� ��ȣ�� NULL ����)
						// ���� �м���ȣ�� ������ table�� �߰��ϱ�
						String Number = func.makeLeNum(lec, Username);
						lec.setleNum(Number);
						mg.InsertLecture(lec);

						// GUI�� ����
						String[] rows = { Integer.toString(lec.getLimGrade()), lec.getleNum(), lec.getDivision(),
								lec.getType(), lec.getleName(), Integer.toString(lec.getCredit()), lec.getTime(),
								lec.getprName(), Integer.toString(lec.getLimMem()), Integer.toString(lec.getAttMem()),
								lec.getPreLecture() };

						model.addRow(rows);

						// ��� �� EditText���� ����
						gradeTxt.setText("");
						creditTxt.setText("");
						nameTxt.setText("");
						timeTxt.setText("");
						classificationTxt.setText("");
						belongTxt4.setText("");
						courseTxt.setText("");
						limitTxt.setText("");
						SubNum++;

						int sumcre = 0;
						if (!allCreTxt.getText().contentEquals("")) {
							sumcre = Integer.parseInt(allCreTxt.getText());
						}

						allLecTxt.setText(Integer.toString(table.getRowCount()));
						allCreTxt.setText(Integer.toString(sumcre + lec.getCredit()));
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});

	}
}
