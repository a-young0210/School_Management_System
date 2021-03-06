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

// 己利包府
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

// 己利包府
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
		setTitle("切荤包府 橇肺弊伐");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.white);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Class.forName("org.mariadb.jdbc.Driver");
		// sql 立加窍扁 困茄 酒捞叼客 厚剐锅龋 涝仿

		con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/school", "root", "1234");

		// 背荐 窃荐俊 楷搬
		P_Management pmg = new P_Management(url, con);
		Functions func = new Functions(url, con);

		// 秦寸 背荐狼 捞抚 阂矾坷扁
		String prName = pmg.checkNamePr(id);

		JPanel menuPanel = new JPanel();
		menuPanel.setBounds(0, 0, 205, 563);
		contentPane.add(menuPanel);
		menuPanel.setLayout(null);

		JButton GradeAdBtn = new JButton("己利 矫胶袍");
		GradeAdBtn.setBounds(0, 0, 205, 50);
		menuPanel.add(GradeAdBtn);

		JButton gradeMenuLabel = new JButton("己利包府");
		gradeMenuLabel.setBounds(10, 63, 152, 18);
		gradeMenuLabel.setBorderPainted(false);
		gradeMenuLabel.setFocusPainted(false);
		gradeMenuLabel.setContentAreaFilled(false);
		gradeMenuLabel.setHorizontalAlignment(SwingConstants.LEFT);
		gradeMenuLabel.setEnabled(false);
		menuPanel.add(gradeMenuLabel);

		JLabel titleLabel = new JLabel("己利包府");
		titleLabel.setBounds(219, 13, 109, 18);
		contentPane.add(titleLabel);

		// 权 拳搁
		JButton homeBtn = new JButton("权 拳搁");
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

		// 切斥档 霓焊冠胶
		String[] listYear = { "急琶", "2019", "2020", "2021" };
		JComboBox yearBox = new JComboBox(listYear);
		yearBox.setModel(new DefaultComboBoxModel(new String[] { "\uC120\uD0DD", "2019", "2020", "2021" }));
		yearBox.setBounds(70, 3, 111, 28);
		selectPanel.add(yearBox);

		// 切扁 霓焊冠胶
		String[] listSemester = { "急琶", "1", "2" };
		JComboBox semesterBox = new JComboBox(listSemester);
		semesterBox.setBounds(253, 3, 111, 28);
		selectPanel.add(semesterBox);

		// 切斥档 扼骇
		JLabel yearLabel = new JLabel("切斥档");
		yearLabel.setBounds(14, 6, 62, 18);
		selectPanel.add(yearLabel);

		// 切扁 扼骇
		JLabel semesterLabel = new JLabel("切扁");
		semesterLabel.setBounds(206, 6, 45, 18);
		selectPanel.add(semesterLabel);

		// 苞格疙 扼骇
		JLabel nameLabel = new JLabel("苞格疙");
		nameLabel.setBounds(617, 6, 45, 18);
		selectPanel.add(nameLabel);

		// 钎 积己 (己利炼雀)
		String colNames[] = { "苞格疙", "捞抚", "切锅", "吝埃绊荤", "扁富绊荤", "苞力", "免籍", "醚痢", "切痢" };// 青 单捞磐
		Object data[][] = {};// 凯 单捞磐
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

		// 荐碍牢盔, 乞闭 panel
		JPanel panel = new JPanel();
		panel.setBounds(682, 407, 240, 35);
		mainPanel.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		// 荐碍牢盔 鞘靛 - 单捞磐 涝仿罐酒具窃
		totalNumField = new JTextField();
		totalNumField.setText("荐碍牢盔:");
		panel.add(totalNumField, BorderLayout.WEST);
		totalNumField.setColumns(10);

		// 乞闭 扼骇 - 单捞磐 涝仿 罐酒具窃
		avgField = new JTextField();
		avgField.setText("乞闭:");
		panel.add(avgField, BorderLayout.CENTER);
		avgField.setColumns(10);

		// 苞格 炼雀
		// 切扁,切斥档 八祸 滚瓢
		JLabel subNameLabel = new JLabel();
		JButton findBtn = new JButton("苞格炼雀");
		findBtn.setBounds(385, 3, 95, 27);
		selectPanel.add(findBtn);

		
		//苞格 炼雀滚瓢
		findBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String YY = (String) yearBox.getSelectedItem();
				String SS = (String) semesterBox.getSelectedItem();
				semester = YY + "-" + SS;

				// 炼雀 滚瓢
				JButton showBtn = new JButton("炼雀");
				selectPanel.add(showBtn);
				showBtn.setBounds(838, 3, 93, 27);

				try {
					List<String> leName = pmg.getLeInSem(prName, semester);

					leName.add(0, "苞格急琶");
					// 苞格疙 霓焊冠胶
					JComboBox nameBox = new JComboBox(leName.toArray());
					selectPanel.add(nameBox);
					nameBox.setBounds(676, 3, 150, 28);

					// 炼雀 滚瓢 - 苞格疙 捞侩窍扁
					showBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							// 急琶等 苞格 罐酒坷扁
							String selectName = (String) nameBox.getSelectedItem();
							System.out.println(selectName);
							pmg.setLeName(selectName);

							// 苞格疙 扼骇 - 单捞磐 涝仿罐酒具 窃
							subNameLabel.setText(selectName);
							mainPanel.add(subNameLabel);
							subNameLabel.setBounds(414, 13, 89, 18);

							// 切斥档, 切扁 罐酒坷扁
							String YY = (String) yearBox.getSelectedItem();
							String SS = (String) semesterBox.getSelectedItem();
							String semester = YY + "-" + SS;

							// 荤侩磊啊 急琶茄 荐诀 按眉甫 罐酒坷扁
							try {
								ResultSet rs = pmg.searchLecture("背苞格", "苞格疙", selectName, semester);

								while (rs.next()) {

									// 阿阿 皋家靛 蔼阑 罐酒客辑 且寸窃.
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
									// 荐诀 按眉 积己
									lec = new Lecture(leName1, leNum, semester1, limGrade, prName, type, time, credit,
											attMem, limMem, preLecture, division, "0");
								}

							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

							// 炼雀 努腐矫 抛捞喉 郴侩 檬扁拳
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

									} catch (Exception e1) { // getPerson() 劳剂记
										e1.printStackTrace();
									}
								}

							} catch (Exception e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}

							// 荐碍牢盔 眉农
							try {
								int cnt;
								cnt = pmg.getCount(lec.getleNum());
								String toInt = Integer.toString(cnt);
								totalNumField.setText("荐碍牢盔: " + toInt);

								// 乞闭 备窍扁
								double avg;
								try {
									avg = pmg.stLecture(semester, lec.getleNum());
									avgField.setText("乞闭: " + avg / cnt);
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

		// 己利涝仿 滚瓢
		JButton putGradeBtn = new JButton("己利涝仿");
		putGradeBtn.setBounds(829, 6, 93, 27);
		mainPanel.add(putGradeBtn);
		
		JLabel ExplainLabel = new JLabel("");
		ExplainLabel.setBounds(430, 380, 50, 15);
		mainPanel.add(ExplainLabel);
		
		
		// 关临 窃荐
		 Font font = ExplainLabel.getFont();
		 ExplainLabel.setForeground(Color.GRAY);
		 Map attributes = font.getAttributes();
		 attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		 ExplainLabel.setFont(font.deriveFont(attributes));
		 

		// 涝仿瓢捞 喘啡阑 锭 -> 己利阑 涝仿窍霸 茄促
		putGradeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExplainLabel.setText("己利 函版 角青吝涝聪促.");
				// 己利涝仿 滚瓢 见扁扁
				if (e.getSource() == putGradeBtn) {
					putGradeBtn.hide();
				}

				// 涝仿 滚瓢 积己
				JButton inputGradeBtn = new JButton("犬牢");
				inputGradeBtn.setBounds(829, 6, 93, 27);
				mainPanel.add(inputGradeBtn);

				// 涝仿 滚瓢阑 喘范阑 锭 -> 己利甸捞 甸绢啊霸 父甸绊 促矫 己利涝仿 滚瓢 哆霸 窃
				inputGradeBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ExplainLabel.setText("己利 函版 肯丰登菌嚼聪促.");
						// 泅犁 钎俊 乐绰 苞格 捞抚 罐酒坷扁
						String leName = model.getValueAt(0, 0).toString();

						// 荐沥茄(upadate等) 钎甸阑 罐酒柯 饶 DB俊 诀单捞飘 窍扁
						int rCnt = model.getRowCount();
						for (int j = 0; j < rCnt; j++) {
							try {
								int midScore = Integer.parseInt(model.getValueAt(j, 3).toString()); // 吝埃绊荤
								int finalScore = Integer.parseInt(model.getValueAt(j, 4).toString());// 扁富绊荤
								int assiScore = Integer.parseInt(model.getValueAt(j, 5).toString());// 苞力
								int attendScore = Integer.parseInt(model.getValueAt(j, 6).toString());// 免籍
								int totalScore = Integer.parseInt(model.getValueAt(j, 7).toString()); // 醚痢
								String grade = model.getValueAt(j, 8).toString();// 切痢

								// 荐沥等 郴侩 按眉俊 历厘
								LectureScore S = new LectureScore(midScore, finalScore, assiScore, attendScore,
										totalScore, grade);

								// 单海 郴侩 函版窍扁
								pmg.modifyScore(model.getValueAt(j, 2).toString(), lec.getleNum(), S);
								// 乞闭 备窍扁
								double avg;
								
								try {
									int cnt;
									cnt = pmg.getCount(lec.getleNum());
									avg = pmg.stLecture(semester, lec.getleNum());
									avgField.setText("乞闭: " + avg / cnt);
								} catch (Exception e1) {
									e1.printStackTrace();
								}
							} catch (Exception e1) {
								e1.printStackTrace();
							}

						}
						putGradeBtn.show(); // 己利 涝仿 滚瓢 唱鸥郴扁
						inputGradeBtn.hide();
					}
				});
			}

		});

	}
}
