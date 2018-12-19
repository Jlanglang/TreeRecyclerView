package com.baozi.demo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.baozi.demo.R;
import com.baozi.demo.item.cart.CartGroupItem;
import com.baozi.treerecyclerview.adpater.TreeRecyclerAdapter;
import com.baozi.treerecyclerview.adpater.TreeRecyclerType;
import com.baozi.treerecyclerview.base.BaseRecyclerAdapter;
import com.baozi.treerecyclerview.base.ViewHolder;
import com.baozi.treerecyclerview.factory.ItemHelperFactory;
import com.baozi.treerecyclerview.item.TreeItem;
import com.baozi.treerecyclerview.item.TreeSelectItemGroup;

import java.util.Arrays;
import java.util.List;

/**
 * Created by a123 on 2018/6/5.
 * 购物车列表
 */
public class CartActivity extends Activity {
    private TreeRecyclerAdapter adapter = new TreeRecyclerAdapter();
    private List<TreeItem> groupItem;
    private boolean isSelectAll;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        RecyclerView rv_content = findViewById(R.id.rv_content);
        rv_content.setLayoutManager(new LinearLayoutManager(this));
        rv_content.setAdapter(adapter);

        List<String> integers = Arrays.asList("1", "1", "1", "1", "1");
        groupItem = ItemHelperFactory.createTreeItemList(integers, CartGroupItem.class, null);
        adapter.getItemManager().replaceAllItem(groupItem);

        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull ViewHolder viewHolder, int position) {
                //因为外部和内部会冲突
                TreeItem item = adapter.getData(position);
                if (item instanceof CartGroupItem) {
                    item.onClick(viewHolder);
                }
                notifyPrice();
            }
        });
        initView();
        notifyPrice();
    }

    /**
     * 更新价格
     */
    public void notifyPrice() {
        isSelectAll = true;//默认全选
        int price = 0;
        for (TreeItem item : groupItem) {
            if (item instanceof TreeSelectItemGroup) {
                TreeSelectItemGroup group = (TreeSelectItemGroup) item;
                if (!group.isChildCheck()) {//是否有选择的子类
                    //有一个没选则不是全选
                    isSelectAll = false;
                    continue;
                }
                if (!group.isSelectAll()) {//是否全选了子类
                    //有一个没选则不是全选
                    isSelectAll = false;
                }
                List<TreeItem> selectItems = group.getSelectItems();
                for (TreeItem child : selectItems) {
                    Integer data = (Integer) child.getData();
                    price += data;
                }
            }
        }
        adapter.notifyDataSetChanged();
        ((TextView) findViewById(R.id.tv_all_price)).setText("￥" + price);
        CheckBox checkBox = findViewById(R.id.cb_all_check);
        checkBox.setChecked(isSelectAll);
    }

    public void initView() {
        final CheckBox checkBox = findViewById(R.id.cb_all_check);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (TreeItem item : groupItem) {
                    if (item instanceof TreeSelectItemGroup) {
                        TreeSelectItemGroup group = (TreeSelectItemGroup) item;
                        group.selectAll(checkBox.isChecked());
                    }
                }
                notifyPrice();
            }
        });
    }
}
