package tiisetso2004;

import java.util.*;
import java.util.regex.Pattern;
import java.util.function.Predicate;
import org.passay.*; // import passay library for password validation

public class Validator {

    public static Scanner sc;
    static final Pattern CELLPHONE_REGEX = Pattern.compile("^\\+27[0-9]{9}$"); //basic regex for an SA cellphone number.
    static final Pattern USERNAME_REGEX = Pattern.compile("^[a-zA-Z0-9_]{5,20}$"); //pattern for alphanumeric 5-20 character string including an underscore.
    static final Pattern NAME_REGEX = Pattern.compile("^[A-Z][a-z'-]{1,29} [A-Z][a-z'-]{1,29}$"); //accepting captialized first and last name in a single string.
    static String usernameErrorMessage = "Incorrect username format.\nThe username must consist of 5-20 characters including an underscore";
    static String cellphoneErrorMessage = "Error unrecognised cellphone number detected, please try again";
    static String nameErrorMessage = "Ivalid entry detected, enter your proper name and surname";
    static String namePrompt = "Please enter your full name and surname only.";
    static String userNamePrompt = "Please enter your new username";
    static String passwordPrompt = "Please enter your new password";
    static String cellphonePrompt = "Please enter your cellphone number in the format +27XXXXXXXXX";
    /***/
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

    public static boolean regexValidator(Pattern regex, String input) { //helper function to compile regex patterns and return true or false
        if (!nullCheck(input)) {
            return false;
        }
        return regex.matcher(input).matches();
    }

    public static boolean validatePassword(String password) {
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

    public static boolean validateFirstName(String name) {
        if (!nullCheck(name)) {
            return false;
        }
        return regexValidator(NAME_REGEX, name);        
    }  
    
    public static boolean validateUsername(String username) {
        if (!nullCheck(username)) {
            return false;
        }
        return regexValidator(USERNAME_REGEX, username);
    }

    public static boolean validateCellphoneNumber(String cell) {
        if (!nullCheck(cell)) {
            return false;
        }
        return regexValidator(CELLPHONE_REGEX, cell);
    }

    public static String promptUntilValid(String prompt, Predicate <String> validator, String errorMessage) { //this is the validation input loop.
        while (true) {
            System.out.println(prompt);
            String input = sc.nextLine();

            if (validator.test(input)) {
                return input;
            }
            System.err.println(errorMessage);
        }
    }

    public static boolean login() { //TODO: implement login method to check if username and password match an existing user in the database.
        return false;
    }
}