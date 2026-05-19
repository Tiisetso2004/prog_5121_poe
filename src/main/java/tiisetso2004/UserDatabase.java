package tiisetso2004;

import java.util.*;

public class UserDatabase {

    //adding all users created during instance of program to one static list.
    private static List <User> userLib = new ArrayList<>();
    private static List <MessageData> messageList =  new ArrayList<>();

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

    //reusing same logic store messages temporarily
    public static boolean captureMessageDraft(MessageData obj) {
        if (obj != null) {
            messageList.add(obj);
            return true;
        } else {
            return false;
        }        
    }

    public static int getMessageCount() {
        return messageList.size();
    }

    public static List <MessageData> getMessages() {
        return Collections.unmodifiableList(messageList);
    }

    public static String printMessages() {
        StringBuilder messages = new StringBuilder();
        for (MessageData messageData : messageList) {
            messages.append(messageData.getMessage()).append("\n");
        }
        return messages.toString().trim(); //trim the trailing whitespace
    }

    public static void deleteMessage(MessageData obj) {
        messageList.remove(obj);
    }
}