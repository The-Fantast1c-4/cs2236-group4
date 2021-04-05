package edu.isu.cs.cs2263.group4project;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

// The IOManager is a static Singleton which allows for a user to save and load data from persistent storage

public class IOManager {

    // Loads the path to the user information (a.k.a. list of UserInfo, a.k.a. list of user profile information)
    private static String getUserInfoPath(){
        // Load the settings file, then pull the user info path from it
        Settings settings = loadSettings();
        return settings.getUserDirectory();
    }

    // Loads the path to the system logger
    private static String getSysLogPath(){
        // Load the settings file, then pull the system log path from it
        Settings settings = loadSettings();
        return settings.getLogLocation();
    }

    // Loads the path to where the user's data will be stored
    private static String getUserDataPath(){
        Settings settings = loadSettings();
        return settings.getUserDataDirectory();
    }

    // Writes the settings to persistent storage
    public static boolean writeSettings(Settings settings){
        // Serialize the settings using Gson
        Gson gson = new Gson();
        String jsonOut = gson.toJson(settings);

        // Attempts to write the settings to this location (settings are ALWAYS in this location)
        try {
            Files.writeString(Paths.get("./config/config.json"), jsonOut);
            return true;
        } catch(IOException ex){
            ex.printStackTrace();
        }
        return false;
    }

    // Loads the settings from persistent data
    public static Settings loadSettings(){
        // Attempt to deserialize the Settings object
        String jsonIn = "";
        try {
            jsonIn = Files.readString(Paths.get("./config/config.json"));
        } catch (IOException e){
            e.printStackTrace();
        }
        Gson gson = new Gson();
        return gson.fromJson(jsonIn, Settings.class);
    }

    // Logs a line of information to the persistent logger file
    public static void logSysInfo(String info){
        // Check to see if system information should be logged
        if (!whetherToLog()){
            return;
        }

        // Specifies the date and time of logging
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String logged = dtf.format(now) + ";    " + info + "\n";

        // Attempts to append to the logger file, but if the file does not exist, it creates this file
        try {
            Files.write(Paths.get(getSysLogPath() + "syslog.txt"), logged.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            File sysInfoFile = new File("syslog.txt");
            try {
                sysInfoFile.createNewFile();
                Files.write(sysInfoFile.toPath(), logged.getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e2){}
        }
    }

    // Checks whether or not the user would like to log information
    public static boolean whetherToLog(){
        Settings settings = loadSettings();
        return settings.isLogSystemInfo();
    }

    // Saves the array of user information (a.k.a. profile information, no lists or other data)
    public static void saveUserMacro(ArrayList<UserInfo> infos){
        // Get the desired location and serialize the data
        String path = getUserInfoPath();
        Gson gson = new Gson();
        String jsonOut = gson.toJson(infos);

        // Attempt to write to this file
        try {
            Files.writeString(Paths.get(path), jsonOut);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    // Load the array of user information (profile information)
    public static ArrayList<UserInfo> loadUserMacro(){
        // Pull json string from persistent data file
        String path = getUserInfoPath();
        String json = "";
        try {
            json = Files.readString(Paths.get(path));
        } catch (IOException e){
            e.printStackTrace();
        }

        // Deserialize the data. Note that deserialization of an ArrayList is more complicated, and it is shown below
        Gson gson = new Gson();
        Type arrayListInfos = new TypeToken<ArrayList<UserInfo>>() {}.getType();
        return gson.fromJson(json, arrayListInfos);
    }

    // Saves only a specific user to the array of userInformation, rather than the whole array at once
    public static void saveUserMacro(UserInfo info){
        // Step 1: Attempt to load an existing array from persistent storage
        ArrayList<UserInfo> macros = null;
        try{
            macros = loadUserMacro();
            if (macros == null) {
                macros = new ArrayList<>();     // If unsuccessful, create a new array
            }
        } catch (Exception e) {
            macros = new ArrayList<>();
        }
        // Step 2: Remove all duplicate elements of the array
        // In other words, we don't want two different users with the same username
        macros.removeIf(user -> user.getUsername().equals(info.getUsername()));
        // Step 3: Add the new user to the list, and save the whole array using the other method
        macros.add(info);
        saveUserMacro(macros);

    }

    // Load a specified user from persistent storage, including all their data
    public static StandardUser loadStandardUser(String username, String password){
        // Deserialize the specific StandardUser
        String path = getUserDataPath();
        String json = "";
        try {
            json = Files.readString(Paths.get(path + username + ".json"));
        } catch (IOException e) {
            System.out.println("User " + username + " does not exist");
        }
        Gson gson = new Gson();
        StandardUser user = gson.fromJson(json, StandardUser.class);

        // Make sure that the username and password match, or else do not allow them to see the user
        if (authenticate(username, password)) {
            return user;
        }

        return null;
    }

    // Checks the saved UserInfo to ensure that the username and password allow logging in
    private static boolean authenticate(String username, String password) {
        // Step 1: Find the UserInfo that corresponds to the username
        ArrayList<UserInfo> infos = loadUserMacro();
        UserInfo myInfo = null;
        for (UserInfo info : infos) {
            if (info.getUsername().equals(username)){
                myInfo = info;
            }
        }
        if (myInfo == null) {
            return false;
        }

        // Step 2: Try to log in
        return myInfo.attemptLogin(password);
    }

    // Attempt to load the Admin user from persistent storage
    public static Admin loadAdmin(String password){
        // Step 1: Deserialize the Admin
        String path = getUserDataPath();
        String json = "";
        try {
            json = Files.readString(Paths.get(path + "admin.json"));
        } catch (IOException e) {
            System.out.println("Admin does not exist");
        }
        Gson gson = new Gson();
        Admin user = gson.fromJson(json, Admin.class);

        // Step 2: Make sure that the password is correct and they have access
        if (authenticate("admin", password)){
            return user;
        }
        return null;
    }

    // Save a user's data to persistent storage
    public static void saveUser(User user){
        // Get the desired location
        String path = getUserDataPath();

        // Serialize the data
        Gson gson = new Gson();
        String jsonOut = gson.toJson(user);

        // Write the data to persistent storage
        try {
            Files.writeString(Paths.get(path + user.getUserInfo().getUsername() + ".json"), jsonOut);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
