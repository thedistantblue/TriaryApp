package com.thedistantblue.triaryapp.start;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.thedistantblue.triaryapp.R;
import com.thedistantblue.triaryapp.mainscreen.MainScreenActivityImpl;
import com.thedistantblue.triaryapp.network.NetworkTest;

public class StartActivity extends AppCompatActivity {

    Button signInButton;
    Button localUseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_layout);


        signInButton = findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NetworkTest.class);
                startActivity(intent);
            }
        });

        localUseButton = findViewById(R.id.local_button);
        localUseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainScreenActivityImpl.class);
                startActivity(intent);
            }
        });

    }
}