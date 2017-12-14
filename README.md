# TreeRecyclerView
树形的reyclerView.使用可以参考demo.

# 要点:
1.可以通过后台控制Item的展示.

2.TreeRecyclerAdapter,可以展开,折叠.多级展示

3.支持Empty,LoadMore,可以添加headview和footview,侧滑删除,分类索引

4.item的样式可以实现复用,与baseItemData的type对应,实现复用



# 目录介绍
+ 1.Adapter
  * Wapper------扩展Adapte的wapper目录
     * EmptyWapper  --------当无数据时显示页面.
     * HeaderAndFootWapper --------添加头部view和尾部view
  - ItemManager --------接口,管理Adatper刷新,增删操作
  - TreeRecyclerAdapter ----多级列表,树形结构的adapter
  - TreeRecyclerViewType ----多级列表的显示样式,枚举

 + 2.base
     - BaseItemData-----item的数据要求.javabean需要继承该类.
     - BaseRecyclerAdapter --------封装的Adatper基类
     - ViewHolder----封装的通用viewHodler

 + 3.factory
   - ItemConfig ----添加item的class,配置样式
   - Itemfactory----通过class生成BaseItem的工厂类
 + 4.item
    - SwipeItem ----侧滑删除,需实现该接口
    - TreeItem  ----树形结构子item
    - TreeItemGroup ----树形结构父item
    - TreeSelectItemGroup ----支持保存选中状态的树形结构父item
    - TreeSortItem ----索引的父item



### QQ交流群:493180098


