package com.example.evlrhawk.digitaljukebox;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

//import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewDatabase extends AppCompatActivity {

    private static final String TAG = "Failed Here";

    private ListView listView;
    DatabaseReference databaseReference;
    List<ToSend> sendList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e(TAG, "3");
        listView = findViewById(R.id.list_view);
        Log.e(TAG, "4");
        databaseReference = FirebaseDatabase.getInstance().getReference("track");
        Log.e(TAG, "5");
        sendList = new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "6");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e(TAG, "7");
                for(DataSnapshot trackSnapshot : dataSnapshot.getChildren()) {
                    Log.e(TAG, "8");
                    ToSend toSend = trackSnapshot.getValue(ToSend.class);
                    Log.e(TAG, "9");
                    sendList.add(toSend);
                    Log.e(TAG, "10");
                }
                ToSendAdapter toSendAdapter = new ToSendAdapter(ViewDatabase.this, sendList);
                Log.e(TAG, "11");
                listView.setAdapter(toSendAdapter);
                Log.e(TAG, "12");
            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG,"13");
            }
        });
    }
}