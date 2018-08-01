package com.baozi.demo.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.baozi.demo.R;
import com.baozi.treerecyclerview.adpater.TreeRecyclerAdapter;
import com.baozi.treerecyclerview.adpater.TreeRecyclerType;
import com.baozi.treerecyclerview.adpater.wrapper.HeaderAndFootWrapper;
import com.baozi.treerecyclerview.base.ViewHolder;
import com.baozi.treerecyclerview.factory.ItemHelperFactory;
import com.baozi.treerecyclerview.item.TreeItem;

import java.util.Arrays;
import java.util.List;


/**
 * 申述页面
 */
public class ComplaintActivity extends Activity {
    private HeaderAndFootWrapper headerAndFootWrapper = new HeaderAndFootWrapper<>(new TreeRecyclerAdapter(TreeRecyclerType.SHOW_ALL));

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_rv_content);
        RecyclerView viewById = findViewById(R.id.rv_content);
        viewById.setLayoutManager(new LinearLayoutManager(this));
        viewById.setAdapter(headerAndFootWrapper);

        List<TreeItem> treeItemList = ItemHelperFactory.createTreeItemList(Arrays.asList(1, 2, 3, 4, 5, 6,7,8,9,10,11,12), ComplaintItem.class, null);
        headerAndFootWrapper.getItemManager().addItems(treeItemList);

    }
}

