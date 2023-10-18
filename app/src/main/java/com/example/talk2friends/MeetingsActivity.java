package com.example.talk2friends;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MeetingsActivity extends AppCompatActivity {
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meetings_page);
        email = getIntent().getStringExtra("email");


    }

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
}
//InformationPopup