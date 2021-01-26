package com.gyoung.movierecord.adapter

import android.content.Context
import android.content.Intent
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gyoung.movierecord.PostDetailActivity
import com.gyoung.movierecord.R
import com.gyoung.movierecord.data.PostItem
import kotlinx.android.synthetic.main.item_post_main.view.*
import kotlinx.android.synthetic.main.item_search_book.view.*


class ShowPostListAdapter(private val context: Context): RecyclerView.Adapter<ShowPostHolder>() {

  	var datas: MutableList<PostItem> = mutableListOf()

  	// xml file을 inflate한 후 viewHolder를 만든다.
      override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowPostHolder {
  	    val view = LayoutInflater.from(context).inflate(R.layout.item_post_main, parent,false)
  		return ShowPostHolder(view)
      }

      override fun getItemCount(): Int {
  		 return datas.size
      }
  	  // viewholder의 항목을 구성하기 위해 호출된다.
      override fun onBindViewHolder(holder: ShowPostHolder, position: Int) {
          holder.bind(datas[position])
          holder.itemView.setOnClickListener {
              val intent = Intent(holder.itemView.context, PostDetailActivity::class.java)
              intent.putExtra("post_id", datas[position]._id)
              holder.itemView.context.startActivity(intent)
          }
      }
  }

  class ShowPostHolder (itemView: View) : RecyclerView.ViewHolder(itemView){

    fun bind(data : PostItem){
        if(data.img_url!=null){
            Glide.with(itemView.context).load(data.img_url).into(itemView.iv_item_post)
        }
        itemView.tv_item_title.text = data.title
//        itemView.tv_item_content.text = data.content
    }
 }