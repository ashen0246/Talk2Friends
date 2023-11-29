package com.example.talk2friends;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {
    String email;
    String name;
    String bday;
    boolean aff;
    boolean prof;

    DatabaseReference myR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);
//no rotation of the screen
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        email = getIntent().getStringExtra("email");

        String databaseUrl = "https://talk2friends-e1b4c-default-rtdb.firebaseio.com/";

        FirebaseDatabase database = FirebaseDatabase.getInstance(databaseUrl);
        myR = database.getReference("users");

        myR.child(email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    name = dataSnapshot.child("name").getValue(String.class);
                    bday = dataSnapshot.child("bday").getValue(String.class);
                    prof = dataSnapshot.child("proficient").getValue(Boolean.class);
                    aff = dataSnapshot.child("student").getValue(Boolean.class);

                    EditText nametv = (EditText) findViewById(R.id.simpleEditText);
                    nametv.setHint(name);

                    EditText bdaytv = (EditText) findViewById(R.id.simpleEditText4);
                    bdaytv.setHint(bday);

                    EditText afftv = (EditText) findViewById(R.id.simpleEditText5);
                    afftv.setHint(aff ? "Student" : "Alumni");

                    EditText proftv = (EditText) findViewById(R.id.simpleEditText6);
                    proftv.setHint(prof ? "Native" : "International");
                } else {
                    System.err.println("User not found in the database.");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.err.println("Database Error: " + databaseError.getMessage());
            }
        });
    }

    public void onClickEditButton(View view) {
        EditText simpleEditText = (EditText) findViewById(R.id.simpleEditText);
        if (!simpleEditText.getText().toString().isEmpty()) {
            name = simpleEditText.getText().toString();
        }

        simpleEditText = (EditText) findViewById(R.id.simpleEditText4);
        if (!simpleEditText.getText().toString().isEmpty()) {
            bday = simpleEditText.getText().toString();
        }

        simpleEditText = (EditText) findViewById(R.id.simpleEditText5);
        if (!simpleEditText.getText().toString().isEmpty()) {
            String affText = simpleEditText.getText().toString().toLowerCase();
            if (affText.equals("student")) {
                aff = true;
            } else {
                aff = false;
            }
        }

        simpleEditText = (EditText) findViewById(R.id.simpleEditText6);
        if (!simpleEditText.getText().toString().isEmpty()) {
            String profText = simpleEditText.getText().toString().toLowerCase();
            if (profText.equals("native")) {
                prof = true;
            } else {
                prof = false;
            }
        }

        EditText nametv = (EditText) findViewById(R.id.simpleEditText);
        nametv.setHint(name);

        EditText bdaytv = (EditText) findViewById(R.id.simpleEditText4);
        bdaytv.setHint(bday);

        EditText afftv = (EditText) findViewById(R.id.simpleEditText5);
        afftv.setHint(aff ? "Student" : "Alumni");

        EditText proftv = (EditText) findViewById(R.id.simpleEditText6);
        proftv.setHint(prof ? "Native" : "International");

        myR.child(email).child("name").setValue(name);
        myR.child(email).child("bday").setValue(bday);
        myR.child(email).child("proficient").setValue(prof);
        myR.child(email).child("student").setValue(aff);
    }

    public void onClickSendButton(View view) {
        EditText body = (EditText) findViewById(R.id.simpleEditText7);
        String bodyText = body.getText().toString();

        if (!bodyText.isEmpty()) {
            body.setText("");

            String[] emailAddresses = {"coenpira@usc.edu"};

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("message/rfc822");
            intent.putExtra(Intent.EXTRA_EMAIL, emailAddresses);
            intent.putExtra(Intent.EXTRA_SUBJECT, "Talk2Friends Support Ticket");
            intent.putExtra(Intent.EXTRA_TEXT, bodyText);

            if (intent.resolveActivity(this.getPackageManager()) != null) {
                this.startActivity(Intent.createChooser(intent, "Send email"));
            }

            body.setHint("Please Describe Your Issue");
            body.setHintTextColor(Color.GRAY);
        }
        else {
            body.setText("");
            body.setHint("Please Input a Message.");
            body.setHintTextColor(Color.RED);
        }
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
