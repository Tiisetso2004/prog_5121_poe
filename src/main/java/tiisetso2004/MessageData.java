package tiisetso2004;

public class MessageData {

    private final String message, messageHash, sender, recepient; //ensure message contents or metadata cannot be changed
    private final int messageID;

    public MessageData(String message, String messageHash, String sender, String recipient, int messageID) {
        this.message = message;
        this.messageHash = messageHash;
        this.messageID = messageID;
        this.sender = sender;
        this.recepient = recipient;
    }
    //getters only to retrieve values for message metadata

    public String getMessage() {
        return message;
    }

    public String getMessageHash() {
        return messageHash;
    }

    public int getMessageID() {
        return messageID;
    }

    public String getSender() {
        return sender;
    }

    public String getRecepient() {
        return recepient;
    }

    public void WriteMessage() {

    }

    
    
}
