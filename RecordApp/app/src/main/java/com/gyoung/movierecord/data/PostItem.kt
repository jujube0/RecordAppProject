package com.gyoung.movierecord.data

data class PostItem(
    val content: String,
    val rate_count: Int,
    val title: String,
    val type: String,
    val release_date : String?,
    val img_url : String?,
    val author : String?

)