package com.gyoung.movierecord

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.ImageView
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_info.*

class InfoActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)


        iv_back_info.setOnClickListener {
            finish()
        }

        tv_main_info.setText(Html.fromHtml(getString(R.string.licenses), Html.FROM_HTML_MODE_COMPACT))
        val imgView = ImageView(this)
        imgView.setImageResource(R.drawable.ic_movieapisource)
        layout_forImg_info.addView(imgView)

    }
}