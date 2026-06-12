package tiisetso2004;

import java.util.*;

public class QuickChat {

    Scanner qcScan = new Scanner(System.in);

    public void draftMessage() {
        boolean isValidCount = false;
        int counter  = 0;

        try {
            while (!isValidCount) {
                System.out.print("\nEnter the amount of messages you wish to send or type 0 to quit: ");
                String input =  qcScan.nextLine();
                counter = Integer.parseInt(input);

                //user exit condition
                if (counter == 0) {
                    System.out.println("You chose to quit...\nGoodbye");
                    break;
                }

                isValidCount = true;
            }
        } catch (NumberFormatException e) {
            System.err.println("Input was not a number, please enter a number");
        } catch (NullPointerException pointerException) {
            System.err.println("Input is empty");
        }

        for (int i = 0; i < counter; i++) {
            int messageNum = i+1;
            String sender = LoginManager.promptUntilValid(qcScan,"Enter your cellphone number: ", Validator::checkCellphoneNumber,MessageLog.getCellphoneErrorMessage(), MessageLog.getCellphoneMessage());
            String contact = LoginManager.promptUntilValid(qcScan,"Enter the recipient's cellphone number: ", Validator::checkCellphoneNumber,MessageLog.getCellphoneErrorMessage(), MessageLog.getCellphoneMessage());

            System.out.println("====== Message no : "+messageNum+" ======");
            String text = LoginManager.promptUntilValid(qcScan,"Enter your message: ", MessageHandler::messageValidator, "Message is too long or is empty","Message ready to send");
                
            String message = text.trim();
            String ID = MetadataGenerator.GenerateMessageID(10);
            String messageHash = MetadataGenerator.createMessageHash(ID, messageNum, message);
            Message messObj = new Message(message, messageHash, sender, contact, ID);
            StorageManager.captureMessageDraft(messObj, StorageManager.getTemporaryMessages());

            while (true) {
                try {
                    System.out.println("What would you like to do with this message?");
                    System.out.println("1.Send message\n2.Store message\n3.Delete message\n4.Configure Stored Messages\nType 'quit' to exit");
                    String choice = qcScan.nextLine();

                    //user exit condition
                    if (choice.equalsIgnoreCase("quit")) {
                        break;
                    }

                    switch (choice) {
                        case "1":
                            sendMessage(messObj);
                            break;

                        case "2":
                            storeMessage(messObj);
                            break;

                        case "3":
                            discardMessage(messObj);
                            break;

                        default:
                            System.out.println("Enter a choice of 1 or 3");
                            break;
                    }
                } catch (NullPointerException npe) {
                    System.err.println("Input is empty");
                }
            }
        }
    }

    private boolean sendMessage(Message messObj) {
        return StorageManager.storeMessage(messObj, StorageManager.getSavedMessagesList()) &&
               StorageManager.storeMessage(messObj, StorageManager.getSentMessagesList());
    }

    //delete temporarily stored message in Array list
    private boolean discardMessage(Message messageObj) {
        return StorageManager.deleteMessage(messageObj, StorageManager.getSavedMessagesList());
    }

    //persistent storage in JSON file
    private boolean storeMessage(Message messageObj) {
       return StorageManager.storeMessage(messageObj,StorageManager.getSavedMessagesList());
    }
}