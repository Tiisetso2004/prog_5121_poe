package tiisetso2004;

import java.util.*;
import java.util.regex.Pattern;
import org.passay.*; //import passay library for password validation.

public class Validator {

    //basic regex for an SA cellphone number.
    static final Pattern CELLPHONE_REGEX = Pattern.compile("^\\+27[0-9]{9}$");

    //pattern for alphanumeric 5 character string that includes underscore.
    static final Pattern USERNAME_REGEX = Pattern.compile("^(?=.*_)[a-zA-Z0-9_]{5}$");

    //basic regex to allow most names.
    static final Pattern NAME_REGEX = Pattern.compile("^[\\p{L}'\\- ]{2,50}$");

    //passay library implementation, rules for specific validation requirements.
    private static final PasswordValidator VALIDATOR = new PasswordValidator(Arrays.asList( 
            new LengthRule(8,30), 
            new CharacterRule(EnglishCharacterData.UpperCase,1), 
            new CharacterRule(EnglishCharacterData.Special,1), 
            new CharacterRule(EnglishCharacterData.Digit,1), 
            new RepeatCharacterRegexRule(3), 
            new WhitespaceRule() 
    ));

    /*private constructor, class takes in no input, utility class will not be instantiated*/
    private Validator() {
    }

    //checks if any string input is null, blank or contains literal string "null".
    public static boolean notNullCheck(String input) {
        if (input == null || input.isBlank() || input.equalsIgnoreCase("null")) {
            System.err.println("Error: input cannot be empty, blank or contain string literal 'null', please fill in this field");
            return false;
        }
        return true;
    }

    /*helper function to receive regex patterns and return true or false.*/
    public static boolean regexReader(Pattern regex, String input) {
        //check if input is null
        if (!notNullCheck(input)) {
            return false;
        }
        //check if regex is null
        if (regex == null) {
            System.err.println("Pattern regex is null");
            return false;
        }
        //returns only if null checks are passed
        return regex.matcher(input.trim()).matches();
    }

    /*Implementation of the regexReader for validation*/
    public static boolean checkFullName(String name) {
        return regexReader(NAME_REGEX, name);        
    }  
    
    public static boolean checkUsername(String username) {
        return regexReader(USERNAME_REGEX, username);
    }

    public static boolean checkCellphoneNumber(String cell) {
        return regexReader(CELLPHONE_REGEX, cell);
    }

    /*Validation logic for passay library.*/
    public static boolean checkPasswordComplexity(String password) {
        if (nullCheck(password)) {
            return false;
        }
        RuleResult result = VALIDATOR.validate(new PasswordData(password));
        if (result.isValid()) {
            return true; //returns true if the argument is valid.
        } else {
            List<String> messages = VALIDATOR.getMessages(result);
            messages.forEach(System.out::println); //gets messages for invalid inputs from passay library.
            return false; //returns false if the argument is invalid.
        } 
    }
}