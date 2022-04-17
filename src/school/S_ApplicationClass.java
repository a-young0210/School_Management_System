package school;

//������û
//������û
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

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
import java.awt.Component;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JEditorPane;
import javax.swing.JSplitPane;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.TableModel;
import javax.swing.DefaultComboBoxModel;

public class S_ApplicationClass extends JFrame {

	private JPanel contentPane;
	private JTextField searchSubField;

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

					S_ApplicationClass frame = new S_ApplicationClass(id);
					P_Management mg = new P_Management(url, con);
					S_Management sg = new S_Management(url, con);
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
	 * @throws Exception
	 */
	public S_ApplicationClass(String id) throws Exception {
		setTitle("�л���� ���α׷�");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.white);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// ����,�л� ��� �Լ� Ŭ������ ����
		P_Management mg = new P_Management(url, con);
		S_Management sg = new S_Management(url, con);

		String Username = sg.getstName(id);

		JPanel menuPanel = new JPanel();
		menuPanel.setBounds(0, 0, 205, 563);
		contentPane.add(menuPanel);
		menuPanel.setLayout(null);

		JButton LectureBtn = new JButton("���� �ý���");
		LectureBtn.setBounds(0, 0, 205, 50);
		menuPanel.add(LectureBtn);

		JButton showAllClassLabel = new JButton("����������ȸ");
		showAllClassLabel.setBounds(10, 63, 152, 18);
		menuPanel.add(showAllClassLabel);
		showAllClassLabel.setBorderPainted(false);
		showAllClassLabel.setFocusPainted(false);
		showAllClassLabel.setContentAreaFilled(false);
		showAllClassLabel.setHorizontalAlignment(SwingConstants.LEFT);
		menuPanel.add(showAllClassLabel);
		showAllClassLabel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				S_ShowAllClasses sacs;
				try {
					sacs = new S_ShowAllClasses(id);
					sacs.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		JButton BasketLabel = new JButton("��ٱ��� �Է�");
		BasketLabel.setBounds(10, 94, 152, 18);
		menuPanel.add(BasketLabel);
		BasketLabel.setBorderPainted(false);
		BasketLabel.setFocusPainted(false);
		BasketLabel.setContentAreaFilled(false);
		BasketLabel.setHorizontalAlignment(SwingConstants.LEFT);
		menuPanel.add(BasketLabel);
		BasketLabel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				S_Bucket sb;
				try {
					sb = new S_Bucket(id);
					sb.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		JButton applicationLabel = new JButton("������û");
		applicationLabel.setBounds(10, 125, 152, 18);
		applicationLabel.setBorderPainted(false);
		applicationLabel.setFocusPainted(false);
		applicationLabel.setHorizontalAlignment(SwingConstants.LEFT);
		applicationLabel.setEnabled(false);
		menuPanel.add(applicationLabel);

		JButton stuClassLabel = new JButton("������û ����");
		stuClassLabel.setBounds(10, 156, 152, 18);
		menuPanel.add(stuClassLabel);
		stuClassLabel.setBorderPainted(false);
		stuClassLabel.setFocusPainted(false);
		stuClassLabel.setContentAreaFilled(false);
		stuClassLabel.setHorizontalAlignment(SwingConstants.LEFT);
		menuPanel.add(stuClassLabel);
		stuClassLabel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				S_StuClasses ssc;
				try {
					ssc = new S_StuClasses(id);
					ssc.setVisible(true);
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
				S_Home shome;
				try {
					shome = new S_Home(id);
					shome.setVisible(true);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		homeBtn.setBounds(45, 500, 105, 27);
		menuPanel.add(homeBtn);

		JLabel titleLabel = new JLabel("������û");
		titleLabel.setBounds(219, 13, 109, 18);
		contentPane.add(titleLabel);

		JPanel selectPanel = new JPanel();
		selectPanel.setBounds(219, 42, 936, 36);
		contentPane.add(selectPanel);
		selectPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, Color.black));
		selectPanel.setLayout(null);

		JComboBox orderBox = new JComboBox();
		orderBox.setModel(new DefaultComboBoxModel(new String[] { "��ٱ���", "�����ʼ�", "���缱��" }));
		orderBox.setBounds(70, 3, 111, 28);
		selectPanel.add(orderBox);

		JComboBox majorBox = new JComboBox();
		majorBox.setModel(
				new DefaultComboBoxModel(new String[] { "����Ʈ���������а�", "������а�", "������а�", "�����а�", "ȭ�а�", "�� ��" }));
		majorBox.setBounds(253, 3, 153, 28);
		selectPanel.add(majorBox);

		// ��ٱ��ϸ� �����ߴٸ� ���� �޺��ڽ� �������� �ʾƵ� �ǵ��� �ϱ�
		if (orderBox.getSelectedItem().toString().equals("��ٱ���")) {
			majorBox.setEnabled(false);
		}

		// ��ٱ��ϸ� �����ߴٸ� ���� �޺��ڽ� �������� �ʾƵ� �ǵ��� �ϱ�
		orderBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource(); // �޺��ڽ� �˾Ƴ���

				int index = cb.getSelectedIndex();// ���õ� �������� �ε���

				if (index == 0) {
					majorBox.setEnabled(false);
				} else {
					majorBox.setEnabled(true);
				}
			}
		});

		JLabel orderLabel = new JLabel("����");
		orderLabel.setBounds(14, 5, 62, 18);
		selectPanel.add(orderLabel);

		JLabel majorLabel = new JLabel("�а�");
		majorLabel.setBounds(195, 5, 62, 18);
		selectPanel.add(majorLabel);

		JButton showBtn = new JButton("��ȸ");
		showBtn.setBounds(833, 3, 93, 25);
		selectPanel.add(showBtn);

		JLabel searchSubLabel = new JLabel("������˻�");
		searchSubLabel.setBounds(432, 5, 85, 18);
		selectPanel.add(searchSubLabel);

		// ������˻� �ʵ�
		searchSubField = new JTextField();
		searchSubField.setBounds(507, 4, 116, 26);
		selectPanel.add(searchSubField);
		searchSubField.setColumns(10);

		JRadioButton subNumRdo = new JRadioButton("�м���ȣ");
		subNumRdo.setBounds(629, 3, 85, 27);
		selectPanel.add(subNumRdo);

		JRadioButton subNameRdo = new JRadioButton("�����");
		subNameRdo.setBounds(713, 3, 93, 27);
		selectPanel.add(subNameRdo);

		// ���� ��ư �� �� �� �ϳ��� ������ �� �ֵ��� �����ϱ�(�߿���)
		ButtonGroup bg = new ButtonGroup();
		bg.add(subNumRdo);
		bg.add(subNameRdo);

		// ǥ ���� - ������û ��û��
		String colNames[] = {"�г�", "�м���ȣ", "�̼�����", "��������", "�����", "����", "���ǽð�", "��米��", "�����ο�", "�����ο�",
				"��������" };// �� ������
		Object data[][] = {};// �� ������
		DefaultTableModel model = new DefaultTableModel(data, colNames);

		// ǥ ���� - ������û ��Ҷ�
		String colNames2[] = {"�г�", "�м���ȣ", "�̼�����", "��������", "�����", "����", "���ǽð�", "��米��", "��������" };// �� ������
		Object data2[][] = {};// �� ������
		DefaultTableModel model2 = new DefaultTableModel(data2, colNames2);
		JTable table2 = new JTable(model2);

		// ��ũ��
		JScrollPane scrollPane2 = new JScrollPane(table2);
		scrollPane2.setBounds(14, 229, 912, 213);

		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, Color.black));
		mainPanel.setBounds(219, 85, 936, 455);
		contentPane.add(mainPanel);
		mainPanel.setBackground(null);
		mainPanel.setLayout(null);
		JTable table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(14, 13, 908, 179);
		scrollPane.setBorder(null);
		mainPanel.add(scrollPane);
		mainPanel.add(scrollPane2);

		JLabel titleLabel2 = new JLabel("��û ����");
		titleLabel2.setBounds(14, 205, 145, 18);
		mainPanel.add(titleLabel2);

		JLabel totalSubLabel = new JLabel("�� ��û ���� ��");
		totalSubLabel.setBounds(197, 205, 99, 18);
		mainPanel.add(totalSubLabel);

		JLabel totalCreLabel = new JLabel("�� ��û ����");
		totalCreLabel.setBounds(376, 205, 99, 18);
		mainPanel.add(totalCreLabel);

		// ���� ��û - ��û �г�
		JTextField panel = new JTextField();
		panel.setEnabled(false);
		panel.setBounds(300, 205, 62, 18);
		mainPanel.add(panel);

		// ���� ��û - ��� �г�
		JTextField panel2 = new JTextField();
		panel2.setEnabled(false);
		panel2.setBounds(463, 205, 62, 18);
		mainPanel.add(panel2);

		JButton submit = new JButton("��û");
		submit.setBounds(829, 202, 93, 25);
		mainPanel.add(submit);
		
		//ù ȭ�� �ε����� �� - �ش� ����ڰ� ������û�� ���� ���� model2�� ����
		ResultSet rs = null;
		rs = sg.getStClasses(id, "2021-1");
		if (rs.getRow() > -1) {
	      while(rs.next())
	      {
	         //�м���ȣ�� �ҷ��ͼ�
	         String leNum = rs.getString(2);
	         //�ش� �м���ȣ�� ���� ���� ��ü ��� ����
	         Lecture lec = sg.getLe(leNum);
	      // GUI�� ����
				String[] rows = { Integer.toString(lec.getLimGrade()), lec.getleNum(), lec.getDivision(), lec.getType(),
						lec.getleName(), Integer.toString(lec.getCredit()), lec.getTime(), lec.getprName(),lec.getPreLecture() };

				model2.addRow(rows);
	      }
		}						
		
		//����ڰ� ��û�� �� ���� ��
		int num = sg.getStudentTotalCount(id, "2021-1");
		panel.setText(Integer.toString(num));
		
		//����ڰ� ��û�� �� ����
		num = sg.getStudentTotalCredit(id, "2021-1");
		panel2.setText(Integer.toString(num));
		

		// ��ȸ��ư�� ������ ��
		// ��ϵǾ� �ִ� ���� ���� �ҷ�����
		showBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultSet rs = null;
				// ���̺� �ο� �ʱ�ȭ ��Ű��
				if (table.getRowCount() > 0) {
					model.setNumRows(0);
				}

				// �ش� �б� ������ �޾ƿ���
				String Semester = "2021-1";

				// �̼����� ������ �޾ƿ���
				String Classification = orderBox.getSelectedItem().toString();

				if (Classification.contentEquals("��ٱ���")) {
					try {
						// ��ó�� ȭ���� ������ �� ȭ�� ����
						rs = sg.getBucket(id, "2021-1");
						if (rs.getRow() > -1) {
							while (rs.next()) {
								// �м���ȣ�� �ҷ��ͼ�
								String leNum = rs.getString(2);
								// �ش� �м���ȣ�� ���� ���� ��ü ��� ����
								Lecture lec = sg.getLe(leNum);
								// GUI�� ����
								String[] rows = { Integer.toString(lec.getLimGrade()), lec.getleNum(),
										lec.getDivision(), lec.getType(), lec.getleName(),
										Integer.toString(lec.getCredit()), lec.getTime(), lec.getprName(),
										Integer.toString(lec.getLimMem()), Integer.toString(lec.getAttMem()),
										lec.getPreLecture() };

								model.addRow(rows);
							}
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else {
					// �а� ������ �޾ƿ���
					String Major;

					// �а��� ���� ���� ���ڸ� �ٸ��� �ϱ�
					if (majorBox.getSelectedItem().toString().equals("����Ʈ���������а�")) {
						Major = "MT";
					} else if (majorBox.getSelectedItem().toString().equals("������а�")) {
						Major = "KL";
					} else if (majorBox.getSelectedItem().toString().equals("������а�")) {
						Major = "EL";
					} else if (majorBox.getSelectedItem().toString().equals("�����а�")) {
						Major = "EC";
					} else if (majorBox.getSelectedItem().toString().equals("ȭ�а�")) {
						Major = "CH";
					} else {
						Major = "AA";
					}

					String queary = searchSubField.getText();
					// ������� �м���ȣ �� � �� ���õǾ����� �޾ƿ���
					String which = null;
					if (subNameRdo.isSelected()) {
						which = "�����";
					} else {
						which = "�м���ȣ";
					}

					try {
						if (queary.equals("")) {
							rs = sg.bucketLecture(Classification, Major, "", which, Semester);
						} else {
							rs = sg.bucketLecture(Classification, Major, queary, which, Semester);
						}
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
								String[] rows = { Integer.toString(lec.getLimGrade()), lec.getleNum(),
										lec.getDivision(), lec.getType(), lec.getleName(),
										Integer.toString(lec.getCredit()), lec.getTime(), lec.getprName(),
										Integer.toString(lec.getLimMem()), Integer.toString(lec.getAttMem()),
										lec.getPreLecture() };

								model.addRow(rows);

							}
						}

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});

		// ������û ��ư�� ������ ��
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultSet rs = null;

				// ������ �ο��� ���� ������
				int row = table.getSelectedRow();
				// �ش� �ο��� �м���ȣ ���� ������ - colnumNum = 2
				String stNum = table.getValueAt(row, 1).toString();
				try {
					// �ش� �м� ��ȣ�� ���� ���� ���� �ҷ�����
					Lecture lec = sg.getLe(stNum);

					//���� �ð� �ߺ� ������, �ش� ���� ���� �ο� �ʰ� �� �ϴ���, 18����(�� �б� ���� ���� ����) ���� �ʴ��� Ȯ���ϱ�
					 if(sg.isClass(id, lec) == 0&&lec.getLimMem()>lec.getAttMem()+1&&sg.getStudentTotalCredit(id, "2021-1")<18)
						{
							sg.applicationClass(id, lec, Username);
							// GUI�� ����
							String[] rows = { Integer.toString(lec.getLimGrade()), lec.getleNum(), lec.getDivision(),
									lec.getType(), lec.getleName(), Integer.toString(lec.getCredit()), lec.getTime(),
									lec.getprName(),lec.getPreLecture() };

							model2.addRow(rows);
						}
					 else if(sg.isClass(id, lec) != 0)
					 {
						 JOptionPane.showMessageDialog(null, "�ߺ� �ð����� ������ �����մϴ�.");
					 }else if(lec.getLimMem()<lec.getAttMem()+1)
					 {
						 JOptionPane.showMessageDialog(null, "���� ���� �ο��� �ʰ��Ͽ����ϴ�.");
					 }else if(sg.getStudentTotalCredit(id, "2021-1")>=18)
					 {
						 JOptionPane.showMessageDialog(null, "�� �б� ���� ���� ������ �ʰ��Ͽ����ϴ�.");
					 }
					 
						//����ڰ� ��û�� �� ���� ��
						int num = sg.getStudentTotalCount(id, "2021-1");
						panel.setText(Integer.toString(num));
						
						//����ڰ� ��û�� �� ����
						num = sg.getStudentTotalCredit(id, "2021-1");
						panel2.setText(Integer.toString(num));
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

	}
}
