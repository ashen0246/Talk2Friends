package com.example.talk2friends;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class User {
    String name;
    String bday;
    boolean isStudent;
    boolean isProficient;
    boolean sports;
    boolean gaming;
    boolean music;
    boolean art;
    ArrayList<String> friends;
    ArrayList<String> incomingFriendRequests;


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

    public User(String name, String bday, boolean isStudent, boolean isProficient,
                boolean sports, boolean gaming, boolean music, boolean art) {
        this.name = name;
        this.bday = bday;
        this.isStudent = isStudent;
        this.isProficient = isProficient;
        this.friends = new ArrayList<String>();
        friends.add("test");
        this.incomingFriendRequests = new ArrayList<String>();
        incomingFriendRequests.add("test");
        this.sports = sports;
        this.gaming = gaming;
        this.music = music;
        this.art = art;
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

    public void setFriends(ArrayList<String> friends) {
        this.friends = friends;
    }

    public void setIncomingFriendRequests(ArrayList<String> incomingFriendRequests) {
        this.incomingFriendRequests = incomingFriendRequests;
    }

    public void acceptFriend(String email) {
        friends.add(email);
        incomingFriendRequests.remove(email);
    }

    public ArrayList<String> getIncomingFriendRequests() {
        return incomingFriendRequests;
    }

    public boolean isSports() {
        return sports;
    }

    public void setSports(boolean sports) {
        this.sports = sports;
    }

    public boolean isGaming() {
        return gaming;
    }

    public void setGaming(boolean gaming) {
        this.gaming = gaming;
    }

    public boolean isMusic() {
        return music;
    }

    public void setMusic(boolean music) {
        this.music = music;
    }

    public boolean isArt() {
        return art;
    }

    public void setArt(boolean art) {
        this.art = art;
    }


}
