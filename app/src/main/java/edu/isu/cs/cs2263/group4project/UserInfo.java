package edu.isu.cs.cs2263.group4project;

public class UserInfo {
    private String firstName;
    private String lastName;
    private String username;
    private String biography;
    private String email;
    private char[] hashedPassword;
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

    private char[] setPassword(String password){
        // Hash the password here
        return null;
    }

    private char[] getHashedPassword(){
        return hashedPassword;
    }

    public boolean attemptLogin(String password){
        return false;
    }

}
