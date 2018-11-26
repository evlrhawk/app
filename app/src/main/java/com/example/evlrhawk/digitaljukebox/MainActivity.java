package com.example.evlrhawk.digitaljukebox;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.Connector;
import com.spotify.android.appremote.api.SpotifyAppRemote;
import com.spotify.protocol.client.Subscription;
import com.spotify.protocol.types.PlayerState;
import com.spotify.protocol.types.Track;

public class MainActivity extends AppCompatActivity {

    private EditText string;
    private Button send;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //new activity to login
        Intent login = new Intent(MainActivity.this, LogIn.class);
        this.startActivity(login);

        // get the database reference

        databaseReference = FirebaseDatabase.getInstance().getReference("string");

        // string taken from text entry in app
        string = (EditText)findViewById(R.id.sendString);
        // our button
        send = (Button)findViewById(R.id.button);

        // to call our addString button on click
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addString();
            }
        });
    }

    public void addString() {
        final String TAG = "From addString()";

        Log.i(TAG, "Call to addString()");
        // the string to be sent
        String fromApp = string.getText().toString();

        // if our text entry has something in it to send
        if (!TextUtils.isEmpty(fromApp)) {
            Log.v(TAG, "Sent to \"full\" 'if' statement");
            // get the database reference id
            String id = databaseReference.push().getKey();
            // make a new object to send to the database
            ToSend toSend1 = new ToSend(fromApp);

            // send the value to the database
            databaseReference.child(id).setValue(toSend1);

            Log.i(TAG, "Sent to firebase");

        }
        // if we don't have anything in our text entry box
        else {
            Log.v(TAG, "Sent to \"blank\" 'if' statement");

            // tell them to put something in
            Toast.makeText(MainActivity.this, "Please type a string to send.", Toast.LENGTH_LONG);
        }


    }
}
