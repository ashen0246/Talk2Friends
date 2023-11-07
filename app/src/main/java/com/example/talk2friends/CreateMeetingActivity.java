package com.example.talk2friends;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CreateMeetingActivity extends AppCompatActivity {

    private EditText topicEditText;
    private EditText timeEditText;
    private EditText locationZoomEditText;
    private TextView errorMessageTextView; // Text view for error messages
    private Button createButton;
    private DatabaseReference databaseMeetings;
    private FirebaseAuth mAuth; // Firebase authentication instance

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_meetings_page);

        // Initialize Firebase components
        mAuth = FirebaseAuth.getInstance(); // Initialize the Firebase Auth instance
        databaseMeetings = FirebaseDatabase.getInstance().getReference("meetings");

        topicEditText = findViewById(R.id.topicEditText);
        timeEditText = findViewById(R.id.timeEditText);
        locationZoomEditText = findViewById(R.id.locationZoomEditText);
        createButton = findViewById(R.id.createButton);
        errorMessageTextView = findViewById(R.id.errorMessage); // Initialize error message text view

        /*
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickCreateMeeting(v);
            }
        });

         */
    }

    public void onClickCreateMeeting(View v) {
        String topic = topicEditText.getText().toString().trim();
        String time = timeEditText.getText().toString().trim();
        String locationZoom = locationZoomEditText.getText().toString().trim();

        //TextView errorMessageTextView = findViewById(R.id.errorMessage);

        if (!topic.isEmpty() && !time.isEmpty() && !locationZoom.isEmpty()) {
            String id = databaseMeetings.push().getKey();
            boolean isAttending = true;
            ArrayList<String> attendants = new ArrayList<>();
            // Add the current user's email to the list of attendants
            String email = mAuth.getCurrentUser().getEmail();
            attendants.add(email);

            Meeting meeting = new Meeting(id, topic, locationZoom, time, isAttending, attendants);
            databaseMeetings.child(id).setValue(meeting);

            // Clear the input
            topicEditText.setText("");
            timeEditText.setText("");
            locationZoomEditText.setText("");

            // Navigate back to the meetings page
            Intent mainPageIntent = new Intent(this, MeetingsActivity.class);
            mainPageIntent.putExtra("email", email);
            startActivity(mainPageIntent);

        } else {
            // Output error message if any fields are empty
            errorMessageTextView.setText("Error: Please Enter All Fields"); // Set the error message
        }
    }
}