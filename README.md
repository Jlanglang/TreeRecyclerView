# TreeRecyclerView

<a href="https://996.icu"><img src="https://img.shields.io/badge/link-996.icu-red.svg" alt="996.icu" /></a>

[![996.icu](https://img.shields.io/badge/link-996.icu-red.svg)](https://996.icu)

[![LICENSE](https://img.shields.io/badge/license-Anti%20996-blue.svg)](https://github.com/996icu/996.ICU/blob/master/LICENSE)

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
 implementation 'com.github.Jlanglang:TreeRecyclerView:'1.2.8.4'
```
根build.gradle里面添加
```
 repositories {
     maven { url 'https://jitpack.io' }
 }
```

# 如何使用,这里只列出折叠的使用方法:
## 一.你需要创建一个adapter:
```
 //根据item的状态展示,可折叠
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

## 三.写具体展示的item
`注意! 使用这个,没有写ViewHolder的概念,只有TreeItem和TreeItemGroup` 子和父级.
##### 父级示例:
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

---
##### 子级示例:
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
 
##### 怎么创建Item呢?
有两种方法:
###### 第一种.在javabean上使用注解:
``` 
 @TreeDataType(iClass = AreaItem.class)
  public class AreasBean{
  ...
  }
```
调用`ItemHelperFactory.createItems()`,直接传入bean对象集合生成
```
 ItemHelperFactory.createItems(data.getAreas(),  this);
```

###### 第二种.自己传入item的class,创建item

```
 ItemHelperFactory.createItems(cityBeen, ProvinceItemParent.class, null);
```


---
## 四.如何更新adapter:
```
treeRecyclerAdapter.getItemManager().replaceAllItem(items);// 替换全部
treeRecyclerAdapter.getItemManager().addItems(items);// 添加一组
```

## 五,如何设置Item点击:
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


# 如果不想写上面的Item类怎么办? 写一次性的.不想复用的.

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


### QQ交流群:493180098


