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

    @POST("/")
    fun makeUser(
        @Body body : RequestSendPost
    ) : Call<ResponseSimple>

    // 게시물 세부 정보 가져오기
    @GET("/post/detail/{post_id}")
    fun getPostDetail(
        @Path("post_id") post_id : String
    ) : Call<PostItem>

    // 게시글 삭제하기
    @DELETE("/post/detail/{post_id}")
    fun deletePostDetail(
        @Path("post_id") post_id : String
    ) : Call < ResponseSimple>

    @PATCH("/post/detail/{post_id}")
    fun patchPostDetail(
        @Path("post_id") post_id: String,
        @Body postItem : PostItem
    ) : Call<ResponseSimple>
}