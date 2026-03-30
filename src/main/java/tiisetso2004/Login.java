package tiisetso2004;

import java.util.*;
import java.util.regex.Pattern;
import java.util.function.Predicate;
import org.passay.*; // import passay library for password validation.

public class Login {

    private User user;
    public static Scanner sc = new Scanner(System.in);
    static final Pattern CELLPHONE_REGEX = Pattern.compile("^\\+27[0-9]{9}$"); //basic regex for an SA cellphone number.
    static final Pattern USERNAME_REGEX = Pattern.compile("^(?=.*_)[a-zA-Z0-9_]{5}$"); //pattern for alphanumeric 5 character string that includes underscore.
    static final Pattern NAME_REGEX = Pattern.compile("^[\\p{L}'\\- ]{2,50}$"); //basic regex to allow most names.

    private static final PasswordValidator VALIDATOR = new PasswordValidator(Arrays.asList( 
            new LengthRule(8,30), 
            new CharacterRule(EnglishCharacterData.UpperCase,1), 
            new CharacterRule(EnglishCharacterData.Special,1), 
            new CharacterRule(EnglishCharacterData.Digit,1), 
            new RepeatCharacterRegexRule(3), 
            new WhitespaceRule() 
    ));
    /*No-args constructor, class takes in no input*/
    public Login() {
    }

    public static boolean nullCheck(String input) { //checks if any string input is null or blank.
        if (input == null || input.isBlank()) {  
            System.err.println("Error: input cannot be empty or blank, please fill in this field");
            return false;
        }
        return true;
    }

    public static void Quit() {
        System.out.println("Type 'QUIT' to quit");
        String exit = sc.nextLine();
        if(exit.toUpperCase().equals("QUIT")) {
            System.out.println("Exiting application.....Goodbye");
            System.exit(0);
        }
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
    /**Feedback loop**/
    public static String promptUntilValid(String prompt, Predicate <String> validator,String errorMessage, String sucessMessage) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine();
            System.out.println();//UX addition: terminal spacing.

            if (validator.test(input)) {
                System.out.println(sucessMessage);
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
    public static boolean loginUsernameAuthenticator(User user) {
        while (true) {
            System.out.println(MessageLog.getUsernameLogin());
            String enteredUsername = sc.nextLine();
            boolean authenticate = loginUsername(user, enteredUsername);
            if (authenticate) {
                System.out.println(MessageLog.getAuthenticatedUsernameMessage());
                return authenticate;
            } else {
                System.err.println(returnLoginStatus(authenticate, user));
                // The loop continues, asking for the username again
            }
        }
    }

    public static boolean loginPasswordAuthenticator(User user) { 
        while(true) {
            System.out.println(MessageLog.getPasswordLogin());
            String enteredPassword = sc.nextLine();
            boolean authenticate = loginPassword(user, enteredPassword);
            if(authenticate) {
                System.out.println(MessageLog.getAuthenticatedPasswordMessage());
                return authenticate;
            } else {
                System.err.println(returnLoginStatus(authenticate, user));
            }
        }
    }
    /**
     * Call to funtions to register new user.
     * This is be the only non static funtion.
     * Each instance of this method creates new unique users.
     * User variables are authenticated and added to UserDatabase.
     **/
     public String registerUser() { 
         String fullName, username, cellphoneNumber, password;
         fullName = promptUntilValid(MessageLog.getNamePrompt(), Login::checkFullName, MessageLog.getNameErrorMessage(), MessageLog.getNameMessage());
         username = promptUntilValid(MessageLog.getUserNamePrompt(), Login::checkUsername, MessageLog.getUsernameErrorMessage(), MessageLog.getUsernameMessage());
         cellphoneNumber = promptUntilValid(MessageLog.getCellphonePrompt(), Login::checkCellphoneNumber, MessageLog.getCellphoneErrorMessage(), MessageLog.getCellphoneMessage());
         password = promptUntilValid(MessageLog.getPasswordPrompt(), Login::checkPasswordComplexity, MessageLog.getPasswordErrorMessage(), MessageLog.getPasswordMessage());
         user = new User(fullName, username, cellphoneNumber, password); //declare a new user object.
         UserDatabase.addUser(user); //add the user
         loginUser(user);
         return "User Sucessfully registered and added to local database";
     }
    /*Function to validate actual login*/
    public static boolean loginUser(User user) {
        if(loginUsernameAuthenticator(user) && loginPasswordAuthenticator(user)) {
            System.out.println(returnLoginStatus(true, user)); //return messaging.
            return true;
        } else {
            return false;
        }
    }
    /*Return login status messaging here, accepts boolean to invoke messaging*/
    public static String returnLoginStatus(boolean isLoggedIn, User user) {
        String message;
        if (!isLoggedIn) {
            message = MessageLog.getLoginError(); //set or retrive customized error messaging.
        } else {
            message = "Login successful, weclome back "+user.getFullName()+", it is great to see you again";
        }
        return message;   
    }
}