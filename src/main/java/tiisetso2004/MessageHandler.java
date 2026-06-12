package tiisetso2004;

import java.util.List;

public class MessageHandler {
    
    private MessageHandler() {
    }

    public static boolean messageValidator(String m) {
        boolean validMessage = Validator.notNullCheck(m) && m.length()<=250;
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
    //Simulate message functions
    public static boolean sendMessage(Message messObj) {
        return StorageManager.storeMessage(messObj, StorageManager.getAllMessages()) &&
               StorageManager.storeMessage(messObj, StorageManager.getSentMessages());
    }

    public static List<Message> searchForMatches(String searchKey, List <Message> list) {
       List <Message> matchedMessages = new ArrayList<>();

        if(Validator.notNullCheck(searchKey) && !list.isEmpty()) {
            String key = searchKey.trim();

            for(Message message: list) {
                boolean validKey = key.equals(message.getMessageHash())||key.equals(message.getMessageID());

                if (MessageHandler.messageRetrievalHandler(message, list) && validKey) {
                    matchedMessages.add(message);
                }
            }
        }
        return matchedMessages;
    }

    public static Message findLongestMessage(List <Message> targetList) {
       if(targetList == null || targetList.isEmpty()) {
           System.err.println("Error: search operation failed list was empty");
           return null;
       }

       Message longestMessage;

       if (messageObjectFieldValidator(targetList.getFirst())) {
           longestMessage = targetList.getFirst();
       } else {
           System.out.println("Error: search object is null");
           return null;
       }

       for (Message current : targetList) {
           if (messageObjectFieldValidator(current)) {
               if(longestMessage == null) {
                   longestMessage = current;
               }
               else if(current.getMessageLength() >= longestMessage.getMessageLength()) {
                   longestMessage = current;
               }
           }
       }
        return longestMessage;
    }
}
