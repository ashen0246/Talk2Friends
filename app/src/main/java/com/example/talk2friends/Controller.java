package com.example.talk2friends;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

public class Controller {
    //email gotten from auth step can find user data with it
    String email = "";
    User user;
    ArrayList<Meeting> meetings;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    public Controller(String email){
        this.email = email;

        //test
        //createNewUser("b", "jan", true, true);
    }

    //for login page
    public boolean isValidLogin(String email, String password){
        this.email = email;
        return true;
    }

    //for verification page
    public boolean isValidCode(String code){
        return true;
    }

    //for create user page

    public void createNewUser(String name, String bday, boolean isStudent, boolean isProficient){
        user = new User(name, bday, isStudent, isProficient);
        myRef.child("users").child(email).setValue(user);
    }

    //for profile page

    public void getUserFromDatabase() {
        //will auto update as profile gets updated
        myRef.child("users").child(email).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                user = dataSnapshot.getValue(User.class);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                System.out.println("Failed to read value. " + error);
            }
        });
    }

    public User getUser(){
        return user;
    }

    public void setName(String name){

    }
    //may change type from String to something else
    public void setBirthday(String birthday){

    }
    public void setAffiliation(boolean student){

    }
    public void setProficiency(boolean proficient){

    }

    //for meetings page

    public ArrayList<Meeting> getMeetings(){
        return null;
    }

    public void createMeeting(Meeting meeting){

    }

    public void attendMeeting(String meetingID){

    }

    public void unAttendMeeting(String meetingID){

    }

    //for friends page

    //String[name, email] as you display name but need the emails to identify users
    public ArrayList<String[]> getFriends(){
        return null;
    }

    public ArrayList<String[]> getRecommendedFriends(){
        return null;
    }

    public ArrayList<String[]> getIncomingFriendRequests(){
        return null;
    }

    public void inviteFriend(String friendEmail){

    }
    public void sendFriendRequest(String friendEmail){

    }
    public void acceptFriendRequest(){

    }
    public void removeFriend(){

    }
}
