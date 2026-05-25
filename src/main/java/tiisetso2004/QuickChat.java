package tiisetso2004;
import java.util.*;

public class QuickChat {

    Scanner qcScan = new Scanner(System.in);
   
    public void draftMessage() {
        try { 
            System.out.print("How many messages would you like to create\n?");
            String input = qcScan.nextLine();
            int counter = Integer.parseInt(input);
            System.out.println();

            for(int i = 0; i < counter; i++) {
                int messageNum = i+1;
                String contact = LoginManager.promptUntilValid(qcScan,"Enter the recpient cellnumber: ", Validator::checkCellphoneNumber,MessageLog.getCellphoneErrorMessage(), MessageLog.getCellphoneMessage());

                System.out.println("======Message no : "+messageNum+" ======");
                String text = LoginManager.promptUntilValid(qcScan,"Enter your message: ",Validator::messageValidator, "Message is too long or is empty","Message ready to send");
                
                String message = text.trim();
                String ID = Message.GenerateMessageID(10);
                String messageHash = Message.createMessageHash(ID, messageNum, message);
                MessageData messObj = new MessageData(message, messageHash, contact, ID);
                StorageManager.captureMessageDraft(messObj, StorageManager.getSavedMessagesList());

                System.out.println("What would you like to do with this message?");
                System.out.println("1.Send message\n3.Delete message");
                System.out.println("Stored messages feature still in development");
                String choice = qcScan.nextLine();
      
                switch (choice) {      
                case "1":
                    sendMessage(messObj);
                    break;
                
              //case "2":
                  //storeMessage(messObj);
                  //break;

                case "3":
                    discardMessage(messObj);
                    break;  
            
                default:
                    System.out.println("Enter a choice between 1-3");
                    break;
                }
            }
        } catch(NumberFormatException e) {
            System.err.println("Invalid input detected, input was not a number");
        }           
    }

    private void sendMessage(MessageData messObj) {
        //save the sent message first
        storeMessage(messObj);
        //print out the message
        System.out.println("Message sent to:"+ messObj.getRecipient());
    }

    //delete temporarily stored message in Array list
    private void discardMessage(MessageData messageObj) {
        StorageManager.deleteMessage(messageObj, StorageManager.getSavedMessagesList());
    }

    //persitent storage in JSON file
    private void storeMessage(MessageData messageObj) {
        StorageManager.storeMessage(messageObj);

    }
}