package com.gyoung.movierecord.data

data class ResponseSearchBook(
    val meta : Meta?,
    val documents : MutableList<Documents>?
)
data class Meta(
    val total_count : Int,
    val pageable_count : Int,
    val is_end : Boolean
)
data class Documents(
    val title : String,
    val authors : ArrayList<String>,
    val thumbnail : String
)