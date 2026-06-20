package com.student.sms.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// StudentFrame same package-ல் இருந்தாலும் இந்த import போட்டுக்கலாம்
import com.student.sms.gui.StudentFrame;

public class LoginFrame extends JFrame implements ActionListener {

    JLabel titleLabel, userLabel, passLabel;
    JTextField userField;
    JPasswordField passField;
    JButton loginButton;

    public LoginFrame() {

        setTitle("Student Management System - Login");
        setSize(400, 300);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        titleLabel = new JLabel("STUDENT LOGIN");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBounds(100, 20, 250, 30);

        userLabel = new JLabel("Username");
        userLabel.setBounds(50, 80, 100, 25);

        userField = new JTextField();
        userField.setBounds(150, 80, 180, 25);

        passLabel = new JLabel("Password");
        passLabel.setBounds(50, 120, 100, 25);

        passField = new JPasswordField();
        passField.setBounds(150, 120, 180, 25);

        loginButton = new JButton("LOGIN");
        loginButton.setBounds(140, 180, 100, 30);
        loginButton.addActionListener(this);

        add(titleLabel);
        add(userLabel);
        add(userField);
        add(passLabel);
        add(passField);
        add(loginButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String username = userField.getText();
        String password = String.valueOf(passField.getPassword());

        if (username.equals("admin") && password.equals("admin123")) {

            JOptionPane.showMessageDialog(this, "Login Successful");

            new StudentFrame();

            dispose();

        } else {

            JOptionPane.showMessageDialog(this, "Invalid Username or Password");

        }
    }
}