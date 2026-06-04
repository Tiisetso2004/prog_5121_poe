package tiisetso2004;

public class Message {

    //ensure message contents or metadata cannot be changed by declaring fields as final.
    private final String message;
    private final String messageHash;
    private final String recipient;
    private final String messageID;

    public Message(String message, String messageHash, String recipient, String messageID) {
        this.message = message;
        this.messageHash = messageHash; //auto generated
        this.messageID = messageID; //auto generated
        this.recipient = recipient;
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

    public String getRecipient() {
        return recipient;
    }   
}