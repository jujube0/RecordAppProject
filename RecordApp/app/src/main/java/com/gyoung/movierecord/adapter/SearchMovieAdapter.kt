package com.gyoung.movierecord.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gyoung.movierecord.R
import com.gyoung.movierecord.SearchItemActivity
import com.gyoung.movierecord.data.MovieSearch
import kotlinx.android.synthetic.main.item_search_book.view.*
import kotlinx.android.synthetic.main.item_search_movie.view.*

class SearchMovieAdapter(private val context: Context): RecyclerView.Adapter<SearchMovieHolder>() {

  	var datas: MutableList<MovieSearch> = mutableListOf()
    lateinit var listener : SearchItemActivity.SearchItemClickListener

  	// xml file을 inflate한 후 viewHolder를 만든다.
      override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMovieHolder {
  	    val view = LayoutInflater.from(context).inflate(R.layout.item_search_movie, parent,false)
  		return SearchMovieHolder(view)
      }

      override fun getItemCount(): Int {
  		 return datas.size
      }
  	  // viewholder의 항목을 구성하기 위해 호출된다.
      override fun onBindViewHolder(holder: SearchMovieHolder, position: Int) {
          holder.bind(datas[position])
          holder.itemView.setOnClickListener {
              listener.onClick2(datas[position])
          }
      }
  }

  class SearchMovieHolder (itemView: View) : RecyclerView.ViewHolder(itemView){

    fun bind(data : MovieSearch){
        itemView.tv_title_iv_mainImage_itemSearchItemMovie.text = data.title
        itemView.tv_releaseDate_itemSearchItemMovie.text = data.release_date
        if(data.poster_path!="https://image.tmdb.org/t/p/w185/"){

            Glide.with(itemView.context).load(data.poster_path).into(itemView.iv_mainImage_itemSearchItemMovie)
        }

    }
 }