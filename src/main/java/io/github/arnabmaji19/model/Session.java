package io.github.arnabmaji19.model;

public class Session {
    //Singleton class for Managing Sessions
    private static Session instance = new Session();

    private String username;
    private String email;
    private boolean isAvailable;

    private Session(){
        isAvailable = false;
    }

    public void createSession(String username, String email) {
        this.username = username;
        this.email = email;
        isAvailable = true;
    }


    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }


    public static Session getInstance() {
        return instance;
    }

    public boolean isAvailable(){
        return isAvailable;
    }
}
