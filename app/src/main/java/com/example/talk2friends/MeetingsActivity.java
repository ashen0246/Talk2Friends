package com.example.talk2friends;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MeetingsActivity extends AppCompatActivity {

    private LinearLayout meetingsLayout;
    private DatabaseReference databaseMeetings;
    private String email;

    public void onClickProfilePage(View view) {
        Intent profilePageIntent = new Intent(this, ProfileActivity.class);
        profilePageIntent.putExtra("email", email);
        startActivity(profilePageIntent);
    }

    public void onClickMeetingsPage(View view) {
    }

    public void onClickFriendsPage(View view) {
        Intent friendsPageIntent = new Intent(this, FriendsActivity.class);
        friendsPageIntent.putExtra("email", email);
        startActivity(friendsPageIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meetings_page);
        email = getIntent().getStringExtra("email");

        meetingsLayout = findViewById(R.id.meetingsLayout);
        databaseMeetings = FirebaseDatabase.getInstance().getReference("meetings");

        databaseMeetings.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                meetingsLayout.removeAllViews();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Meeting meeting = postSnapshot.getValue(Meeting.class);
                    if (meeting != null) {
                        meeting.setMeetingID(postSnapshot.getKey());  // Ensure the id is set here
                    }
                    addMeetingView(meeting);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }

    public void onClickCreateMeetingPage(View view) {
        Intent createMeetingPageIntent = new Intent(this, CreateMeetingActivity.class);
        createMeetingPageIntent.putExtra("email", email);
        startActivity(createMeetingPageIntent);
    }

    private void addMeetingView(Meeting meeting) {
        View meetingView = getLayoutInflater().inflate(R.layout.meeting_item, null);
        TextView topicTextView = meetingView.findViewById(R.id.meetingTopic);
        TextView timeTextView = meetingView.findViewById(R.id.meetingTime);
        TextView locationTextView = meetingView.findViewById(R.id.meetingLocation);
        TextView attendingTextView = meetingView.findViewById(R.id.meetingAttendants);
        Button joinLeaveButton = meetingView.findViewById(R.id.joinLeaveButton);

        topicTextView.setText(meeting.getTopic());
        timeTextView.setText(meeting.getTime());
        locationTextView.setText(meeting.getLocation());
        attendingTextView.setText(meeting.getAttendants() != null ? String.join(", ", meeting.getAttendants()) : "None");

        // Set the tag for the button to the meeting's ID
        joinLeaveButton.setTag(meeting.getMeetingID());
        boolean isAttending = meeting.getAttendants() != null && meeting.getAttendants().contains(email);
        joinLeaveButton.setText(isAttending ? "Leave" : "Join");

        joinLeaveButton.setOnClickListener(view -> {
            // Toggle the user's participation status
            if (meeting.getAttendants().contains(email)) {
                meeting.getAttendants().remove(email);
                joinLeaveButton.setText("Join");
            } else {
                meeting.getAttendants().add(email);
                joinLeaveButton.setText("Leave");
            }
            updateMeetingInFirebase(meeting.getMeetingID(), meeting); // Use getId() to get the meeting ID
        });

        // Set the initial text for the Join/Leave button
        if (meeting.getAttendants().contains(email)) {
            joinLeaveButton.setText("Leave");
        } else {
            joinLeaveButton.setText("Join");
        }

        attendingTextView.setText(TextUtils.join(", ", meeting.getAttendants()));

        meetingsLayout.addView(meetingView);
    }


    private void updateMeetingInFirebase(String meetingId, Meeting meeting) {
        if (meetingId != null) {
            databaseMeetings.child(meetingId).setValue(meeting)
                    .addOnSuccessListener(aVoid -> {
                    })
                    .addOnFailureListener(e -> {
                    });
        }
        else {
        }
    }
}