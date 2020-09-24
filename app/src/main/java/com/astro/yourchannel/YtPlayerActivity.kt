package com.astro.yourchannel

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.astro.yourchannel.util.Constants.Companion.API_KEY
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import kotlinx.android.synthetic.main.activity_yt_player.*

class YtPlayerActivity : YouTubeBaseActivity() {

    lateinit var onInitializedListener : YouTubePlayer.OnInitializedListener
    private val TAG = "YtPlayerActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_yt_player)

        val videoId = intent.getStringExtra("videoId")


        onInitializedListener = object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(
                provider: YouTubePlayer.Provider,
                youTubePlayer: YouTubePlayer,
                b: Boolean
            ) {

                youTubePlayer.loadVideo(videoId)
                youTubePlayer.setShowFullscreenButton(false)
            }

            override fun onInitializationFailure(
                provider: YouTubePlayer.Provider,
                youTubeInitializationResult: YouTubeInitializationResult
            ) {
                Log.d(TAG, "onInitializationFailure: FAILED")
            }
        }

        ytPlayer.initialize(API_KEY,onInitializedListener)


    }
}