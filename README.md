# TreeRecyclerView

#树形的reyclerView.示例图：

![image](https://github.com/Jlanglang/TreeRecyclerView/blob/master/image/20180928114547.png)
![image](https://github.com/Jlanglang/TreeRecyclerView/blob/master/image/20180928114623.png)
![image](https://github.com/Jlanglang/TreeRecyclerView/blob/master/image/20180928114648.png)
![image](https://github.com/Jlanglang/TreeRecyclerView/blob/master/image/20180928120846.png)
![image](https://github.com/Jlanglang/TreeRecyclerView/blob/master/image/20180928120934.png)

#####以上示例都只使用了一个RecyclerView，没有嵌套

# 要点:
1.可以通过后台控制Item的展示.

2.TreeRecyclerAdapter,可以展开,折叠.多级展示

3.支持Empty,LoadMore,可以添加headview和footview,侧滑删除,分类索引

4.item的样式可以实现复用,与baseItemData的type对应,实现复用

# 依赖方式
```
 implementation 'com.github.Jlanglang:TreeRecyclerView:'Latest release'
```
根build.gradle里面添加
```
 repositories {
     maven { url 'https://jitpack.io' }
 }
```
# 目录介绍
+ 1.Adapter
  * Wrapper------扩展Adapter的wrapper目录
     * BaseWrapper  --------wrapper基类
     * HeaderAndFootWrapper  --------添加头部的wrapper，foot废弃，实用性不强.
     * LoadingWrapper  --------加载页wrapper，可配置空，加载中，加载更多等显示状态.
     * SwipeWrapper --------支持侧滑删除的wrapper
  - ItemManager --------接口,管理Adapter刷新,增删操作
  - TreeRecyclerAdapter ----多级列表,树形结构的adapter
  - TreeRecyclerViewType ----多级列表的显示样式,枚举

 + 2.base
     - BaseItemData-----item的数据要求.javabean需要继承该类.
     - BaseRecyclerAdapter --------封装的Adapter基类
     - ViewHolder----封装的通用viewHolder

 + 3.factory
   - ItemConfig ----注册item的class,配置样式，通过
   - ItemHelperFactory----通过class生成BaseItem的工厂类,查找子item等功能
 + 4.item
    - SwipeItem ----侧滑删除,需实现该接口
    - TreeItem  ----树形结构子item
    - TreeItemGroup ----树形结构父item
    - TreeSelectItemGroup ----支持保存选中状态的树形结构父item
    - TreeSortItem ----索引的父item



### QQ交流群:493180098


