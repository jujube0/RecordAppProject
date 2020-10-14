package com.gyoung.movierecord.data

data class RequestSendPost(
    val content: String,
    val img_url: String?,
    val rate_count: Int,
    val release_date: String?,
    val author : String?,
    val title: String,
    val type: String
)