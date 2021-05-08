package com.example.animationpractice

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.graphics.drawable.Drawable
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
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var goodButtonImageView: ImageView
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
        goodButtonImageView = findViewById(R.id.good_button)
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
        imageView.visibility = VISIBLE
        // glideを使用しない場合
//        val source = ImageDecoder.createSource(assets, "splash_gif.gif")
//        val gifAnimationDrawable = ImageDecoder.decodeDrawable(source) as? AnimatedImageDrawable
//            ?: throw ClassCastException()
//        imageView.setImageDrawable(gifAnimationDrawable)
//        gifAnimationDrawable.start()
        // glide(4.x)を使用する場合(3.xと4.xでは書き方が異なるので注意する)
        Glide.with(this)
            .asGif()
            .load(R.raw.splash_gif)
            .listener(object : RequestListener<GifDrawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<GifDrawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: GifDrawable?,
                    model: Any?,
                    target: Target<GifDrawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    resource!!.setLoopCount(1)
                    resource.registerAnimationCallback(object :
                        Animatable2Compat.AnimationCallback() {
                        override fun onAnimationEnd(drawable: Drawable?) {
                            super.onAnimationEnd(drawable)
                            imageView.visibility = GONE
                        }
                    }
                    )
                    return false
                }
            })
            .into(imageView)
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
        goodButtonImageView
            .animate()
            .scaleX(1.4f)
            .scaleY(1.4f)
            .setDuration(225)
            .alpha(1f)
            .withEndAction {
                goodButtonImageView
                    .animate()
                    .setDuration(60)
                    .scaleX(1f)
                    .scaleY(1f)
                    .alpha(1f)
                    .start()
            }
            .start()
    }
}