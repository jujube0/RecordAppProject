package com.gyoung.movierecord

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import com.gyoung.movierecord.adapter.SearchItemMainAdapter
import com.gyoung.movierecord.adapter.SearchMovieAdapter
import com.gyoung.movierecord.data.Documents
import com.gyoung.movierecord.data.MovieSearch
import com.gyoung.movierecord.function.customEnqueue
import com.gyoung.movierecord.function.showCustomToast
import com.gyoung.movierecord.network.RequestToServer
import kotlinx.android.synthetic.main.activity_search_item.*
import java.lang.Exception

class SearchItemActivity : AppCompatActivity() {
    var bookDatas : MutableList<Documents> = mutableListOf()
    var movieDatas = mutableListOf<MovieSearch>()
    val rvAdapterBook =  SearchItemMainAdapter(this)
    val rvAdapterMovie = SearchMovieAdapter(this)
    // 책검색
    var isBookChosen = true
    lateinit var searchItemClickListener : SearchItemClickListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_item)


        // 영화검색, 책검색
        val typeClickListener = View.OnClickListener{
            if(isBookChosen){
                btn_statusBook_searchItem.setTextColor(resources.getColor(R.color.light_grey))
                btn_statusMovie_searchItem.setTextColor(resources.getColor(R.color.text_dark))
                bookDatas = mutableListOf()
                rvAdapterBook.datas = bookDatas
                rv_main_searchItem.adapter = rvAdapterBook
                rvAdapterBook.notifyDataSetChanged()

            }else{
                btn_statusBook_searchItem.setTextColor(resources.getColor(R.color.text_dark))
                btn_statusMovie_searchItem.setTextColor(resources.getColor(R.color.light_grey))
                movieDatas = mutableListOf()
                rvAdapterMovie.datas = movieDatas
                rv_main_searchItem.adapter = rvAdapterMovie
                rvAdapterMovie.notifyDataSetChanged()
            }
            isBookChosen=!isBookChosen
        }
        btn_statusMovie_searchItem.setOnClickListener(typeClickListener)
        btn_statusBook_searchItem.setOnClickListener(typeClickListener)

        btn_search_searchItem.setOnClickListener {
            if(isBookChosen)
                searchBook()
            else
                searchMovie()
        }
        //확인 누르면 검색되도록
        et_query_searchItem.setOnEditorActionListener { textView, actionId, keyEvent ->
            if(actionId == EditorInfo.IME_ACTION_SEARCH){
                if(isBookChosen)
                    searchBook()
                else
                    searchMovie()
                true
            }else{
                false
            }
        }
        searchItemClickListener = object : SearchItemClickListener{
            override fun onClick(documents: Documents) {
                val intent = Intent()
                intent.putExtra("isBook", true)
                intent.putExtra("title", documents.title)
                intent.putExtra("author", documents.authors[0])
                intent.putExtra("thumbnail", documents.thumbnail)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }

            override fun onClick2(movie: MovieSearch) {
                val intent = Intent()
                intent.putExtra("isBook", false)
                intent.putExtra("title", movie.title)
                intent.putExtra("author", movie.release_date)
                intent.putExtra("thumbnail", movie.poster_path)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
        rvAdapterMovie.datas = movieDatas
        rvAdapterMovie.listener = searchItemClickListener

        rvAdapterBook.datas = bookDatas
        rvAdapterBook.listener = searchItemClickListener
        rv_main_searchItem.adapter = rvAdapterBook

        btn_inputSelf_searchItem.setOnClickListener {
            setResult(Activity.RESULT_OK, null)
            finish()
        }
        btn_goBack_searchItem.setOnClickListener {
            val intent = Intent()
            setResult(Activity.RESULT_CANCELED, intent)
            finish()
        }


    }
    

    fun searchMovie(){
        movieDatas = mutableListOf()
        val query =  et_query_searchItem.text.toString()
        try{
            RequestToServer.movieService.searchMovie(
                getString(R.string.tmdb_key),
                query
            ).customEnqueue(
                onSuccess = {
                    if(it.results.isNotEmpty()){
                        Log.d("SearchItem", it.results.toString())
                        for(result in it.results){
                            movieDatas.add(
                                MovieSearch(
                                    title = result.title,
                                    release_date =  result.release_date,
                                    poster_path = "https://image.tmdb.org/t/p/w185/"+result.poster_path
                                )
                            )
                        }

                        rvAdapterMovie.datas = movieDatas
                        rv_main_searchItem.adapter = rvAdapterMovie
                        rvAdapterMovie.notifyDataSetChanged()
                    }else{
                        this.showCustomToast("검색 결과가 없습니다")
                    }
                },
                onError = { e -> Log.d("SearchItem",e.message())}
            ,onFail = {}
            )
        }catch(e : Exception){}
    }

    fun searchBook(){
        bookDatas = mutableListOf()
        val query = et_query_searchItem.text.toString()
        try{
            val requestToServer = RequestToServer
            requestToServer.kakaoService.searchBook(
                "KakaoAK " + "a" + getString(R.string.kakao_key_restApi),
                query
            ).customEnqueue(
                onSuccess = {
                    if(it.documents!=null){
                        for(document in it.documents){
                            bookDatas.add(Documents(
                                document.title,
                                document.authors,
                                document.thumbnail
                            ))
                        }

                        rvAdapterBook.datas = bookDatas
                        rv_main_searchItem.adapter = rvAdapterBook
                        rvAdapterBook.notifyDataSetChanged()
                    }else{
                        this.showCustomToast("검색 결과가 없습니다")
                    }
                },
                onFail = {},
                onError = { Log.d("SearchItem", it.message())}
            )
        }catch (e : Exception){

        }


    }
    interface SearchItemClickListener{
        fun onClick(documents : Documents)
        fun onClick2(movie : MovieSearch)
    }
}