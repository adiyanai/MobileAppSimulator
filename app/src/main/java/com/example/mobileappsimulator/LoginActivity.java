package com.example.mobileappsimulator;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.EditText;
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
        Intent joystickIntent = new Intent(this, JoystickActivity.class);
        EditText ipText = findViewById(R.id.ip_id);
        EditText portText = findViewById(R.id.port_id);

        String ip = ipText.getText().toString();
        String port = portText.getText().toString();

        joystickIntent.putExtra("ip", ip);
        joystickIntent.putExtra("port", port);
        // Start the new activity.
        startActivity(joystickIntent);
    }
}
