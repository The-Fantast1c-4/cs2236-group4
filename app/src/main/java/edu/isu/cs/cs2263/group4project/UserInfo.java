package edu.isu.cs.cs2263.group4project;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;

// UserInfo is the profile information of every single User
// This also allows users to log in to the program since it holds their password

public class UserInfo {
    // Fields
    private String firstName;
    private String lastName;
    private String username;
    private String biography;
    private String email;
    private byte[] hashedPassword;
    private String pathToPicture;

    // Construct a profile for a new user
    public UserInfo(String username, String firstName, String lastName, String bio, String email, String pathToPicture, String password){
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.biography = bio;
        this.email = email;
        this.pathToPicture = pathToPicture;
        setPassword(password);
    }

    // Basic getter and setter methods
    public String getFirstName(){return firstName;}
    public String getLastName(){return lastName;}
    public String getUsername(){return username;}
    public String getBiography(){return biography;}
    public String getEmail(){return email;}
    public String getPathToPicture(){return pathToPicture;}

    public void setPathToPicture(String pathToPicture){this.pathToPicture=pathToPicture;}
    public void setBiography(String biography) {this.biography = biography;}


    // Privately hashes a given password using SHA-512 encryption (unsalted)
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

    // Returns the hashed password privately to attemptLogin()
    private byte[] getHashedPassword(){
        return hashedPassword;
    }

    // Sets a new password. This method is only accessible to the Admin through the UI
    public void setPassword(String password){
        this.hashedPassword = hashPassword(password);
    }

    // Checks to see if the hashing of the attempted password is equal to the original hashed password
    public boolean attemptLogin(String password){
        byte[] attempt = hashPassword(password);
        return Arrays.equals(getHashedPassword(), attempt);
    }

}
