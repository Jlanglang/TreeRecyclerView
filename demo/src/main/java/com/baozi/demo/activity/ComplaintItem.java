package com.baozi.demo.activity;

import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;

import com.baozi.demo.R;
import com.baozi.treerecyclerview.base.ViewHolder;
import com.baozi.treerecyclerview.item.TreeItem;

public class ComplaintItem extends TreeItem<Integer> {
    private String content = "";
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            content = s.toString();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.item_complaint;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder) {
        EditText view = viewHolder.getView(R.id.et_content);
        view.removeTextChangedListener((TextWatcher) view.getTag());
        view.setText(content);
        view.setTag(textWatcher);
        view.addTextChangedListener(textWatcher);
    }
}
