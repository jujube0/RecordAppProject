package com.gyoung.movierecord

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Rect
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import android.view.Window
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import com.gyoung.movierecord.data.RatingMakePostAdapter
import com.gyoung.movierecord.etc.MyLeadingMarginSpan2
import com.gyoung.movierecord.function.customEnqueue
import com.gyoung.movierecord.function.showCustomToast
import com.gyoung.movierecord.network.RequestToServer
import kotlinx.android.synthetic.main.activity_post_detail.*

class PostDetailActivity : AppCompatActivity() {

    var post_id : String = ""
    var adapter_rating = RatingMakePostAdapter(this)
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_detail)

        post_id = intent.getStringExtra("post_id").toString()
        adapter_rating.clickedEnabled=false
        rv_rating_postDetail.adapter = adapter_rating

//        getPost()

        btn_back_postDetail.setOnClickListener { finish() }

        btn_menu_postDetail.setOnClickListener {
            setMenu()
        }

    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onStart() {
        super.onStart()
        getPost()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun getPost(){
        RequestToServer.mainService.getPostDetail(post_id)
            .customEnqueue(
                onSuccess = {
                    adapter_rating.clickdIdx = it.rate_count!!
                    adapter_rating.notifyDataSetChanged()
                    tv_title_postDetail.text = it.title
                    if(!it.img_url.isNullOrBlank()){
                        Glide.with(this).load(it.img_url).into(iv_img_postDetail)
                    }
                    if(it.type == "movie"){
                        tv_author_postDetail.setText("개봉일 ${it.release_date}")
                    }else{
                        tv_author_postDetail.setText("작가 ${it.author}")
                    }
                    tv_content_postDetail.text = it.content

                    val vto = iv_img_postDetail.viewTreeObserver
                    vto.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener{
                        override fun onGlobalLayout() {
                            iv_img_postDetail.viewTreeObserver.removeOnGlobalLayoutListener(this)
                            val height = iv_img_postDetail.measuredHeight
                            val width = iv_img_postDetail.measuredWidth
                            makeSpan(height,width)
                        }
                    })
                },
                onFail = {},
                onError = {r -> Log.d("PostDetail", r.message())}
            )
    }

    private fun makeSpan(height: Int, width: Int) {
        val allTextStart = 0
        val text = tv_content_postDetail.text.toString()
        val allTextEnd = text.length -1

        var lines = 0
        val bounds = Rect()
        val b = if(text.length >= 10) 10 else text.length
        tv_content_postDetail.paint.getTextBounds(text.substring(0,b), 0, 1, bounds )

        val fontSpacing = tv_content_postDetail.paint.fontSpacing
        lines = height / fontSpacing.toInt() +1

        val span = MyLeadingMarginSpan2(lines, width + 10)
        var st = SpannableString(text)
        st.setSpan(span, allTextStart, allTextEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        tv_content_postDetail.setText(st)
    }

    fun setMenu(){
        val dialog = AlertDialog.Builder(this, R.style.CustomDesign)

        dialog.setTitle("게시글 관리")
        dialog.setItems(R.array.item_post_detail, object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, choice: Int) {
                if(choice == 0){
                    // 게시글 수정하기
                    val intent = Intent(this@PostDetailActivity, ModifyPostActivity::class.java)
                    intent.putExtra("post_id", post_id)
                    startActivity(intent)
                }else{
                    // 게시글 삭제하기
                    deletePost()
                }
                dialog?.dismiss()

            }
        })
        dialog.show()
    }

    fun deletePost(){
        val dialog = AlertDialog.Builder(this, R.style.CustomDesign)
        dialog.setTitle("게시글을 정말 삭제하시겠습니까?")
        dialog.setPositiveButton("예", object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, p1: Int) {
                RequestToServer.mainService.deletePostDetail(post_id)
                    .customEnqueue(
                        onSuccess = {
                            finish()
                        },
                        onFail = {
                            showCustomToast("삭제에 실패하였습니다")
                        }
                    )
            }
        })
        dialog.setNegativeButton("아니오", object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, p1: Int) {
                dialog?.dismiss()
            }
        })
        dialog.show()
    }
}