package com.gyoung.movierecord.data

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gyoung.movierecord.R
import kotlinx.android.synthetic.main.item_rating_make_post.view.*

class RatingMakePostAdapter(private val context: Context): RecyclerView.Adapter<RatingMakePostHolder>() {

    var clickdIdx = 0
    var clickedEnabled = true

  	// xml file을 inflate한 후 viewHolder를 만든다.
      override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  RatingMakePostHolder{
  	    val view = LayoutInflater.from(context).inflate(R.layout.item_rating_make_post, parent,false)
  		return RatingMakePostHolder(view)
      }

      override fun getItemCount(): Int {
  		 return 5
      }
  	  // viewholder의 항목을 구성하기 위해 호출된다.
      override fun onBindViewHolder(holder: RatingMakePostHolder, position: Int) {
          if(position<=clickdIdx){
              holder.setSelected()
          }else{
              holder.setUnSeleced()
          }
          holder.itemView.isEnabled = clickedEnabled
          holder.itemView.setOnClickListener {
              clickdIdx = position
              this.notifyDataSetChanged()
          }

      }
  }

class RatingMakePostHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
    fun setSelected(){
        Glide.with(itemView.context).load(R.drawable.ic_smile_selected).into(itemView.iv_ratingMake)
    }
    fun setUnSeleced(){
        Glide.with(itemView.context).load(R.drawable.ic_smile).into(itemView.iv_ratingMake)
    }
 }