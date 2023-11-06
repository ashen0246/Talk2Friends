package com.example.talk2friends;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
    String email;

    public void onClickProfilePage(View view) {
        Intent profilePageIntent = new Intent(this, ProfileActivity.class);
        profilePageIntent.putExtra("email", email);
        startActivity(profilePageIntent);
    }
    public void onClickMeetingsPage(View view) {
        //skip
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
                    addMeetingView(meeting);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle possible errors.
            }
        });
    }

    private void addMeetingView(Meeting meeting) {
        View meetingView = getLayoutInflater().inflate(R.layout.meeting_item, null);
        TextView topicTextView = meetingView.findViewById(R.id.meetingTopic);
        TextView timeTextView = meetingView.findViewById(R.id.meetingTime);
        TextView locationTextView = meetingView.findViewById(R.id.meetingLocation);

        topicTextView.setText(meeting.getTopic());
        timeTextView.setText(meeting.getTime());
        locationTextView.setText(meeting.getLocation());

        meetingsLayout.addView(meetingView);
    }

    // Define other methods for joining/creating meetings, etc.
}