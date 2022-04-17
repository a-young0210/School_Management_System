package school;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;

public class S_Graduation extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;

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

					con = DriverManager.getConnection(url, "root", "1234");

					S_Graduation frame = new S_Graduation(id);

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
	 * @throws Exception 
	 */

	// ���� �ý���
	public S_Graduation(String id) throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// ���� �Լ��� ����
		P_Management mg = new P_Management(url, con);
		S_Management sg = new S_Management(url, con);

		// �ش� ������ �̸� �ҷ�����
		String Username = sg.getstName(id);

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

		JButton graduation = new JButton("���� ��� ��Ȳ");
		graduation.setBounds(10, 83, 152, 18);
		graduation.setBorderPainted(false);
		graduation.setFocusPainted(false);
		graduation.setContentAreaFilled(false);
		graduation.setHorizontalAlignment(SwingConstants.LEFT);
		graduation.setEnabled(false);
		menuPanel.add(graduation);

		JButton graduationcheck = new JButton("�������� ��ȸ");
		graduationcheck.setBounds(10, 103, 152, 18);
		graduationcheck.setBorderPainted(false);
		graduationcheck.setFocusPainted(false);
		graduationcheck.setHorizontalAlignment(SwingConstants.LEFT);
		graduationcheck.setContentAreaFilled(false);
		menuPanel.add(graduationcheck);

		JButton homeBtn = new JButton("Ȩ ȭ��");
		homeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				S_Home phome;
				try {
					phome = new S_Home(id);
					phome.setVisible(true);
				} catch (ClassNotFoundException | SQLException e1) {
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

		JLabel Title = new JLabel("���� ��� ��Ȳ");
		Title.setFont(new Font("���Ļ�浸��", Font.BOLD, 26));
		Title.setBounds(12, 10, 200, 44);
		Main.add(Title);

		JLabel CategoryScore = new JLabel("�̼� ���к� ���� ��Ȳ");
		CategoryScore.setFont(new Font("���Ļ�浸��", Font.BOLD, 14));
		CategoryScore.setBounds(415, 86, 180, 30);
		Main.add(CategoryScore);

		// ���� ���� �Է�
		String colNames[] = { "�г⵵", "�б�", "��û ����", "�̼� ����", "�ش� �б� ���� ���" };
		String rows[][] = {};
		DefaultTableModel model = new DefaultTableModel(rows, colNames);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 138, 946, 147);
		Main.add(scrollPane);

		// �̼� ���к� ���� ��� ��Ȳ
		table = new JTable();
		table.setFont(new Font("����", Font.PLAIN, 12));
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setModel(
				new DefaultTableModel(
						new Object[][] { { "������", "130", null, null }, { "���� �ʼ�", "10", null, null },
								{ "���� ����", "27", null, null }, { "���� �ʼ�", "14", null, null },
								{ "���� ����", "-", null, null }, { "���� ��", "69", null, null }, },
						new String[] { "", "�̼� ���� ����", "��� ����", "�ܿ� ����" }));
		scrollPane.setViewportView(table);

		JLabel TotalScore_Title = new JLabel("��ü ���� ����");
		TotalScore_Title.setFont(new Font("���Ļ�浸��", Font.BOLD, 14));
		TotalScore_Title.setBounds(442, 354, 95, 44);
		Main.add(TotalScore_Title);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 419, 946, 44);
		Main.add(scrollPane_1);

		String colNames1[] = { "���� �� ����", "�� �̼� ����", "�� ����" };
		String rows1[][] = {{"", "", ""}};
		DefaultTableModel model1 = new DefaultTableModel(rows1, colNames1);
		table_1 = new JTable(model1);
		table_1.setFont(new Font("����", Font.PLAIN, 12));
		table_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane_1.setViewportView(table_1);

		// ù��° ���̺� ������ �����ϱ�
		// �ʿ��� �����͵�
		// �� �̼� ����
		int TotalCredit = sg.getTotalCredit(id, "������");
		// ���� �ʼ�
		int credit1 = sg.getTotalCredit(id, "�����ʼ�");
		// ���� ����
		int credit2 = sg.getTotalCredit(id, "���缱��");
		// ���� �ʼ�
		int credit3 = sg.getTotalCredit(id, "�����ʼ�");
		// ���� ����
		int credit4 = sg.getTotalCredit(id, "��������");
		// ���� ��(���� �ʼ� + ���� ����)
		int MajorCredit = credit3 + credit4;

		//���̺� �ֱ�
		table.setValueAt(TotalCredit, 0, 2);
		table.setValueAt(credit1, 1, 2);
		table.setValueAt(credit2, 2, 2);
		table.setValueAt(credit3, 3, 2);
		table.setValueAt(credit4, 4, 2);
		table.setValueAt(MajorCredit, 5, 2);
		
		int Totalremained = 130 - TotalCredit;
		int remained1 = 10 - credit1;
		int remained2 = 27 - credit2;
		int remained3 = 14 - credit3;
		int MajorRemained = 69-MajorCredit;
		
		table.setValueAt(Totalremained, 0, 3);
		table.setValueAt(remained1, 1, 3);
		table.setValueAt(remained2, 2, 3);
		table.setValueAt(remained3, 3, 3);
		table.setValueAt("-", 4, 3);
		table.setValueAt(MajorRemained, 5, 3);
		
		//�ι�° ���̺� ����
		//���� �������� �� ������ �ҷ�����
		//���� = ����
		float Totalgrade = sg.getTotalGrade(id, "����");
		
		table_1.setValueAt(MajorCredit, 0, 0);
		table_1.setValueAt(TotalCredit, 0, 1);
		table_1.setValueAt(Totalgrade, 0, 2);
		
		// ���� ���� ���� ��ȸ�� �̵��ϴ� �׼� ������
		graduationcheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				S_Graduation_Check nextpage;
				try {
					nextpage = new S_Graduation_Check(id);
					nextpage.setVisible(true);
					dispose();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

	}

}
