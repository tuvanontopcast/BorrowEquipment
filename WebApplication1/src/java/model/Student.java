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
public class Student {
    private int id;
    private String username;
    private String password;

    public Student() {
    }

    public Student(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    

    @Override
    public String toString() {
        return "Students{" + "id=" + id + ", username=" + username + ", password=" + password + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
