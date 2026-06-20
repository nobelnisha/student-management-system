package com.student.sms.dao;

import com.student.sms.db.DBConnection;
import com.student.sms.model.Student;

import java.sql.*;
import java.util.ArrayList;

public class StudentDAO {

    // Add Student
    public boolean addStudent(Student student) {

        String sql = "INSERT INTO student(id,name,department,email,phone) VALUES(?,?,?,?,?)";

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, student.getId());
            ps.setString(2, student.getName());
            ps.setString(3, student.getDepartment());
            ps.setString(4, student.getEmail());
            ps.setString(5, student.getPhone());

            int result = ps.executeUpdate();

            return result > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // View All Students
    public ArrayList<Student> getAllStudents() {

        ArrayList<Student> list = new ArrayList<>();

        try {

            Connection con = DBConnection.getConnection();

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM student");

            while (rs.next()) {

                Student s = new Student();

                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setDepartment(rs.getString("department"));
                s.setEmail(rs.getString("email"));
                s.setPhone(rs.getString("phone"));

                list.add(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // Search Student
    public Student searchStudent(int id) {

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM student WHERE id=?");

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Student s = new Student();

                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setDepartment(rs.getString("department"));
                s.setEmail(rs.getString("email"));
                s.setPhone(rs.getString("phone"));

                return s;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // Update Student
    public boolean updateStudent(Student student) {

        String sql = "UPDATE student SET name=?, department=?, email=?, phone=? WHERE id=?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, student.getName());
            ps.setString(2, student.getDepartment());
            ps.setString(3, student.getEmail());
            ps.setString(4, student.getPhone());
            ps.setInt(5, student.getId());

            int result = ps.executeUpdate();

            return result > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete Student
    public boolean deleteStudent(int id) {

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement("DELETE FROM student WHERE id=?");

            ps.setInt(1, id);

            int result = ps.executeUpdate();

            return result > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}