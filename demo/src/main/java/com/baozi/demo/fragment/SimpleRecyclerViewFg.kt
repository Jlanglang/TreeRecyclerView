package com.baozi.demo.fragment

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.baozi.demo.R
import com.baozi.treerecyclerview.adpater.TreeRecyclerAdapter
import com.baozi.treerecyclerview.base.BaseRecyclerAdapter


abstract class SimpleRecyclerViewFg<T : BaseRecyclerAdapter<*>> : Fragment() {
    protected var recyclerView: RecyclerView? = null
    protected var mContext: Context? = null
    protected open var adapter = TreeRecyclerAdapter()

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        this.mContext = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (recyclerView == null) {
            recyclerView = inflater.inflate(R.layout.layout_rv_content, container, false) as RecyclerView
            recyclerView!!.setBackgroundColor(Color.GRAY)
        }
        return recyclerView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerView!!.itemAnimator = DefaultItemAnimator()
        recyclerView!!.layoutManager = LinearLayoutManager(context)
        recyclerView!!.adapter = adapter
    }
}
