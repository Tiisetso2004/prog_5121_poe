package tiisetso2004;

//TODO: make this metadata generator class after clearing.
public class Message {

    public Message() {
    }

    public final static String GenerateMessageID(int length) {
        String charset = "4682519730"; //define characterset at random.
        StringBuilder sb = new StringBuilder(length); //use string builder for a mutable string object.

        for (int i = 0; i < length; i++) {
            int index = (int) (charset.length() * Math.random()); //Math.Random returns 0.0 --> 1.0 cast it to int to get a valid whole number index.    
            sb.append(charset.charAt(index)); //add a randomly selected character to the string builder object.            
        }
        final String ID = sb.toString(); //convert generated id to a string
        return ID;  
    } 

    //checks if the ID exceeds the 10 character limit.
    public static boolean checkMessageID(String ID) {
        boolean validId = ID.length() == 10 && Validator.nullCheck(ID);
        return validId;    
    }

    //first two num of mID (:) and message num (:) first and last words of the message.
    //colons are delimiters.
    public static String createMessageHash(String messageID, int messageNum, String message) {
        String[] textArray = message.trim().split("\\s+"); //turn message into array

        StringBuilder mHash = new StringBuilder();
        mHash.append(messageID,0,2 ).append(":") //first two numbers
             .append(messageNum).append(":") //append the entire string
             .append(textArray[0])
             .append(textArray[textArray.length-1]); //first and last word of message appended together
        String messageHash = mHash.toString();

        return messageHash;
    }

    //----------UI Caller----------//
    //send, discard or store message options
    public static void SendMessage() {
        QuickChat chat = new QuickChat();
        chat.draftMessage();
    } 

    //prints all the messages from the start of the program
    public static String printMessages() {
        System.out.println(StorageManager.printMessages(StorageManager.getSavedMessagesList()));
        return StorageManager.printMessages(StorageManager.getSavedMessagesList());
    }

    //Returns no of total messages 'sent' during program
    public static int returnTotalMessages() {
        System.out.println("Total messages sent:"+ StorageManager.getMessageCount(StorageManager.getSentMessagesList()));
        return StorageManager.getMessageCount(StorageManager.getSentMessagesList());
    }
}