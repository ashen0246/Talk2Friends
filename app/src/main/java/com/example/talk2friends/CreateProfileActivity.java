package com.example.talk2friends;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class CreateProfileActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    boolean nativeSpeaker = true;
    boolean student = true;
    boolean sports = false;
    boolean gaming = false;
    boolean music = false;
    boolean art = false;

    EditText name;
    EditText bday;

    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_profile);
        //No rotation of screen
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        name = findViewById(R.id.nameText);
        bday =  findViewById(R.id.bdayText);

        email = getIntent().getStringExtra("email");
    }

    public void onClickAffiliationToggle(View view){
        student = !student;
        Button b = findViewById(R.id.affiliationToggle);
        if (student) {
            b.setText(R.string.student);
        }
        else{
            b.setText(R.string.alumni);
        }
    }
    public void onClickProficiencyToggle(View view){
        nativeSpeaker = !nativeSpeaker;
        Button b = findViewById(R.id.proficiencyToggle);
        if (nativeSpeaker) {
            b.setText(R.string.nativeSpeaker);
        }
        else{
            b.setText(R.string.ellSpeaker);
        }
    }

    public void onClickSportsToggle(View view){
        sports = !sports;
        Button b = findViewById(R.id.sportsToggle);
        if (sports) {
            b.setBackgroundColor(Color.GRAY);
        }
        else{
            b.setBackgroundColor(Color.LTGRAY);
        }
    }
    public void onClickGamingToggle(View view){
        gaming = !gaming;
        Button b = findViewById(R.id.gamingToggle);
        if (gaming) {
            b.setBackgroundColor(Color.GRAY);
        }
        else{
            b.setBackgroundColor(Color.LTGRAY);
        }
    }

    public void onClickMusicToggle(View view){
        music = !music;
        Button b = findViewById(R.id.musicToggle);
        if (music) {
            b.setBackgroundColor(Color.GRAY);
        }
        else{
            b.setBackgroundColor(Color.LTGRAY);
        }
    }

    public void onClickArtToggle(View view){
        art = !art;
        Button b = findViewById(R.id.artToggle);
        if (art) {
            b.setBackgroundColor(Color.GRAY);
        }
        else{
            b.setBackgroundColor(Color.LTGRAY);
        }
    }



    public void onClickNextPage(View view) {
        TextView t = findViewById(R.id.errorMessage);

        if (name.getText().toString().length() == 0){
            //error message
            t.setText(R.string.nameError);
        }
        else{
            if (bday.getText().toString().length() == 0) {
                t.setText(R.string.bdayError);
            }
            else{
                User user = new User(name.getText().toString(), bday.getText().toString(), student, nativeSpeaker, sports, gaming, music, art);
                myRef.child("users").child(email).setValue(user);

                Intent mainPageIntent = new Intent(this, MeetingsActivity.class);
                startActivity(mainPageIntent);
            }
        }
    }
}