package com.baozi.demo.activity;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import com.baozi.demo.R;
import com.baozi.demo.fragment.MineFg;

public class TBActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) throws NullPointerException{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tb_home);
        ViewPager content = findViewById(R.id.content);
        content.setAdapter(new FragmentPagerAdapter(this.getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return new MineFg();
            }

            @Override
            public int getCount() {
                return 5;
            }

            @NonNull
            @Override
            public CharSequence getPageTitle(int position) {
                return position + "";
            }
        });
        TabLayout tb_title = findViewById(R.id.tb_title);
        tb_title.setupWithViewPager(content);
    }
}
