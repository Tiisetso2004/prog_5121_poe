package tiisetso2004;

import java.util.*;

public class UserDatabase {

    //adding all users created during instance of program to one static list.
    private static List<User> userLib = new ArrayList<>();

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

    public static List<User> getUsers() {
        
        return Collections.unmodifiableList(userLib);
    }
}