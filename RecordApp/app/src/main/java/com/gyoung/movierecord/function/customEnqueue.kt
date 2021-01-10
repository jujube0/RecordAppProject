package com.gyoung.movierecord.function

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun<ResponseType> Call<ResponseType>.customEnqueue(
    onFail:()-> Unit,
    onSuccess:(ResponseType)->Unit,
    onError:(Response<ResponseType>)->Unit={}
){
    this.enqueue(object: Callback<ResponseType> {
        override fun onFailure(call: Call<ResponseType>, t: Throwable) {
            onFail()
            Log.d("network", t.message.toString())
        }

        override fun onResponse(call: Call<ResponseType>, response: Response<ResponseType>) {
            response.body()?.let{
                onSuccess(it)
            }?:onError(response)

            Log.d("networkaa", response.toString() + call.toString())
        }
    })
}