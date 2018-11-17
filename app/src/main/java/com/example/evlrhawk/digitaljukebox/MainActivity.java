package com.example.evlrhawk.digitaljukebox;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private EditText string;
    private Button send;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseReference = FirebaseDatabase.getInstance().getReference("string");


        string = (EditText)findViewById(R.id.sendString);
        send = (Button)findViewById(R.id.button);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addString();
            }
        });
    }

    public void addString() {

        String fromApp = string.getText().toString();

        if (!TextUtils.isEmpty(fromApp)) {

            String id = databaseReference.push().getKey();

            ToSend toSend1 = new ToSend(fromApp);

            databaseReference.child(id).setValue(toSend1);

        }
        else {
            Toast.makeText(MainActivity.this, "Please type a string to send.", Toast.LENGTH_LONG);
        }


    }
}
