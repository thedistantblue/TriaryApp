package com.thedistantblue.triaryapp.start;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.thedistantblue.triaryapp.R;
import com.thedistantblue.triaryapp.mainscreen.MainScreenActivity;

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
                Toast.makeText(getApplicationContext(), "Sign in", Toast.LENGTH_SHORT).show();
            }
        });

        localUseButton = findViewById(R.id.local_button);
        localUseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "Local use", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainScreenActivity.class);
                startActivity(intent);
            }
        });

    }
}
