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
import javax.swing.JButton;

// ��й�ȣ ã��
public class M_FindPassWord extends JFrame {

	private JPanel contentPane;
	private JTextField IdField;
	private JTextField emailField;
	private JTextField phoneNumField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {// ����̹� �ε�
		Class.forName("org.mariadb.jdbc.Driver");

		// sql �����ϱ� ���� ���̵�� ��й�ȣ �Է�
		String url = "jdbc:mariadb://localhost:3306/school";
		Connection con = DriverManager.getConnection(url, "root", "1234");
		P_Management pmg = new P_Management(url, con); // ����DB ����
		S_Management smg = new S_Management(url, con); // �л�DB ����
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					M_FindID frame = new M_FindID(pmg, smg);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public M_FindPassWord(P_Management pmg, S_Management smg) {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); // â �ݱ� �ɼ� �缳��
		setBounds(600, 200, 450, 334);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel TitleLabel = new JLabel("��й�ȣ ã��");
		TitleLabel.setBounds(52, 13, 331, 52);
		TitleLabel.setHorizontalAlignment(JLabel.CENTER);
		TitleLabel.setFont(new Font("����", Font.BOLD, 25));
		contentPane.add(TitleLabel);

		// ���̵� �Է¶�
		IdField = new JTextField();
		IdField.setBounds(82, 78, 281, 36);
		contentPane.add(IdField);
		IdField.setColumns(10);
		IdField.setText("�й�/����");
		IdField.setFocusable(false);
		IdField.setForeground(Color.GRAY);
		IdField.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				IdField.setFocusable(true);
				IdField.setText(null);
				IdField.setForeground(Color.BLACK);
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

		// �̸��� �Է¶�
		emailField = new JTextField();
		emailField.setColumns(10);
		emailField.setBounds(82, 127, 281, 36);
		contentPane.add(emailField);
		emailField.setText("�̸���");
		emailField.setFocusable(false);
		emailField.setForeground(Color.GRAY);
		emailField.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				emailField.setFocusable(true);
				emailField.setText(null);
				emailField.setForeground(Color.BLACK);
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

		// ��ȭ��ȣ �Է¶�
		phoneNumField = new JTextField();
		phoneNumField.setText("��ȭ��ȣ");
		phoneNumField.setForeground(Color.GRAY);
		phoneNumField.setFocusable(false);
		phoneNumField.setColumns(10);
		phoneNumField.setBounds(82, 176, 281, 36);
		contentPane.add(phoneNumField);
		phoneNumField.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				phoneNumField.setFocusable(true);
				phoneNumField.setText(null);
				phoneNumField.setForeground(Color.BLACK);
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

		// Ȯ��(ã��)��ư
		JButton okBtn = new JButton("Ȯ��");
		okBtn.setBounds(82, 229, 281, 27);
		okBtn.setHorizontalAlignment(JLabel.CENTER);
		contentPane.add(okBtn);
		// if - else ���߿� �߰� �ٶ�!
		okBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String id = IdField.getText();
					String email = emailField.getText();
					String phoneNum = phoneNumField.getText();
					
					if (pmg.findPw(id, email, phoneNum)!= null) { // ������ �Է��ϰ� �α����� �õ����� ��

						String prPw = pmg.findPw(id, email, phoneNum);
						JOptionPane.showMessageDialog(null, "��й�ȣ : " + prPw, "Ȯ��", JOptionPane.INFORMATION_MESSAGE);

					} else if (smg.findPw(id, email, phoneNum)!= null) { // �й��� �Է��ϰ� �α����� �õ����� ��

						String stPw = smg.findPw(id, email, phoneNum);
						JOptionPane.showMessageDialog(null, "��й�ȣ " + stPw, "Ȯ��", JOptionPane.INFORMATION_MESSAGE);
					} else 
						JOptionPane.showMessageDialog(null,"�ش� �й�/������ �������� �ʽ��ϴ�. �̸���/��ȭ��ȣ�� Ȯ�����ּ���.");
				} catch (Exception e1) {
				JOptionPane.showMessageDialog(null,"�ش� �й�/������ �������� �ʽ��ϴ�.");
			}
		}
		});
	}

}
