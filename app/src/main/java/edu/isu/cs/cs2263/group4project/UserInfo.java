package edu.isu.cs.cs2263.group4project;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;

public class UserInfo {
    private String firstName;
    private String lastName;
    private String username;
    private String biography;
    private String email;
    private byte[] hashedPassword;
    private String pathToPicture;

    public UserInfo(String username, String firstName, String lastName, String bio, String email, String pathToPicture, String password){
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.biography = bio;
        this.email = email;
        this.pathToPicture = pathToPicture;
        this.hashedPassword = setPassword(password);
    }

    public String getFirstName(){return firstName;}
    public String getLastName(){return lastName;}
    public String getUsername(){return username;}
    public String getBiography(){return biography;}
    public String getEmail(){return email;}
    public String getPathToPicture(){return pathToPicture;}

    private byte[] setPassword(String password){
        // Hash the password here
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        try{
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt);
            byte[] hashedPass = md.digest(password.getBytes(StandardCharsets.UTF_8));
            return hashedPass;
        } catch (Exception NoSuchAlgorithmException){
            return null;
        }
    }

    private byte[] getHashedPassword(){
        return hashedPassword;
    }

    public boolean attemptLogin(String password){
        return false;
    }

}
