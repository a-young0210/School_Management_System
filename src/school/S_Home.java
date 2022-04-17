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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.swing.JMenuItem;

public class S_Home extends JFrame {

	private JPanel contentPane;
	private JTextField Phone;
	private JTextField Email;
	private JTable table;
	private JTable Home_Subject;

	public static String id;
	public static String url = "jdbc:mariadb://localhost:3306/school";
	public static Connection con;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					System.out.println("Ȩ ȭ�� ����");
					Class.forName("org.mariadb.jdbc.Driver");
					// sql �����ϱ� ���� ���̵�� ��й�ȣ �Է�
					url = "jdbc:mariadb://localhost:3306/school";

					con = DriverManager.getConnection(url, "root", "1234");

					S_Management mg = new S_Management(url, con);
					P_Home frame = new P_Home(id);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public S_Home(String id) throws SQLException, ClassNotFoundException {
		setTitle("�л���� ���α׷�");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 600);

		// ���� �Լ��� ����
		S_Management mg = new S_Management(url, con);
		Functions func = new Functions(url, con);

		// �ش� ������ �̸� �ҷ�����
		Student s = mg.getStudent(id);

		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		// TitledBorder tb = new TitledBorder(new LineBorder(Color.BLACK));
		contentPane.setBorder(new LineBorder(Color.BLACK)); //
		// contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel menuPanel = new JPanel();
		menuPanel.setBounds(0, 0, 205, 563);
		contentPane.add(menuPanel);
		menuPanel.setBorder(new LineBorder(Color.BLACK)); //
		menuPanel.setLayout(null);

		JPanel Main = new JPanel();
		Main.setBackground(Color.WHITE);
		Main.setForeground(Color.WHITE);
		Main.setBounds(12, 5, 1169, 553);
		contentPane.add(Main);
		Main.setLayout(null);

		JButton lectureBtn = new JButton("���� �ý���");
		lectureBtn.setBounds(0, 0, 205, 50);
		menuPanel.add(lectureBtn);

		JButton gradeBtn = new JButton("���� �ý���");
		gradeBtn.setBounds(0, 50, 205, 50);
		menuPanel.add(gradeBtn);

		JButton infoBtn = new JButton("���� �ý���");
		infoBtn.setBounds(0, 100, 205, 50);
		menuPanel.add(infoBtn);

		JButton graduateBtn = new JButton("���� �ý���");
		graduateBtn.setBounds(0, 150, 205, 50);
		menuPanel.add(graduateBtn);

		// ******** ���� �ý��� ********
		lectureBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel menuPanel2 = new JPanel();
				menuPanel2.setBounds(0, 0, 205, 563);
				menuPanel.add(menuPanel2);
				menuPanel2.setLayout(null);

				JButton lectureBtn = new JButton("���� �ý���");
				lectureBtn.setBounds(0, 0, 205, 50);
				menuPanel2.add(lectureBtn);

				// �޴� ���� ȭ�� �̵�
				JButton homeBtn = new JButton("�ڷΰ���");
				menuPanel2.add(homeBtn);
				homeBtn.setBounds(45, 500, 105, 27);
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

				// ��ư ���� �Լ� ���� - ����ȭ
				lectureBtn.setVisible(false);
				gradeBtn.setVisible(false);
				infoBtn.setVisible(false);
				graduateBtn.setVisible(false);

				JButton showAllClassLabel = new JButton("����������ȸ");
				showAllClassLabel.setBounds(10, 63, 152, 18);
				menuPanel2.add(showAllClassLabel);
				showAllClassLabel.setBorderPainted(false);
				showAllClassLabel.setFocusPainted(false);
				showAllClassLabel.setContentAreaFilled(false);
				showAllClassLabel.setHorizontalAlignment(SwingConstants.LEFT);
				menuPanel2.add(showAllClassLabel);
				showAllClassLabel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
						S_ShowAllClasses sac;
						try {
							sac = new S_ShowAllClasses(id);
							sac.setVisible(true);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});

				JButton BasketLabel = new JButton("��ٱ��� �Է�");
				BasketLabel.setBounds(10, 94, 152, 18);
				menuPanel2.add(BasketLabel);
				BasketLabel.setBorderPainted(false);
				BasketLabel.setFocusPainted(false);
				BasketLabel.setContentAreaFilled(false);
				BasketLabel.setHorizontalAlignment(SwingConstants.LEFT);
				menuPanel2.add(BasketLabel);
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
				menuPanel2.add(applicationLabel);
				applicationLabel.setBorderPainted(false);
				applicationLabel.setFocusPainted(false);
				applicationLabel.setContentAreaFilled(false);
				applicationLabel.setHorizontalAlignment(SwingConstants.LEFT);
				menuPanel2.add(applicationLabel);
				applicationLabel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
						S_ApplicationClass sac;
						try {
							sac = new S_ApplicationClass(id);
							sac.setVisible(true);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});

				JButton stuClassLabel = new JButton("������û ����");
				stuClassLabel.setBounds(10, 156, 152, 18);
				menuPanel2.add(stuClassLabel);
				stuClassLabel.setBorderPainted(false);
				stuClassLabel.setFocusPainted(false);
				stuClassLabel.setContentAreaFilled(false);
				stuClassLabel.setHorizontalAlignment(SwingConstants.LEFT);
				menuPanel2.add(stuClassLabel);
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

			}
		});

		// ******** ���� �ý��� ********
		gradeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel menuPanel2 = new JPanel();
				menuPanel2.setBounds(0, 0, 205, 563);
				menuPanel.add(menuPanel2);
				menuPanel2.setLayout(null);

				JButton gradeBtn2 = new JButton("���� �ý���");
				gradeBtn2.setBounds(0, 0, 205, 50);
				menuPanel2.add(gradeBtn2);

				// ��ư ���� �Լ� ���� - ����ȭ
				lectureBtn.setVisible(false);
				gradeBtn.setVisible(false);
				infoBtn.setVisible(false);
				graduateBtn.setVisible(false);

				JButton recentGradeLabel = new JButton("���б⼺����ȸ");
				recentGradeLabel.setBounds(10, 63, 152, 18);
				menuPanel2.add(recentGradeLabel);
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
							e1.printStackTrace();
						}
					}
				});

				JButton allGradeLabel = new JButton("��ü������ȸ");
				allGradeLabel.setBounds(10, 94, 152, 18);
				menuPanel2.add(allGradeLabel);
				allGradeLabel.setBorderPainted(false);
				allGradeLabel.setFocusPainted(false);
				allGradeLabel.setContentAreaFilled(false);
				allGradeLabel.setHorizontalAlignment(SwingConstants.LEFT);
				allGradeLabel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
						S_ShowAllGrade sag;
						try {
							sag = new S_ShowAllGrade(id);
							sag.setVisible(true);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				});

				// �޴� ���� ȭ�� �̵�
				JButton homeBtn = new JButton("�ڷΰ���");
				menuPanel2.add(homeBtn);
				homeBtn.setBounds(45, 500, 105, 27);
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
			}
		});

		// ******** ���� �ý��� ********
		infoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel menuPanel2 = new JPanel();
				menuPanel2.setBounds(0, 0, 205, 563);
				menuPanel.add(menuPanel2);
				menuPanel2.setLayout(null);

				JButton infoBtn2 = new JButton("���� �ý���");
				infoBtn2.setBounds(0, 0, 205, 50);
				menuPanel2.add(infoBtn2);

				// ��ư ���� �Լ� ���� - ����ȭ
				lectureBtn.setVisible(false);
				gradeBtn.setVisible(false);
				infoBtn.setVisible(false);
				graduateBtn.setVisible(false);

				// 1.������ ����
				JButton sManage = new JButton("������ ����");
				sManage.setBounds(10, 63, 152, 18);
				menuPanel2.add(sManage);
				sManage.setBorderPainted(false);
				sManage.setFocusPainted(false);
				sManage.setContentAreaFilled(false);
				sManage.setHorizontalAlignment(SwingConstants.LEFT);

				// ���� �Լ�
				Font font = sManage.getFont();
				sManage.setForeground(Color.RED);
				Map attributes = font.getAttributes();
				attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
				sManage.setFont(font.deriveFont(attributes));

				sManage.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// ���⿡ ȭ����ȯ �Լ� �ڵ带 ������.
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

				// �л�������ȸ
				JButton showSInfo = new JButton("�л�������ȸ");
				showSInfo.setBounds(10, 83, 152, 18);
				menuPanel2.add(showSInfo);
				showSInfo.setBorderPainted(false);
				showSInfo.setFocusPainted(false);
				showSInfo.setContentAreaFilled(false);
				showSInfo.setHorizontalAlignment(SwingConstants.LEFT);
				showSInfo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// ���⿡ ȭ����ȯ �Լ� �ڵ带 ������.
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

				// 2.����������ȸ
				JButton showRegistChange = new JButton("����������ȸ");
				showRegistChange.setBounds(10, 113, 152, 18);
				menuPanel2.add(showRegistChange);
				showRegistChange.setBorderPainted(false);
				showRegistChange.setFocusPainted(false);
				showRegistChange.setContentAreaFilled(false);
				showRegistChange.setHorizontalAlignment(SwingConstants.LEFT);
				showRegistChange.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
		  				S_Huhak info;
						try {
							info = new S_Huhak(id);
		    				info.setVisible(true);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	  				dispose();
						}
					});

				showRegistChange.setForeground(Color.RED);
				showRegistChange.setFont(font.deriveFont(attributes));

				// ���н�û
				JButton huHakGo = new JButton("���н�û");
				huHakGo.setBounds(10, 133, 152, 18);
				menuPanel2.add(huHakGo);
				huHakGo.setBorderPainted(false);
				huHakGo.setFocusPainted(false);
				huHakGo.setContentAreaFilled(false);
				huHakGo.setHorizontalAlignment(SwingConstants.LEFT);
				huHakGo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
		  				S_Huhak nextpage;
						try {
							nextpage = new S_Huhak(id);
			  				nextpage.setVisible(true);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	  				dispose();
						}
					});	

				// ���н�û
				JButton bokHakGo = new JButton("���н�û");
				bokHakGo.setBounds(10, 153, 152, 18);
				menuPanel2.add(bokHakGo);
				bokHakGo.setBorderPainted(false);
				bokHakGo.setFocusPainted(false);
				bokHakGo.setContentAreaFilled(false);
				bokHakGo.setHorizontalAlignment(SwingConstants.LEFT);
				bokHakGo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
		  				S_Bokhak nextpage;
						try {
							nextpage = new S_Bokhak(id);
			  				nextpage.setVisible(true);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	  				dispose();
						}
					});	

				// �����û
				JButton zaTaeGo = new JButton("�����û");
				zaTaeGo.setBounds(10, 173, 152, 18);
				menuPanel2.add(zaTaeGo);
				zaTaeGo.setBorderPainted(false);
				zaTaeGo.setFocusPainted(false);
				zaTaeGo.setContentAreaFilled(false);
				zaTaeGo.setHorizontalAlignment(SwingConstants.LEFT);
				zaTaeGo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
		  				S_Jatuae nextpage;
						try {
							nextpage = new S_Jatuae(id);
			  				nextpage.setVisible(true);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	  				dispose();
						}
					});	

				// 3.���������ȸ
				JButton showMajorChange = new JButton("���������ȸ");
				showMajorChange.setBounds(10, 203, 152, 18);
				menuPanel2.add(showMajorChange);
				showMajorChange.setBorderPainted(false);
				showMajorChange.setFocusPainted(false);
				showMajorChange.setContentAreaFilled(false);
				showMajorChange.setHorizontalAlignment(SwingConstants.LEFT);
				showMajorChange.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						S_BuBokSu nextpage;
						try {
							nextpage = new S_BuBokSu(id);
							nextpage.setVisible(true);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						dispose();
						}
					});	

				showMajorChange.setForeground(Color.RED);
				showMajorChange.setFont(font.deriveFont(attributes));

				// ��/������û
				JButton doubleMajorGo = new JButton("��/������û");
				doubleMajorGo.setBounds(10, 223, 152, 18);
				menuPanel2.add(doubleMajorGo);
				doubleMajorGo.setBorderPainted(false);
				doubleMajorGo.setFocusPainted(false);
				doubleMajorGo.setContentAreaFilled(false);
				doubleMajorGo.setHorizontalAlignment(SwingConstants.LEFT);
				doubleMajorGo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						S_BuBokSu nextpage;
						try {
							nextpage = new S_BuBokSu(id);
							nextpage.setVisible(true);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						dispose();
						}
					});	

				// ������û
				JButton changeMajorGo = new JButton("������û");
				changeMajorGo.setBounds(10, 243, 152, 18);
				menuPanel2.add(changeMajorGo);
				changeMajorGo.setBorderPainted(false);
				changeMajorGo.setFocusPainted(false);
				changeMajorGo.setContentAreaFilled(false);
				changeMajorGo.setHorizontalAlignment(SwingConstants.LEFT);
				changeMajorGo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						S_JunKua nextpage;
						try {
							nextpage = new S_JunKua(id);
							nextpage.setVisible(true);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						dispose();
						}
					});

				// �޴� ���� ȭ�� �̵�
				JButton homeBtn = new JButton("�ڷΰ���");
				menuPanel2.add(homeBtn);
				homeBtn.setBounds(45, 500, 105, 27);
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
			}
		});

		// ******** ���� �ý��� ********
		graduateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel menuPanel2 = new JPanel();
				menuPanel2.setBounds(0, 0, 205, 563);
				menuPanel.add(menuPanel2);
				menuPanel2.setLayout(null);

				JButton graduateBtn2 = new JButton("���� �ý���");
				graduateBtn2.setBounds(0, 0, 205, 50);
				menuPanel2.add(graduateBtn2);

				// ��ư ���� �Լ� ���� - ����ȭ
				lectureBtn.setVisible(false);
				gradeBtn.setVisible(false);
				infoBtn.setVisible(false);
				graduateBtn.setVisible(false);

				JButton gradauateM = new JButton("���������Ȳ");
				gradauateM.setBounds(10, 63, 152, 18);
				menuPanel2.add(gradauateM);
				gradauateM.setBorderPainted(false);
				gradauateM.setFocusPainted(false);
				gradauateM.setContentAreaFilled(false);
				gradauateM.setHorizontalAlignment(SwingConstants.LEFT);
				gradauateM.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
						S_Graduation frame;
						try {
							frame = new S_Graduation(id);
							frame.setVisible(true);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});

				JButton ifGraduate = new JButton("����������ȸ");
				ifGraduate.setBounds(10, 94, 152, 18);
				menuPanel2.add(ifGraduate);
				ifGraduate.setBorderPainted(false);
				ifGraduate.setFocusPainted(false);
				ifGraduate.setContentAreaFilled(false);
				ifGraduate.setHorizontalAlignment(SwingConstants.LEFT);
				ifGraduate.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
						S_Graduation_Check shome;
						try {
							shome = new S_Graduation_Check(id);
							shome.setVisible(true);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});

				// �޴� ���� ȭ�� �̵�
				JButton homeBtn = new JButton("�ڷΰ���");
				menuPanel2.add(homeBtn);
				homeBtn.setBounds(45, 500, 105, 27);
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
			}
		});

		JPanel User = new JPanel();
		User.setBackground(Color.WHITE);
		User.setForeground(Color.WHITE);
		User.setBounds(218, 73, 378, 145);
		User.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, Color.black));
		Main.add(User);
		User.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, Color.black));
		panel.setBounds(12, 10, 141, 125);
		User.add(panel);

		JLabel UserPhoto = new JLabel("UserPhoto");
		panel.add(UserPhoto);

		JLabel StuNum = new JLabel("�й� : ");
		StuNum.setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		StuNum.setBounds(165, 38, 125, 15);
		User.add(StuNum);

		JLabel Name = new JLabel("�̸� : ");
		Name.setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		Name.setBounds(165, 63, 125, 15);
		User.add(Name);

		JLabel College = new JLabel("�Ҽ� �ܴ� : ");
		College.setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		College.setBounds(165, 88, 200, 15);
		User.add(College);

		JLabel Department = new JLabel("�Ҽ� �а� : ");
		Department.setFont(new Font("���Ļ�浸��", Font.PLAIN, 12));
		Department.setBounds(165, 113, 200, 15);
		User.add(Department);

		JLabel Home_Profile = new JLabel("������");
		Home_Profile.setFont(new Font("���Ļ�浸��", Font.BOLD, 23));
		Home_Profile.setBounds(218, 33, 142, 27);
		Main.add(Home_Profile);

		// ���� ���� Ȯ��
		String colNames[] = { "�����", "����", "���� �ð�", "��米��" };
		String rows[][] = {};
		DefaultTableModel model = new DefaultTableModel(rows, colNames);

		Home_Subject = new JTable(model);
		Home_Subject.setBackground(Color.WHITE);
		JScrollPane scrollPane_sub = new JScrollPane(Home_Subject);
		scrollPane_sub.setBounds(221, 273, 924, 222);
		Main.add(scrollPane_sub);

		JLabel StudentHome = new JLabel("���� ��ȸ");
		StudentHome.setFont(new Font("���Ļ�浸��", Font.BOLD, 23));
		StudentHome.setBounds(218, 233, 189, 27);
		Main.add(StudentHome);

		JLabel Home_Graduation = new JLabel("���� �ܿ� ����");
		Home_Graduation.setFont(new Font("���Ļ�浸��", Font.BOLD, 23));
		Home_Graduation.setBounds(632, 36, 208, 27);
		Main.add(Home_Graduation);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(632, 73, 513, 145);
		Main.add(scrollPane);

		// ������ ���� ����
		StuNum.setText("�й�: "+s.getstNum());
		Name.setText("�̸�: "+s.getstName());
		College.setText("�ҼӴܴ�: "+s.getCollege());
		Department.setText("�Ҽ��а�: "+s.getfMajor());

		// �̼� ���к� ���� ��� ��Ȳ ����
		table = new JTable();
		table.setFont(new Font("����", Font.PLAIN, 12));
		table.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		table.setModel(
				new DefaultTableModel(
						new Object[][] { { "������", "130", null, null }, { "���� �ʼ�", "10", null, null },
								{ "���� ����", "27", null, null }, { "���� �ʼ�", "14", null, null },
								{ "���� ����", "-", null, null }, { "���� ��", "69", null, null }, },
						new String[] { "", "�̼� ���� ����", "��� ����", "�ܿ� ����" }));
		scrollPane.setViewportView(table);

		// ù��° ���̺� ������ �����ϱ�
		// �ʿ��� �����͵�
		// �� �̼� ����
		int TotalCredit = mg.getTotalCredit(id, "������");
		// ���� �ʼ�
		int credit1 = mg.getTotalCredit(id, "�����ʼ�");
		// ���� ����
		int credit2 = mg.getTotalCredit(id, "���缱��");
		// ���� �ʼ�
		int credit3 = mg.getTotalCredit(id, "�����ʼ�");
		// ���� ����
		int credit4 = mg.getTotalCredit(id, "��������");
		// ���� ��(���� �ʼ� + ���� ����)
		int MajorCredit = credit3 + credit4;

		// ���̺� �ֱ�
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
		int MajorRemained = 69 - MajorCredit;

		table.setValueAt(Totalremained, 0, 3);
		table.setValueAt(remained1, 1, 3);
		table.setValueAt(remained2, 2, 3);
		table.setValueAt(remained3, 3, 3);
		table.setValueAt("-", 4, 3);
		table.setValueAt(MajorRemained, 5, 3);

		// ���б� ���� ���� ����
		ResultSet rs = null;
		rs = mg.getStClasses(id, "2021-1");
		if (rs.getRow() > -1) {
			while (rs.next()) {
				// �м���ȣ�� �ҷ��ͼ�
				String leNum = rs.getString(2);
				// �ش� �м���ȣ�� ���� ���� ��ü ��� ����
				Lecture lec = mg.getLe(leNum);
				// GUI�� ����
				String[] row = { lec.getleName(), Integer.toString(lec.getCredit()), lec.getTime(), lec.getprName()};

				model.addRow(row);
			}
		}

	}
}