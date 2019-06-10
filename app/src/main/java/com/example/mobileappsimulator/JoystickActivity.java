package com.example.mobileappsimulator;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

public class JoystickActivity extends AppCompatActivity {

    private String elevatorPath = "set controls/flight/elevator ";
    private String aileronPath = "set controls/flight/aileron ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joystick);

        // Get the Intent that started this activity and extract the ip and the port
        Intent intent = getIntent();
        String ip = intent.getStringExtra("ip");
        String port = intent.getStringExtra("port");

        //todo- connect the server
    }

    protected void onDestroy(){
        super.onDestroy();

        //todo- close the connection to the server
    }

}
