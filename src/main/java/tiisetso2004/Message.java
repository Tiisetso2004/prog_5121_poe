package tiisetso2004;

public class Message {

    int messsagesSent;

    public Message() {
    }

    public static String GenerateMessageID(int length) {
        String charset = "4682519730"; //define characterset at random.
        StringBuilder sb = new StringBuilder(length); //use string builder for a mutable string object.

        for (int i = 0; i < length; i++) {
            int index = (int) (charset.length() * Math.random()); //Math.Random returns 0.0 --> 1.0 cast it to int to get a valid whole number index.    
            sb.append(charset.charAt(index)); //add a randomly selected character to the string builder object.
        }
        //return it as string matching method return type.
        return sb.toString();
    } 

    //checks if the ID exceeds the 10 character limit.
    public boolean checkMessageID(String ID) {
        if(Validator.nullCheck(ID) || ID.length() > 10) {
            System.err.println("Ivalid ID: The ID is empty or exceeds 10 character limit");
            return false;
        } else {
            System.out.println("ID sucessfully validated");
            return true;
        }
    }

    //reuse static helper defined in Validator class for cellphone number validation.
    public String checkRecepientCell(String cellphoneNumber) {
        if(!Validator.checkCellphoneNumber(cellphoneNumber)) {
            System.err.println(MessageLog.getCellphoneErrorMessage());
            return MessageLog.getCellphoneErrorMessage();
        } else {
            System.out.println(MessageLog.getCellphoneErrorMessage());
            return cellphoneNumber;
        }
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

    //----------UI----------//
    //send, discard or store message options
    public void SendMessage() {
    } 

    //prints all the messages from the start of the program
    public String printMessages() {
        return UserDatabase.printMessages();
    }

    //Returns no of total messages sent
    public int returnTotalMessages() {
        return UserDatabase.getMessageCount();
    }

    //TODO: store message objects in JSON
    public void StoreMessage() {
        
    }
}