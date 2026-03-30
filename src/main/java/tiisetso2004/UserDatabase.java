package tiisetso2004;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserDatabase {

    private static List<User> userLib = new ArrayList<>(); //adding all users created during instance of program to one static list.

    public static boolean addUser(User user) {
        if (user != null) { //check if user objects are null
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