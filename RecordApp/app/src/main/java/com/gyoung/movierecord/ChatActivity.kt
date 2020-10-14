package com.gyoung.movierecord

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.gyoung.movierecord.R
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import kotlinx.android.synthetic.main.activity_chat.*
import org.json.JSONObject
import java.lang.Exception
import java.net.URISyntaxException

class ChatActivity : AppCompatActivity() {

    lateinit var mSocket : Socket;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        try{
            mSocket = IO.socket("http://172.30.79.128:5000")
            Log.d("chatActivity socket", "connected")
        }catch(e : URISyntaxException){
            Log.d("chatActivity socket", "failed")
        }


        button2.setOnClickListener {

            val obj = JSONObject()
            obj.put("name", et_name.text.toString())
            obj.put("message", et_message.text.toString())
            mSocket.emit("chat message", obj)

        }

        mSocket.on("get message", setMessage)
        mSocket.connect();
    }

    var setMessage = Emitter.Listener { args ->
        val obj = JSONObject(args[0].toString())
        val a = tv1.text.toString()
        Log.d("chat activity", obj.toString())
//        try{
//            tv1.text = a + "\n" + obj.get("name") + ": " + obj.get("message")
//        }catch(e : Exception){
//            e.printStackTrace()
//        }
        Thread(object : Runnable{
            override fun run() {
                runOnUiThread(Runnable {
                    kotlin.run {
                        tv1.text = a + "\n" + obj.get("name") + ": " + obj.get("message")
                    }
                })
            }
        }).start()
    }


}
