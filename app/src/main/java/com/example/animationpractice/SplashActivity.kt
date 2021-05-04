package com.example.animationpractice

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val videoView = findViewById<VideoView>(R.id.logo)
        videoView.setOnCompletionListener { mp: MediaPlayer? -> showMainActivity() }
        videoView.setVideoURI(Uri.parse("android.resource://" + packageName + "/" + R.raw.splash_mp4))
        videoView.start()
    }

    private fun showMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}