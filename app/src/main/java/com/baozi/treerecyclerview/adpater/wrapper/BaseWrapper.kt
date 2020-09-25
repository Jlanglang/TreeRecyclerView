package com.baozi.treerecyclerview.adpater.wrapper

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

import com.baozi.treerecyclerview.base.BaseRecyclerAdapter
import com.baozi.treerecyclerview.base.ViewHolder
import com.baozi.treerecyclerview.manager.ItemManager

/**
 * Created by baozi on 2017/5/16.
 */

open class BaseWrapper<T>(protected var mAdapter: BaseRecyclerAdapter<T>) : BaseRecyclerAdapter<T>() {

    final override val itemManager: ItemManager<T> = mAdapter.itemManager

    init {
        itemManager.adapter = this;
    }

    override fun onBindViewHolderClick(viewHolder: ViewHolder, view: View) {
        mAdapter.onBindViewHolderClick(viewHolder, view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return mAdapter.onCreateViewHolder(parent, viewType)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        mAdapter.onAttachedToRecyclerView(recyclerView)
    }

    override fun onViewAttachedToWindow(holder: ViewHolder) {
        mAdapter.onViewAttachedToWindow(holder)
    }

    override fun getItemViewType(position: Int): Int {
        return mAdapter.getItemViewType(position)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        mAdapter.onBindViewHolder(holder, position)
    }

    override fun getItemCount(): Int {
        return mAdapter.itemCount
    }


    override fun getLayoutId(position: Int): Int {
        return mAdapter.getLayoutId(position)
    }

    override fun getData(position: Int): T? {
        return mAdapter.getData(position)
    }

    override fun getData(): MutableList<T> {
        return mAdapter.getData()
    }

    override fun setData(data: List<T>?) {
        mAdapter.setData(data)
    }

    override fun onBindViewHolder(holder: ViewHolder, t: T?, position: Int) {
        mAdapter.onBindViewHolder(holder, t, position)
    }

    override fun checkPosition(position: Int): Int {
        return mAdapter.checkPosition(position)
    }


    override fun setOnItemClickListener(onItemClickListener: (viewHolder: ViewHolder, position: Int) -> Unit) {
        mAdapter.setOnItemClickListener(onItemClickListener)
    }

    override fun setOnItemLongClickListener(onItemLongClickListener: (viewHolder: ViewHolder, position: Int) -> Boolean) {
        mAdapter.setOnItemLongClickListener(onItemLongClickListener)
    }

    override fun getItemSpanSize(position: Int, maxSpan: Int): Int {
        return mAdapter.getItemSpanSize(position, maxSpan)
    }

    override fun clear() {
        mAdapter.clear()
    }

}
