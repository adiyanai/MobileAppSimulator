package com.example.mobileappsimulator;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void goToJoystick(View view) {
        // Create an Intent to start the Joystick activity
        Intent randomIntent = new Intent(this, JoystickActivity.class);
        // Start the new activity.
        startActivity(randomIntent);
    }
}
