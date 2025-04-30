package com.sahanmw.roadsafetyapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class VideoPlayerActivity extends AppCompatActivity {

    private SeekBar seekBar; // Retain seekBar if you want to implement a different function
    private Button acceptButton, rejectButton, rewardsButton;
    private TextView videoDetailsTextView;
    private TextView videoUrlTextView; // TextView for video URL
    private LinearLayout rewardSection; // Reward section layout

    private static final String NOTIFICATION_PREF = "NotificationPref";
    private static final String USER_NOTIFICATION = "UserNotification";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        // Initialize UI components
        videoDetailsTextView = findViewById(R.id.videoDetailsTextView);
        videoUrlTextView = findViewById(R.id.videoUrlTextView); // Initialize video URL TextView
        acceptButton = findViewById(R.id.acceptButton);
        rejectButton = findViewById(R.id.rejectButton);
        rewardsButton = findViewById(R.id.rewardsButton);
        rewardSection = findViewById(R.id.rewardSection);

        // Hide reward section initially
        rewardSection.setVisibility(View.GONE);

        // Get video details from the intent
        String username = getIntent().getStringExtra("username");
        String videoId = getIntent().getStringExtra("videoId"); // Assuming you pass the videoId

        // Fetch video details and URL from the database
        fetchVideoDetails(videoId);

        // Accept button logic
        acceptButton.setOnClickListener(v -> {
            Toast.makeText(VideoPlayerActivity.this, "Video accepted", Toast.LENGTH_SHORT).show();
            saveNotificationForUser(username, "Your video has been accepted");
        });

        // Reject button logic
        rejectButton.setOnClickListener(v -> {
            Toast.makeText(VideoPlayerActivity.this, "Video rejected", Toast.LENGTH_SHORT).show();
            saveNotificationForUser(username, "Your video has been rejected");
        });

        // Rewards button logic (showing the reward section)
        rewardsButton.setOnClickListener(v -> {
            rewardSection.setVisibility(View.VISIBLE); // Show reward section when button is clicked
        });

        // Set click listener for video URL to play the video
        videoUrlTextView.setOnClickListener(v -> {
            String videoUrl = videoUrlTextView.getText().toString();
            playVideo(videoUrl);
        });
    }

    // Fetch video details from the database
    private void fetchVideoDetails(String videoId) {
        // Replace with actual database logic to retrieve video details
        String videoUrl = "https://firebasestorage.googleapis.com/v0/b/road-safety-app-1dfff.appspot.com/o/videos%2F1728457453532.mp4?alt=media&token=ac4dfa9d-de79-4ce4-83f9-19847247cd78"; // Example URL
        String videoDate = "2024-10-09"; // Example data
        String videoDescription = "wrong way";
        String videoPlace = "kandy";
        String videoTime = "09.00";
        String videoCategory = "wrong-way driving";

        // Set video details in the TextView
        String videoDetails = "Date: " + videoDate + "\n" +
                "Description: " + videoDescription + "\n" +
                "Place: " + videoPlace + "\n" +
                "Time: " + videoTime + "\n" +
                "Category: " + videoCategory;

        videoDetailsTextView.setText(videoDetails);
        videoUrlTextView.setText(videoUrl); // Set video URL
    }

    // Function to play the video
    private void playVideo(String videoUrl) {
        // Create an Intent to start VideoPlaybackActivity
        Intent intent = new Intent(this, VideoPlaybackActivity.class);
        intent.putExtra("videoUrl", videoUrl);
        startActivity(intent);
    }

    // Set video details in the TextView
    private void setVideoDetails() {
        // This method is now obsolete since we are fetching details from the database
    }

    // Function to save a notification for the user
    private void saveNotificationForUser(String username, String message) {
        SharedPreferences sharedPreferences = getSharedPreferences(NOTIFICATION_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_NOTIFICATION + "_" + username, message);
        editor.apply();
    }
}
