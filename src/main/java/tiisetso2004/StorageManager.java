package tiisetso2004;

import java.util.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;

public class StorageManager {

    // Lists tracking application state in RAM
    private static List<User> userLib = new ArrayList<>();
    private static List<Message> temporaryMessages = new ArrayList<>();
    private static List<Message> allMessages =  new ArrayList<>();
    private static List<Message> sentMessages = new ArrayList<>();
    private static List<Message> deletedMessages = new ArrayList<>();

    // File pointers on disk
    private static final File storedMessagesJson = new File("stored_messages.json");
    private static final File deletedMessagesJson = new File("deleted_messages.json");
    private static final File sentMessagesJson = new File("sent_messages.json");

    private StorageManager () {
        // Private constructor prevents instantiation of this utility class
    }

    // --- User Related Operations ---
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

    public static List<User> getUsers() {
        return Collections.unmodifiableList(userLib);
    }

    public static void deleteUser(User user) {
        userLib.remove(user);
    }

    // --- List Getters & Setters ---
    public static List<Message> getAllMessages() { return allMessages; }
    public static List<Message> getSentMessages() { return sentMessages; }
    public static List<Message> getTemporaryMessages() { return temporaryMessages; }
    public static List<Message> getDeletedMessages() { return deletedMessages; }

    public static void setSentMessages(List<Message> source) {
        sentMessages = (source != null) ? source : new ArrayList<>();
    }

    public static void setAllMessages(List<Message> source) {
        allMessages = (source != null) ? source : new ArrayList<>();
    }
    public static void setDeletedMessages(List<Message> source) {
        deletedMessages = (source != null) ? source : new ArrayList<>();
    }

    // --- File Getters ---
    public static File getStoredMessagesJson() {
        return storedMessagesJson;
    }
    public static File getSentMessagesJson() {
        return sentMessagesJson;
    }
    public static File getDeletedMessagesJson() {
        return deletedMessagesJson;
    }

    // --- Utility Operations ---
    public static int getMessageCount(List<Message> list) {
        return (list != null) ? list.size() : 0;
    }

    public static List<Message> getUnmodifiedMessages(List<Message> list) {
        return Collections.unmodifiableList(list != null ? list : new ArrayList<>());
    }

    public static void captureMessageDraft(Message obj, List<Message> list) {
        if (list != null && MessageHandler.messageObjectFieldValidator(obj)) {
            list.add(obj);
        } else {
            System.err.println("Failed to capture message draft");
        }
    }

    public static void printMessages(List<Message> list) {
        if (list != null && !list.isEmpty()) {
            for (Message message : list) {
                if (MessageHandler.messageObjectFieldValidator(message)) {
                    System.out.println(message.toString());
                }
            }
            System.out.printf("%nTotal Messages: %d%n", getMessageCount(list));
        } else {
            System.err.println("Error: List was empty or null, could not print messages");
        }
    }

    // --- Core Persistence Engines ---

    /**
     * Deletes a message from a given queue and moves it to the deleted messages archive.
     * Updates BOTH affected JSON files on disk.
     */
    public static void deleteMessage(String hash, List<Message> queue, File sourceFile) {
        if (queue == null || queue.isEmpty() || hash == null) {
            System.err.println("Failed to delete: Queue is empty/null or invalid hash.");
            return;
        }

        boolean itemRemoved = false;
        ListIterator<Message> iterator = queue.listIterator();

        while (iterator.hasNext()) {
            Message obj = iterator.next();

            if (MessageHandler.messageObjectFieldValidator(obj) && hash.equals(obj.getMessageHash())) {
                iterator.remove();
                getDeletedMessages().add(obj);
                itemRemoved = true;
                System.out.println("Message successfully removed from active queue.");
            }
        }

        if (itemRemoved) {
            // Save BOTH the updated source list AND the updated deleted archive list
            storeInJson(queue, sourceFile);
            storeInJson(getDeletedMessages(), getDeletedMessagesJson());
            System.out.println("Disk architecture successfully updated post-deletion.");
        } else {
            System.err.println("Deletion failed: Message hash not found in the list.");
        }
    }

    /**
     * Stores a message dynamically to a targeted memory list and its respective disk file.
     */
    public static boolean storeMessage(Message obj, List<Message> sourceList, File targetFile) {
        if (sourceList == null || targetFile == null) {
            System.err.println("Error: Target storage components cannot be null.");
            return false;
        }

        if (MessageHandler.messageObjectFieldValidator(obj)) {
            sourceList.add(obj);
            storeInJson(sourceList, targetFile);
            System.out.println("Message successfully stored to list and disk.");
            return true;
        }
        System.err.println("Error storing message: Field validation failed.");
        return false;
    }

    public static void storeInJson(List<Message> messages, File filename) {
        if (filename == null || messages == null) return;

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(messages);

        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(jsonString);
        } catch (IOException e) {
            System.err.println("Failed to save messages to JSON file: " + filename.getName());
        }
    }

    public static List<Message> loadMessagesFromJson(File filename) {
        List<Message> loadedMessages = new ArrayList<>();

        if (filename != null && filename.exists()) {
            Gson gson = new GsonBuilder().create();
            try (FileReader reader = new FileReader(filename)) {
                Type generic = new TypeToken<ArrayList<Message>>() {}.getType();
                List<Message> parsed = gson.fromJson(reader, generic);
                if (parsed != null) {
                    loadedMessages = parsed;
                }
            } catch (IOException e) {
                System.err.println("Failed to read file: " + filename.getName());
            }
        }
        return loadedMessages;
    }

    /**
     * Boots up the system state cleanly across sessions
     */
    public static void loadAllJFromJson() {
        setAllMessages(loadMessagesFromJson(getStoredMessagesJson()));
        setSentMessages(loadMessagesFromJson(getSentMessagesJson()));
        setDeletedMessages(loadMessagesFromJson(getDeletedMessagesJson()));
    }
}