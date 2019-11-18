package io.github.arnabmaji19.model;

import org.bson.types.ObjectId;

public class Session {
    //Singleton class for Managing Sessions
    private static Session instance = new Session();

    private ObjectId userId;
    private String username;
    private String email;
    private boolean isAvailable;

    private Session(){
        isAvailable = false;
    }

    public void createSession(ObjectId userId , String username, String email) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        isAvailable = true;
    }

    public ObjectId getUserId() {
        return userId;
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
