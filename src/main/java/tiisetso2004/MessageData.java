package tiisetso2004;

public class MessageData {

    //ensure message contents or metadata cannot be changed by declaring fields as final.
    private final String message, messageHash, recepient;
    private final String messageID;

    public MessageData(String message, String messageHash, String recipient, String messageID) {
        this.message = message;
        this.messageHash = messageHash; //auto generated
        this.messageID = messageID; //auto generated
        this.recepient = recipient;
    }
    
    //getters only to retrieve values for message metadata

    public String getMessage() {
        return message;
    }

    public String getMessageHash() {
        return messageHash;
    }

    public String getMessageID() {
        return messageID;
    }

    public String getRecepient() {
        return recepient;
    }

    public void WriteMessage() {

    }   
}