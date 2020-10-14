package com.gyoung.movierecord.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gyoung.movierecord.R
import com.gyoung.movierecord.SearchItemActivity
import com.gyoung.movierecord.data.Documents
import kotlinx.android.synthetic.main.item_search_book.view.*

class SearchItemMainAdapter(private val context: Context): RecyclerView.Adapter<SearchItemMainHolder>() {

  	var datas: MutableList<Documents> = mutableListOf()
    lateinit var listener : SearchItemActivity.SearchItemClickListener

  	// xml file을 inflate한 후 viewHolder를 만든다.
      override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  SearchItemMainHolder{
  	    val view = LayoutInflater.from(context).inflate(R.layout.item_search_book, parent,false)
  		return SearchItemMainHolder(view)
      }

      override fun getItemCount(): Int {
  		 return datas.size
      }
  	  // viewholder의 항목을 구성하기 위해 호출된다.
        override fun onBindViewHolder(holder: SearchItemMainHolder, position: Int) {
          holder.bind(datas[position])
          holder.itemView.setOnClickListener {
              Log.d("SearchItemMainAdapter", datas[position].toString())
            listener.onClick(datas[position])
          }
        }
  }

class SearchItemMainHolder (itemView: View) : RecyclerView.ViewHolder(itemView){

    fun bind(data : Documents){
        Glide.with(itemView.context).load(data.thumbnail).into(itemView.iv_mainImage_itemSearchItemMain)
        itemView.tv_title_iv_mainImage_itemSearchItemMain.text = data.title
        itemView.tv_author_itemSearchItemMain.text = data.authors.get(0)
    }
 }