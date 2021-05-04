package com.example.animationpractice

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.ImageView
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var videoView: VideoView
    private lateinit var imageView: ImageView
    private lateinit var lottieAnimationView: LottieAnimationView
    private lateinit var goodImageView: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val showGifButton = findViewById<Button>(R.id.show_gif_button)
        showGifButton.setOnClickListener(this)
        val showJsonButton = findViewById<Button>(R.id.show_json_button)
        showJsonButton.setOnClickListener(this)
        val showMp4Button = findViewById<Button>(R.id.show_mp4_button)
        showMp4Button.setOnClickListener(this)
        val showSnackBarButton = findViewById<Button>(R.id.show_snack_bar_button)
        showSnackBarButton.setOnClickListener(this)
        val showGoodButton = findViewById<Button>(R.id.show_good_button)
        showGoodButton.setOnClickListener(this)
        videoView = findViewById(R.id.video_view)
        imageView = findViewById(R.id.image_view)
        lottieAnimationView = findViewById(R.id.lottie_animation_view)
        goodImageView = findViewById(R.id.good_image_view)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.show_gif_button -> {
                showGifAnimation()
            }
            R.id.show_json_button -> {
                showJsonAnimation()
            }
            R.id.show_mp4_button -> {
                showMp4Animation()
            }
            R.id.show_snack_bar_button -> {
                showSnackBarAnimation()
            }
            R.id.show_good_button -> {
                showGoodAnimation()
            }
            else -> {
                // 何もしない
            }
        }
    }

    private fun showGifAnimation() {
    }

    private fun showJsonAnimation() {
        lottieAnimationView.visibility = VISIBLE
        lottieAnimationView.playAnimation()
        lottieAnimationView.addAnimatorListener(object :
            AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
            }

            override fun onAnimationEnd(animation: Animator) {
                lottieAnimationView.visibility = GONE
            }

            override fun onAnimationCancel(animation: Animator) {
            }

            override fun onAnimationRepeat(animation: Animator) {
            }
        })
    }

    private fun showMp4Animation() {
        videoView.visibility = VISIBLE
        videoView.setOnCompletionListener { mp: MediaPlayer? -> videoView.visibility = GONE }
        videoView.setVideoURI(Uri.parse("android.resource://" + packageName + "/" + R.raw.splash_mp4))
        videoView.start()
    }

    private fun showSnackBarAnimation() {
    }

    private fun showGoodAnimation() {
    }
}