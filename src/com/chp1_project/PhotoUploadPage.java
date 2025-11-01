package com.chp1_project;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PhotoUploadPage extends JFrame {
	JButton uploadBtn;
	JLabel fileLabel;

	public PhotoUploadPage() {
		setLayout(new FlowLayout());

		uploadBtn = new JButton("Upload Photo");
		fileLabel = new JLabel("No File Selected");

		add(uploadBtn);
		add(fileLabel);

		setTitle("Photo Upload");
		setSize(400, 150);
		setVisible(true);

		uploadBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setDialogTitle("No File Selected");
				int result = chooser.showOpenDialog(PhotoUploadPage.this);
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = chooser.getSelectedFile();
					fileLabel.setText("Selected: " + selectedFile.getName());

				} else {
					fileLabel.setText("No File Selected");
				}

			}
		});

	}

	public static void main(String[] args) {
		new PhotoUploadPage();
	}
}
