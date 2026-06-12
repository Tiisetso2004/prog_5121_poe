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
                            StorageManager.deleteMessage(messageHash, StorageManager.getAllMessages());
                            StorageManager.loadAllJFromJson();
                            break;

                        case "4":
                            StorageManager.loadAllJFromJson();
                            storedMessagesOption();
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

    private void storedMessagesOption() {
        try {
            while (true) {
                System.out.print("""
                
                Choose your option or type 0 to quit:
        
                1. Search for message
                2. Delete message
                3. Print Message report
                4. Search for longest stored message
        
                Choice:\s""");

    //delete temporarily stored message in Array list
    private boolean discardMessage(Message messageObj) {
        return StorageManager.deleteMessage(messageObj, StorageManager.getSavedMessagesList());
    }

                switch (choice) {
                    case "1":
                        System.out.print("\nEnter the message hash, sender, ID, cellphone number to search for: ");
                        String searchKey = qcScan.nextLine();
                        List<Message> messages = MessageHandler.searchForMatches(searchKey, StorageManager.getAllMessages());
                        System.out.println(messages);
                        break;

                    case "2":
                        System.out.print("\nEnter the required message hash to delete the message: ");
                        String hash = qcScan.nextLine();
                        StorageManager.deleteMessage(hash, StorageManager.getAllMessages());
                        StorageManager.loadAllJFromJson();
                        break;

                    case "3":
                        StorageManager.printMessages(StorageManager.getAllMessages());
                        break;

                    case "4":
                        String results = String.format("%nLongest Stored Message: %s%nLongest Sent Message: %s%nLongest Deleted Message: %s%n",
                                MessageHandler.findLongestMessage(StorageManager.getAllMessages()),
                                MessageHandler.findLongestMessage(StorageManager.getSentMessages()),
                                MessageHandler.findLongestMessage(StorageManager.getDeletedMessages()));
                        System.out.println(results);
                        break;

                    default:
                        System.err.println("Invalid option. Enter only from the choices provided above.");
                        break; // Restarts the loop to ask for input again
                }
            }
        } catch (NullPointerException e) {
            System.err.println("Missing input detected");
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid input detected");
        }
    }
}