package tiisetso2004;

import java.util.*;

public class StorageManager {

    //adding all users created during instance of program to one static list.
    private static List <User> userLib = new ArrayList<>();
    private static List <Message> temporaryMessages = new ArrayList<>();
    private static List <Message> allMessages =  new ArrayList<>();
    private static List <Message> sentMessages = new ArrayList<>();
    private static List <Message> deletedMessages = new ArrayList<>();

    private static File storedMessagesJson = new File("stored_messages.json");
    private static File deletedMessagesJson = new File("deleted_messages.json");
    private static File sentMessagesJson = new File("sent_messages.json");

    private StorageManager () {
    }
    //check if user objects are null
    public static void addUser(User user) {
        if (user != null) {
            userLib.add(user);
        } else {
            return false;
        }
    }

    public static int getUserCount() {
        return userLib.size();
    }

    public static List <User> getUsers() {  
        return Collections.unmodifiableList(userLib);
    }

    public static void deleteUser(User user) {
        userLib.remove(user);
    }

    public static List <Message> getAllMessages() {
        return allMessages;
    }

    public static void setSentMessagesJson(List<Message> source) {
        sentMessages = source;
    }

    public static void setAllMessages (List<Message> source) {
        allMessages = source;
    }

    public static void setDeletedMessages (List<Message> source) {
        deletedMessages = source;
    }

    public static List <Message> getSentMessages() {
        return sentMessages;
    }

    public static List <Message> getTemporaryMessages() {
        return temporaryMessages;
    }

    public static List <Message> getDeletedMessages() {
        return deletedMessages;
    }

    public static int getMessageCount(List <Message> list) {
        return list.size();
    }

    public static File getStoredMessagesJson() {
        return storedMessagesJson;
    }

    public static File getSentMessagesJson() {
        return sentMessagesJson;
    }

    public static File getDeletedMessagesJson() {
        return deletedMessagesJson;
    }

    public static List <Message> getUnmodifiedMessages(List <Message> list) {
        return Collections.unmodifiableList(list);
    }

    //reusing same logic store messages temporarily
    public static void captureMessageDraft(Message obj, List <Message> list) {
        if (MessageHandler.messageObjectFieldValidator(obj)) {
            list.add(obj);
        } else {
            System.err.println("Failed to capture message");
        }
    }

    public static void printMessages(List <Message> list) {
        if(!list.isEmpty()) {
            for (Message message : list) {
                if (MessageHandler.messageObjectFieldValidator(message)) {
                    System.out.println(message.toString());
                }
            }
            System.out.printf("%nTotal Messages:%s ", getMessageCount(list));
        }  else {
            System.err.println("Error: list was empty could not print messages");
        }
    }

    public static void deleteMessage(String hash, List <Message> queue) {
        if(!queue.isEmpty()) {
            ListIterator <Message> iterator = queue.listIterator();

            while (iterator.hasNext()) {
                Message obj = iterator.next();

                if(MessageHandler.messageObjectFieldValidator(obj) && hash.equals(obj.getMessageHash())) {
                    iterator.remove();
                    getDeletedMessages().add(obj);
                    storeInJson(getDeletedMessages(),getDeletedMessagesJson());
                    System.out.println("Message successfully deleted");
                }
            }
        }
        System.err.println("Failed to delete, message: Operation on empty list was attempted");
    }

    public static boolean storeMessage(Message obj, List <Message> list) {
        if(MessageHandler.messageOperationHandler(obj, list)) {
            list.remove(obj);
            System.out.println("MetadataGenerator successfully stored to list");
            return true;
        }
        System.err.println("Error storing message to list");
        return false;
    }
}