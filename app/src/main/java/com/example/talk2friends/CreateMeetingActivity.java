package com.example.talk2friends;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CreateMeetingActivity extends AppCompatActivity {

    private EditText topicEditText;
    private EditText timeEditText;
    private EditText locationZoomEditText;
    private Button createButton;
    private DatabaseReference databaseMeetings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_meetings_page);

        // Initialize Firebase components
        databaseMeetings = FirebaseDatabase.getInstance().getReference("meetings");

        topicEditText = findViewById(R.id.topicEditText);
        timeEditText = findViewById(R.id.timeEditText);
        locationZoomEditText = findViewById(R.id.locationZoomEditText);
        createButton = findViewById(R.id.createButton);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createMeeting();
            }
        });
    }

    private void createMeeting() {
        String topic = topicEditText.getText().toString().trim();
        String time = timeEditText.getText().toString().trim();
        String locationZoom = locationZoomEditText.getText().toString().trim();

        if (!topic.isEmpty() && !time.isEmpty() && !locationZoom.isEmpty()) {
            String id = databaseMeetings.push().getKey();
            boolean isAttending = true;
            ArrayList<String> attendants; // NEED TO SET ATTENDANTS TO USER EMAIL WHO CREATED MEETING

            Meeting meeting = new Meeting(id, topic, locationZoom, time, isAttending, attendants);
            // Meeting(String meetingID, String topic, String location, String time, boolean isAttending, ArrayList<String> attendants)
            databaseMeetings.child(id).setValue(meeting);

            // Clear the input
            topicEditText.setText("");
            timeEditText.setText("");
            locationZoomEditText.setText("");

            // Handle the success scenario (e.g., displaying a message to the user)

        } else {
            // Handle the error scenario (e.g., informing the user to fill in all fields)
        }
    }
}

/*
class Meeting {
    public String id;
    public String topic;
    public String time;
    public String locationZoom;

    public Meeting(String id, String topic, String time, String locationZoom) {
        this.id = id;
        this.topic = topic;
        this.time = time;
        this.locationZoom = locationZoom;
    }
    // Getters and setters if necessary
}
*/

