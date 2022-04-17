package school;

import java.awt.BorderLayout;
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
import java.util.Map;
import java.util.Scanner;

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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

public class P_Bokhak extends JFrame {

	private JPanel contentPane;
	private JTextField Phone;
	private JTextField Email;
	
	//public int SubNum = 0;
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

					P_Management mg = new P_Management(url, con);
					S_Management sg = new S_Management(url, con);
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

	public P_Bokhak(String id) throws Exception {
		
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

		// ����,�л� ��� �Լ� Ŭ������ ����
		P_Management pm = new P_Management(url, con);
		S_Management sm = new S_Management(url, con);
		Functions func = new Functions(url, con);
						
		// �ش� ������ �̸� �ҷ�����
		Professor p = pm.getProfessor(id);
		String Username = pm.checkNamePr(id);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel menuPanel = new JPanel();
		menuPanel.setBounds(0, 0, 205, 563);
		contentPane.add(menuPanel);
		menuPanel.setLayout(null);
		
		//�޴��ȳ� ��
		JButton LectureBtn = new JButton("���� �ý���");
		LectureBtn.setBounds(0, 0, 205, 50);
		menuPanel.add(LectureBtn);
		
		JButton Menu1 = new JButton("������ ����");
		Menu1.setBounds(4, 63, 152, 18);
		Menu1.setBorderPainted(false);
		Menu1.setFocusPainted(false);
		Menu1.setContentAreaFilled(false);
		Menu1.setHorizontalAlignment(SwingConstants.LEFT);
		menuPanel.add(Menu1);
		
		// ���� �Լ�
		 Font font = Menu1.getFont();
		 Menu1.setForeground(Color.RED);
		 Map attributes = font.getAttributes();
		 attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		 Menu1.setFont(font.deriveFont(attributes));
		
		JButton info = new JButton("���� ���� ��ȸ");
		info.setBounds(10, 83, 152, 18);
		info.setBorderPainted(false);
		info.setFocusPainted(false);
		info.setHorizontalAlignment(SwingConstants.LEFT);
		info.setContentAreaFilled(false);
		menuPanel.add(info);
		
		JButton Menu2 = new JButton("���� ���� ��ȸ");
		Menu2.setBounds(4, 113, 152, 18);
		Menu2.setBorderPainted(false);
		Menu2.setFocusPainted(false);
		Menu2.setHorizontalAlignment(SwingConstants.LEFT);
		Menu2.setContentAreaFilled(false);
		menuPanel.add(Menu2);
		
		Menu2.setForeground(Color.RED);
		Menu2.setFont(font.deriveFont(attributes));
		
		
		JButton Huhak = new JButton("���� ��û ��ȸ");
		Huhak.setBounds(10, 133, 152, 18);
		Huhak.setBorderPainted(false);
		Huhak.setFocusPainted(false);
		Huhak.setHorizontalAlignment(SwingConstants.LEFT);
		Huhak.setContentAreaFilled(false);
		menuPanel.add(Huhak);		
		
		JButton Bokhak = new JButton("���� ��û ��ȸ");
		Bokhak.setBounds(10, 153, 152, 18);
		Bokhak.setBorderPainted(false);
		Bokhak.setFocusPainted(false);
		Bokhak.setHorizontalAlignment(SwingConstants.LEFT);
		Bokhak.setContentAreaFilled(false);
		Bokhak.setEnabled(false);
		menuPanel.add(Bokhak);		
		
		JButton Jatuae = new JButton("���� ��û ��ȸ");
		Jatuae.setBounds(10, 173, 152, 18);
		Jatuae.setBorderPainted(false);
		Jatuae.setFocusPainted(false);
		Jatuae.setHorizontalAlignment(SwingConstants.LEFT);
		Jatuae.setContentAreaFilled(false);
		menuPanel.add(Jatuae);
		
		
		JButton Menu3 = new JButton("���� ��� ��ȸ");
		Menu3.setBounds(4, 203, 152, 18);
		Menu3.setBorderPainted(false);
		Menu3.setFocusPainted(false);
		Menu3.setHorizontalAlignment(SwingConstants.LEFT);
		Menu3.setContentAreaFilled(false);
		menuPanel.add(Menu3);
		
		Menu3.setForeground(Color.RED);
		Menu3.setFont(font.deriveFont(attributes));
		
		JButton BuBoksu = new JButton("��/���� ��û ��ȸ");
		BuBoksu.setBounds(10, 223, 152, 18);
		BuBoksu.setBorderPainted(false);
		BuBoksu.setFocusPainted(false);
		BuBoksu.setHorizontalAlignment(SwingConstants.LEFT);
		BuBoksu.setContentAreaFilled(false);
		menuPanel.add(BuBoksu);		
		
		JButton Junkua = new JButton("���� ��û ��ȸ");
		Junkua.setBounds(10, 243, 152, 18);
		Junkua.setBorderPainted(false);
		Junkua.setFocusPainted(false);
		Junkua.setHorizontalAlignment(SwingConstants.LEFT);
		Junkua.setContentAreaFilled(false);
		menuPanel.add(Junkua);		
		
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
		Main.setForeground(Color.WHITE);
		Main.setBounds(211, 5, 970, 553);
		contentPane.add(Main);
		Main.setLayout(null);
		
		JPanel User = new JPanel();
		User.setBackground(Color.WHITE);
		User.setForeground(Color.WHITE);
		User.setBounds(12, 10, 946, 170);
		User.setBorder(new EtchedBorder(EtchedBorder.LOWERED,null,Color.black));
		Main.add(User);
		User.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED,null,Color.black));
		panel.setBounds(12, 10, 141, 150);
		User.add(panel);
		
		JLabel UserPhoto = new JLabel("UserPhoto");
		panel.add(UserPhoto);
		
		JLabel StuNum = new JLabel("���� : ");
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
		
		
		JLabel TextFieldPhone = new JLabel("��ȭ��ȣ : ");
		TextFieldPhone.setBounds(165, 104, 140, 15);
		User.add(TextFieldPhone);
		
		JLabel TextFieldEmail = new JLabel("�̸��� : ");
		TextFieldEmail.setBounds(354, 104, 140, 15);
		User.add(TextFieldEmail);
		
		JTextField textFieldPhone = new JTextField();
		textFieldPhone.setColumns(10);
		textFieldPhone.setBounds(229, 100, 96, 21);
		User.add(textFieldPhone);
		
		JTextField textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(410, 100, 96, 21);
		User.add(textFieldEmail);
		
		JButton Edit = new JButton("����");
		Edit.setBounds(843, 137, 91, 23);
		User.add(Edit);
		
  		Edit.addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent e) {
  				try {
					pm.editInfo(p.getprNum(), textFieldPhone.getText(), textFieldEmail.getText());
					JOptionPane.showMessageDialog(null,"������ �����Ǿ����ϴ�.");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
  			}
  		});
		
		// ������ ���� ����
		StuNum.setText("����: "+p.getprNum());
		Name.setText("�̸�: "+p.getprName());
		College.setText("�ҼӴܴ�: "+p.getCollege());
		Department.setText("�Ҽ��а�: "+p.getMajor());
		Birth.setText("�������: "+p.getBirth());
		TextFieldPhone.setText("��ȭ��ȣ: ");
		TextFieldEmail.setText("�̸���: ");
		textFieldPhone.setText(p.getPhoneNum());
		textFieldEmail.setText(p.getEmail());
		
		//���� ��û Ȯ��
        String colNames[] = {"�й�", "�̸�", "�г⵵", "�б�", "��û��", "ó�� ����"};
        String rows[][] = {};
        DefaultTableModel model = new DefaultTableModel(rows, colNames);
		
        JTable table = new JTable(model
        	);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(12, 247, 946, 192);
        Main.add(scrollPane);     
        
        // �ش� �л� ��û ���� ����
     	ResultSet rs = null;
     	rs = pm.getGoBack();
     	if (rs.getRow() > -1) {
     		while (rs.next()) {
     			// ��û �л� �̸� �޾ƿ���
     			String name = rs.getString(1);
     			// ��û �л� �й� �޾ƿ���
     			String num = rs.getString(2);
     			// ��û��¥ �޾ƿ���
     			String now = rs.getString(9);
     			// ���� �޾ƿ���
     			String what = rs.getString(4);
     			// �ش� �м���ȣ�� ���� ���� ��ü ��� ����
     			SchoolApplication sa = sm.getSa(num, now, what);
     			// GUI�� ����
     			String[] row = { num, name, sa.getApSemester().substring(0,4), sa.getApSemester().substring(5), sa.getApCount(), sa.getApState()};

     			model.addRow(row);
     		}
     	}
        
        JLabel ProfBokhak = new JLabel("���� ��û ��ȸ");
        ProfBokhak.setFont(new Font("���Ļ�浸��", Font.BOLD, 23));
        ProfBokhak.setBounds(12, 210, 172, 27);
        Main.add(ProfBokhak);
        
        JButton Delete = new JButton("��û ���");
        Delete.setBounds(771, 446, 91, 23);
        Main.add(Delete);
        
     	Delete.addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent e) {
  				try {
  					//������ ��(row)
  					int row = table.getSelectedRow();

  					//���� ���ϰ� ���� ��� 
  					if(row == -1) 
  						JOptionPane.showMessageDialog(null,"��û�� ����� �����͸� �������ּ���.");
  					
  					String stNum = (String) table.getValueAt(row, 0);
  					String now = (String) table.getValueAt(row, 4);
					pm.deleteGoBack(stNum, now);
					model.removeRow(row);
					JOptionPane.showMessageDialog(null,"�ش� ���� ��û�� ��ҵǾ����ϴ�.");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
  			}
  		});
        
        JButton Confirm = new JButton("����");
        Confirm.setBounds(867, 446, 91, 23);
        Main.add(Confirm);
        
     	Confirm.addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent e) {
  				try {
  					//������ ��(row)
  					int row = table.getSelectedRow();

  					//���� ���ϰ� ���� ��� 
  					if(row == -1) 
  						JOptionPane.showMessageDialog(null,"������ �����͸� �������ּ���.");
  					
  					String stNum = (String) table.getValueAt(row, 0);
  					String now = (String) table.getValueAt(row, 4);
					pm.acceptGoBack(stNum, now);
					JOptionPane.showMessageDialog(null,"�ش� ���� ��û�� ���εǾ����ϴ�.");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
  			}
  		});
        
        
      		//���� �ý��� �׼� ������ - �޴�
      		Menu1.addActionListener(new ActionListener() {
      			public void actionPerformed(ActionEvent e) {
      				P_Info info;
					try {
						info = new P_Info(id);
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
      				P_Huhak huhak;
					try {
						huhak = new P_Huhak(id);
	      				huhak.setVisible(true);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
    				dispose();
      			}
      		});
      		
      		Menu3.addActionListener(new ActionListener() {
      			public void actionPerformed(ActionEvent e) {
      				P_BuBoksu buboksu;
					try {
						buboksu = new P_BuBoksu(id);
	      				buboksu.setVisible(true);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
    				dispose();
      			}
      		});
      		
      		
      		//���� �޴� �̵��ϴ� ���
          	//���� ���� ��ȸ �׼� ������
        		info.addActionListener(new ActionListener() {
          			public void actionPerformed(ActionEvent e) {
          				P_Info info;
						try {
							info = new P_Info(id);
	          				info.setVisible(true);	
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
          				P_Huhak huhak;
						try {
							huhak = new P_Huhak(id);
	          				huhak.setVisible(true);		
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
          				P_Jatuae jatuae;
						try {
							jatuae = new P_Jatuae(id);
	          				jatuae.setVisible(true);	
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}	
        				dispose();	
          			}
          		});
        		

        		//��/���� ��û ��ȸ �׼� ������
        		BuBoksu.addActionListener(new ActionListener() {
          			public void actionPerformed(ActionEvent e) {
          				P_BuBoksu buboksu;
						try {
							buboksu = new P_BuBoksu(id);
	          				buboksu.setVisible(true);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}		
        				dispose();	
          			}
          		});
        		
        		//���� ��û ��ȸ �׼� ������
        		Junkua.addActionListener(new ActionListener() {
          			public void actionPerformed(ActionEvent e) {
          				P_JunKua junkua;
						try {
							junkua = new P_JunKua(id);
	          				junkua.setVisible(true);	
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}	
        				dispose();	
          			}
          		});
        		
	}

}
