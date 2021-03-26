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

public class IOManager {

    private static String getUserInfoPath(){
        // Load the settings file, then pull the user info path from it
        Settings settings = loadSettings();
        return settings.getUserDirectory();
    }

    private static String getSysLogPath(){
        // Load the settings file, then pull the system log path from it
        Settings settings = loadSettings();
        return settings.getLogLocation();
    }

    private static String getUserDataPath(){
        Settings settings = loadSettings();
        return settings.getUserDataDirectory();
    }

    public static boolean writeSettings(Settings settings){
        Gson gson = new Gson();
        String jsonOut = gson.toJson(settings);
        try {
            Files.writeString(Paths.get("./config/config.json"), jsonOut);
            return true;
        } catch(IOException ex){
            ex.printStackTrace();
        }
        return false;
    }

    public static Settings loadSettings(){
        String jsonIn = "";
        try {
            jsonIn = Files.readString(Paths.get("./config/config.json"));
        } catch (IOException e){
            e.printStackTrace();
        }
        Gson gson = new Gson();
        return gson.fromJson(jsonIn, Settings.class);
    }

    public static void logSysInfo(String info){
        // Check to see if system information should be logged
        if (!whetherToLog()){
            return;
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String logged = dtf.format(now) + ";    " + info + "\n";
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

    public static boolean whetherToLog(){
        Settings settings = loadSettings();
        return settings.isLogSystemInfo();
    }

    public static void saveUserMacro(ArrayList<UserInfo> infos){
        String path = getUserInfoPath();
        Gson gson = new Gson();
        String jsonOut = gson.toJson(infos);

        try {
            Files.writeString(Paths.get(path), jsonOut);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static ArrayList<UserInfo> loadUserMacro(){
        String path = getUserInfoPath();
        String json = "";
        try {
            json = Files.readString(Paths.get(path));
        } catch (IOException e){
            e.printStackTrace();
        }

        Gson gson = new Gson();
        Type arrayListInfos = new TypeToken<ArrayList<UserInfo>>() {}.getType();
        return gson.fromJson(json, arrayListInfos);
    }

    public static User loadUser(String username, String password){
        String path = getUserDataPath();
        String json = "";
        try {
            json = Files.readString(Paths.get(path + username + ".json"));
        } catch (IOException e) {
            System.out.println("User " + username + " does not exist");
        }
        Gson gson = new Gson();
        User user = gson.fromJson(json, User.class);
        if (!user.attemptLogin(password)){
            return null;
        }
        return user;
    }

    public static void saveUser(User user){
        String path = getUserDataPath();
        Gson gson = new Gson();
        String jsonOut = gson.toJson(user);

        try {
            Files.writeString(Paths.get(path + user.getUserInfo().getUsername() + ".json"), jsonOut);
        } catch (IOException e){
            e.printStackTrace();
        }
        return;
    }

    public static void main(String[] args){
        UserInfo myInfo = new UserInfo("spierob2", "Robbie", "Spiers", "dumb college teen", "spierob2@isu.edu", "path", "hereismypassword");
        User me = new User(myInfo);
        UserInfo hisInfo = new UserInfo("mistryman", "Shivank", "Mistry", "info major", "shivu@gmail.com", "path2", "hereishispassword");
        User shivu = new User(hisInfo);

        ArrayList<UserInfo> infos = new ArrayList<>();
        infos.add(myInfo);
        infos.add(hisInfo);
        saveUserMacro(infos);
        saveUser(me);
        saveUser(shivu);

        User user1 = loadUser("spierob2", "hereismypassword");
        User user2 = loadUser("spierob2", "wrongpassword");
        ArrayList<UserInfo> loaded = loadUserMacro();
    }
}
