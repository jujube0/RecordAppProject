package com.gyoung.movierecord.network

import android.app.Application
import com.gyoung.movierecord.R
import com.kakao.sdk.common.KakaoSdk

class App :Application(){
    override fun onCreate() {
        super.onCreate()

        KakaoSdk.init(this, getString(R.string.kakao_key_restApi))
    }
}