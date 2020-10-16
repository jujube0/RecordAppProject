package com.gyoung.movierecord.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import com.gyoung.movierecord.InfoActivity
import com.gyoung.movierecord.R
import com.gyoung.movierecord.function.showCustomToast
import kotlinx.android.synthetic.main.fragment_setting.*

class SettingFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val items = arrayListOf<CharSequence>("라이센스", "pdf 내보내기")

        val adapter  = ArrayAdapter(view.context, android.R.layout.simple_list_item_1, items)
        listView_settingFrag.adapter = adapter

        listView_settingFrag.onItemClickListener = object  : AdapterView.OnItemClickListener{
            override fun onItemClick(parent: AdapterView<*>?, v: View?, position: Int, id: Long) {
                if(position == 0){
                    val intent = Intent(view.context , InfoActivity::class.java )
                    startActivity(intent)
                }else{
                    view.context.showCustomToast("아직 준비중입니다.")
                }
            }

        }
    }

}