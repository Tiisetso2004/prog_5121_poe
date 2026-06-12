package tiisetso2004;

import java.util.List;

public class MessageHandler {
    
    private MessageHandler() {
    }

    public static boolean messageValidator(String m) {
        boolean validMessage = Validator.nullCheck(m) && m.length()<=250;
        if(!validMessage) {
            System.out.println("Message is invalid:\nit exceeds 250 characters or is blank");
            return false;
        } else {
            System.out.println("Message accepted");
            return true;
        }
    }
    //checks if the ID exceeds the 10-character limit.
    public static boolean validateMessageID(String ID) {
        return Validator.notNullCheck(ID) && ID.length() ==10;
    }
    //extra validation for message objects
    public static boolean messageObjectFieldValidator(Message obj) {
       if(obj ==null){
           System.err.println("Error: message object is null");
           return false;
       }
        boolean hasInvalidField =
            !Validator.notNullCheck(obj.getMessage()) ||
            !Validator.notNullCheck(obj.getMessageHash()) ||
            !Validator.notNullCheck(obj.getMessageID()) ||
            !Validator.notNullCheck(obj.getRecipient());
        if(hasInvalidField) {
            System.err.println("Error: message object is null or contains null fields");
            return false;
        }
        return true;
    }
    //use for operations that require retrievals from memory
    public static boolean messageRetrievalHandler(Message obj, List <Message> list) {
        if(!messageObjectFieldValidator(obj) || list == null || list.isEmpty()) {
            System.err.println("Error: attempted operation on empty list");
            return false;
        }
        return true;
    }
}
