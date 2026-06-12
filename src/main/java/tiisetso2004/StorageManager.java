package tiisetso2004;

import java.util.*;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;

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
            System.err.println("Invalid user");
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

    public static boolean storeMessage(Message obj, List <Message> sourceList) {
        if(MessageHandler.messageObjectFieldValidator(obj)) {
            sourceList.add(obj);
            storeInJson(sourceList, getStoredMessagesJson());
            System.out.println("Message successfully stored to list");
            return true;
        }
        System.err.println("Error storing message to list");
        return false;
    }

    public static void storeInJson(List<Message> messages, File filename) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        String jsonString = gson.toJson(messages);
        System.out.println(jsonString);

        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(jsonString);
            System.out.println("Messages successfully written to json");
        } catch (IOException e) {
            System.err.println("Failed to save messages to JSON file");
        } catch (NullPointerException e) {
            System.err.println("Null field detected");
        }
    }

    public static List <Message> loadMessagesFromJson(File filename) {
        Gson gson = new GsonBuilder().create();

        List <Message> loadedMessages = new ArrayList<>();
        try (FileReader reader = new FileReader(filename)) {
            Type generic = new TypeToken <ArrayList<Message>>(){}.getType();
            loadedMessages = gson.fromJson(reader, generic);
        } catch (IOException e) {
            System.err.println("Failed to read file");
        } catch (NullPointerException e) {
            System.err.println("Null field detected");
        }
        return loadedMessages;
    }

    public static void loadAllJFromJson() {
        setAllMessages(loadMessagesFromJson(getStoredMessagesJson()));
        setSentMessagesJson(loadMessagesFromJson(getSentMessagesJson()));
        setDeletedMessages(loadMessagesFromJson(getDeletedMessagesJson()));
    }
}