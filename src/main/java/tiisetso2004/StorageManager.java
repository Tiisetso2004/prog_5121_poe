package tiisetso2004;

import java.util.*;

public class StorageManager {

    //adding all users created during instance of program to one static list.
    private static List <User> userLib = new ArrayList<>();
    private static List <MessageData> allMessagesList =  new ArrayList<>();
    private static List <MessageData> sentMessagesList = new ArrayList<>();
    private static List <MessageData> discardedMessagesList = new ArrayList<>();

    //check if user objects are null
    public static boolean addUser(User user) {
        if (user != null) {
            userLib.add(user);
            return true;
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

    public static List<MessageData> getSavedMessagesList() {
        return allMessagesList;
    }

    public static List<MessageData> getSentMessagesList() {
        return sentMessagesList;
    }

    public static int getMessageCount(List <MessageData> list) {
        return list.size();
    }

    public static List <MessageData> getMessages(List <MessageData> list) {
        return Collections.unmodifiableList(list);
    }

    //reusing same logic store messages temporarily
    public static void captureMessageDraft(MessageData obj, List <MessageData> list) {
        if (obj != null) {
            list.add(obj);
        } else {
            System.err.println("Failed to capture message");
        }
    }

    public static String printMessages(List <MessageData> list) {
        StringBuilder messages = new StringBuilder();
        for (MessageData messageData : list) {
            messages.append(messageData.getMessage()).append("\n");
        }
        return messages.toString().trim(); //trim the trailing whitespace
    }

    public static boolean deleteMessage(MessageData obj, List <MessageData> list) {
        if(MessageHandler.messageOperationHandler(obj, list)) {
            list.remove(obj);
            System.out.println("MetadataGenerator successfully deleted");
            return true;
        }
        System.err.println("Failed to delete, message");
        return false;
    }

    public static boolean storeMessage(MessageData obj, List <MessageData> list) {
        if(MessageHandler.messageOperationHandler(obj, list)) {
            list.remove(obj);
            System.out.println("MetadataGenerator successfully stored to list");
            return true;
        }
        System.err.println("Error storing message to list");
        return false;
    }
}