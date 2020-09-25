package com.baozi.treerecyclerview.base

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

import com.baozi.treerecyclerview.manager.ItemManageImpl
import com.baozi.treerecyclerview.manager.ItemManager

import java.util.ArrayList
import kotlin.properties.Delegates

/**
 * Created by zhy on 16/4/9.
 */
abstract class BaseRecyclerAdapter<T> : RecyclerView.Adapter<ViewHolder>() {
    protected var mOnItemClickListener: ((viewHolder: ViewHolder, position: Int) -> Unit)? = null
    protected var mOnItemLongClickListener: ((viewHolder: ViewHolder, position: Int) -> Boolean)? = null
    private val data: MutableList<T> = ArrayList()
    /**
     * 操作adapter
     *
     * @return
     */
    open val itemManager: ItemManager<T> by lazy {
        ItemManageImpl(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = ViewHolder.createViewHolder(parent, viewType)
        onBindViewHolderClick(holder, holder.itemView)
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        onBindViewHolder(holder, getData(position), position)
    }

    /**
     * 实现item的点击事件
     */
    open fun onBindViewHolderClick(viewHolder: ViewHolder, view: View) {
        //判断当前holder是否已经设置了点击事件
        if (!view.hasOnClickListeners()) {
            view.setOnClickListener {
                //获得holder的position
                val layoutPosition = viewHolder.layoutPosition
                //检查item的position,是否可以点击.
                //                  检查并得到真实的position
                val itemPosition = itemManager.itemToDataPosition(layoutPosition)
                if (mOnItemClickListener != null) {
                    mOnItemClickListener!!.invoke(viewHolder, itemPosition)
                }
            }
        }
        if (!view.isLongClickable) {
            view.setOnLongClickListener {
                //获得holder的position
                val layoutPosition = viewHolder.layoutPosition
                //检查position是否可以点击
                //检查并得到真实的position
                val itemPosition = itemManager.itemToDataPosition(layoutPosition)
                mOnItemLongClickListener?.invoke(viewHolder, itemPosition) ?: false
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutId(position)
    }

    override fun getItemCount(): Int {
        return getData().size
    }


    open fun getItemSpanSize(position: Int, maxSpan: Int): Int {
        return maxSpan
    }

    open fun getData(): MutableList<T> {
        return data
    }

    open fun setData(data: List<T>?) {
        if (data != null) {
            getData().clear()
            getData().addAll(data)
        }
    }

    open fun getData(position: Int): T? {
        return if (position >= 0) {
            getData()[position]
        } else null
    }

    /**
     * 直接强转为指定类型,可以能null
     *
     * @param position
     * @param <D>
     * @return
    </D> */
    fun <D> getCastData(position: Int): D? {
        try {
            return getData(position) as D?
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }

    }

    open fun setOnItemClickListener(onItemClickListener: (viewHolder: ViewHolder, position: Int) -> Unit) {
        mOnItemClickListener = onItemClickListener
    }

    open fun setOnItemLongClickListener(onItemLongClickListener: (viewHolder: ViewHolder, position: Int) -> Boolean) {
        mOnItemLongClickListener = onItemLongClickListener
    }


    //检查当前position,获取原始角标
    open fun checkPosition(position: Int): Int {
        return itemManager.itemToDataPosition(position)
    }


    /**
     * 获取该position的item的layout
     *
     * @param position 角标
     * @return item的layout id
     */
    abstract fun getLayoutId(position: Int): Int

    /**
     * view与数据绑定
     *
     * @param holder   ViewHolder
     * @param t        数据类型
     * @param position position
     */
    open fun onBindViewHolder(holder: ViewHolder, t: T?, position: Int) {

    }

    open fun clear() {
        getData().clear()
    }
}
