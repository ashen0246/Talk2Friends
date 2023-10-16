package com.example.talk2friends;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class EmailVerificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email_verification_page);


    }

    public void onClickNextPage(View view) {
        Intent createProfileIntent = new Intent(this, CreateProfileActivity.class);
        startActivity(createProfileIntent);
    }
}