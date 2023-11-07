package com.example.talk2friends;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);
        //No rotation of screen
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        email = getIntent().getStringExtra("email");
    }

    public void onClickProfilePage(View view) {
        //skip
    }
    public void onClickMeetingsPage(View view) {
        Intent meetingsPageIntent = new Intent(this, MeetingsActivity.class);
        meetingsPageIntent.putExtra("email", email);
        startActivity(meetingsPageIntent);
    }

    public void onClickFriendsPage(View view) {
        Intent friendsPageIntent = new Intent(this, FriendsActivity.class);
        friendsPageIntent.putExtra("email", email);
        startActivity(friendsPageIntent);
    }
}
//InformationPopup

