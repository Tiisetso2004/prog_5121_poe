package tiisetso2004;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StorageManager {

    //adding all users created during instance of program to one static list.
    private static List <User> userLib = new ArrayList<>();
    private static List <MessageData> savedMessagesList =  new ArrayList<>();
    private static List <MessageData> sentMessagesList = new ArrayList<>();

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
        return savedMessagesList;
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
    public static boolean captureMessageDraft(MessageData obj, List <MessageData> list) {
        if (obj != null) {
            list.add(obj);
            return true;
        } else {
            return false;
        }        
    }

    public static String printMessages(List <MessageData> list) {
        StringBuilder messages = new StringBuilder();
        for (MessageData messageData : list) {
            messages.append(messageData.getMessage()).append("\n");
        }
        return messages.toString().trim(); //trim the trailing whitespace
    }

    public static void deleteMessage(MessageData obj, List <MessageData> list) {
        list.remove(obj);
    }

    public static void storeMessage(MessageData obj) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String objString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            System.out.println("Failed to save JSON file");
            e.printStackTrace();
        }
        
        try {
            Path outputPath = Paths.get("message_list.json");
            // write the object as JSON bytes to the file
            Files.write(outputPath, mapper.writeValueAsBytes(obj));
        } catch (Exception e) {
            // Failed to write JSON to file
            System.out.println("Failed to write to JSON file");
            e.printStackTrace();
        }
    }
}