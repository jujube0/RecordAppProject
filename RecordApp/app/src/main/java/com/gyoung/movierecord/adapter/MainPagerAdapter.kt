package com.gyoung.movierecord.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.gyoung.movierecord.fragment.MainFragment

class MainPagerAdapter(fm : FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> MainFragment()
            1 -> MainFragment()
            2 -> MainFragment()
            else -> MainFragment()
        }
    }

    override fun getCount(): Int {
        return 4
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "전체"
            1 -> "영화"
            2 -> "책"
            else -> "설정"
        }
    }
}