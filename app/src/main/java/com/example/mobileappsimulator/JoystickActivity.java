package com.example.mobileappsimulator;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;


public class JoystickActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joystick);

        DrawCircles circles = new DrawCircles(this);
        setContentView(circles);

        // Get the Intent that started this activity and extract the ip and the port
        Intent intent = getIntent();
        String ip = intent.getStringExtra("ip");
        String port = intent.getStringExtra("port");

        CommandModel.getInstance().connect(ip, port);
    }

    protected void onDestroy() {
        super.onDestroy();

        //todo -- CHECK IF WORKING
        CommandModel.getInstance().close();
    }

}
