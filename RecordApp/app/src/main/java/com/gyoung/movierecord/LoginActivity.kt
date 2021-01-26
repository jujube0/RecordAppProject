package com.gyoung.movierecord

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.gyoung.movierecord.network.RequestToServer
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        button.setOnClickListener {
            RequestToServer.service.requestProfile2(
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MTQsImlhdCI6MTYxMDQ0NDE4OCwiZXhwIjoxNjM2MzY0MTg4LCJpc3MiOiJiZW1lIn0.cqQ4JmmfU_YgOujm_MInZczkqCml-5CTZl-ID2Gyf-A",
                pic,
                map
            ).customEnqueue(
                onFail = {
                    Log.d("profile request", "프로필 변경 실패")
                },
                onSuccess = {
//                    Log.d("profile request", "프로필 변경 성공")
//                    Log.d("profile request", "${rqNickname}")
//                    val intent = Intent(this, MainActivity::class.java)
//                    startActivity(intent)
                    finish()
                },
                onError = {
                    Log.d("profile request", "에러")
                }
            )
        }
    }
}