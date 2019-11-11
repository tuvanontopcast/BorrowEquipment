/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import db.BuildConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Student;

/**
 *
 * @author Top
 */
public class StudentController {

    Connection conn;

    public Student findStudentByUsername(String username) {
        try {
            conn = BuildConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM students WHERE username = ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Student(rs.getInt("id"), rs.getString("username"), rs.getString("password"));
            }
            rs.close();
            conn.close();
        } catch (SQLException ex) { 
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public Student findStudentById(int id) {
        try {
            conn = BuildConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM students WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Student(rs.getInt("id"), rs.getString("username"), rs.getString("password"));
            }
            rs.close();
            conn.close();
        } catch (SQLException ex) { 
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static void main(String[] args) {
        StudentController sdao = new StudentController();
        Student s = sdao.findStudentById(1);
        System.out.println(s);
    }
}
