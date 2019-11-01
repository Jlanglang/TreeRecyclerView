package com.baozi.demo.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baozi.demo.R;
import com.baozi.treerecyclerview.adpater.TreeRecyclerAdapter;
import com.baozi.treerecyclerview.base.BaseRecyclerAdapter;


public abstract class SimpleRecyclerViewFg<T extends BaseRecyclerAdapter> extends Fragment {
    protected RecyclerView recyclerView;
    protected Context context;
    protected TreeRecyclerAdapter adapter = new TreeRecyclerAdapter();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (recyclerView == null) {
            recyclerView = (RecyclerView) inflater.inflate(R.layout.layout_rv_content, container, false);
            recyclerView.setBackgroundColor(Color.GRAY);
        }
        return recyclerView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
    }
}
