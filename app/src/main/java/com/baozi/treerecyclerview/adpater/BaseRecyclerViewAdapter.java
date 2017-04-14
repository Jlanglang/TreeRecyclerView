package com.baozi.treerecyclerview.adpater;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ViewGroup;
import android.widget.EditText;

import com.baozi.treerecyclerview.viewholder.TreeItem;
import com.baozi.treerecyclerview.viewholder.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baozi on 2017/4/14.
 */

public class BaseRecyclerViewAdapter<T extends TreeItem> extends RecyclerView.Adapter<ViewHolder> {
    private Context mContext;
    /**
     * 存储原始的items;
     */
    private List<T> mDatas;//处理后的展示数据

    /**
     * @param context 上下文
     * @param datas   条目数据
     */
    public BaseRecyclerViewAdapter(Context context, List<T> datas) {
        mContext = context;
        mDatas = datas == null ? new ArrayList<T>() : datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ViewHolder.createViewHolder(mContext, parent, viewType);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        T t = mDatas.get(position);
        t.onBindViewHolder(holder);
    }


    @Override
    public int getItemViewType(int position) {
        return mDatas.get(position).getLayoutId();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    TreeItem treeItem = mDatas.get(position);
                    if (treeItem.getSpanSize() == 0) {
                        return gridLayoutManager.getSpanCount();
                    }
                    return treeItem.getSpanSize();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
//
//    public void setEd(final int size) {
//        new EditText(mContext).addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                int length = s.length();
//                //大于最小触发时
//                if (length > size + 1) {
//                    //删除所有空格
//                    String s1 = s.toString().replaceAll(" ", "");
//                    //判断是否达到触发点
//                    if (s1.length() % size == 0) {
//                        char[] chars = s1.toCharArray();
//                        StringBuilder stringBuilder = new StringBuilder();
//                        //遍历添加
//                        for (int i = 0; i < chars.length; i++) {
//                            stringBuilder.append(chars[i]);
//                            //触发点添加空格
//                            if (i % size == 0) {
//                                stringBuilder.append(" ");
//                            }
//                        }
//                        String s2 = stringBuilder.toString();
//                    }
//                } else if (s.length() == size) {//等于最小触发的时候
//                    s = s + " ";
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//    }
}
