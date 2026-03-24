package tiisetso2004;

import java.util.*;
import java.util.regex.Pattern;
import java.util.function.Predicate;
import org.passay.*; // import passay library for password validation

/*
 * The login class will primarily consist entirely of utility functions.
 */
public class Login {

    private User user;
    public static Scanner sc = new Scanner(System.in);

    static final Pattern CELLPHONE_REGEX = Pattern.compile("^\\+27[0-9]{9}$"); //basic regex for an SA cellphone number.
    static final Pattern USERNAME_REGEX = Pattern.compile("^(?=.*_)[a-zA-Z0-9_]{5}$"); //pattern for alphanumeric 5 character string that includes underscore.
    static final Pattern NAME_REGEX = Pattern.compile("^[\\p{L}_'\\- ]{2,50}$"); //basic regex to allow most names

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

    public static boolean checkFullName(String name) {
        return regexReader(NAME_REGEX, name.trim());        
    }  
    
    public static boolean checkUsername(String username) {
        return regexReader(USERNAME_REGEX, username.trim());
    }

    public static boolean checkCellphoneNumber(String cell) {
        return regexReader(CELLPHONE_REGEX, cell.trim());
    }

    public static String promptUntilValid(String prompt, Predicate <String> validator, String errorMessage, String sucessMessage) { //this is the validation input loop.
        while (true) {
            System.out.println(prompt);
            String input = sc.nextLine();

            if (validator.test(input)) {
                System.out.println(sucessMessage);
                return input;
            } else {
                System.err.println(errorMessage);
            }
        }
    }

    public String registerUser() { //returns messaging for false or true entries.
        String fullName, username, cellphoneNumber, password;
        fullName = promptUntilValid(MessageLog.getNamePrompt(), Login::checkFullName, MessageLog.getNameErrorMessage(), MessageLog.getNameMessage());
        username = promptUntilValid(MessageLog.getUserNamePrompt(), Login::checkUsername, MessageLog.getUsernameErrorMessage(), MessageLog.getUsernameMessage());
        cellphoneNumber = promptUntilValid(MessageLog.getCellphonePrompt(), Login::checkCellphoneNumber, MessageLog.getCellphoneErrorMessage(), MessageLog.getCellphoneMessage());
        password = promptUntilValid(MessageLog.getPasswordPrompt(), Login::checkPasswordComplexity, MessageLog.getPasswordErrorMessage(), MessageLog.getPasswordMessage());
        
        this.user = new User(fullName, username, cellphoneNumber, password); //assing varuables to user object
        UserDatabase.addUser(this.user);
        returnLoginStatus(); //returns login status and has internal call to login.
        return "User registered successfully: " + user.getFullName();
    }

    public boolean loginUser() {         
        System.out.println("Re-enter your username to login");
        String enteredUsername = sc.nextLine();
        nullCheck(enteredUsername);
        System.out.println("Re-enter your password to login");
        String enteredPassword  = sc.nextLine();
        nullCheck(enteredPassword);
        return enteredUsername.equals(user.getUsername()) && enteredPassword.equals(user.getPassword());
        
    }

    public String returnLoginStatus() {
        String message;
        boolean isLoggedIn = false;
        while (!isLoggedIn) {
            isLoggedIn = loginUser();

            if (!isLoggedIn) {
                System.out.println("Login Unsucessfull, your username or password do not match previous details");
            }
        }
        message = "Login successful, weclome back "+this.user.getFullName()+", it is great to see you again";
        System.out.println(message);
        return message;   
    }
}
