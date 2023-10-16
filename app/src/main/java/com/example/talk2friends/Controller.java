package com.example.talk2friends;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Controller {
    String email;

    public Controller(String email){
        this.email = email;
    }

    //for login page
    public boolean isValidLogin(String email, String password){
        return true;
    }

    //for verification page
    public boolean isValidCode(String code){
        return true;
    }

    //for create user page
    //affiliation = true = student
    //proficiency = true = native speaker
    public void createUser(String name, String bday, Boolean Affiliation, Boolean proficiency){

    }

    //for profile page

    //returns [name, bday, student, proficient]
    public String[] getProfileInfo() {
        return null;
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
