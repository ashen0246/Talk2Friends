package com.example.talk2friends;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class FriendsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friends_page);
        //No rotation of screen
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);



    }

    public void onClickProfilePage(View view) {
        Intent profilePageIntent = new Intent(this, ProfileActivity.class);
        startActivity(profilePageIntent);
    }
    public void onClickMeetingsPage(View view) {
        Intent meetingsPageIntent = new Intent(this, MeetingsActivity.class);
        startActivity(meetingsPageIntent);
    }

    public void onClickFriendsPage(View view) {
        //skip
    }
}
//InformationPopup