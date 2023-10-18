package com.example.talk2friends;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

public class FriendsActivity extends AppCompatActivity {
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friends_page);
        //No rotation of screen
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        email = getIntent().getStringExtra("email");


    }

    public void onClickProfilePage(View view) {
        Intent profilePageIntent = new Intent(this, ProfileActivity.class);
        profilePageIntent.putExtra("email", email);
        startActivity(profilePageIntent);
    }
    public void onClickMeetingsPage(View view) {
        Intent meetingsPageIntent = new Intent(this, MeetingsActivity.class);
        meetingsPageIntent.putExtra("email", email);
        startActivity(meetingsPageIntent);
    }

    public void onClickFriendsPage(View view) {
        //skip
    }
}
//InformationPopup