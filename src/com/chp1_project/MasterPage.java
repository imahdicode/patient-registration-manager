package com.chp1_project;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;

public class MasterPage extends JFrame {

	JMenuBar bar;
	JMenu homeMenu, doctorMenu, patientMenu, accountsMenu;
	JMenuItem aboutUs, services, location, photoUpload, exit;
	JMenuItem doctorInfo, registerDoctor, paymentPatient;
	JMenuItem patientDetails, billing, registration;
	JMenuItem gst, ngoAccounts, paymentDetails;

	JToolBar toolbar;
	JButton regBtn, photoUploadBtn, exitBtn;

	public MasterPage() {

		setLayout(new BorderLayout());
		bar = new JMenuBar();

		toolbar = new JToolBar();

		homeMenu = new JMenu("Home");
		doctorMenu = new JMenu("Doctor");
		patientMenu = new JMenu("Patient");
		accountsMenu = new JMenu("Accounts");

		aboutUs = new JMenuItem("About Us");
		services = new JMenuItem("Services");
		location = new JMenuItem("Location");
		photoUpload = new JMenuItem("Photo Upload");
		exit = new JMenuItem("Exit");
		doctorInfo = new JMenuItem("Doctor Info");
		registerDoctor = new JMenuItem("Doctor Registration");
		paymentPatient = new JMenuItem("Payment Patient");
		patientDetails = new JMenuItem("Patient Details");
		billing = new JMenuItem("Billing");
		registration = new JMenuItem("Registration");
		gst = new JMenuItem("GST");
		ngoAccounts = new JMenuItem("NGO Accounts");
		paymentDetails = new JMenuItem("Payment Details");

		regBtn = new JButton("Registration");
		photoUploadBtn = new JButton("Photo Upload");
		exitBtn = new JButton("Exit");

		homeMenu.add(aboutUs);
		homeMenu.add(services);
		homeMenu.add(location);
		homeMenu.add(photoUpload);
		homeMenu.addSeparator();
		homeMenu.add(exit);

		doctorMenu.add(doctorInfo);
		doctorMenu.add(registerDoctor);
		doctorMenu.add(paymentPatient);

		patientMenu.add(patientDetails);
		patientMenu.add(billing);
		patientMenu.add(registration);

		accountsMenu.add(gst);
		accountsMenu.add(ngoAccounts);
		accountsMenu.add(paymentDetails);

		bar.add(homeMenu);
		bar.add(doctorMenu);
		bar.add(patientMenu);
		bar.add(accountsMenu);

		toolbar.add(regBtn);
		toolbar.add(photoUploadBtn);
		toolbar.add(exitBtn);

		add(toolbar, BorderLayout.NORTH);
		toolbar.setFloatable(false);

		setJMenuBar(bar);
		setSize(800, 400);
		setTitle("Master Page");
		setVisible(true);

		// main menu
		photoUpload.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new PhotoUploadPage();
				dispose();
			}
		});

		registration.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new Registration();
			}
		});

		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new ExitForm();

			}
		});

		// toolbar

		regBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new Registration();

			}
		});

		photoUploadBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new PhotoUploadPage();

			}
		});

		exitBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}
		});

	}

	public static void main(String[] args) {
		new MasterPage();

	}
}
