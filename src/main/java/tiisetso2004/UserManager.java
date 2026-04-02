package tiisetso2004;

import java.util.Scanner;
 /**
 * UserManager is the UI manager for the application.
 * All designs for the user interface of the program will are made here. 
 **/
public class UserManager {

    Scanner capture  = new Scanner(System.in);
    /*Default no-args constructor*/
    public UserManager() {
    }
    /*Terminal UI to run program sessions*/
    public void startSession() {
        boolean isRunning = true;
        while(isRunning) { 
        System.out.println();
        System.out.println("--------------------");
        System.out.printf("%16s%n","Login Manager");
        System.out.println("--------------------");
        System.out.println("Choose your option:");
        System.out.println("0.QUIT \n1.Create new user");
        try {
            int choice  = capture.nextInt();
            //as program grows add new cases with function calls.
            switch (choice) {
                case 0:
                    System.out.println("You chose QUIT, confirm y/n?");
                    capture.nextLine(); //clearing of buffer from next int.
                    String confirm = capture.nextLine();
                    if(confirm.toLowerCase().equals("y")) {
                        System.out.println("Quiting.....\nGoodbye");
                        System.exit(0);
                    } else if(confirm.toLowerCase().equals("n")) {
                        System.out.println("You chose to continue");
                    }
                    break;
                case 1:
                    createUser();
                    break;
                default:
                    System.err.println("invalid input detected");
            }
        } 
        catch (java.util.InputMismatchException e) {
            System.err.println("Invalid input. Please enter a number."); //catching non numeric input to prevent loop from crashing
            capture.nextLine();
        }
    }
}
    /*Create new login on every call*/
    private void createUser() {
        Login login = new Login();
        login.registerUser();
    }
}
