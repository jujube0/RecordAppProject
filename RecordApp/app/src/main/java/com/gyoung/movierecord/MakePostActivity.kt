package com.gyoung.movierecord

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.gyoung.movierecord.data.RatingMakePostAdapter
import com.gyoung.movierecord.data.RequestSendPost
import com.gyoung.movierecord.function.customEnqueue
import com.gyoung.movierecord.function.showCustomToast
import com.gyoung.movierecord.network.RequestToServer
import kotlinx.android.synthetic.main.activity_make_post.*
import java.lang.Exception

class MakePostActivity : AppCompatActivity() {
    var title = ""
    var author = ""
    var thumbnail = ""
    var isBook = true

    val SEARCH_BOOK = 200

    val ratingAdapter = RatingMakePostAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_post)

        val intent = Intent(this, SearchItemActivity::class.java)
        startActivityForResult(intent, SEARCH_BOOK)

        btn_searchAgain_makePost.setOnClickListener {
            val intent = Intent(this, SearchItemActivity::class.java)
            startActivityForResult(intent, SEARCH_BOOK)
        }

        lv_rating_makePost.adapter = ratingAdapter

        btn_goBack_makePost.setOnClickListener {
            finish()
        }

        btn_changeType_makePost.setOnClickListener {
            isBook = !isBook
            changeType()
        }
        btn_confirm_makePost.setOnClickListener {
            sendPost()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode== Activity.RESULT_OK){
            try{

                isBook = data!!.getBooleanExtra("isBook", true)

                title = data!!.getStringExtra("title").toString()
                author = data!!.getStringExtra("author").toString()
                thumbnail = data!!.getStringExtra("thumbnail").toString()

                changeType()
                Log.d("MakePost", title)

            }catch (e : Exception){
                title = ""
                author = ""
                thumbnail = ""
            }
            et_title_makePost.setText(title)
            et_author_makePost.setText(author)
            if(!thumbnail.isNullOrBlank()){
                Glide.with(this).load(thumbnail).into(iv_mainImage_makePost)
            }else{
                Glide.with(this).load("").into(iv_mainImage_makePost)
            }
        }else if(resultCode == Activity.RESULT_CANCELED){
            finish()
        }
    }

    fun changeType(){
        if(isBook){
            tv_title_makePost.text = "책 제목"
            tv_author_makePost.text = "작가"
            btn_changeType_makePost.text = "영화로 변경"
        }else{
            tv_title_makePost.text = "영화 제목"
            tv_author_makePost.text = "개봉일"
            btn_changeType_makePost.text = "책으로 변경"
        }
    }

    fun sendPost(){
        try{
            RequestToServer.mainService.sendPost(
                RequestSendPost(
                    content = tv_content_makePost.text.toString(),
                    title = tv_title_makePost.text.toString(),
                    type = if(isBook) "book" else "movie",
                    rate_count = ratingAdapter.clickdIdx,
                    author = if(isBook) et_author_makePost.text.toString() else null,
                    release_date = if(isBook) null else et_author_makePost.text.toString(),
                    img_url = if(thumbnail.isNullOrBlank()) null else thumbnail
                )
            ).customEnqueue(
                onSuccess = {
                    finish()
                },
                onFail = {},
                onError = { e -> showCustomToast("내용을 모두 채워주세요")}
            )
        }catch (e : Exception){
            showCustomToast(e.message.toString())
        }
    }

}