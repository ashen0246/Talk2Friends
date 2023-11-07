package com.example.talk2friends;

import java.util.ArrayList;

public class Meeting {
    String meetingID;
    String topic;
    String location;
    String time;
    boolean isAttending;
    ArrayList<String> attendants;

    // No-argument constructor required for Firebase
    public Meeting() {
        // Default constructor required for calls to DataSnapshot.getValue(Meeting.class)
    }

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

    public String getMeetingID() {
        return meetingID;
    }

    public void setMeetingID(String id) {
        this.meetingID = id;
    }

    public boolean isAttending() {
        return isAttending;
    }

    public ArrayList<String> getAttendants() {
        if (this.attendants == null) {
            this.attendants = new ArrayList<>();
        }
        return this.attendants;
    }


    public void addAttendant(String email) {
        if (this.attendants == null) {
            this.attendants = new ArrayList<>();
        }
        if (!this.attendants.contains(email)) {
            this.attendants.add(email);
        }
    }

    public void removeAttendant(String userEmail) {
        if (this.attendants != null) {
            this.attendants.remove(userEmail);
        }
    }

}
