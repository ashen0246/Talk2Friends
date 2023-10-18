package com.example.talk2friends;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    boolean login = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        //No rotation of screen
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
    }

    public void onClickToggleLogin(View view) {
        login = !login;
        Button button = findViewById(R.id.toggleLoginButton);
        if (login){
            button.setText(getString(R.string.signup_button));
        }
        else{
            button.setText(getString(R.string.login_button));
        }

    }
    public void onClickNextPage(View view) {
        if (login){
            //login
            Intent mainPageIntent = new Intent(this, MeetingsActivity.class);

            Controller c = new Controller("test");
            startActivity(mainPageIntent);
        }
        else{
            //signup
            Intent emailVerificationIntent = new Intent(this, EmailVerificationActivity.class);
            startActivity(emailVerificationIntent);
        }

    }
}