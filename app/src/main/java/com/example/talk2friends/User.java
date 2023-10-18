package com.example.talk2friends;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class User {
    String name;
    String bday;
    boolean isStudent;
    boolean isProficient;
    ArrayList<String> friends;
    ArrayList<String> incomingFriendRequests;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    public User(){
    }

    public User(String name, String bday, boolean isStudent, boolean isProficient, ArrayList<String> friends, ArrayList<String> incomingFriendRequests) {
        this.name = name;
        this.bday = bday;
        this.isStudent = isStudent;
        this.isProficient = isProficient;
        this.friends = friends;
        this.incomingFriendRequests = incomingFriendRequests;
    }

    public User(String name, String bday, boolean isStudent, boolean isProficient) {
        this.name = name;
        this.bday = bday;
        this.isStudent = isStudent;
        this.isProficient = isProficient;
        this.friends = new ArrayList<String>();
        this.incomingFriendRequests = new ArrayList<String>();
    }

    public User(String name, ArrayList<String> friends, ArrayList<String> incomingFriendRequests) {
        this.name = name;
        this.friends = new ArrayList<String>();
        this.incomingFriendRequests = new ArrayList<String>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBday() {
        return bday;
    }

    public void setBday(String bday) {
        this.bday = bday;
    }

    public boolean isStudent() {
        return isStudent;
    }

    public void setIsStudent(boolean student) {
        isStudent = student;
    }

    public boolean isProficient() {
        return isProficient;
    }

    public void setIsProficient(boolean proficient) {
        isProficient = proficient;
    }

    public ArrayList<String> getFriends() {
        return friends;
    }

    public void acceptFriend(String email) {
    }

    public ArrayList<String> getIncomingFriendRequests() {
        return incomingFriendRequests;
    }

    public void sendFriendRequest(String email) {
    }
}
