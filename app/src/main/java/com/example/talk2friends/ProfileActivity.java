package com.example.talk2friends;

import android.content.Intent;
import android.content.pm.ActivityInfo;
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
        //No rotation of screen
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        email = getIntent().getStringExtra("email");

        //use email to update current values in hints on load
        String databaseUrl = "https://talk2friends-e1b4c-default-rtdb.firebaseio.com/";

        FirebaseDatabase database = FirebaseDatabase.getInstance(databaseUrl);
        myR = database.getReference("users");

        myR.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    name = userSnapshot.child("name").getValue(String.class);
                    bday = userSnapshot.child("bday").getValue(String.class);
                    prof = userSnapshot.child("proficient").getValue(Boolean.class);
                    aff = userSnapshot.child("student").getValue(Boolean.class);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.err.println("Database Error: " + databaseError.getMessage());
            }
        });

        //set name hint
        final TextView nametv = (TextView) findViewById(R.id.simpleEditText);
        nametv.setText(name);

        //set bday hint
        final TextView bdaytv = (TextView) findViewById(R.id.simpleEditText4);
        bdaytv.setText(bday);

        //set aff hint
        final TextView afftv = (TextView) findViewById(R.id.simpleEditText5);
        if (aff)    {
            bdaytv.setText("Student");
        }
        else    {
            bdaytv.setText("Alumni");
        }

        //set prof hint
        final TextView proftv = (TextView) findViewById(R.id.simpleEditText6);
        if (prof)   {
            bdaytv.setText("Native");
        }
        else    {
            bdaytv.setText("International");
        }
    }

    //when you click the edit button, take all the values entered in each edittext and push them to firebase
    //don't forget to reload the profile page after, probably using an intent
    public void onClickEditButton(View view) {
        EditText simpleEditText = (EditText) findViewById(R.id.simpleEditText);
        if (!simpleEditText.getText().toString().equals(""))  {
            name = simpleEditText.getText().toString();
        }

        simpleEditText = (EditText) findViewById(R.id.simpleEditText4);
        if (!simpleEditText.getText().toString().equals("")) {
            bday = simpleEditText.getText().toString();
        }

        simpleEditText = (EditText) findViewById(R.id.simpleEditText5);
        if (!simpleEditText.getText().toString().equals("")) {
            aff = Boolean.parseBoolean(simpleEditText.getText().toString());
        }

        simpleEditText = (EditText) findViewById(R.id.simpleEditText6);
        if (!simpleEditText.getText().toString().equals("")) {
            prof = Boolean.parseBoolean(simpleEditText.getText().toString());
        }

        myR.child("name").setValue(name);
        myR.child("birthday").setValue(bday);
        myR.child("proficient").setValue(prof);
        myR.child("student").setValue(aff);

        //maybe need intent here to reload if it doesn't automatically change the hint values after edit button is clicked
        //will test
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