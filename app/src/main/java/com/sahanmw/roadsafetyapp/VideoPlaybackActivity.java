package com.sahanmw.roadsafetyapp;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class VideoPlaybackActivity extends AppCompatActivity {

    private VideoView videoView;




        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_video_playback);

            videoView = findViewById(R.id.videoView); // Ensure you have a VideoView in your layout

            String videoUrl = getIntent().getStringExtra("videoUrl");
            playVideo(videoUrl);
        }

        private void playVideo(String videoUrl) {
            if (videoUrl != null && !videoUrl.isEmpty()) {
                videoView.setVideoURI(Uri.parse(videoUrl));
                videoView.start();
            } else {
                Toast.makeText(this, "Invalid video URL", Toast.LENGTH_SHORT).show();
            }
        }
    }



