package com.baozi.demo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.baozi.demo.R;

/**
 * @author jlanglang  2016/12/22 9:58
 * @版本 2.0
 * @Change
 */

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.bt_city).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CityListActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.bt_shopcart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShopListActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.bt_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TestListAativity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.bt_shoptab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShopTabListActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.bt_news).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewsActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.bt_sort).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SortActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.bt_swipe_sort).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SwipeSortActivity.class);
                startActivity(intent);
            }
        });
    }
}
