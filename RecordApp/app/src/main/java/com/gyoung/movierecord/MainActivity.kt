package com.gyoung.movierecord

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.gyoung.movierecord.adapter.MainPagerAdapter
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.Request
import okhttp3.WebSocket
import java.io.IOException
import java.net.URISyntaxException

class MainActivity : AppCompatActivity() {

    lateinit var socket: Socket
    val TAG = "FIREBASE_MESSAGING"
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager_main.adapter = MainPagerAdapter(supportFragmentManager)
        tabLayout_main.setupWithViewPager(viewPager_main)
        tabLayout_main.setTabTextColors(resources.getColor(R.color.text_dark, null), resources.getColor(R.color.text_brown, null))
        tabLayout_main.setSelectedTabIndicatorColor(resources.getColor(R.color.colorPrimaryDark, null))

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            // Log and toast
            val msg =token.toString();
            Log.d(TAG, msg)
            Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
        })

        // 글쓰기 버튼 누르면
        btn_makePost_main.setOnClickListener {
            val intent = Intent(this, MakePostActivity::class.java)
            startActivity(intent)
        }
    }


}

