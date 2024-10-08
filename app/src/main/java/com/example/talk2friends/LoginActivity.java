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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.regex.Pattern;

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
        TextView e = findViewById(R.id.errorMessage);
        e.setText("");
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
        TextView text = findViewById(R.id.errorMessage);
        text.setText("");
        if (!verifyEmailStep) {
            if (login) {
                //login
                if (!email.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) {
                    text.setText(R.string.loggingIn);
                    mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        if (mAuth.getCurrentUser().isEmailVerified()) {
                                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                                            DatabaseReference myRef = database.getReference();

                                            myRef.child("users").child(email.getText().toString().substring(0, email.getText().toString().indexOf("@"))).addValueEventListener(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(DataSnapshot dataSnapshot) {
                                                    if (dataSnapshot.exists()) {
                                                        // User exists, go to meetings page
                                                        goToMeetingPage();
                                                    } else {
                                                        // User does not exist
                                                        // Handle the case where the user is not found in the database
                                                        goToCreateProfilePage();
                                                    }
                                                }

                                                @Override
                                                public void onCancelled(DatabaseError error) {
                                                    // Failed to read value
                                                    System.out.println("Failed to read value. " + error);
                                                }
                                            });
                                        } else {
                                            //maybe need to send new link
                                            Button button = findViewById(R.id.nextPageButton);
                                            button.setText(getString(R.string.emailVerified));
                                            TextView text = findViewById(R.id.errorMessage);
                                            text.setText(getString(R.string.plsVerify));
                                            verifyEmailStep = true;
                                        }
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        text.setText(R.string.invLogin);
                                    }
                                }
                            });
                }
                else{
                    text.setText("Please Fill in All Fields");
                }
            } else {
                //signup

                //input cleaning
                String e = email.getText().toString();
                String p = password.getText().toString();
                text.setText(R.string.invPwd);
                if(p.length()>=6){
                    text.setText(R.string.invEmail);
                    if (e.endsWith("@usc.edu") & e.length()>8 & isValidEmail(e)) {
                        text.setText("Please use USC email without dot.");
                        if (!e.substring(0, e.indexOf("@")).contains(".")) {
                            text.setText(R.string.sendingEmail);
                            mAuth.createUserWithEmailAndPassword(e, p)
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
                                                                    button.setText(getString(R.string.emailVerified));
                                                                    verifyEmailStep = true;
                                                                    text.setText(R.string.emailSent);
                                                                    TextView s = findViewById(R.id.toggleLoginButton);
                                                                    s.setText(R.string.cancel);
                                                                } else {
                                                                    //apparently it always works
                                                                    text.setText(R.string.invEmail);
                                                                    deleteUser();
                                                                }
                                                            }
                                                        });
                                            } else {
                                                // If sign in fails, display a message to the user.
                                                text.setText(R.string.invNewAccount);
                                            }
                                        }
                                    });
                        }
                    }
                }
            }
        }
        else{
            mAuth.getCurrentUser().reload().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        if(mAuth.getCurrentUser().isEmailVerified()){
                            goToCreateProfilePage();
                        }
                        else{
                            text.setText(R.string.emailNotVerified);
                        }
                    } else {
                        // Handle the error
                    }
                }
            });
        }
    }

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    public void goToCreateProfilePage(){
        Intent emailVerificationIntent = new Intent(this, CreateProfileActivity.class);
        emailVerificationIntent.putExtra("email", email.getText().toString().substring(0, email.getText().toString().indexOf("@")));
        startActivity(emailVerificationIntent);
    }
    public void goToMeetingPage(){
        //if not in main database fo to create profile page
        Intent mainPageIntent = new Intent(this, MeetingsActivity.class);
        mainPageIntent.putExtra("email", email.getText().toString().substring(0, email.getText().toString().indexOf("@")));
        startActivity(mainPageIntent);
    }

    public void deleteUser(){
        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            user.delete()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                // User account has been deleted successfully.
                            } else {
                                // An error occurred while deleting the account.
                            }
                        }
                    });
        }

    }
}