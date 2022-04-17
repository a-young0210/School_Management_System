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
import javax.swing.JComboBox;

// ȸ������ 
public class M_Register extends JFrame {

	private JPanel contentPane;
	private JTextField IDField;
	private JTextField passWordField;
	private JTextField yearField;
	private JTextField emailField;
	private JTextField phonNumField;
	private JTextField dayField;
	private JTextField passWordField2;
	static P_Management pm = null;
	static S_Management sm = null;
	

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
	
	public M_Register(P_Management pmg, S_Management smg) throws SQLException, ClassNotFoundException {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); // â �ݱ� �ɼ� �缳��
		setBounds(600, 200, 450, 558);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel TitleLabel = new JLabel("ȸ������");
		TitleLabel.setBounds(52, 13, 331, 52);
		TitleLabel.setHorizontalAlignment(JLabel.CENTER);
		TitleLabel.setFont(new Font("����", Font.BOLD, 25));
		contentPane.add(TitleLabel);

		// ���̵� �Է¶�
		IDField = new JTextField();
		IDField.setBounds(82, 80, 281, 36);
		contentPane.add(IDField);
		IDField.setColumns(10);

		// ��й�ȣ �Է¶�
		passWordField = new JTextField();
		passWordField.setColumns(10);
		passWordField.setBounds(82, 147, 281, 36);
		contentPane.add(passWordField);

		// ��й�ȣ ��Ȯ��
		passWordField2 = new JTextField();
		passWordField2.setColumns(10);
		passWordField2.setBounds(82, 206, 281, 36);
		contentPane.add(passWordField2);

		// ������� - ��
		yearField = new JTextField();
		yearField.setText("��(4����)");
		yearField.setFocusable(false);
		yearField.setForeground(Color.GRAY);
		yearField.setColumns(10);
		yearField.setBounds(82, 274, 88, 36);
		contentPane.add(yearField);
		yearField.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				yearField.setFocusable(true);
				yearField.setText(null);
				yearField.setForeground(Color.BLACK);
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

		// ������� - ��
		String month[] = { "��", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
		JComboBox monthCombo = new JComboBox(month);
		monthCombo.setBackground(Color.white);
		monthCombo.setBounds(184, 274, 88, 36);
		contentPane.add(monthCombo);

		// ���⼭ ���õ� month�� ��ȯ�Ѵ�.
		String selectedMonth = monthCombo.getSelectedItem().toString();

		// ������� - �� �Է¶�
		dayField = new JTextField();
		dayField.setText("��");
		dayField.setForeground(Color.GRAY);
		dayField.setFocusable(false);
		dayField.setColumns(10);
		dayField.setBounds(286, 274, 77, 36);
		contentPane.add(dayField);
		dayField.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				dayField.setFocusable(true);
				dayField.setText(null);
				dayField.setForeground(Color.BLACK);
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

		// �̸��� �Է¶�
		emailField = new JTextField();
		emailField.setColumns(10);
		emailField.setBounds(82, 341, 281, 36);
		contentPane.add(emailField);

		// ��ȭ��ȣ �Է¶�
		phonNumField = new JTextField();
		phonNumField.setColumns(10);
		phonNumField.setBounds(82, 407, 281, 36);
		contentPane.add(phonNumField);

		//////// �� /////////
		JLabel passWordLabel = new JLabel("��й�ȣ");
		passWordLabel.setBounds(82, 129, 62, 18);
		contentPane.add(passWordLabel);

		JLabel passWordLabel_2 = new JLabel("��й�ȣ ��Ȯ��");
		passWordLabel_2.setBounds(82, 187, 125, 18);
		contentPane.add(passWordLabel_2);

		JLabel IDLabel = new JLabel("�й�/����");
		IDLabel.setBounds(82, 62, 62, 18);
		contentPane.add(IDLabel);

		JLabel birthLabel = new JLabel("�������");
		birthLabel.setBounds(82, 256, 62, 18);
		contentPane.add(birthLabel);

		JLabel emailLabel = new JLabel("�̸���");
		emailLabel.setBounds(82, 323, 62, 18);
		contentPane.add(emailLabel);

		JLabel numLabel = new JLabel("��ȭ��ȣ");
		numLabel.setBounds(82, 390, 62, 18);
		contentPane.add(numLabel);

		
		// �����ϱ� ��ư
		JButton registBtn = new JButton("�����ϱ�");
		registBtn.setBounds(82, 460, 281, 38);
		registBtn.setHorizontalAlignment(JLabel.CENTER);
		contentPane.add(registBtn);
		//�����ϱ� ��ư�� ������ ��ü�� ����� ���̺� ������ �����ִ� ������
	
		// if - else ���߿� �߰� �ٶ�!
		registBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					boolean check = false;
					//�й�/������ ���� �ʾ��� �� �˾�â ����
					if(IDField.getText().isEmpty()) { 
						JOptionPane.showMessageDialog(null,"�й�/������ �Է����ּ���.");
					}
					//��й�ȣ�� ���� �ʾ��� �� �˾�â ����
					else if(passWordField.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "��й�ȣ�� �Է����ּ���.");
					}
					//��й�ȣ ��Ȯ���� ���� �ʾ��� �� �˾�â ����
					else if(passWordField2.getText().isEmpty()) { 
						JOptionPane.showMessageDialog(null,"��й�ȣ�� �� �� �� �Է����ּ���.");
					}
					//������� - �⵵�� ���� �ʾ��� �� �˾�â ����
					else if(yearField.getText().isEmpty()) { 
						JOptionPane.showMessageDialog(null,"�⵵�� �Է����ּ���.");
					}
					//������� - ���� ���� �ʾ��� �� �˾�â ����
					else if(monthCombo.getSelectedItem().toString() == "��") { 
						JOptionPane.showMessageDialog(null,"���� �������ּ���.");
					}
					//������� - ���� ���� �ʾ��� �� �˾�â ����
					else if(dayField.getText().isEmpty()) { 
						JOptionPane.showMessageDialog(null,"���� �Է����ּ���.");
					}
					//�̸����� ���� �ʾ��� �� �˾�â ����
					else if(emailField.getText().isEmpty()) { 
						JOptionPane.showMessageDialog(null,"�̸����� �Է����ּ���.");
					}
					//��ȭ��ȣ�� ���� �ʾ��� �� �˾�â ����
					else if(phonNumField.getText().isEmpty()) { 
						JOptionPane.showMessageDialog(null,"��ȭ��ȣ�� �Է����ּ���.");
					}
					//��� ������ ������ ���
					else {
						boolean pass = false;
						//��й�ȣ �ؽ�Ʈ�ڽ��� ��й�ȣ Ȯ�� �ؽ�Ʈ �ڽ��� ��ġ�ϴ��� Ȯ��
						if(passWordField.getText().equals(passWordField2.getText()) ) {
							pass = true;
						} else {
						}
						
						//pass�� false�̸� �˾�â ����
						if(pass == false) {
							JOptionPane.showMessageDialog(null,"��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
							passWordField2.setText("");
						}
						
						//�й�/������ �������� ���� �� �˾�â ����
						else if(smg.checkId(IDField.getText()) == false && pmg.checkId(IDField.getText()) == false) {
							JOptionPane.showMessageDialog(null,"�ش� �й�/������ �������� �ʽ��ϴ�. �й�/������ Ȯ�����ּ���.");
							IDField.setText("");
						}
						
						//��ȭ��ȣ�� �ߺ��� �� �˾�â ����
						else if(smg.checkPhoneNum(phonNumField.getText())==true || pmg.checkPhoneNum(phonNumField.getText())==true) { 
							JOptionPane.showMessageDialog(null,"�̹� ��ϵ� ��ȭ��ȣ �Դϴ�.");
							phonNumField.setText("");	
						} 
						
						else {
							try {
								if (IDField.getText().length() == 10) { // �й��� �Է��ϰ� ȸ�������� �õ����� ��
									smg.add(IDField.getText(), passWordField.getText(), yearField.getText() + monthCombo.getSelectedItem().toString() + dayField.getText(),
										emailField.getText(), phonNumField.getText());	//�л� ��� �޼ҵ�	
									JOptionPane.showMessageDialog(null,"���ԵǾ����ϴ�."); //��ü�� ���������� �߰� �Ǿ��� �� �ߴ� �˾�â
									dispose();

								} else if (IDField.getText().length() == 6) { // ������ �Է��ϰ� ȸ�������� �õ����� ��
									pmg.add(IDField.getText(), passWordField.getText(), yearField.getText() + monthCombo.getSelectedItem().toString() + dayField.getText(),
										emailField.getText(), phonNumField.getText());	//���� ��� �޼ҵ�
									JOptionPane.showMessageDialog(null,"���ԵǾ����ϴ�."); //��ü�� ���������� �߰� �Ǿ��� �� �ߴ� �˾�â
									dispose();
								}
							} catch (Exception e1) { //�Է��� ���������� ���� �ʾ��� ���
								JOptionPane.showMessageDialog(null,"���Կ� �����߽��ϴ�."); //��ü�� ���������� �߰����� �ʾ��� �� �ߴ� �˾�â
								//�Է� �� �ؽ�Ʈ �ʵ� �� ����
								IDField.setText("");
								passWordField.setText("");
								passWordField2.setText("");
								yearField.setText("");
								dayField.setText("");
								emailField.setText("");
								phonNumField.setText("");	
							}
						}
						
					}
			}
		});
		
		JButton cancelbutton = new JButton("���"); //"���"��ư �����
		//"���"��ư�� ������ ���� â�� ������.
		cancelbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
}
