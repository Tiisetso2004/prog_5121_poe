package tiisetso2004;

public class User {

    private String fullName, username, cellphoneNumber, password;
    
    //call to parameterized constructor to auto-assign first time users
    public User (String name, String usr, String cell, String pass) { 
        this.fullName = name;
        this.username = usr;
        this.cellphoneNumber = cell;
        this.password = pass;
    }

    //use setters and utilities to update fields
    public boolean updateFullName(String name) {
        this.fullName = name;
        return true;
    }

    public boolean updateUsername(String usr) {
        this.username = usr;
        return true;
    }

    public boolean updateCellphoneNumber(String cell) {
        this.cellphoneNumber = cell;
        return true;
    }

    public boolean updatePassword(String pass) {
        this.password = pass;
        return true;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUsername() {
        return username;
    }

    public String getCellphoneNumber() {
        return cellphoneNumber;
    }

    public String getPassword() {
        return password;
    }
}