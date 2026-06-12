package tiisetso2004;

public class Message {

    //ensure message contents or metadata cannot be changed by declaring fields as final.
    private final String message;
    private final String messageHash;
    private final String recipient;
    private final String sender;
    private final String messageID;

    public Message(String message, String messageHash, String sender, String recipient, String messageID) {
        this.message = message;
        this.messageHash = messageHash; //auto generated
        this.sender = sender;
        this.messageID = messageID; //auto generated
        this.recipient = recipient;
    }
    
    //getters only to retrieve values for message metadata
    public String getMessage() {
        return message;
    }

    public int getMessageLength() {
        return getMessage().length();
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

    public String getSender() {
        return sender;
    }

    @Override
    public String toString() {
        return String.format("%n<---------- Retrieved Message ----------> %n%nSender: %s%nRecipient: %s%nHash: %s%nMessageID: %s%nMessage: %n%s%n",
                getSender(),
                getRecipient(),
                getMessageHash(),
                getMessageID(),
                getMessage());
    }
}