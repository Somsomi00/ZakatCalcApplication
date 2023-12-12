package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);

        // Find the back button by its ID
        ImageButton backButton = findViewById(R.id.float_bckbutton);

        // Set an OnClickListener to handle the button click
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an intent to navigate back to MainActivity
                Intent intent = new Intent(AboutActivity.this, MainActivity.class);

                // Start the MainActivity
                startActivity(intent);

                // Finish the current activity (AboutActivity)
                finish();
            }
        });

        // Find the GitHub link TextView by its ID
        TextView githubLink = findViewById(R.id.github_link);

        // Set an OnClickListener to open the GitHub page
        githubLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGitHubPage();
            }
        });

        // Floating Action Button (FAB) setup
        FloatingActionButton fab = findViewById(R.id.floating_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a send intent
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send");
                sendIntent.setType("text/plain");

                // Create a share intent
                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
            }
        });
    }

    // Method to open the GitHub page
    public void openGitHubPage() {
        // Get the GitHub URL
        String githubUrl = "https://github.com/Somsomi00/ZakatApplication.git";

        // Create an intent to open the URL
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(githubUrl));

        // Check if there's an app to handle the intent
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            // Handle the case where there is no app to handle the intent
            Toast.makeText(this, "No app available to handle this action", Toast.LENGTH_SHORT).show();
        }
    }
}
