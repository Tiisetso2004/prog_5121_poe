package tiisetso2004;

import java.util.Scanner;
import java.util.function.Predicate;

public class LoginManager {
    
    private final Scanner sc = new Scanner(System.in);

    /**Feedback loop**/
    public static String promptUntilValid(Scanner sc, String prompt, Predicate <String> validator,String errorMessage, String successMessage) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine();
            System.out.println();//UX addition: terminal spacing.

            if (validator.test(input)) {
                System.out.println(successMessage);
                return input;
            } else {
                System.err.println(errorMessage);
            }
        }
    }

    /*Helper methods to perform simple login, with specific users as parameters*/
    private static boolean loginUsername(User user, String enteredUsername) {  
        return enteredUsername.equals(user.getUsername());
    }

    private static boolean loginPassword(User user, String enteredPassword) {
        return enteredPassword.equals(user.getPassword());
    }
    
    /* Implementation of login helpers with internal loops */
    public static boolean loginUsernameAuthenticator(User user, Scanner sc) {
        System.out.println(MessageLog.getUsernameLogin());
        String enteredUsername = sc.nextLine();
        boolean authenticate = loginUsername(user, enteredUsername);
        if (authenticate) {
            System.out.println(MessageLog.getAuthenticatedUsernameMessage());
        } else {
            System.err.println(returnLoginStatus(false, user));
        }
        return authenticate;
    }

    public static boolean loginPasswordAuthenticator(User user, Scanner sc) {   
            System.out.println(MessageLog.getPasswordLogin());
            String enteredPassword = sc.nextLine();
            boolean authenticate = loginPassword(user, enteredPassword);
            if (authenticate) {
                System.out.println(MessageLog.getAuthenticatedPasswordMessage());
            } else {
                System.err.println(returnLoginStatus(false, user));
            }
        return authenticate;
    }

    /**
     * Call to functions to register new user.
     * This is the only non-static function.
     * Each instance of this method creates new unique users.
     * User variables are authenticated and added to UserDatabase.
     **/
     public void registerUser() {
        String fullName, username, cellphoneNumber, password;
        fullName = promptUntilValid(sc, MessageLog.getNamePrompt(), Validator::checkFullName, MessageLog.getNameErrorMessage(), MessageLog.getNameMessage());
        username = promptUntilValid(sc, MessageLog.getUserNamePrompt(), Validator::checkUsername, MessageLog.getUsernameErrorMessage(), MessageLog.getUsernameMessage());
        cellphoneNumber = promptUntilValid(sc, MessageLog.getCellphonePrompt(), Validator::checkCellphoneNumber, MessageLog.getCellphoneErrorMessage(), MessageLog.getCellphoneMessage());
        password = promptUntilValid(sc, MessageLog.getPasswordPrompt(), Validator::checkPasswordComplexity, MessageLog.getPasswordErrorMessage(), MessageLog.getPasswordMessage());
        User user = new User(fullName, username, cellphoneNumber, password); //declare a new user object.
        StorageManager.addUser(user); //add the user to database
        loginUser(user,sc);
         returnLoginStatus(user);
     }

    /*Function to validate actual login*/
    public static boolean loginUser(User user,Scanner sc) {
        if(loginUsernameAuthenticator(user, sc) && loginPasswordAuthenticator(user, sc)) {
            System.out.println(returnLoginStatus(true, user)); //return messaging.
            return true;
        } else {
            return false;
        }
    }

    /*Overloaded returnLoginStatus(), accepts boolean to invoke messaging alongside 'User' objects*/
    public static String returnLoginStatus(boolean isLoggedIn, User user) {
        String message;
        if (!isLoggedIn) {
            message = MessageLog.getLoginError(); //set or retrieve customized error messaging.
        } else {//successful login 
            message = MessageLog.getLoginSuccessMessage(user);
        }
        return message;   
    }

    /*returnLoginStatus specific to registerUser()*/
    public static String returnLoginStatus(User user) {
        String message;
        if (user !=null) {
            message = MessageLog.getCaptureSuccessEntry();
        } else {
            message = MessageLog.getNullUserError(); //set or retrieve customized error messaging.
        }
        System.out.println(message);
        return message;
    }
}