# 2021-3-7更新

1.优化了获取注解的逻辑,减少了读取class的注解的次数

2.ItemManager新增了setTag.getTag方法,可以通过该方法绑定Activity或者Fragment等对象.在TreeItem可以获取该对象

3.TreeItem新增了init方法,可以做一些初始化设置,比如默认是否展开等等

4.新增了getItemOffsets的重写函数,与ItemDecoration接口保持一致(旧的方法依旧保留)



# 更新

新增androidx分支.转换支持androidX
版本号:v1.3.1-androidx

新增Kotlin版本分支.
版本号v1.3.1-kt

<a href="https://996.icu"><img src="https://img.shields.io/badge/link-996.icu-red.svg" alt="996.icu" /></a>
[![LICENSE](https://img.shields.io/badge/license-Anti%20996-blue.svg)](https://github.com/996icu/996.ICU/blob/master/LICENSE)

# 特点

```
1.支持实现树形结构列表及大部分列表样式
2.每一种条目的逻辑独立,充分解耦,支持复用
3.支持组件化
4.支持服务器动态下发,动态组合
5.侵入性低,不修改RecyclerView原有特性
```


# 示例图：

![image](https://github.com/Jlanglang/TreeRecyclerView/blob/master/image/20180928114547.png)
![image](https://github.com/Jlanglang/TreeRecyclerView/blob/master/image/20180928114623.png)
![image](https://github.com/Jlanglang/TreeRecyclerView/blob/master/image/20180928114648.png)
![image](https://github.com/Jlanglang/TreeRecyclerView/blob/master/image/20180928120846.png)
![image](https://github.com/Jlanglang/TreeRecyclerView/blob/master/image/20180928120934.png)
![image](https://github.com/Jlanglang/TreeRecyclerView/blob/master/image/20180928145931.png)
##### 以上示例都只使用了一个RecyclerView，没有嵌套


# 依赖方式
```
 implementation 'com.github.Jlanglang:TreeRecyclerView:1.3.1.1'
```
根build.gradle里面添加
```
 repositories {
     maven { url 'https://jitpack.io' }
 }
```

---


# 如何使用:

## 一.你需要创建一个adapter:
```
 //可折叠
 TreeRecyclerAdapter treeRecyclerAdapter = new TreeRecyclerAdapter(TreeRecyclerType.SHOW_EXPAND);
```

## 二.你需要选择一种展开方式
```
public enum TreeRecyclerType {
    /**
     * 显示所有,不可展开折叠
     * 适用场景,不需要折叠，默认显示所有item
     */
    SHOW_ALL,

    /**
     * 根据isExpand的状态显示展开与折叠,
     * 适用场景,多级的data数据展示,保存展开状态
     */
    SHOW_EXPAND,

    /**
     * 默认只显示第一级,点击展开,折叠不会影响子级展开折叠
     * 适用场景,一级一级展开，保存展开状态
     */
    SHOW_DEFAULT
}
```

构造函数传入,不传默认则使用`SHOW_DEFAULT`.

# 三.怎么写Item

注意! 使用这个,没有写`ViewHolder`的概念,只有`TreeItem`和`TreeItemGroup`.子级和父级.

#### 父级示例:
```
/**
* 城市
 */
public class CountyItemParent extends TreeItemGroup<ProvinceBean.CityBean> {//泛型代表绑定的javabean

    //创建子TreeItem.
    @Override
    public List<TreeItem> initChildList(ProvinceBean.CityBean data) {
        return ItemHelperFactory.createItems(data.getAreas(),  this);
    }

    //该级具体展示的Layout
    @Override
    public int getLayoutId() {
        return R.layout.item_two;
    }

    //view和data绑定
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder) {
        holder.setText(R.id.tv_content, data.getCityName());
    }
}
```


#### 子级示例:
```
/**
* 县
 */
public class AreaItem extends TreeItem<ProvinceBean.CityBean.AreasBean> {//泛型代表绑定的javabean

    @Override
    public int getLayoutId() {
        return R.layout.item_three;
    }
    //绑定操作,
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder) {
        holder.setText(R.id.tv_content, data.getAreaName());
    }
    //这个Item,在RecyclerView的每行所占比,只有RecyclerView设置了GridLayoutManager才会生效.
    //这里之所以用除法,是为了可以做到,只改变GridLayoutManager的总数,无需改变每个Item,当然也可以直接返回一个int值.
    @Override
    public int getSpanSize(int maxSpan) {
        return maxSpan / 6;
    }
}

```

# 怎么创建Item:

有两种方法:

#### 第一种:

在javabean上使用注解,
```
 @TreeDataType(iClass = AreaItem.class)
  public class AreasBean{
  ...
  }
```
然后传入bean对象
```
 ItemHelperFactory.createItems(list,  treeItemGroup);
```

#### 第二种:

直接传入bean对象和item的class,
```
 ItemHelperFactory.createItems(list, Item.class, treeItemGroup);
```

---

# 四.如何更新adapter:

增删该查都有.

```
treeRecyclerAdapter.getItemManager().replaceAllItem(items);// 替换全部Item
treeRecyclerAdapter.getItemManager().addItems(items);// 添加一组Item
treeRecyclerAdapter.getItemManager().removeItems(items);// 添加一组Item
```

#### 在item里面也是可以更新的:

```
    @Override
    public void onClick(ViewHolder viewHolder) {
        super.onClick(viewHolder);
        getItemManager().notifyDataChanged();
    }
```

# 五,如何设置Item点击:

1.重写`TreeItem`的`onClick()`

2.`adapter`设置`setOnItemClickListener`
```
 adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull ViewHolder viewHolder, int position) {

            }
        });
```
#####  注意.二者冲突.
`ItemClickListener`优先级高于`TreeItem`的`onClick`.


# 如果不想写上面的Item类怎么办?

使用`SimpleTreeItem`

```
 ArrayList<TreeItem> items = new ArrayList<>();
        for (Pair itemPair : itemPairs) {
            SimpleTreeItem simpleTreeItem = new SimpleTreeItem(R.layout.item_mine)//传入布局id.
                    .onItemBind(viewHolder -> {

                    })
                    .onItemClick(viewHolder -> {

                    });
            simpleTreeItem.setData(itemPair);
            items.add(simpleTreeItem);
        }
 adapter.getItemManager().replaceAllItem(items);
```

# 混淆
```
-keep public class * extends  com.baozi.treerecyclerview.item.TreeItem {}
-keep public class * extends android.support.annotation.**

```

# 最后

直接设置就行了.adapter可以不先setData
可以直接setAdapter.然后`adapter.getItemManager().replaceAllItem(items);`


```
 recyclerView.setAdapter(adapter);
```



更多效果.见demo
### 欢迎大家留言,提出问题. QQ交流群:493180098




