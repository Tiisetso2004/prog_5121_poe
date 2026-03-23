package tiisetso2004;

import java.util.*;
import java.util.regex.Pattern;
import java.util.function.Predicate;
import org.passay.*; // import passay library for password validation

/*
 * The login class will primarily consist entirely of utility functions.
 */
public class Login {

    private String fullName, username, cellphoneNumber, password;
    private User user = new User(fullName, username, cellphoneNumber, password);
    public static Scanner sc = new Scanner(System.in);

    static final Pattern CELLPHONE_REGEX = Pattern.compile("^\\+27[0-9]{9}$"); //basic regex for an SA cellphone number.
    static final Pattern USERNAME_REGEX = Pattern.compile("^[a-zA-Z0-9_]{5,20}$"); //pattern for alphanumeric 5-20 character string including an underscore.
    static final Pattern NAME_REGEX = Pattern.compile("^[\\p{L}_'\\- ]{2,50}$"); //basic regex to allow most names

    static String usernameErrorMessage = "Incorrect username format.\nThe username must consist of 5-20 characters including an underscore";
    static String cellphoneErrorMessage = "Error unrecognised cellphone number detected, please try again";
    static String nameErrorMessage = "Ivalid entry detected, enter your proper name and surname";
    static String passwordErrorMessage  = "";

    static String namePrompt = "Please enter your full name and surname only.";
    static String userNamePrompt = "Please enter your new username";
    static String passwordPrompt = "Please enter your new password";
    static String cellphonePrompt = "Please enter your cellphone number in the format +27XXXXXXXXX";

    private static final PasswordValidator VALIDATOR = new PasswordValidator(Arrays.asList( 
            new LengthRule(8,30), 
            new CharacterRule(EnglishCharacterData.UpperCase,1), 
            new CharacterRule(EnglishCharacterData.Special,1), 
            new CharacterRule(EnglishCharacterData.Digit,1), 
            new RepeatCharacterRegexRule(3), 
            new WhitespaceRule() 
    ));

    public static boolean nullCheck(String input) { //checks if any string input is null or blank.
        if (input == null || input.isBlank()) {  
            System.err.println("Error: input cannot be empty or blank, please fill in this field");
            return false;
        }
        return true;
    }

    public static boolean regexReader(Pattern regex, String input) { //helper function to compile regex patterns and return true or false
        if (!nullCheck(input)) {
            return false;
        }
        return regex.matcher(input).matches();
    }

    public static boolean checkPasswordComplexity(String password) {
        if (!nullCheck(password)) {
            return false;
        }
        RuleResult result = VALIDATOR.validate(new PasswordData(password));
        if (result.isValid()) {
            System.out.println("valid password");
            return true; //returns true if the argument is valid
        } else {
            List<String> messages = VALIDATOR.getMessages(result);
            messages.forEach(System.out::println); //gets messages for invalid inputs from passay library
            return false; //returns false if the argument is invalid
        }           
    }

    public static boolean checkFirstName(String name) {
        return regexReader(NAME_REGEX, name.trim());        
    }  
    
    public static boolean checkUsername(String username) {
        return regexReader(USERNAME_REGEX, username.trim());
    }

    public static boolean checkCellphoneNumber(String cell) {
        return regexReader(CELLPHONE_REGEX, cell.trim());
    }

    public static String promptUntilValid(String prompt, Predicate <String> validator, String errorMessage) { //this is the validation input loop.
        while (true) {
            System.out.println(prompt);
            String input = sc.nextLine();

            if (validator.test(input)) {
                return input;
            } else {
                System.err.println(errorMessage);
            }
        }
    }

    public String registerUser() { //returns messaging for false or true entries.
        
        fullName = promptUntilValid(namePrompt, Login::checkFirstName, nameErrorMessage);
        username = promptUntilValid(userNamePrompt, Login::checkUsername, usernameErrorMessage);
        cellphoneNumber = promptUntilValid(cellphonePrompt, Login::checkCellphoneNumber, cellphoneErrorMessage);
        password = promptUntilValid(passwordPrompt, Login::checkPasswordComplexity, passwordErrorMessage);

        user = new User(fullName, username, cellphoneNumber, password); //assing varuables to user object
        UserDatabase.addUser(user);
        returnLoginStatus(); //returns login status and has internal call to login.
        return "User registered successfully: " + user.getFullName();
    }

    public boolean loginUser() { 
        System.out.println("Re-enter your username to login");
        String enterdUsername = sc.nextLine();
        System.out.println("Re-enter your password to login");
        String enteredPassword  = sc.nextLine();

        return enterdUsername.equals(user.getUsername()) && enteredPassword.equals(user.getPassword());
               
        
    }

    public String returnLoginStatus() {
        String message;
        boolean validLogin = loginUser();

        if (!validLogin) {
            message = "Login Unsuccessful, please try again";
            System.err.println(message);            
        } else {
            message = "Login Successful, Welcome back, It is great to see you again";
            System.out.println(message);         
        }
        return message;   
    }
}
