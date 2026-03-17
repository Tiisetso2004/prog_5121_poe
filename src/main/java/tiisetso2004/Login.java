package tiisetso2004;

public class Login {

    String fullname, username, cellphoneNumber, password;

    //call helper methods then return cutsom error messaging
    public boolean checkFullName(String name) { 
        fullname = name;
        return Validator.validateFirstName(name);
    }

    public boolean checkUsername(String user) {
        username = user;
        return Validator.validateUsername(user);
    }

    public boolean checkPasswordComplexity(String pass) { //passay already returns error messaging
        password = pass;
        return Validator.validatePassword(password);
    }

    public boolean checkCellphoneNumber(String cell) {
        cellphoneNumber = cell;
        return Validator.validateCellphoneNumber(cell);
    }
    
    public String registerUser() { //returns login messaging, assignment instructions make messaging tedious.
        String message = "";
        
        if (!checkFullName(fullname)) {
            message = Validator.nameErrorMessage;
            System.err.println(Validator.nameErrorMessage);
        } 
        else if (!checkUsername(username)) {
            message = Validator.usernameErrorMessage;
            System.err.println(Validator.usernameErrorMessage);
        } 
        else if (!checkCellphoneNumber(cellphoneNumber)) {
            message = Validator.cellphoneErrorMessage;
            System.err.println(Validator.cellphoneErrorMessage);
        }
        return message;     
    }

    public boolean loginUser() { //TODO:find more suitable implement in Validator class.
        return Validator.login();
    }

    public String returnLoginStatus(String fullName) {
        String message;
        if (!loginUser()) {
            message = "Login was uncuccessful";   
        } else {
            message = "Welcome back " + fullName + " it is great to see you again.";
        }
        return message;       
    }
}