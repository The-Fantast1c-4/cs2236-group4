package edu.isu.cs.cs2263.group4project;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;

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
        setPassword(password);
    }

    public String getFirstName(){return firstName;}
    public String getLastName(){return lastName;}
    public String getUsername(){return username;}
    public String getBiography(){return biography;}
    public String getEmail(){return email;}
    public String getPathToPicture(){return pathToPicture;}

    public void setPathToPicture(String pathToPicture){this.pathToPicture=pathToPicture;}
    public void setBiography(String biography) {this.biography = biography;}


    private byte[] hashPassword(String password){
        // Hash the password here

        try{
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] hashedPass = md.digest(password.getBytes(StandardCharsets.UTF_8));
            return hashedPass;
        } catch (Exception NoSuchAlgorithmException){
            return null;
        }
    }

    private byte[] getHashedPassword(){
        return hashedPassword;
    }

    public void setPassword(String password){
        this.hashedPassword = hashPassword(password);
    }

    public boolean attemptLogin(String password){
        byte[] attempt = hashPassword(password);
        return Arrays.equals(getHashedPassword(), attempt);
    }

}
