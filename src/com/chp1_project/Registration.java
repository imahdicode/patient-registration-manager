package com.chp1_project;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Registration extends JFrame {

	JLabel name, contact, mobile, gender, comment, city;
	JTextField nameField, contactField, mobileField, commentField, cityField;
	JButton submit, delete, reset;

	JRadioButton maleRadio;
	JRadioButton femaleRadio;

	public Registration() {
		setLayout(new FlowLayout());

		name = new JLabel("Name: ");
		contact = new JLabel("Contact: ");
		mobile = new JLabel("Mobile: ");
		gender = new JLabel("Gender: ");
		comment = new JLabel("Comment: ");
		city = new JLabel("City: ");

		nameField = new JTextField(20);
		contactField = new JTextField(20);
		mobileField = new JTextField(20);
		commentField = new JTextField(50);
		cityField = new JTextField(20);

		submit = new JButton("Submit");
		delete = new JButton("Delete");
		reset = new JButton("Reset");

		maleRadio = new JRadioButton("Male");
		femaleRadio = new JRadioButton("Female");

		ButtonGroup genderGroup = new ButtonGroup();
		genderGroup.add(maleRadio);
		genderGroup.add(femaleRadio);

		add(name);
		add(nameField);
		add(contact);
		add(contactField);
		add(mobile);
		add(mobileField);
		add(gender);
		add(maleRadio);
		add(femaleRadio);
		add(comment);
		add(commentField);
		add(city);
		add(cityField);
		add(submit);
		add(delete);
		add(reset);

		setTitle("Registration");
		setSize(800, 400);
		setVisible(true);

		submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					PreparedStatement pst = GetConnection.getConnection().prepareStatement(
							"insert into registration (name, contact, mobile, gender, city, comment) VALUES(?,?,?,?,?,?)");

					pst.setString(1, nameField.getText());
					pst.setString(2, contactField.getText());
					pst.setString(3, mobileField.getText());

					String genderField = "";
					if (maleRadio.isSelected()) {
						genderField = "Male";
					} else if (femaleRadio.isSelected()) {
						genderField = "Female";
					}

					pst.setString(4, genderField);
					pst.setString(5, cityField.getText());
					pst.setString(6, commentField.getText());

					pst.executeUpdate();
					JOptionPane.showMessageDialog(Registration.this, "Data inserted successfully!");
					System.out.println("Data inserted successfully!");

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				nameField.setText("");
				contactField.setText("");
				mobileField.setText("");
				commentField.setText("");
				cityField.setText("");
				maleRadio.setSelected(false);
				femaleRadio.setSelected(false);

			}
		});

		delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new DeleteRegistration();

			}
		});

	}

	public static void main(String[] args) {
		new Registration();
	}
}
