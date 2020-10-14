package com.gyoung.movierecord

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
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
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager_main.adapter = MainPagerAdapter(supportFragmentManager)
        tabLayout_main.setupWithViewPager(viewPager_main)
        tabLayout_main.setTabTextColors(resources.getColor(R.color.text_dark, null), resources.getColor(R.color.text_brown, null))
        tabLayout_main.setSelectedTabIndicatorColor(resources.getColor(R.color.colorPrimaryDark, null))

        // 글쓰기 버튼 누르면
        btn_makePost_main.setOnClickListener {
            val intent = Intent(this, MakePostActivity::class.java)
            startActivity(intent)
        }
    }


}

