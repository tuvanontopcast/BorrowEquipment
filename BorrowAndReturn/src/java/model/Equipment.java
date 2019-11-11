/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Top
 */
public class Equipment {

    private int equipmentId;
    private String equipmentName;
    private Student borrower;

    public Equipment() {
    }

    public Equipment(int equipmentId, String equipmentName) {
        this.equipmentId = equipmentId;
        this.equipmentName = equipmentName;
    }

    public Equipment(int equipmentId, String equipmentName, Student borrower) {
        this.equipmentId = equipmentId;
        this.equipmentName = equipmentName;
        this.borrower = borrower;
    }

    
    @Override
    public String toString() {
        return "Equipment{" + "equipmentid=" + equipmentId + ", equipmentName=" + equipmentName + ", borrower=" + borrower + '}';
    }

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public Student getBorrower() {
        return borrower;
    }

    public void setBorrower(Student borrower) {
        this.borrower = borrower;
    }

}
