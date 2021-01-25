package com.edusys.form;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.edusys.dao.impl.NhanVienDao;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class Login extends JFrame {
	private JPanel contentPane;
	private JTextField textUsername;
	private JPasswordField textPassword;
	static Login frame = new Login();
	String change = "Change password";
	JButton btnLogin = new JButton("Đăng nhập");
	StringBuilder error = new StringBuilder();
	boolean check = false;
	public static String vaiTro;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
					frame.setTitle("Login");
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	Action loginAction = new AbstractAction() {
		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
	};
	private JButton btnCancel;

	public Login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\udpm\\Image\\fpt.png"));
		setTitle("Đăng nhập");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 497, 274);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 100, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setFocusable(true);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textUsername = new JTextField(" Username");
		textUsername.setForeground(Color.black);
		textUsername.setBackground(Color.white);
		textUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textUsername.setColumns(10);
		textUsername.setBorder(new LineBorder(Color.WHITE));
		textUsername.setBounds(198, 89, 257, 33);

		contentPane.add(textUsername);

		textPassword = new JPasswordField();
		textPassword.setForeground(Color.black);
		textPassword.setBackground(Color.white);
		textPassword.setText(" Password");
		textPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textPassword.setColumns(10);
		textPassword.setBorder(new LineBorder(Color.WHITE));
		textPassword.setBounds(198, 133, 257, 33);
		contentPane.add(textPassword);

		JLabel lblLogin = new JLabel("ĐĂNG NHẬP");
		lblLogin.setForeground(new Color(255, 255, 255));
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblLogin.setBounds(198, 38, 257, 40);
		contentPane.add(lblLogin);
		btnLogin.setIcon(null);

		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					login();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnLogin.setBorder(new LineBorder(new Color(255, 255, 255)));
		btnLogin.setBackground(Color.BLACK);
		btnLogin.setBounds(267, 191, 89, 23);
		contentPane.add(btnLogin);

		JLabel lblUser = new JLabel("New label");
		lblUser.setIcon(new ImageIcon("C:\\udpm\\Image\\Secure.png"));
		lblUser.setBounds(-41, 11, 257, 277);
		contentPane.add(lblUser);

		textUsername.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (textUsername.getText().equals(" Username")) {
					textUsername.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (textUsername.getText().equals("")) {
					textUsername.setText(" Username");
				}
			}
		});

		textPassword.addFocusListener(new FocusAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void focusGained(FocusEvent e) {
				if (textPassword.getText().equals(" Password")) {
					textPassword.setText("");
				}
			}

			@SuppressWarnings("deprecation")
			@Override
			public void focusLost(FocusEvent e) {
				if (textPassword.getText().equals("")) {
					textPassword.setText(" Password");
				}
			}
		});

		textUsername.addActionListener(loginAction);
		textPassword.addActionListener(loginAction);

		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnLogin.setBounds(264, 188, 95, 29);
				btnLogin.setBorder(new LineBorder(new Color(255, 255, 255), 2));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnLogin.setBounds(267, 191, 89, 23);
				btnLogin.setBorder(new LineBorder(new Color(255, 255, 255), 1));
			}
		});

		btnLogin.setContentAreaFilled(false);

		btnCancel = new JButton("Kết thúc");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login.this.dispose();
				
			}
		});
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnCancel.setBounds(363, 188, 95, 29);
				btnCancel.setBorder(new LineBorder(new Color(255, 255, 255), 2));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnCancel.setBounds(366, 191, 89, 23);
				btnCancel.setBorder(new LineBorder(new Color(255, 255, 255), 1));
			}
		});
		
		btnCancel.setForeground(new Color(255, 255, 255));
		btnCancel.setContentAreaFilled(false);
		btnCancel.setBorder(new LineBorder(new Color(255, 255, 255)));
		btnCancel.setBackground(Color.BLACK);
		btnCancel.setBounds(366, 191, 89, 23);
		contentPane.add(btnCancel);
		
	}
	
	public void login() throws SQLException {
		@SuppressWarnings("deprecation")
		ResultSet nhanVien = new NhanVienDao().getNhanVien(textUsername.getText(), textPassword.getText());
		if(nhanVien.next()) {
			QLDT qldt = new QLDT();
			qldt.setVisible(true);
			qldt.setLocationRelativeTo(null);
			qldt.getVaiTro(nhanVien.getInt(4), nhanVien.getString(1));
			Login.this.dispose();
		} else {
			JOptionPane.showMessageDialog(null, "Bạn nhập sai thông tin đăng nhập!");
		}
	}
}