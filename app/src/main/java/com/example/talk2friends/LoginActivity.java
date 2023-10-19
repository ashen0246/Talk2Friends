package com.example.talk2friends;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.google.android.gms.tasks.Task;

public class LoginActivity extends AppCompatActivity {

    boolean login = true;
    boolean verifyEmailStep = false;
    EditText email;
    EditText password;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        //No rotation of screen
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        email = findViewById(R.id.enterEmail);
        password =  findViewById(R.id.enterPassword);
        mAuth = FirebaseAuth.getInstance();
    }

    public void onClickToggleLogin(View view) {
        verifyEmailStep = false;
        login = !login;
        Button button = findViewById(R.id.toggleLoginButton);
        TextView textView = findViewById(R.id.loginLabel);
        Button b2 = findViewById(R.id.nextPageButton);
        if (login) {
            button.setText(getString(R.string.signup_button));
            textView.setText(getString(R.string.login));
            b2.setText(getString(R.string.next_page_button));
        } else {
            button.setText(getString(R.string.login_button));
            textView.setText(getString(R.string.signup));
            b2.setText(getString(R.string.verifyEmail));
        }

    }
    public void onClickNextPage(View view) {
        if (!verifyEmailStep) {
            if (login) {
                //login
                mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    goToMeetingPage();
                                } else {
                                    // If sign in fails, display a message to the user.
                                }
                            }
                        });
            } else {
                //signup

                mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    mAuth.getCurrentUser().sendEmailVerification()
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        Button button = findViewById(R.id.nextPageButton);
                                                        button.setText(getString(R.string.EmailVerified));
                                                        verifyEmailStep = true;
                                                    }
                                                }
                                            });
                                } else {
                                    // If sign in fails, display a message to the user.

                                }
                            }
                        });
            }
        }
        else{
            mAuth.getCurrentUser().reload();
            if(mAuth.getCurrentUser().isEmailVerified()){
                goToCreateProfilePage();
            }
        }
    }
    public void goToCreateProfilePage(){
        Intent emailVerificationIntent = new Intent(this, EmailVerificationActivity.class);
        emailVerificationIntent.putExtra("email", email.getText());
        startActivity(emailVerificationIntent);
    }
    public void goToMeetingPage(){
        //if not in main database fo to create profile page
        Intent mainPageIntent = new Intent(this, MeetingsActivity.class);
        mainPageIntent.putExtra("email", email.getText());
        startActivity(mainPageIntent);
    }
}