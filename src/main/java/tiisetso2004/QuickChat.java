package tiisetso2004;
import java.util.*;

public class QuickChat {

    Scanner qcScan = new Scanner(System.in);
   
    public void draftMessage() {
        System.out.print("How many messages would you like to create?");
        int counter = qcScan.nextInt();
        System.out.println();

        for(int i = 0; i > counter; i++) {
            int messageNum = counter;

            String contact = LoginManager.promptUntilValid(qcScan,"Enter the recpient cellnumber", Validator::checkCellphoneNumber, MessageLog.getCellphoneMessage(), MessageLog.getCellphoneErrorMessage());

            System.out.println("======Message:"+counter+"======");
            System.out.println("Enter the message:");
            String text = qcScan.nextLine();
        
            String message = text.trim();
            String ID = Message.GenerateMessageID(10);
            String messageHash = Message.createMessageHash(ID, messageNum, message);
            MessageData messObj = new MessageData(message, messageHash, contact, ID);
            StorageManager.captureMessageDraft(messObj, StorageManager.getSavedMessagesList());

            System.out.println("What would you like to do with this message?");
            System.out.println("1.Send message\n2.Store message\n3.Delete message");
            int choice = qcScan.nextInt();

            try {            
                switch (choice) {
                    
                case 1:
                    sendMessage(messObj);
                    break;
                
                case 2:
                    storeMessage(messObj);
                    break;

                case 3:
                    discardMessage(messObj);
                    break;  
            
                default:
                    System.out.println("Enter a choice between 1-3");
                    break;
                }
            } catch(NumberFormatException e) {
                System.err.println("Invalid format detected, Input was not a number");
            }
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