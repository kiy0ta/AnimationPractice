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
        val vv = findViewById<VideoView>(R.id.logo)
        vv.setOnCompletionListener { mp: MediaPlayer? -> showMainActivity() }
        vv.setVideoURI(Uri.parse("android.resource://" + packageName + "/" + R.raw.splash_mp4))
        vv.start()
    }

    private fun showMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}