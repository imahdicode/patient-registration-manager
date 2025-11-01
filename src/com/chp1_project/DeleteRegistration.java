package com.chp1_project;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class DeleteRegistration extends JFrame {

	JLabel nameLabel;
	JTextField nameField;
	JButton deleteBtn;

	public DeleteRegistration() {
		setLayout(new FlowLayout());

		nameLabel = new JLabel("Enter name to delete: ");
		nameField = new JTextField(20);
		deleteBtn = new JButton("Delete");

		add(nameLabel);
		add(nameField);
		add(deleteBtn);

		setTitle("Delete Registration");
		setSize(400, 150);
		setVisible(true);

		deleteBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					PreparedStatement pst = GetConnection.getConnection()
							.prepareStatement("DELETE FROM registration WHERE name = ?");

					pst.setString(1, nameField.getText());
					int rowsAffected = pst.executeUpdate();

					if (rowsAffected > 0) {
						JOptionPane.showMessageDialog(DeleteRegistration.this, "Data deleted successfully!");
					} else {
						JOptionPane.showMessageDialog(DeleteRegistration.this, "No matching record found!");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(DeleteRegistration.this, "Error while deleting data!");
				}
			}
		});
	}

	public static void main(String[] args) {
		new DeleteRegistration();
	}
}
