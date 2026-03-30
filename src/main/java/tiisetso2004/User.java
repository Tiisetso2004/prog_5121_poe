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

    //use setters to update fields
    public void setFullName(String name) {
        this.fullName = name;
    }

    public void setUsername(String usr) {
        this.username = usr;
    }

    public void setCellphoneNumber(String cell) {
        this.cellphoneNumber = cell;
    }

    public void setPassword(String pass) {
        this.password = pass;
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