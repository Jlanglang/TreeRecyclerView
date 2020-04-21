package com.baozi.demo.activity;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.CheckBox;
import android.widget.TextView;

import com.baozi.demo.R;
import com.baozi.demo.item.cart.CartBean;
import com.baozi.demo.item.cart.CartItem;
import com.baozi.treerecyclerview.adpater.TreeRecyclerAdapter;
import com.baozi.treerecyclerview.factory.ItemHelperFactory;
import com.baozi.treerecyclerview.item.TreeItem;
import com.baozi.treerecyclerview.item.TreeSelectItemGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by a123 on 2018/6/5.
 * 购物车列表
 */
public class CartAt extends AppCompatActivity {
    private TreeRecyclerAdapter adapter = new TreeRecyclerAdapter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        RecyclerView rv_content = findViewById(R.id.rv_content);
        rv_content.setLayoutManager(new LinearLayoutManager(this));
        rv_content.setAdapter(adapter);
        List<CartBean> beans = new ArrayList<>();
        beans.add(new CartBean(3));

        List<TreeItem> groupItem = ItemHelperFactory.createItems(beans);

        adapter.getItemManager().replaceAllItem(groupItem);
        adapter.setOnItemClickListener((viewHolder, position) -> {
            //因为外部和内部会冲突
            TreeItem item = adapter.getData(position);
            if (item != null) {
                item.onClick(viewHolder);
            }
            notifyPrice();
        });
        initView();
        notifyPrice();
    }

    /**
     * 更新价格
     */
    public void notifyPrice() {
        boolean isSelectAll = true;//默认全选
        int price = 0;
        for (TreeItem item : adapter.getData()) {
            if (item instanceof TreeSelectItemGroup) {
                TreeSelectItemGroup group = (TreeSelectItemGroup) item;
                if (!group.isSelect()) {//是否有选择的子类
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
                    if (child instanceof CartItem) {
                        Integer data = (Integer) child.getData();
                        price += data;
                    }
                }
            }
        }
        adapter.notifyDataSetChanged();

        ((TextView) findViewById(R.id.tv_all_price)).setText("￥" + price);
        CheckBox checkBox = findViewById(R.id.cb_all_check);
        checkBox.setChecked(isSelectAll);
    }

    public void initView() {
        CheckBox checkBox = findViewById(R.id.cb_all_check);
        checkBox.setOnClickListener(v -> {
            for (TreeItem item : adapter.getData()) {
                if (item instanceof TreeSelectItemGroup) {
                    TreeSelectItemGroup group = (TreeSelectItemGroup) item;
                    group.selectAll(((CheckBox) v).isChecked());
                }
            }
            notifyPrice();
        });
    }

}
