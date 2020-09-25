package com.baozi.demo.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.baozi.demo.R
import com.baozi.demo.fragment.MineFg

class TBActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tb_home)
        val content = findViewById<ViewPager>(R.id.content)
        content.adapter = object : FragmentPagerAdapter(this.supportFragmentManager) {
            override fun getItem(i: Int): Fragment {
                return MineFg()
            }

            override fun getCount(): Int {
                return 5
            }

            override fun getPageTitle(position: Int): CharSequence {
                return position.toString() + ""
            }
        }
        val tb_title = findViewById<TabLayout>(R.id.tb_title)
        tb_title.setupWithViewPager(content)
    }
}
