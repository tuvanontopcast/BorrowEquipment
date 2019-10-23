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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Top
 */
public class Equipmentdao {
    
    Connection conn = null;
    
    public Equipment getEquipmentByid(int id){
        Studentdao sdao = new Studentdao();
        try {
            conn = BuildConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM equipments WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Student s = sdao.getStudentById(rs.getInt("borrower"));
                return new Equipment(rs.getInt("id"), rs.getString("name"),s);
            }
            rs.close();
            conn.close();
        } catch (SQLException ex) { 
            Logger.getLogger(Equipmentdao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    
    public ArrayList<Equipment> getEquipmentByBorrower(Student s){
        try {
            conn = BuildConnection.getConnection();
            ArrayList<Equipment> alle = new ArrayList();
            Studentdao sdao = new Studentdao();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM equipments where borrower = ?");
            ps.setInt(1, s.getId());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                alle.add( new Equipment(rs.getInt("id"), rs.getString("name") , s));
            }
            return alle;
        } catch (SQLException ex) { 
            Logger.getLogger(Equipmentdao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public ArrayList<Equipment> getAllEquipment(){
        ArrayList<Equipment> es = new ArrayList();
        Studentdao sdao = new Studentdao();
        String query = "SELECT * FROM equipments";
        try {
            conn = BuildConnection.getConnection(); 
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                Student s = sdao.getStudentById(rs.getInt("borrower"));
                Equipment e = new Equipment(rs.getInt("id"), rs.getString("name"), s);
                es.add(e);   
            }return es;

        }   catch (SQLException ex) {
            Logger.getLogger(Equipmentdao.class.getName()).log(Level.SEVERE, null, ex);
            }
        return null;
    }
    
    public boolean UpdateEquipmentBorrower(Equipment adde){
        
        try {
            Equipmentdao edao = new Equipmentdao();
            if(adde.getBorrower() == null){
                try{
                    Equipment e = edao.getEquipmentByid(adde.getId());      
                    conn = BuildConnection.getConnection(); 
                    e.setBorrower(null);
                    PreparedStatement ps = conn.prepareStatement("UPDATE equipments SET borrower = ? WHERE id = ?");
                    ps.setObject(1, null);
                    ps.setInt(2, adde.getId());
                    ps.executeUpdate();
                    return true;
                }catch (SQLException ex) {
                    Logger.getLogger(Equipmentdao.class.getName()).log(Level.SEVERE, null, ex);
                }return false;
            }
            Equipment e = edao.getEquipmentByid(adde.getBorrower().getId());      
            conn = BuildConnection.getConnection(); 
            PreparedStatement ps = conn.prepareStatement("UPDATE equipments SET borrower = ? WHERE id = ?"); 
            ps.setObject(1, adde.getBorrower().getId());
            ps.setInt(2, adde.getId());
            ps.executeUpdate();
            e.setBorrower(adde.getBorrower());
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Equipmentdao.class.getName()).log(Level.SEVERE, null, ex);
        }
            return false;
    }
    
    
}
