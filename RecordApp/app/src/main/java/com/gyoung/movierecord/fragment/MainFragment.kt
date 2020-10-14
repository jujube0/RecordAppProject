package com.gyoung.movierecord.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gyoung.movierecord.R
import com.gyoung.movierecord.adapter.ShowPostListAdapter
import com.gyoung.movierecord.data.PostItem
import com.gyoung.movierecord.function.customEnqueue
import com.gyoung.movierecord.network.RequestToServer
import kotlinx.android.synthetic.main.fragment_main.*
import java.lang.Exception

class MainFragment(val index : Int) : Fragment() {

    var datas = mutableListOf<PostItem>()
    lateinit var rv_adapter : ShowPostListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rv_adapter = ShowPostListAdapter(view.context)
        rv_adapter.datas = datas
        rv_mainFrag.adapter = rv_adapter
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        try{
            getPosts()
        }catch (e : Exception){
            Log.d("MainFrag", e.message.toString())
        }
    }

    fun getPosts(){
        datas = mutableListOf()
        RequestToServer.mainService.getPosts()
            .customEnqueue(
                onSuccess = { item ->
                    datas.addAll(item)
                    Log.d("MainFrag", datas.toString())

                    if(index == 1){
                        datas = datas.filter{
                            it.type == "movie"
                        } as MutableList<PostItem>
                    }else if(index == 2){
                        datas = datas.filter{
                            it.type == "book"
                        } as MutableList<PostItem>
                    }

                    rv_adapter.datas = datas
                    rv_adapter.notifyDataSetChanged()
                },
                onError = {e -> Log.d("MainFrag",e.message() )},
                onFail = {}
            )

    }

}