package com.gyoung.movierecord.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RequestToServer {
    var kakaoRetrofit = Retrofit.Builder()
        .baseUrl("https://dapi.kakao.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var movieRetrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var mainRetrofit = Retrofit.Builder()
        .baseUrl("http://15.164.67.58:4000")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var retrofit = Retrofit.Builder()
        .baseUrl("http://15.164.67.58:3000")
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    var kakaoService: RequestInterface = kakaoRetrofit.create(RequestInterface::class.java)
    var movieService = movieRetrofit.create(RequestInterface::class.java)
    var mainService = mainRetrofit.create(RequestInterface::class.java)
    var service = retrofit.create(RequestInterface::class.java)

}