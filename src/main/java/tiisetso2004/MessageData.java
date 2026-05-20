package tiisetso2004;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageData {

    //ensure message contents or metadata cannot be changed by declaring fields as final.
    @JsonProperty("message")
    private final String message;

    @JsonProperty("messageHash")   
    private final String messageHash;

    @JsonProperty("recipient")
    private final String recipient;

    @JsonProperty("Message ID")
    private final String messageID;

    @JsonCreator
    public MessageData(
        @JsonProperty("message") String message, 
        @JsonProperty("messageHash") String messageHash, 
        @JsonProperty("recipient") String recipient, 
        @JsonProperty("Message ID") String messageID) {

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