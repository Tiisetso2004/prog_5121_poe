package tiisetso2004;

import java.util.*;
import java.util.regex.Pattern;
import org.passay.*; // import passay library for password validation.

public class Validator {

    static final Pattern CELLPHONE_REGEX = Pattern.compile("^\\+27[0-9]{9}$"); //basic regex for an SA cellphone number.
    static final Pattern USERNAME_REGEX = Pattern.compile("^(?=.*_)[a-zA-Z0-9_]{5}$"); //pattern for alphanumeric 5 character string that includes underscore.
    static final Pattern NAME_REGEX = Pattern.compile("^[\\p{L}'\\- ]{2,50}$"); //basic regex to allow most names.

    private static final PasswordValidator VALIDATOR = new PasswordValidator(Arrays.asList( 
            new LengthRule(8,30), 
            new CharacterRule(EnglishCharacterData.UpperCase,1),//at least one uppercase char 
            new CharacterRule(EnglishCharacterData.Special,1), 
            new CharacterRule(EnglishCharacterData.Digit,1), 
            new RepeatCharacterRegexRule(3), 
            new WhitespaceRule() 
    ));
    /*No-args constructor, class takes in no input*/
    public Validator() {
    }

    public static boolean nullCheck(String input) { //checks if any string input is null or blank.
        if (input == null || input.isBlank()) {  
            System.err.println("Error: input cannot be empty or blank, please fill in this field");
            return false;
        }
        return true;
    }

    public static boolean regexReader(Pattern regex, String input) { //helper function to compile regex patterns and return true or false.
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
            return true; //returns true if the argument is valid.
        } else {
            List<String> messages = VALIDATOR.getMessages(result);
            messages.forEach(System.out::println); //gets messages for invalid inputs from passay library.
            return false; //returns false if the argument is invalid.
        }           
    }
    /*Implementation of the regexReader*/
    public static boolean checkFullName(String name) {
        return regexReader(NAME_REGEX, name.trim());        
    }  
    
    public static boolean checkUsername(String username) {
        return regexReader(USERNAME_REGEX, username.trim());
    }

    public static boolean checkCellphoneNumber(String cell) {
        return regexReader(CELLPHONE_REGEX, cell.trim());
    }
}