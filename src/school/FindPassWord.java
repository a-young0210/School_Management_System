package school;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Canvas;
import java.awt.Panel;

public class FindPassWord extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FindPassWord frame = new FindPassWord();
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
	public FindPassWord() {
		// �⺻ �г� ����
		setTitle("�л���� ���α׷�");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.white);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(120, 62, 950, 425);
		contentPane.add(panel);
		panel.setLayout(null);
		
		//*�ȳ� ��
		//�й�/����
		Font font  = new Font("����",Font.BOLD,30);
		JLabel idLabel = new JLabel("�й�/����");
		idLabel.setBounds(271, 124, 145, 65);
		idLabel.setFont(font);
		panel.add(idLabel);
		
		//��й�ȣ
		JLabel passWordLabel = new JLabel("��й�ȣ");
		passWordLabel.setBounds(271, 202, 153, 64);
		passWordLabel.setFont(font);
		panel.add(passWordLabel);
		
		//�й�
		JLabel showId = new JLabel("2019111111");
		showId.setBounds(441, 135, 276, 42);
		showId.setFont(font);
		panel.add(showId);
		
		//��й�ȣ �� (���Ŀ� ������ �޾ƿ��� ��)
		JLabel showPassWord = new JLabel("lovegogo22");
		showPassWord.setBounds(438, 213, 173, 42);
		panel.add(showPassWord);
		showPassWord.setFont(font);
		
		//������ �г� (�ϴ�)
		Panel panelBottom = new Panel();
		panelBottom.setBounds(0, 326, 950, 99);
		panelBottom.setBackground(Color.GRAY);
		panel.add(panelBottom);
		panelBottom.setLayout(null);
		
		//������ �г�(���)
		Panel panelTop = new Panel();
		panelTop.setLayout(null);
		panelTop.setBackground(Color.GRAY);
		panelTop.setBounds(0, 0, 950, 72);
		panel.add(panelTop);
		
		//�ȳ� �޼��� ��
		JLabel textLabel = new JLabel("��й�ȣ Ȯ��");
		textLabel.setBounds(25, 13, 950, 52);
		textLabel.setFont(font);
		panelTop.add(textLabel);

		// Ȯ�� ��ư
		JButton okBtn = new JButton("\uD655\uC778");
		okBtn.setBounds(411, 13, 102, 55);
		panelBottom.add(okBtn);
		okBtn.addActionListener(new ActionListener() { //Ȯ�� ��ư ���� �Լ�
			public void actionPerformed(ActionEvent e) {
			}
		});
		
	}
}
