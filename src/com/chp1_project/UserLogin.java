package com.chp1_project;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class UserLogin extends JFrame {
	JLabel label1, label2;
	JTextField field1, field2;

	JButton submit, reset, admin;

	public UserLogin() {

		setLayout(new FlowLayout());

		label1 = new JLabel("Username: ");
		label2 = new JLabel("Password: ");

		field1 = new JTextField(20);
		field2 = new JTextField(20);

		submit = new JButton("Submit");
		reset = new JButton("Reset");
		admin = new JButton("Admin Login");

		add(label1);
		add(field1);

		add(label2);
		add(field2);

		add(submit);
		add(reset);
		add(admin);

		submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					PreparedStatement pst = GetConnection.getConnection()
							.prepareStatement("select * from logDetails where username = ? and password = ?");

					String userName = field1.getText();
					String password = field2.getText();

					pst.setString(1, userName);
					pst.setString(2, password);

					ResultSet rs = pst.executeQuery();

					if (rs.next()) {
						JOptionPane.showMessageDialog(UserLogin.this, "Login Successful");
						System.out.println("Login Successfull");
						field1.setText("");
						field2.setText("");
						new MasterPage();
					} else {
						System.out.println("Error: Invalid username or password");
						field1.setText("");
						field2.setText("");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				field1.setText("");
				field2.setText("");

			}
		});
		admin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new AdminLogin();

			}
		});
		setTitle("User Login Page");
		setSize(800, 400);
		setVisible(true);

	}

	public static void main(String[] args) {
		new UserLogin();
	}

}