package school;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;

// �α��� ȭ��
public class M_Login extends JFrame {

	private JPanel contentPane;
	private JTextField IdField;
	private JTextField passWordField;

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// ����̹� �ε�
		Class.forName("org.mariadb.jdbc.Driver");

		// sql �����ϱ� ���� ���̵�� ��й�ȣ �Է�
		String url = "jdbc:mariadb://localhost:3306/school";
		Connection con = DriverManager.getConnection(url, "root", "1234");
		P_Management pmg = new P_Management(url, con); // ����DB ����
		S_Management smg = new S_Management(url, con); // �л�DB ����

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					M_Login frame = new M_Login(pmg, smg); // ���� or �л� �α��� �õ�
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// �α��� �õ�(���� or �л�)
	public M_Login(P_Management pmg, S_Management smg) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel TitleLabel = new JLabel("�л���� ���α׷�");
		TitleLabel.setBounds(52, 13, 331, 52);
		TitleLabel.setHorizontalAlignment(JLabel.CENTER);
		TitleLabel.setFont(new Font("����", Font.BOLD, 25));
		contentPane.add(TitleLabel);

		// ���̵� �Է¶�(�й�/����)
		IdField = new JTextField();
		IdField.setBounds(82, 68, 281, 36);
		contentPane.add(IdField);
		IdField.setColumns(10);
		IdField.setText("�й�/����");
		IdField.setFocusable(false); // Ŀ�� �۵� x
		IdField.setForeground(Color.GRAY); // ���� ȸ��
		IdField.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				IdField.setFocusable(true); // Ŀ�� �۵� o
				IdField.setText(null); // �ؽ�Ʈ �ʵ� �ʱ�ȭ
				IdField.setForeground(Color.BLACK); // ���� ����
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});

		// ��й�ȣ �Է¶�
		passWordField = new JTextField();
		passWordField.setColumns(10);
		passWordField.setBounds(82, 117, 281, 36);
		contentPane.add(passWordField);
		passWordField.setText("��й�ȣ");
		passWordField.setFocusable(false);
		passWordField.setForeground(Color.GRAY);
		passWordField.addMouseListener(new MouseListener() {

			// ���콺�� Ŭ������ ��
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				passWordField.setFocusable(true); // Ŀ�� �۵� o
				passWordField.setText(null); // �ؽ�Ʈ �ʵ� �ʱ�ȭ
				passWordField.setForeground(Color.BLACK); // ���� ����
			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});

		// �α��� ��ư
		JButton loginBtn = new JButton("�α���");
		loginBtn.setBounds(82, 166, 281, 27);
		loginBtn.setHorizontalAlignment(JLabel.CENTER);
		contentPane.add(loginBtn);
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = IdField.getText();
				String pw = passWordField.getText();
				
				//�й�/������ ���� �ʾ��� �� �˾�â ����
				if(id.isEmpty()) { 
					JOptionPane.showMessageDialog(null,"�й�/������ �Է����ּ���.");
				}
				//��й�ȣ�� ���� �ʾ��� �� �˾�â ����
				else if(pw.isEmpty()) {
					JOptionPane.showMessageDialog(null, "��й�ȣ�� �Է����ּ���.");
				} 
				else {
					try {
						if (pmg.loginPr(id, pw)) { // ������ �Է��ϰ� �α����� �õ����� ��

							String prName = pmg.checkNamePr(id); // �α����� ���� �̸� �޾ƿ���
							P_Home pHome = new P_Home(id); // Ȩ ȭ�� ����(����)
							pHome.setVisible(true);
							dispose();

						} else if (smg.loginSt(id, pw)) { // �й��� �Է��ϰ� �α����� �õ����� ��

							String stName = smg.getstName(id); // �α����� �л� �̸� �޾ƿ���
							S_Home sHome = new S_Home(id); // Ȩ ȭ�� ����(�л�)
							sHome.setVisible(true);
							dispose();
						}
						JOptionPane.showMessageDialog(null, "���̵� �������� �ʰų� ��й�ȣ�� �߸��Ǿ����ϴ�. �ٽ� Ȯ�����ּ���.");
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				

			}
		});

		// ���̵� ã�� ��ư
		JButton findIdBtn = new JButton("���̵� ã��");
		findIdBtn.setBounds(90, 206, 107, 27);
		contentPane.add(findIdBtn);
		findIdBtn.setBorderPainted(false);
		findIdBtn.setFocusPainted(false);
		findIdBtn.setContentAreaFilled(false);
		findIdBtn.setHorizontalAlignment(SwingConstants.LEFT);
		findIdBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				M_FindID findID = new M_FindID(pmg, smg);
				findID.setVisible(true); // ���̵� ã�� â ����
			}
		});

		// ��й�ȣ ã�� ��ư
		JButton findPassWordBtn = new JButton("��й�ȣ ã��");
		findPassWordBtn.setBounds(173, 206, 121, 27);
		contentPane.add(findPassWordBtn);
		findPassWordBtn.setBorderPainted(false);
		findPassWordBtn.setFocusPainted(false);
		findPassWordBtn.setContentAreaFilled(false);
		findPassWordBtn.setHorizontalAlignment(SwingConstants.LEFT);
		findPassWordBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				M_FindPassWord findPassword = new M_FindPassWord(pmg, smg);
				findPassword.setVisible(true); // ��й�ȣ ã�� â ����
			}
		});

		// ȸ������ ��ư
		JButton RegisterBtn = new JButton("ȸ������");
		RegisterBtn.setBounds(270, 206, 88, 27);
		contentPane.add(RegisterBtn);
		RegisterBtn.setBorderPainted(false);
		RegisterBtn.setFocusPainted(false);
		RegisterBtn.setContentAreaFilled(false);
		RegisterBtn.setHorizontalAlignment(SwingConstants.LEFT);
		RegisterBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				M_Register register = null;
				try {
					register = new M_Register(pmg, smg);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				register.setVisible(true);
			}
		});
	}
}
