package tiisetso2004;

import java.util.*;

public class StorageManager {

    //adding all users created during instance of program to one static list.
    private static List <User> userLib = new ArrayList<>();
    private static List <Message> allMessagesList =  new ArrayList<>();
    private static List <Message> sentMessagesList = new ArrayList<>();
    private static List <Message> discardedMessagesList = new ArrayList<>();

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

    public static List<Message> getSavedMessagesList() {
        return allMessagesList;
    }

    public static List<Message> getSentMessagesList() {
        return sentMessagesList;
    }

    public static int getMessageCount(List <Message> list) {
        return list.size();
    }

    public static List <Message> getMessages(List <Message> list) {
        return Collections.unmodifiableList(list);
    }

    //reusing same logic store messages temporarily
    public static void captureMessageDraft(Message obj, List <Message> list) {
        if (obj != null) {
            list.add(obj);
        } else {
            System.err.println("Failed to capture message");
        }
    }

    public static String printMessages(List <Message> list) {
        StringBuilder messages = new StringBuilder();
        for (Message message : list) {
            messages.append(message.getMessage()).append("\n");
        }
        return messages.toString().trim(); //trim the trailing whitespace
    }

    public static boolean deleteMessage(Message obj, List <Message> list) {
        if(MessageHandler.messageOperationHandler(obj, list)) {
            list.remove(obj);
            System.out.println("MetadataGenerator successfully deleted");
            return true;
        }
        System.err.println("Failed to delete, message");
        return false;
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