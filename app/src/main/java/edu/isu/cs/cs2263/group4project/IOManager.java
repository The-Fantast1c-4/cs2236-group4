package edu.isu.cs.cs2263.group4project;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
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
        return;
    }

    public static boolean whetherToLog(){
        Settings settings = loadSettings();
        return settings.isLogSystemInfo();
    }

    public ArrayList<UserInfo> loadUserMacro(){
        return null;
    }

    public User loadUser(String username, String password){
        return null;
    }

    public void saveUser(User user){
        return;
    }

    public static void main(String[] args){

    }
}
