package tiisetso2004;

import java.util.*;

public class SessionManager {

    Scanner capture  = new Scanner(System.in);

    /*Default no-args constructor*/
    public SessionManager() {
    }
    
    /*Terminal UI to run program as sessions*/
    public void startSession() {
        boolean isRunning = true;
        while(isRunning) { 
        System.out.println();
        System.out.println("-----------------------------------------");
        System.out.println("Login Manager V1.0");
        System.out.println("-----------------------------------------");
        System.out.println("Choose your option:");
        System.out.println("0.QUIT \n1.Create new user");
        try {
            String scannerInput = capture.nextLine();
            int choice = Integer.parseInt(scannerInput);

            //as program grows add new cases with function calls.
            switch (choice) {
                case 0:
                    System.out.println("You chose QUIT, confirm y/n?");
                    String confirm = capture.nextLine();
                    if(confirm.equalsIgnoreCase("y")) {
                        System.out.println("Quiting.....\nGoodbye");
                        isRunning = false;
                        System.exit(0);
                    } else if (confirm.equalsIgnoreCase("n")) {
                        System.out.println("You chose to continue");
                    }
                    break;
                case 1:
                    createUser();
                    QuickChat chat = new QuickChat();
                    chat.draftMessage();
                    break;
                default:
                    System.err.println("invalid input detected");
            }
        } catch (NullPointerException e) {
            System.err.println("No input was entered");
        }
    }
}
    /*Create new login on every call*/
    private void createUser() {
        LoginManager login = new LoginManager();
        login.registerUser();
    }
    /*TODO:Create internal menu system*/
    public void updateUserDetails() {

    }
    /*TODO:Call function from user database*/
    public void deleteUser() {

    }
}