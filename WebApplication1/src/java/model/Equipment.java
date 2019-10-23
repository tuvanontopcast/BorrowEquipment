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
    private int id;
    private String name;
    private Student borrower;

    public Equipment() {
    }

    public Equipment(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Equipment(int id, String name, Student borrower) {
        this.id = id;
        this.name = name;
        this.borrower = borrower;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Equipment{" + "id=" + id + ", name=" + name + ", borrower=" + borrower + '}';
    }

    public Student getBorrower() {
        return borrower;
    }

    public void setBorrower(Student borrower) {
        this.borrower = borrower;
    }
    
}
