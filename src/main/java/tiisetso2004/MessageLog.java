package tiisetso2004;

public class MessageLog {

    private static String usernameErrorMessage = "Username is not correctly formated; \nplease ensure that username contains an underscore \nand is no more than 5 characters in length.";
    private static String cellphoneErrorMessage = "Cell phone number incorrectly formatted or does not contain international code";
    private static String nameErrorMessage = "Invalid entry detected, enter your proper name and surname";
    private static String passwordErrorMessage  = "";

    private static String usernameMessage = "Username successfully captured";
    private static String cellphoneMessage = "Cellphone number successfully captured";
    private static String nameMessage =  "Name succesfully captured";
    private static String passwordMessage = "Password sucessfully captured";

    private static String namePrompt = "Please enter your full name and surname only.";
    private static String userNamePrompt = "Please enter your new username";
    private static String passwordPrompt = "Please enter your new password";
    private static String cellphonePrompt = "Please enter your cellphone number in the format +27XXXXXXXXX";

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

}