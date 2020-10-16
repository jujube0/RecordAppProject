package com.gyoung.movierecord

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.gyoung.movierecord.data.PostItem
import com.gyoung.movierecord.data.RatingMakePostAdapter
import com.gyoung.movierecord.function.customEnqueue
import com.gyoung.movierecord.function.showCustomToast
import com.gyoung.movierecord.network.RequestToServer
import kotlinx.android.synthetic.main.activity_make_post.*
import java.lang.Exception

class ModifyPostActivity : AppCompatActivity() {

    var post_idx : String = ""
    var rating_adapter = RatingMakePostAdapter(this)

    lateinit var original : PostItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_post)

        post_idx = intent.getStringExtra("post_id").toString()
        btn_changeType_makePost.visibility = View.INVISIBLE
        btn_changeType_makePost.isEnabled=false
        btn_searchAgain_makePost.visibility = View.INVISIBLE
        btn_searchAgain_makePost.isEnabled = false

        btn_goBack_makePost.setOnClickListener {
            finish()
        }
        lv_rating_makePost.adapter = rating_adapter
        getPostDetail()
        btn_confirm_makePost.setOnClickListener {
            var newObj = PostItem(null,
                title = if(et_title_makePost.text.toString()!=original.title) et_title_makePost.text.toString() else null,
                content = if(tv_content_makePost.text.toString() != original.content) tv_content_makePost.text.toString() else null,
                author = if(original.type == "book" && et_author_makePost.text.toString() != original.author) et_author_makePost.text.toString() else null,
                release_date = if(original.type == "movie" && et_author_makePost.text.toString() != original.release_date) et_author_makePost.text.toString() else null,
                rate_count = if(original.rate_count != rating_adapter.clickdIdx) rating_adapter.clickdIdx else null,
                type = null,
                img_url = null
            )

            sendPost(newObj)

        }
    }

    fun getPostDetail(){
        post_idx = intent.getStringExtra("post_id").toString()
        RequestToServer.mainService.getPostDetail(
            post_idx
        ).customEnqueue(
            onSuccess = {
                Log.d("ModifyPostActivity", post_idx)
                original = it
                et_title_makePost.setText(it.title)
                if(it.type == "book") {
                    tv_author_makePost.text = "작가"
                    et_author_makePost.setText(it.author)
                }else{
                    tv_author_makePost.text = "개봉일"
                    et_author_makePost.setText(it.release_date)
                }
                try{
                    if(it.img_url !=null ) Glide.with(this).load(it.img_url).into(iv_mainImage_makePost)
                    rating_adapter.clickdIdx = it.rate_count!!
                    rating_adapter.notifyDataSetChanged()
                    tv_content_makePost.setText(it.content)

                }catch (e : Exception){}
            },
            onFail = {}
        )
    }

    fun sendPost(obj : PostItem){
        RequestToServer.mainService.patchPostDetail(
            post_idx,
            obj
        ).customEnqueue(
            onSuccess = {
                finish()
            },
            onFail = {
                showCustomToast("수정에 실패하였습니다.")
            }
        )
    }
}