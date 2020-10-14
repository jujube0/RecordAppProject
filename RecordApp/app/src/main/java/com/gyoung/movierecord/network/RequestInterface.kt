package com.gyoung.movierecord.network

import com.gyoung.movierecord.data.ResponseMovieSearch
import com.gyoung.movierecord.data.ResponseSearchBook
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

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

}