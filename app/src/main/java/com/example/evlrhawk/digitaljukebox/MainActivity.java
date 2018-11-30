package com.example.evlrhawk.digitaljukebox;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Failed Here";
    private EditText string;
    private Button send, btnPull;
    private ListView listView;
    List<ToSend> sendList;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnPull = (Button) findViewById(R.id.button2);
        listView = findViewById(R.id.list_view);

        // get the database reference
        databaseReference = FirebaseDatabase.getInstance().getReference("track");
        sendList = new ArrayList<>();
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
//        // Attach a listener to read the data at our posts reference

        btnPull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showData();
            }
        });
    }
    public void showData(){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot trackSnapshot : dataSnapshot.getChildren()) {
                    ToSend toSend = new ToSend();
                    if (toSend == null){
                        toSend.setToSend("1");
                        toSend = trackSnapshot.getValue(ToSend.class);
                    }
                    else {
                        toSend = trackSnapshot.getValue(ToSend.class);
                    }
                    sendList.add(toSend);
                }
                ToSendAdapter toSendAdapter = new ToSendAdapter(MainActivity.this, sendList);
                listView.setAdapter(toSendAdapter);
            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG,"13");
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

