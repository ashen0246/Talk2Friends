package com.example.talk2friends;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Objects;
import java.util.Properties;
import java.util.Vector;
import java.util.regex.Pattern;

import java.io.IOException;

public class FriendsActivity extends AppCompatActivity {
    String email;
    TableLayout t1;
    TableLayout t2;
    TableLayout t3;

    Vector<String> requestSent = new Vector<>();

    DatabaseReference myR;
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friends_page);
        //no rotation of screen
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        email = getIntent().getStringExtra("email");

        String databaseUrl = "https://talk2friends-e1b4c-default-rtdb.firebaseio.com/";

        t1 = (TableLayout) findViewById(R.id.friendsTable);
        t2 = (TableLayout) findViewById(R.id.requestsTable);
        t3 = (TableLayout) findViewById(R.id.recTable);

        FirebaseDatabase database = FirebaseDatabase.getInstance(databaseUrl);
        myR = database.getReference("users");

        Log.d("CREATED", "Friend Name: ");

        fetchFriendData();
        fetchReqData();
        fetchRecData();
    }

    private void fetchFriendData() {
        myR.child(email).child("friends").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    DataSnapshot friendsSnapshot = dataSnapshot;

                    for (DataSnapshot friendSnapshot : friendsSnapshot.getChildren()) {
                        String friendName = friendSnapshot.getValue(String.class);

                        Log.d("InFETCH", "Friend Name: ");
                        Log.d("FirebaseData", "Friend Name: " + friendName);

                        count = (int) dataSnapshot.getChildrenCount();

                        int height = 100/count;

                        TableRow row = new TableRow(getApplicationContext());

                        TextView nameTextView = new TextView(getApplicationContext());
                        nameTextView.setText(friendName);

                        Button removeButton = new Button(getApplicationContext());
                        removeButton.setText("Remove");
                        //removeButton.setLayoutParams(param1);
                        removeButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //handle the removal of the friend here
                                String keytoR = friendSnapshot.getKey();
                                if (!Objects.equals(keytoR, "")) {
                                    myR.child(email).child("friends").child(keytoR).removeValue();
                                }

                                t1.removeView(row);
                            }
                        });

                        row.addView(nameTextView);
                        row.addView(removeButton);

                        t1.addView(row);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private void fetchReqData() {
        myR.child(email).child("incomingFriendRequests").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    DataSnapshot friendsSnapshot = dataSnapshot;

                    for (DataSnapshot friendSnapshot : friendsSnapshot.getChildren()) {
                        String friendName = friendSnapshot.getValue(String.class);

                        Log.d("InFETCH", "Friend Name: ");
                        Log.d("FirebaseData", "Friend Name: " + friendName);

                        count = (int) dataSnapshot.getChildrenCount();

                        int height = 100/count;

                        TableRow row = new TableRow(getApplicationContext());

                        TextView nameTextView = new TextView(getApplicationContext());
                        nameTextView.setText(friendName);

                        Button acceptButton = new Button(getApplicationContext());
                        acceptButton.setText("Accept");
                        //removeButton.setLayoutParams(param1);
                        acceptButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //handle the removal of the friend here
                                String keytoR = friendSnapshot.getKey();
                                if (!Objects.equals(keytoR, "")) {
                                    myR.child(email).child("incomingFriendRequests").child(keytoR).removeValue();
                                    myR.child(email).child("friends").child(friendName).setValue(friendName);
                                    myR.child(friendName).child("friends").child(email).setValue(email);
                                }
                                t2.removeView(row);
                            }
                        });

                        Button removeButton = new Button(getApplicationContext());
                        removeButton.setText("Reject");
                        //removeButton.setLayoutParams(param1);
                        removeButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //handle the removal of the friend here
                                String keytoR = friendSnapshot.getKey();
                                if (!Objects.equals(keytoR, "")) {
                                    myR.child(email).child("incomingFriendRequests").child(keytoR).removeValue();
                                }
                                t2.removeView(row);
                            }
                        });

                        row.addView(nameTextView);
                        row.addView(acceptButton);
                        row.addView(removeButton);

                        t2.addView(row);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private void fetchRecData() {
        myR.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean art = false;
                boolean game = false;
                boolean music = false;
                boolean sport = false;
                int common = 0;
                if(Boolean.TRUE.equals(dataSnapshot.child(email).child("art").getValue(Boolean.class)))  {
                    art = true;
                }
                if(Boolean.TRUE.equals(dataSnapshot.child(email).child("gaming").getValue(Boolean.class)))  {
                    game = true;
                }
                if(Boolean.TRUE.equals(dataSnapshot.child(email).child("music").getValue(Boolean.class)))  {
                    music = true;
                }
                if(Boolean.TRUE.equals(dataSnapshot.child(email).child("sports").getValue(Boolean.class)))  {
                    sport = true;
                }

                for (DataSnapshot dS : dataSnapshot.getChildren()) {
                    if (dS.exists() && !Objects.equals(dS.getKey(), email)) {
                        if (Boolean.TRUE.equals(dS.child("art").getValue(Boolean.class)) && art) {
                            common++;
                        }
                        if (Boolean.TRUE.equals(dS.child("gaming").getValue(Boolean.class)) && game) {
                            common++;
                        }
                        if (Boolean.TRUE.equals(dS.child("music").getValue(Boolean.class)) && music) {
                            common++;
                        }
                        if (Boolean.TRUE.equals(dS.child("sports").getValue(Boolean.class)) && sport) {
                            common++;
                        }


                        if (dS.child("incomingFriendRequests").hasChild(email)) {
                            //do nothing
                        }
                        else if (common >= 2 && !requestSent.contains(dS.getKey())) {
                            TableRow row = new TableRow(getApplicationContext());

                            TextView nameTextView = new TextView(getApplicationContext());
                            nameTextView.setText(dS.getKey());

                            Button removeButton = new Button(getApplicationContext());
                            removeButton.setText("Send Request");
                            //removeButton.setLayoutParams(param1);
                            removeButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //handle the removal of the friend here
                                    //String keytoR = friendSnapshot.getKey();
                                    if (!Objects.equals(dS.getKey(), "")) {
                                        myR.child(dS.getKey()).child("incomingFriendRequests").child(email).setValue(email);
                                    }
                                    t3.removeView(row);
                                }
                            });

                            row.addView(nameTextView);
                            row.addView(removeButton);

                            t3.addView(row);
                            requestSent.add(dS.getKey());
                        }
                        common = 0;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void onClickInviteButton(View view) throws IOException {
        EditText inviteEmail = (EditText) findViewById(R.id.inviteFriendEmail);
        String ie = inviteEmail.getText().toString();
        if (ie.endsWith("@usc.edu") & ie.length()>8 & isValidEmail(ie)) {
            inviteEmail.setText("");

            String[] emailAddresses = {ie};

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("message/rfc822");
            intent.putExtra(Intent.EXTRA_EMAIL, emailAddresses);
            intent.putExtra(Intent.EXTRA_SUBJECT, "Join Talk2Friends!");
            intent.putExtra(Intent.EXTRA_TEXT, "Your friend has invited you to join Talk2Friends! Download and join now!");

            if (intent.resolveActivity(this.getPackageManager()) != null) {
                this.startActivity(Intent.createChooser(intent, "Send email"));
            }

            inviteEmail.setHint("Enter USC Email");
            inviteEmail.setHintTextColor(Color.GRAY);
        }
        else    {
            inviteEmail.setText("");
            inviteEmail.setHint("Email Invalid.");
            inviteEmail.setHintTextColor(Color.RED);
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