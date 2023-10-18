package com.example.talk2friends;

import java.util.ArrayList;

public class Meeting {
    String meetingID;
    String topic;
    String location;
    String time;
    boolean isAttending;
    ArrayList<String> attendants;

    public Meeting(String meetingID, String topic, String location, String time, boolean isAttending, ArrayList<String> attendants){
        this.meetingID = meetingID;
        this.topic = topic;
        this.location = location;
        this.time = time;
        this.isAttending = isAttending;
        this.attendants = attendants;
    }

    public String getTopic() {
        return topic;
    }

    public String getLocation() {
        return location;
    }

    public String getTime() {
        return time;
    }

    public boolean isAttending() {
        return isAttending;
    }

    public ArrayList<String> getAttendants() {
        return attendants;
    }
}
