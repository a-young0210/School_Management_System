package school;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

public class S_JunKua extends JFrame{

	private JFrame frame;
	private JPanel contentPane;
	private JTextField textField;
	int sem;

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
	 * @throws Exception
	 */

	public S_JunKua(String id) throws Exception {
		
		setTitle("�л���� ���α׷�");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.white);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// ����,�л� ��� �Լ� Ŭ������ ����
		P_Management pm = new P_Management(url, con);
		S_Management sm = new S_Management(url, con);

		// �ش� ������ �̸� �ҷ�����
		Student s = sm.getStudent(id);
		String Username = sm.getstName(id);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel menuPanel1 = new JPanel();
		menuPanel1.setBounds(0, 0, 205, 563);
		contentPane.add(menuPanel1);
		menuPanel1.setLayout(null);
		
		//�޴��ȳ� ��
		JButton LectureBtn = new JButton("���� �ý���");
		LectureBtn.setBounds(0, 0, 205, 50);
		menuPanel1.add(LectureBtn);
		
		JButton Menu1 = new JButton("������ ����");
		Menu1.setBounds(4, 63, 152, 18);
		Menu1.setBorderPainted(false);
		Menu1.setFocusPainted(false);
		Menu1.setContentAreaFilled(false);
		Menu1.setHorizontalAlignment(SwingConstants.LEFT);
		menuPanel1.add(Menu1);
		
		// ���� �Լ�
		 Font font = Menu1.getFont();
		 Menu1.setForeground(Color.RED);
		 Map attributes = font.getAttributes();
		 attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		 Menu1.setFont(font.deriveFont(attributes));
		
		JButton info = new JButton("�л� ���� ��ȸ");
		info.setBounds(10, 83, 152, 18);
		info.setBorderPainted(false);
		info.setFocusPainted(false);
		info.setHorizontalAlignment(SwingConstants.LEFT);
		info.setContentAreaFilled(false);
		menuPanel1.add(info);
		
		JButton Menu2 = new JButton("���� ���� ��ȸ");
		Menu2.setBounds(4, 113, 152, 18);
		Menu2.setBorderPainted(false);
		Menu2.setFocusPainted(false);
		Menu2.setHorizontalAlignment(SwingConstants.LEFT);
		Menu2.setContentAreaFilled(false);
		menuPanel1.add(Menu2);
		
		Menu2.setForeground(Color.RED);
		Menu2.setFont(font.deriveFont(attributes));
		
		
		JButton Huhak = new JButton("���� ��û");
		Huhak.setBounds(10, 133, 152, 18);
		Huhak.setBorderPainted(false);
		Huhak.setFocusPainted(false);
		Huhak.setHorizontalAlignment(SwingConstants.LEFT);
		Huhak.setContentAreaFilled(false);
		menuPanel1.add(Huhak);		
		
		JButton Bokhak = new JButton("���� ��û");
		Bokhak.setBounds(10, 153, 152, 18);
		Bokhak.setBorderPainted(false);
		Bokhak.setFocusPainted(false);
		Bokhak.setHorizontalAlignment(SwingConstants.LEFT);
		Bokhak.setContentAreaFilled(false);
		menuPanel1.add(Bokhak);		
		
		JButton Jatuae = new JButton("���� ��û");
		Jatuae.setBounds(10, 173, 152, 18);
		Jatuae.setBorderPainted(false);
		Jatuae.setFocusPainted(false);
		Jatuae.setHorizontalAlignment(SwingConstants.LEFT);
		Jatuae.setContentAreaFilled(false);
		menuPanel1.add(Jatuae);
		
		
		JButton Menu3 = new JButton("���� ��� ��ȸ");
		Menu3.setBounds(4, 203, 152, 18);
		Menu3.setBorderPainted(false);
		Menu3.setFocusPainted(false);
		Menu3.setHorizontalAlignment(SwingConstants.LEFT);
		Menu3.setContentAreaFilled(false);
		menuPanel1.add(Menu3);
		
		Menu3.setForeground(Color.RED);
		Menu3.setFont(font.deriveFont(attributes));
		
		JButton BuBoksu = new JButton("��/���� ��û");
		BuBoksu.setBounds(10, 223, 152, 18);
		BuBoksu.setBorderPainted(false);
		BuBoksu.setFocusPainted(false);
		BuBoksu.setHorizontalAlignment(SwingConstants.LEFT);
		BuBoksu.setContentAreaFilled(false);
		menuPanel1.add(BuBoksu);		
		
		JButton Junkua = new JButton("���� ��û");
		Junkua.setBounds(10, 243, 152, 18);
		Junkua.setBorderPainted(false);
		Junkua.setFocusPainted(false);
		Junkua.setHorizontalAlignment(SwingConstants.LEFT);
		Junkua.setContentAreaFilled(false);
		Junkua.setEnabled(false);
		menuPanel1.add(Junkua);		
				
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
		menuPanel1.add(homeBtn);
		
		JPanel Main = new JPanel();
		Main.setBackground(Color.WHITE);
		Main.setForeground(Color.WHITE);
		Main.setBounds(211, 5, 970, 553);
		contentPane.add(Main);
		Main.setLayout(null);
		
		JPanel User = new JPanel();
		User.setBackground(Color.WHITE);
		User.setForeground(Color.WHITE);
		User.setBounds(12, 51, 946, 170);
		User.setBorder(new EtchedBorder(EtchedBorder.LOWERED,null,Color.black));
		Main.add(User);
		User.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED,null,Color.black));
		panel.setBounds(12, 10, 141, 150);
		User.add(panel);
		
		JLabel UserPhoto = new JLabel("UserPhoto");
		panel.add(UserPhoto);
		
		JLabel StuNum = new JLabel("�й� : ");
		StuNum.setBounds(165, 29, 140, 15);
		User.add(StuNum);
	
		
		JLabel Name = new JLabel("�̸� : ");
		Name.setBounds(165, 54, 120, 15);
		User.add(Name);
		
		JLabel Birth = new JLabel("������� : ");
		Birth.setBounds(354, 54, 140, 15);
		User.add(Birth);
		
		JLabel College = new JLabel("�Ҽ� �ܴ� : ");
		College.setBounds(165, 79, 140, 15);
		User.add(College);
		
		JLabel Department = new JLabel("�Ҽ� �а� : ");
		Department.setBounds(354, 79, 160, 15);
		User.add(Department);
		
		JLabel Grade = new JLabel("�г� : ");
		Grade.setBounds(165, 108, 120, 15);
		User.add(Grade);
		
		JLabel Semester = new JLabel("�б� : ");
		Semester.setBounds(354, 108, 140, 15);
		User.add(Semester);
		
		JLabel TextFieldPhone = new JLabel("��ȭ��ȣ : ");
		TextFieldPhone.setBounds(165, 133, 140, 15);
		User.add(TextFieldPhone);
		
		JLabel TextFieldEmail = new JLabel("�̸��� : ");
		TextFieldEmail.setBounds(354, 133, 140, 15);
		User.add(TextFieldEmail);
		
		JTextField textFieldPhone = new JTextField();
		textFieldPhone.setColumns(10);
		textFieldPhone.setBounds(229, 130, 96, 21);
		User.add(textFieldPhone);
		
		JTextField textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(410, 130, 96, 21);
		User.add(textFieldEmail);
		
		JButton Edit = new JButton("����");
		Edit.setBounds(843, 137, 91, 23);
		User.add(Edit);
		
  		Edit.addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent e) {
  				try {
					sm.editInfo(s.getstNum(), textFieldPhone.getText(), textFieldEmail.getText());
					JOptionPane.showMessageDialog(null,"������ �����Ǿ����ϴ�.");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
  			}
  		});
		
		// ������ ���� ����
		StuNum.setText("�й�: "+s.getstNum());
		Name.setText("�̸�: "+s.getstName());
		College.setText("�ҼӴܴ�: "+s.getCollege());
		Department.setText("�Ҽ��а�: "+s.getfMajor());
		Birth.setText("�������: "+s.getBirth());
		Grade.setText("�г�: "+s.getGrade());
		Semester.setText("�б�: 5");
		TextFieldPhone.setText("��ȭ��ȣ: ");
		TextFieldEmail.setText("�̸���: ");
		textFieldPhone.setText(s.getPhoneNum());
		textFieldEmail.setText(s.getEmail());
		
		//���� ��Ȳ ����
        String colNames[] = {"�г⵵", "�б�", "��û ����", "��û����", "ó�� ����"};
        String rows[][] = {};
        DefaultTableModel model = new DefaultTableModel(rows, colNames);
		
        JTable table1 = new JTable(model    	
        );
        JScrollPane scrollPane_1 = new JScrollPane(table1);
        scrollPane_1.setBounds(12, 371, 946, 150);
        Main.add(scrollPane_1);
        
		// �ش� �л� ��û ���� ����
		ResultSet rs = null;
		rs = sm.getChange(id);
		if (rs.getRow() > -1) {
			while (rs.next()) {
				// ��û��¥ �޾ƿ���
				String now = rs.getString(6);
				// ���� �޾ƿ���
				String what = rs.getString(3);
				// �ش� �м���ȣ�� ���� ���� ��ü ��� ����
				MajorApplication ma = sm.getMa(id, now, what);
				// GUI�� ����
				String[] row = { ma.getApSemester().substring(0,4), ma.getApSemester().substring(5), ma.getApMajor(), 
						ma.getApDate(), ma.getApState()};

				model.addRow(row);
			}
		}
		
		Calendar today = Calendar.getInstance();
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = date.format(today.getTime());
        SimpleDateFormat year = new SimpleDateFormat ("yyyy");
        SimpleDateFormat month = new SimpleDateFormat ("MM");
        SimpleDateFormat day = new SimpleDateFormat ("dd");
        String strMonth = month.format(today.getTime());
        String strYear = year.format(today.getTime());
        String strDay = day.format(today.getTime());
        int intMonth =  Integer.parseInt(strMonth);
        String allDate = strYear +"-" + strMonth + "-" + strDay;
        
        if(intMonth >= 1 && intMonth < 7)
        {
        	sem = 2;
        } else
        {
        	sem = 1;
        }
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(Color.WHITE);
        panel_1.setBounds(12, 231, 946, 130);
        Main.add(panel_1);
        panel_1.setLayout(null);
        
        JLabel J_Year = new JLabel("���� �г⵵ : " + strYear );
        J_Year.setBounds(12, 10, 110, 21);
        panel_1.add(J_Year);
        
        JLabel J_Semester = new JLabel("���� �б� : " + sem);
        J_Semester.setBounds(204, 10, 77, 21);
        panel_1.add(J_Semester);
        
        //���� ���� �Է�
        JComboBox J_Summary = new JComboBox();
        J_Summary.setModel(new DefaultComboBoxModel(new String[] {"���� ����", "�о� ��ȹ ����", "���"}));
        J_Summary.setBounds(12, 54, 85, 23);
        panel_1.add(J_Summary);
        
        //������ �а�
        JComboBox J_Department = new JComboBox();
        J_Department.setModel(new DefaultComboBoxModel(new String[] {"��û �а�(����)", "����Ʈ���������а�", "������ȣ�а�", "�����й̵���а�"}));
        J_Department.setBounds(204, 54, 115, 23);
        panel_1.add(J_Department);
        
        JButton Submit = new JButton("��û");
        Submit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
				try {
					sm.addChange(sm.getstName(id), id, strYear + "-" + sem, J_Department.getSelectedItem().toString(), 
							J_Summary.getSelectedItem().toString(), strDate);
					JOptionPane.showMessageDialog(null,"������û�� �Ϸ�Ǿ����ϴ�.");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        Submit.setBounds(843, 97, 91, 23);
        panel_1.add(Submit);
        
       /* JLabel S_Date = new JLabel("��û ��Ȳ");
        S_Date.setBounds(391, 10, 77, 21);
        panel_1.add(S_Date);*/
        
       /* JLabel J_Agree = new JLabel("��ü ���� ��ȸ");
        J_Agree.setBounds(12, 98, 91, 21);
        panel_1.add(J_Agree);*/
        
       /* JRadioButton Yes = new JRadioButton("YES");
        Yes.setBackground(Color.WHITE);
        Yes.setBounds(120, 97, 68, 23);
        panel_1.add(Yes);
        
        JRadioButton No = new JRadioButton("NO");
        No.setBackground(Color.WHITE);
        No.setBounds(204, 97, 68, 23);
        panel_1.add(No);*/
        
        /*JButton PlanUpload = new JButton("�о���ȹ�� ���ε�");
        PlanUpload.setBounds(556, 9, 150, 23);
        panel_1.add(PlanUpload);*/
        
        textField = new JTextField();
        textField.setText("���� ��û");
        textField.setFont(new Font("���Ļ�浸��", Font.BOLD, 20));
        textField.setColumns(10);
        textField.setBounds(12, 9, 105, 32);
        Main.add(textField);
        
        //���� �ý��� �׼� ������ - �޴�
    		Menu1.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				S_Info info;
					try {
						info = new S_Info(id);
	    				info.setVisible(true);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
  				dispose();
    			}
    		});

    		Menu2.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				S_Huhak huhak;
					try {
						huhak = new S_Huhak(id);
	    				huhak.setVisible(true);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
  				dispose();
    			}
    		});
    		
    		
    	//���� �޴� �̵��ϴ� ���
    		//�л� ���� ��ȸ �׼� ������
    		info.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				S_Info Info;
					try {
						Info = new S_Info(id);
	    				Info.setVisible(true);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
  				dispose();
    			}
    		});
    		
      		//���� ��û ��ȸ �׼� ������
      		Huhak.addActionListener(new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
        				S_Huhak huhak;
						try {
							huhak = new S_Huhak(id);
	        				huhak.setVisible(true);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}		
      				dispose();	
        			}
        		});
      		
      		//���� ��û ��ȸ �׼� ������
      		Bokhak.addActionListener(new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
        				S_Bokhak bokhak;
						try {
							bokhak = new S_Bokhak(id);
	        				bokhak.setVisible(true);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}		
      				dispose();	
        			}
        		});

      		//���� ��û ��ȸ �׼� ������
      		Jatuae.addActionListener(new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
        				S_Jatuae jatuae;
						try {
							jatuae = new S_Jatuae(id);
	        				jatuae.setVisible(true);	
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}	
      				dispose();	
        			}
        		});
      		
      		//��/���� ��û ��ȸ
      		BuBoksu.addActionListener(new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
        				S_BuBokSu buboksu;
						try {
							buboksu = new S_BuBokSu(id);
	        				buboksu.setVisible(true);	
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}	
      				dispose();	
        			}
        		});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
