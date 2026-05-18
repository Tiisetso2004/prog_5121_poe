package tiisetso2004;
import java.util.*;

public class QuickChat {

    Scanner qcScan = new Scanner(System.in);
   
    public void sendMessage() {

    }

    public void writeMessage(String message) {
       System.out.print("How many messages would you like to create?");
       int counter = qcScan.nextInt();
       System.out.println();

       for(int i = 0; i > counter; i++) {
        int messageNum = counter;

        String contact = LoginManager.promptUntilValid(qcScan,"Enter the recpient cellnumber", Validator::checkCellphoneNumber, MessageLog.getCellphoneMessage(), MessageLog.getCellphoneErrorMessage());

        System.out.println("======Message:"+counter+"======");
        System.out.println("Enter the message:");
        String text = qcScan.nextLine();
        
        message = text.trim();
        String ID = Message.GenerateMessageID(10);
        String messageHash = Message.createMessageHash(ID, messageNum, message);
        MessageData messObj = new MessageData(message, messageHash, contact, ID);
       }
    }

    public void discardMessage(MessageData messageObj) {

    }

    public void storeMessage(MessageData messageObj) {

    }
}