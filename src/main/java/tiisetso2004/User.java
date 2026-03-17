package tiisetso2004;

public class User {

    private String fullName, username, cellphoneNumber, password;
    Login user = new Login();
    
    public User (String name, String user, String cell, String pass) {

        this.fullName = name;
        this.username = user;
        this.cellphoneNumber = cell;
        this.password = pass;
    }

    public boolean setFullName(String fullName) {
        this.fullName = fullName;
        return user.checkFullName(fullName);
    }

    public boolean setUsername(String username) {
        this.username = username;
        return user.checkUsername(username);
    }

    public boolean setCellphoneNumber(String cellphoneNumber) {
        this.cellphoneNumber = cellphoneNumber;
        return user.checkCellphoneNumber(cellphoneNumber);
    }

    public boolean setPassword(String password) {
        this.password = password;
        return user.checkPasswordComplexity(password);
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