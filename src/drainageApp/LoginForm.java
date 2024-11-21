package drainageApp;

import java.awt.EventQueue;
import javax.swing.*;
import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginForm {

	private JFrame frame;
	private JPasswordField txtPass;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForm window = new LoginForm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LoginForm() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 536, 498);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Login");
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel.setBounds(106, 154, 85, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password:");
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(106, 208, 85, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JTextArea txtUser = new JTextArea();
		txtUser.setBackground(Color.WHITE);
		txtUser.setForeground(Color.BLACK);
		txtUser.setBounds(201, 150, 120, 20);
		txtUser.setBorder(null);
		frame.getContentPane().add(txtUser);
		
		txtPass = new JPasswordField();
		txtPass.setForeground(Color.BLACK);
		txtPass.setBounds(201, 205, 120, 20);
		txtPass.setBorder(null);
		frame.getContentPane().add(txtPass);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String uname = txtUser.getText();
				String pass = String.valueOf(txtPass.getPassword());
				
				if(uname.equals("admin") && pass.equals("123")){
					JOptionPane.showMessageDialog(null,"Login was successful");
					frame.setVisible(false);
					Main newWindow = new Main();
					newWindow.setVisible(true);
				}
				else {
					JOptionPane.showMessageDialog(null,"Login was not successful");
				}
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLogin.setBounds(103, 313, 113, 37);
		frame.getContentPane().add(btnLogin);
		
		JLabel lblNewLabel_2 = new JLabel("Login Form");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_2.setBounds(172, 32, 215, 46);
		frame.getContentPane().add(lblNewLabel_2);
		
		JButton btnClose = new JButton("Close");
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);				
			}
		});
		btnClose.setBounds(247, 313, 113, 37);
		frame.getContentPane().add(btnClose);
	}
}

