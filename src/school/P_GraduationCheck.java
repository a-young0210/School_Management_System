package school;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

public class P_GraduationCheck extends JFrame {

	private JPanel contentPane;
	private JTable table;
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
				try {
					Class.forName("org.mariadb.jdbc.Driver");
					// sql �����ϱ� ���� ���̵�� ��й�ȣ �Է�

					con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/school", "root", "1234");

					mg = new P_Management(url, con);

					
					P_GraduationCheck frame = new P_GraduationCheck(id);
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
	public P_GraduationCheck(String id) throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// ���� �Լ��� ����
		P_Management mg = new P_Management(url, con);
		Functions func = new Functions(url, con);

		// �ش� ������ �̸� �ҷ�����
		String Username = mg.checkNamePr(id);

		JPanel menuPanel = new JPanel();
		menuPanel.setBounds(0, 0, 205, 563);
		contentPane.add(menuPanel);
		menuPanel.setLayout(null);

		// �޴��ȳ� ��
		JButton LectureBtn = new JButton("���� �ý���");
		LectureBtn.setBounds(0, 0, 205, 50);
		menuPanel.add(LectureBtn);

		JButton Menu1 = new JButton("���� ����");
		Menu1.setBounds(4, 63, 152, 18);
		Menu1.setBorderPainted(false);
		Menu1.setFocusPainted(false);
		Menu1.setContentAreaFilled(false);
		Menu1.setHorizontalAlignment(SwingConstants.LEFT);
		menuPanel.add(Menu1);

		JButton graduation = new JButton("���� ��� ��Ȳ ��ȸ");
		graduation.setBounds(10, 83, 152, 18);
		graduation.setBorderPainted(false);
		graduation.setFocusPainted(false);
		graduation.setContentAreaFilled(false);
		graduation.setHorizontalAlignment(SwingConstants.LEFT);
		menuPanel.add(graduation);

		JButton graduationcheck = new JButton("�������� ��ȸ");
		graduationcheck.setBounds(10, 103, 152, 18);
		graduationcheck.setBorderPainted(false);
		graduationcheck.setFocusPainted(false);
		graduationcheck.setHorizontalAlignment(SwingConstants.LEFT);
		graduationcheck.setContentAreaFilled(false);
		graduationcheck.setEnabled(false);
		menuPanel.add(graduationcheck);

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

		JPanel Main = new JPanel();
		Main.setBackground(Color.WHITE);
		Main.setBounds(211, 5, 970, 553);
		Main.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, Color.black));
		contentPane.add(Main);
		Main.setLayout(null);

		String colNames1[] = { "�й�", "�̸�", "�Ҽ�", "�����������", "���̼�����", "���� ����" };
		String rows1[][] = {};
		DefaultTableModel model1 = new DefaultTableModel(rows1, colNames1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 148, 946, 339);
		Main.add(scrollPane);

		String colNames[] = { "�й�", "�̸�", "�Ҽ�", "���� ��� ����", "�� �̼� ����", "���� ����" };
		String rows[][] = {};
		DefaultTableModel model = new DefaultTableModel(rows, colNames);

		table = new JTable(model);

		scrollPane.setViewportView(table);
		table.setFont(new Font("����", Font.PLAIN, 12));
		table.setBorder(new LineBorder(new Color(0, 0, 0)));

		JLabel Department = new JLabel("�Ҽ�");
		Department.setBounds(12, 102, 50, 15);
		Main.add(Department);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"\uD559\uACFC\uBA85", "\uC18C\uD504\uD2B8\uC6E8\uC5B4\uC735\uD569\uD559\uACFC", "\uAD6D\uC5B4\uAD6D\uBB38\uD559\uACFC", "\uC601\uC5B4\uC601\uBB38\uD559\uACFC", "\uACBD\uC81C\uD559\uACFC", "\uD654\uD559\uACFC", "\uADF8 \uC678"}));
		comboBox_1.setBounds(54, 98, 150, 23);
		Main.add(comboBox_1);

		JButton SearchBtn = new JButton("��ȸ");
		SearchBtn.setIcon(new ImageIcon(
				"C:\\Users\\Swim\\Desktop\\\uD559\uAD50\uD0C8\uCD9C\\2021-1\\\uD504\uB85C\uC81D\uD2B8\\\uCEA1\uCC98.PNG"));
		SearchBtn.setBounds(220, 91, 70, 33);
		Main.add(SearchBtn);

		JLabel Title = new JLabel("���������Ȳ ��ȸ");
		Title.setFont(new Font("���Ļ�浸��", Font.BOLD, 26));
		Title.setBounds(12, 10, 240, 44);
		Main.add(Title);

		// ������(�˻�)��ư ������ ����Ǵ� �׼� ������
		SearchBtn.addActionListener(new ActionListener() {
			ResultSet rs = null;
			Student s = new Student();
			public void actionPerformed(ActionEvent e) {
				// ���̺� ��� �� �ʱ�ȭ �ϱ� - �ƿ� ���� �����̱� ����
				if (table.getRowCount() > 0) {
					model.setNumRows(0);
				}

				// ���̺� ��� �� �ʿ��� row number�� ����� ����.
				int count = 0;

				if (comboBox_1.getSelectedItem().toString().equals("�а���")) {
					//�޺� �ڽ��� �а����� �� �˻� ��ư�� �������� ���â���� �а��� �����ش޶�� �ȳ��ϱ�
					 JOptionPane.showMessageDialog(null, "��ȸ�� �а��� �������ֽʽÿ�.");
				} else {
					String major = comboBox_1.getSelectedItem().toString();
					try {
						rs = mg.getStudent(major);
						while (rs.next()) {
							//�л� ��ü�� ���� ���� - ��� �����Ͱ� ����ִ� ����
							//�ش� ��ü�� �ʿ��� ������ �̾ƿͼ� ����
							s.setstName(rs.getString(1));
							s.setstNum(rs.getString(2));
							s.setGrade(rs.getInt(3));
							s.setfMajor(rs.getString(6));


							// ���̺�2 - ���� ���� ��ȸ�� ��� ���� ��� ������ �� �̼� ���� �ҷ�����, ���̺� ����
							String stNum = s.getstNum();
							// �� �̼� ����
							int TotalCredit = mg.getTotalCredit(stNum, "������");
							// ���� �ʼ�
							int credit3 = mg.getTotalCredit(stNum, "�����ʼ�");
							// ���� ����
							int credit4 = mg.getTotalCredit(stNum, "��������");
							// ���� ��(���� �ʼ� + ���� ����)
							int MajorCredit = credit3 + credit4;

							//�̾ƿ� ������ table�� ����
							String[] rows = { s.getstNum(), s.getstName(),s.getfMajor(), Integer.toString(MajorCredit), Integer.toString(TotalCredit), ""};
							model.addRow(rows);
							
							
							// ���� ���� ��ȸ �������� if���� �̿��ؼ� �˻��ϱ�
							if (TotalCredit > 130 && MajorCredit > 69) {
								table.setValueAt("Y", count, 5);
							} else {
								table.setValueAt("N", count, 5);
							}
							count++;
						}

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			}
		});

		// ���� ���� ���� ��ȸ �׼� ������
		graduation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				P_Graduation nextpage;
				try {
					nextpage = new P_Graduation(id);				
					nextpage.setVisible(true);
					dispose();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		// Ȩ ȭ�� ���ư��� �׼� ������
		homeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				P_Home nextpage;
				try {
					nextpage = new P_Home(id);
					nextpage.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});

	}
}
