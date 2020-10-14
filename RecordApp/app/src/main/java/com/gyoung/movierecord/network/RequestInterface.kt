package com.gyoung.movierecord.network

import com.gyoung.movierecord.data.*
import retrofit2.Call
import retrofit2.http.*

interface RequestInterface {
    @GET("/v3/search/book")
    fun searchBook(
        @Header("Authorization") token : String,
        @Query("query") query: String,
        @Query("target") target : String = "title"
    ) : Call<ResponseSearchBook>

    @GET("/3/search/movie")
    fun searchMovie(
        @Query("api_key") key : String,
        @Query("query") query :String,
        @Query("page") page : Int = 1,
        @Query("language") k : String = "ko-KO"
    ) : Call<ResponseMovieSearch>

    @GET("/post")
    fun getPosts() : Call<ResponseGetPosts>

    @POST("/post")
    fun sendPost(
        @Body body : RequestSendPost
    ) : Call<ResponseSimple>

}