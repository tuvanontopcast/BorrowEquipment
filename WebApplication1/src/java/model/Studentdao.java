/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import db.BuildConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Top
 */
public class Studentdao {
    
    Connection conn;
    
    public Student getStudentByUsername(String username){
        try {
            conn = BuildConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM students WHERE username = ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return new Student(rs.getInt("id"), rs.getString("username"), rs.getString("password"));
            }
            rs.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Studentdao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public Student getStudentById(int id){
        try {
            conn = BuildConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM students WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return new Student(rs.getInt("id"), rs.getString("username"), rs.getString("password"));
            }
            rs.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Studentdao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public ArrayList<Student> getResultListStudent(ResultSet rs) throws SQLException{
        ArrayList<Student> allStudent = new ArrayList();
        allStudent.add(new Student(rs.getInt("id"), rs.getString("username"), rs.getString("password")));
        return allStudent;
        
    }
    
    public ResultSet getStudentResult(ResultSet rs){
        try {
            conn = BuildConnection.getConnection();
            if(rs.next()){
              return (ResultSet) new Student(rs.getInt("id"), rs.getString("username"), rs.getString("password"));
            }
        }catch (SQLException ex) {
                Logger.getLogger(Studentdao.class.getName()).log(Level.SEVERE, null, ex);
            }
        return null;
    }
    
}
