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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Equipment;
import model.Student;

/**
 *
 * @author Top
 */
public class EquipmentController {
    Connection conn = null;
    
    public Equipment findEquipmentByid(int id){
        StudentController sdao = new StudentController();
        try {
            conn = BuildConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM equipments WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Student s = sdao.findStudentById(rs.getInt("borrower"));
                return new Equipment(rs.getInt("id"), rs.getString("name"),s);
            }
            rs.close();
            conn.close();
        } catch (SQLException ex) { 
            Logger.getLogger(EquipmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    
    public ArrayList<Equipment> findByStudentid(int sid){
        try {
            conn = BuildConnection.getConnection();
            ArrayList<Equipment> alle = new ArrayList();
            StudentController sdao = new StudentController();
            Student ss = sdao.findStudentById(sid);
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM equipments where borrower = ?");
            ps.setInt(1, sid);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                alle.add( new Equipment(rs.getInt("id"), rs.getString("name") , ss));
            }
            return alle;
        } catch (SQLException ex) { 
            Logger.getLogger(EquipmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public ArrayList<Equipment> findEquipmentEntities(){
        ArrayList<Equipment> es = new ArrayList();
        StudentController sdao = new StudentController();
        String query = "SELECT * FROM equipments";
        try {
            conn = BuildConnection.getConnection(); 
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                Student s = sdao.findStudentById(rs.getInt("borrower"));
                Equipment e = new Equipment(rs.getInt("id"), rs.getString("name"), s);
                es.add(e);   
            }return es;

        }   catch (SQLException ex) {
            Logger.getLogger(EquipmentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        return null;
    }
    
    public boolean editBorrower(Equipment adde){
        
        try {
            EquipmentController edao = new EquipmentController();
            if(adde.getBorrower() == null){
                try{
                    Equipment e = edao.findEquipmentByid(adde.getEquipmentId());      
                    conn = BuildConnection.getConnection(); 
                    e.setBorrower(null);
                    PreparedStatement ps = conn.prepareStatement("UPDATE equipments SET borrower = ? WHERE id = ?");
                    ps.setObject(1, null);
                    ps.setInt(2, adde.getEquipmentId());
                    ps.executeUpdate();
                    return true;
                }catch (SQLException ex) {
                    Logger.getLogger(EquipmentController.class.getName()).log(Level.SEVERE, null, ex);
                }return false;
            }
            Equipment e = edao.findEquipmentByid(adde.getBorrower().getStudentId());      
            conn = BuildConnection.getConnection(); 
            PreparedStatement ps = conn.prepareStatement("UPDATE equipments SET borrower = ? WHERE id = ?"); 
            ps.setObject(1, adde.getBorrower().getStudentId());
            ps.setInt(2, adde.getEquipmentId());
            ps.executeUpdate();
            e.setBorrower(adde.getBorrower());
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EquipmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
            return false;
    }
    
    
}
