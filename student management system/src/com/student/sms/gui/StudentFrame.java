package com.student.sms.gui;

import com.student.sms.dao.StudentDAO;
import com.student.sms.model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class StudentFrame extends JFrame implements ActionListener {

    JLabel lblTitle, lblId, lblName, lblDept, lblEmail, lblPhone;

    JTextField txtId, txtName, txtDept, txtEmail, txtPhone;

    JButton btnAdd, btnSearch, btnUpdate,
            btnDelete, btnClear, btnExit;

    JTable table;
    JScrollPane scrollPane;

    DefaultTableModel model;

    StudentDAO dao = new StudentDAO();

    public StudentFrame() {

        setTitle("Student Management System");
        setSize(850,600);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        lblTitle = new JLabel("STUDENT MANAGEMENT SYSTEM");
        lblTitle.setBounds(250,20,350,30);

        lblId = new JLabel("Student ID");
        lblId.setBounds(30,80,100,25);

        txtId = new JTextField();
        txtId.setBounds(140,80,150,25);

        lblName = new JLabel("Student Name");
        lblName.setBounds(30,120,100,25);

        txtName = new JTextField();
        txtName.setBounds(140,120,150,25);

        lblDept = new JLabel("Department");
        lblDept.setBounds(30,160,100,25);

        txtDept = new JTextField();
        txtDept.setBounds(140,160,150,25);

        lblEmail = new JLabel("Email");
        lblEmail.setBounds(30,200,100,25);

        txtEmail = new JTextField();
        txtEmail.setBounds(140,200,150,25);

        lblPhone = new JLabel("Phone");
        lblPhone.setBounds(30,240,100,25);

        txtPhone = new JTextField();
        txtPhone.setBounds(140,240,150,25);

        btnAdd = new JButton("Add");
        btnAdd.setBounds(350,80,100,30);

        btnSearch = new JButton("Search");
        btnSearch.setBounds(470,80,100,30);

        btnUpdate = new JButton("Update");
        btnUpdate.setBounds(590,80,100,30);

        btnDelete = new JButton("Delete");
        btnDelete.setBounds(350,130,100,30);

        btnClear = new JButton("Clear");
        btnClear.setBounds(470,130,100,30);

        btnExit = new JButton("Exit");
        btnExit.setBounds(590,130,100,30);

        model = new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Department");
        model.addColumn("Email");
        model.addColumn("Phone");

        table = new JTable(model);

        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30,320,760,200);

        add(lblTitle);

        add(lblId);
        add(txtId);

        add(lblName);
        add(txtName);

        add(lblDept);
        add(txtDept);

        add(lblEmail);
        add(txtEmail);

        add(lblPhone);
        add(txtPhone);

        add(btnAdd);
        add(btnSearch);
        add(btnUpdate);
        add(btnDelete);
        add(btnClear);
        add(btnExit);

        add(scrollPane);

        btnAdd.addActionListener(this);
        btnSearch.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnDelete.addActionListener(this);
        btnClear.addActionListener(this);
        btnExit.addActionListener(this);

        loadTable();

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Add Student
        if (e.getSource() == btnAdd) {

            Student s = new Student();

            s.setId(Integer.parseInt(txtId.getText()));
            s.setName(txtName.getText());
            s.setDepartment(txtDept.getText());
            s.setEmail(txtEmail.getText());
            s.setPhone(txtPhone.getText());

            if (dao.addStudent(s)) {

                JOptionPane.showMessageDialog(this,
                        "Student Added Successfully");

                loadTable();

                clearFields();

            } else {

                JOptionPane.showMessageDialog(this,
                        "Failed to Add Student");
            }
        }

        // Clear Button
        if (e.getSource() == btnClear) {

            clearFields();
        }

        // Exit Button
        if (e.getSource() == btnExit) {

            System.exit(0);
        }
        // Search Student
        if (e.getSource() == btnSearch) {

            int id = Integer.parseInt(txtId.getText());

            Student s = dao.searchStudent(id);

            if (s != null) {

                txtName.setText(s.getName());
                txtDept.setText(s.getDepartment());
                txtEmail.setText(s.getEmail());
                txtPhone.setText(s.getPhone());

            } else {

                JOptionPane.showMessageDialog(this,
                        "Student Not Found");
            }
        }
        // Update Student
        if (e.getSource() == btnUpdate) {

            Student s = new Student();

            s.setId(Integer.parseInt(txtId.getText()));
            s.setName(txtName.getText());
            s.setDepartment(txtDept.getText());
            s.setEmail(txtEmail.getText());
            s.setPhone(txtPhone.getText());

            if (dao.updateStudent(s)) {

                JOptionPane.showMessageDialog(this,
                        "Student Updated Successfully");

                loadTable();
                clearFields();

            } else {

                JOptionPane.showMessageDialog(this,
                        "Update Failed");
            }
        }
        // Delete Student
        if (e.getSource() == btnDelete) {

            int id = Integer.parseInt(txtId.getText());

            if (dao.deleteStudent(id)) {

                JOptionPane.showMessageDialog(this,
                        "Student Deleted Successfully");

                loadTable();
                clearFields();

            } else {

                JOptionPane.showMessageDialog(this,
                        "Delete Failed");
            }
        }
    }
    // Clear TextFields
    public void clearFields() {

        txtId.setText("");
        txtName.setText("");
        txtDept.setText("");
        txtEmail.setText("");
        txtPhone.setText("");
    }

    // Load JTable
    public void loadTable() {

        model.setRowCount(0);

        for (Student s : dao.getAllStudents()) {

            Object row[] = {
                    s.getId(),
                    s.getName(),
                    s.getDepartment(),
                    s.getEmail(),
                    s.getPhone()
            };

            model.addRow(row);
        }
    }
}
