package tiisetso2004;
/**
 * @author tiisetso2004
 * The Message Log class is a utility to return simple messages.
 * It consists of getters to return String messages for specfic actions.
 */
public class MessageLog {
    /*Error Messaging*/
    private final static String usernameErrorMessage = "Username is not correctly formated; \nplease ensure that username contains an underscore \nand is no more than 5 characters in length.";
    private final static String cellphoneErrorMessage = "Cell phone number incorrectly formatted or does not contain international code";
    private final static String nameErrorMessage = "Invalid entry detected, enter your proper name and surname";
    private final static String passwordErrorMessage  = "Please try again and follow the above instructions for a valid password ^";
    /*Sucess messaging*/
    private final static String usernameMessage = "Username successfully captured";
    private final static String cellphoneMessage = "Cellphone number successfully captured";
    private final static String nameMessage =  "Identity confirmed";
    private final static String passwordMessage = "Password successfully captured";
    /*prompt messages*/
    private final static String namePrompt = "\nPlease enter your full name and surname only: ";
    private final static String userNamePrompt = "\nPlease enter your new username: ";
    private final static String passwordPrompt = "\nPlease enter your new password: ";
    private final static String cellphonePrompt = "\nPlease enter your cellphone number in the format +27XXXXXXXXX: ";
    /*Login specific messaging*/
    private final static String usernameLogin = "\nRe-enter your username to login: ";
    private final static String passwordLogin = "\nRe-enter your password to login: ";
    private final static String authenticatedUsernameMessage = "Username confirmed";
    private final static String authenticatedPasswordMessage = "Password confirmed";
    private final static String LoginError = "Login Failed: your username or password does not match the one you prevoiusly entered, please try again";
    private final static String nullUserError = "Error: User object does not exist";
    private final static String captureSucessEntry = "Database Entry Confirmation: User was created and stored in local database";
 

    public static String getUsernameErrorMessage() {
        return usernameErrorMessage;
    }

    public static String getCellphoneErrorMessage() {
        return cellphoneErrorMessage;
    }

    public static String getNameErrorMessage() {
        return nameErrorMessage;
    }

    public static String getPasswordErrorMessage() {
        return passwordErrorMessage;
    }

    public static String getUsernameMessage() {
        return usernameMessage;
    }

    public static String getCellphoneMessage() {
        return cellphoneMessage;
    }

    public static String getNameMessage() {
        return nameMessage;
    }

    public static String getPasswordMessage() {
        return passwordMessage;
    }

    public static String getNamePrompt() {
        return namePrompt;
    }

    public static String getUserNamePrompt() {
        return userNamePrompt;
    }

    public static String getPasswordPrompt() {
        return passwordPrompt;
    }

    public static String getCellphonePrompt() {
        return cellphonePrompt;
    }

    public static String getUsernameLogin() {
        return usernameLogin;
    }

    public static String getPasswordLogin() {
        return passwordLogin;
    }

    public static String getAuthenticatedUsernameMessage() {
        return authenticatedUsernameMessage;
    }

    public static String getAuthenticatedPasswordMessage() {
        return authenticatedPasswordMessage;
    }
    
    public static String getLoginError() {
        return LoginError;
    }

    public static String getLoginSucessMessage(User user) {
        return "\nLogin successful, welcome back " + user.getFullName() + ", it is great to see you again";
    }
    
    public static String getNullUserError() {
        return nullUserError;
    }

    public static String getCaptureSucessEntry() {
        return captureSucessEntry;
    }
}