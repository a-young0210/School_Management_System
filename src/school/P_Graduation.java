package school;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

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
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class P_Graduation extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private JTextField SearchNum;
	private JScrollPane scrollPane;
	private JTextField textField;
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

					P_Graduation frame = new P_Graduation(id);
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
	public P_Graduation(String id) throws Exception {
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
		
		//�޴��ȳ� ��
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
		Main.setBorder(new EtchedBorder(EtchedBorder.LOWERED,null,Color.black));
		contentPane.add(Main);
		Main.setLayout(null);
        
        JLabel Title = new JLabel("���� ��� ��Ȳ");
        Title.setFont(new Font("���Ļ�浸��", Font.BOLD, 26));
        Title.setBounds(12, 17, 200, 44);
        Main.add(Title);
        
        JScrollPane scrollPane;
        scrollPane = new JScrollPane();
        scrollPane.setBounds(12, 180, 946, 125);
        Main.add(scrollPane);
        
        JLabel CategoryScore = new JLabel("�̼� ���к� ���� ��Ȳ");
        CategoryScore.setFont(new Font("���Ļ�浸��", Font.BOLD, 14));
        CategoryScore.setBounds(415, 125, 200, 30);
        Main.add(CategoryScore);
        
        //�̼� ���к� ���� ��� ��Ȳ
        table = new JTable();
        table.setFont(new Font("����", Font.PLAIN, 12));
        table.setBorder(new LineBorder(new Color(0, 0, 0)));
		String colNames[] = {"", "�̼� ���� ����", "��� ����", "�ܿ� ����"};
		String rows[][] = {
        		{"������", "130", null, null},
        		{"���� �ʼ�", "10", null, null},
        		{"���� ����", "27", null, null},
        		{"���� �ʼ�", "14", null, null},
        		{"���� ����", "-", null, null},
        		{"���� ��", "69", null, null},
        	};
		DefaultTableModel model = new DefaultTableModel(rows, colNames);

		table = new JTable(model);
		
        scrollPane.setViewportView(table);

        
        JLabel TotalScore_Title = new JLabel("��ü ���� ����");
        TotalScore_Title.setFont(new Font("���Ļ�浸��", Font.BOLD, 14));
        TotalScore_Title.setBounds(442, 337, 95, 44);
        Main.add(TotalScore_Title);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(12, 391, 946, 85);
        Main.add(scrollPane_1);
        
        String colNames1[] = {"���� �� ����", "�� �̼� ����","�� ����"};
        String rows1[][] = {};
        DefaultTableModel model1 = new DefaultTableModel(rows1, colNames1);
        table_1 = new JTable(model1);
        table_1.setFont(new Font("����", Font.PLAIN, 12));
        table_1.setBorder(new LineBorder(new Color(0, 0, 0)));
        scrollPane_1.setViewportView(table_1);
        
        //�л� ���� �Է� �޴� �κ�
       
        
        JLabel Summary = new JLabel("��ȸ�� �л��� �й��� �Է����ּ���.");
        Summary.setFont(new Font("���Ļ�浸��", Font.PLAIN, 13));
        Summary.setBounds(650, 60, 230, 15);
        Main.add(Summary);
        
        JLabel lblNewLabel = new JLabel("\uD559\uBC88 : ");
        lblNewLabel.setFont(new Font("���Ļ�浸��", Font.PLAIN, 14));
        lblNewLabel.setBounds(608, 36, 100, 15);
        Main.add(lblNewLabel);
        
        textField = new JTextField();
        textField.setBounds(648, 34, 215, 21);
        Main.add(textField);
        textField.setColumns(10);
        
        JButton SearchBtn = new JButton("��ȸ");
        SearchBtn.setIcon(new ImageIcon("C:\\Users\\Swim\\Desktop\\\uD559\uAD50\uD0C8\uCD9C\\2021-1\\\uD504\uB85C\uC81D\uD2B8\\\uCEA1\uCC98.PNG"));
        SearchBtn.setBounds(870, 31, 70, 27);
        Main.add(SearchBtn);

		// ���� ���� ��ȸ�ϰ� ���� �л��� �й� �˻� ���
        //�˻� ���
		SearchBtn.addActionListener(new ActionListener() {
			ResultSet rs = null;
			Student s = new Student();
			public void actionPerformed(ActionEvent e) {
				// ���̺� ��� �� �ʱ�ȭ �ϱ� - �ƿ� ���� �����̱� ����
				if (table.getRowCount() > 0) {
					model1.setNumRows(0);
				}

				// ���̺� ��� �� �ʿ��� row number�� ����� ����.
				int count = 0;

				if (textField.getText().equals("")) {
					//�޺� �ڽ��� �а����� �� �˻� ��ư�� �������� ���â���� �а��� �����ش޶�� �ȳ��ϱ�
					 JOptionPane.showMessageDialog(null, "��ȸ�� �й��� �Է����ֽʽÿ�.");
				} else {
					String stNum = textField.getText();
					try {
						
						// ù��° ���̺� ������ �����ϱ�
						// �ʿ��� �����͵�
						// �� �̼� ����
						int TotalCredit = mg.getTotalCredit(stNum, "������");
						// ���� �ʼ�
						int credit1 = mg.getTotalCredit(stNum, "�����ʼ�");
						// ���� ����
						int credit2 = mg.getTotalCredit(stNum, "���缱��");
						// ���� �ʼ�
						int credit3 = mg.getTotalCredit(stNum, "�����ʼ�");
						// ���� ����
						int credit4 = mg.getTotalCredit(stNum, "��������");
						// ���� ��(���� �ʼ� + ���� ����)
						int MajorCredit = credit3 + credit4;
						
						//���̺� �ֱ�
						table.setValueAt(TotalCredit, 0, 2);
						table.setValueAt(credit1, 1, 2);
						table.setValueAt(credit2, 2, 2);
						table.setValueAt(credit3, 3, 2);
						table.setValueAt(credit4, 4, 2);
						table.setValueAt(MajorCredit, 5, 2);

						//�ܿ����� ���
						int Totalremained = 130 - TotalCredit;
						int remained1 = 10 - credit1;
						int remained2 = 27 - credit2;
						int remained3 = 14 - credit3;
						int MajorRemained = 69-MajorCredit;
						
						//���̺� �ֱ�
						table.setValueAt(Totalremained, 0, 3);
						table.setValueAt(remained1, 1, 3);
						table.setValueAt(remained2, 2, 3);
						table.setValueAt(remained3, 3, 3);
						table.setValueAt("-", 4, 3);
						table.setValueAt(MajorRemained, 5, 3);
						
						//�ι�° ���̺� ������ ����
						
						//���� �� ����(�� ����) �ҷ�����
						float Totalgrade = mg.getTotalGrade(stNum, "����"); 
						
						String[] rows = { Integer.toString(MajorCredit), Integer.toString(TotalCredit), Float.toString(Totalgrade)};
						model1.addRow(rows);


					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}
		});
        
      //���� ���� ���� ��ȸ �׼� ������
		graduationcheck.addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent e) {
  				P_GraduationCheck nextpage;
				try {
					nextpage = new P_GraduationCheck(id);
	  				nextpage.setVisible(true);		
					dispose();	
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
  			}
  		});
		
		//Ȩ ȭ�� ���ư��� �׼� ������
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
