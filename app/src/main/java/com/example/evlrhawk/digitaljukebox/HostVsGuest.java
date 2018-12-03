package com.example.evlrhawk.digitaljukebox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HostVsGuest extends AppCompatActivity {

    private static final String TAG = "HostVsGuest";

    public Boolean isHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_vs_guest);

        Button guestBtn = findViewById(R.id.guestBtn);
        Button hostBtn = findViewById(R.id.hostBtn);

        guestBtn.setOnClickListener(setGuest());
        hostBtn.setOnClickListener(setHost());

    }

    private View.OnClickListener setHost(){
//        Intent resultData = new Intent();
//        setResult(MainActivity.RESULT_OK, resultData);
        isHost = true;
        return null;
    }

    private View.OnClickListener setGuest(){
        isHost = false;
        return null;
    }


}
