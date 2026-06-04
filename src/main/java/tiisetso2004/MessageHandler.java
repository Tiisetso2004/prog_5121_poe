package tiisetso2004;

import java.util.List;

public class MessageHandler {

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
        return ID.length() == 10 && Validator.nullCheck(ID);
    }

    public static boolean messageOperationHandler(Message obj, List <Message> list) {
        boolean isNullField = Validator.nullCheck(obj.getMessage()) && Validator.nullCheck(obj.getMessageHash()) && Validator.nullCheck(obj.getMessageID()) && Validator.nullCheck(obj.getRecipient());
        if(list.isEmpty()||isNullField) {
            System.err.println("Error: attempted operation on empty list, or message object contains null fields");
            return false;
        }
        return true;
    }
}
