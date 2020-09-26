package com.baozi.demo.activity

import android.graphics.Rect
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.baozi.demo.R
import com.baozi.treerecyclerview.adpater.TreeRecyclerAdapter
import com.baozi.treerecyclerview.adpater.TreeRecyclerType
import com.baozi.treerecyclerview.adpater.wrapper.LoadingWrapper
import com.baozi.treerecyclerview.annotation.TreeDataType
import com.baozi.treerecyclerview.base.ViewHolder
import com.baozi.treerecyclerview.factory.ItemHelperFactory
import com.baozi.treerecyclerview.item.TreeItem
import kotlinx.android.synthetic.main.at_test.*
import java.util.*

class TestActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {
    val adapter = LoadingWrapper(TreeRecyclerAdapter(TreeRecyclerType.SHOW_ALL))
    override fun onRefresh() {
        loadData()
    }

    fun loadData() {
        sw_refresh.isRefreshing = true
        val imageList = Arrays.asList(
                TestGroup("1"),
                TestGroup("1"),
                TestGroup("1"),
                TestGroup("1"),
                TestGroup("1"),
                TestGroup("1")
        )
        val createItems = ItemHelperFactory.createItems(imageList)
        adapter.itemManager.replaceAllItem(createItems)
        sw_refresh.isRefreshing = false
        adapter.setType(LoadingWrapper.Type.REFRESH_OVER)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.at_test)
        sw_refresh.setOnRefreshListener(this)
        initRv()
        onRefresh()
    }

    private fun initRv() {
        rv_content.layoutManager = LinearLayoutManager(this)
        rv_content.adapter = adapter
        adapter.setLoadMore(object : LoadingWrapper.LoadMoreItem(this) {
            override fun getLoadMoreLayout(): Int = 0

            override fun getLoadOverLayout(): Int = 0

            override fun getMinPageSize(): Int = 5
        })
        adapter.setLoadMoreListener {
        }
    }
}


@TreeDataType(iClass = TestItemGroup::class)
data class TestGroup(
        val str: String = ""
)

class TestItemGroup : TreeItem<String>() {
    val adapter = TreeRecyclerAdapter(TreeRecyclerType.SHOW_ALL);
    private val pagerAdapter = object : PagerAdapter() {
        // 当要显示的图片可以进行缓存的时候，会调用这个方法进行显示图片的初始化，我们将要显示的ImageView加入到ViewGroup中，然后作为返回值返回即可
        override fun instantiateItem(view: ViewGroup, position: Int): Any {
            val inflate = LayoutInflater.from(view.context).inflate(R.layout.item_image, view, false)
            inflate.layoutParams.width = ViewPager.LayoutParams.MATCH_PARENT
            inflate.layoutParams.width = ViewPager.LayoutParams.WRAP_CONTENT
            view.addView(inflate);
            return inflate
        }

        override fun getCount(): Int {
            return adapter.itemCount
        }

        // 例如PagerAdapter只缓存三张要显示的图片，如果滑动的图片超出了缓存的范围，就会调用这个方法，将图片销毁
        override fun destroyItem(view: ViewGroup, position: Int, `object`: Any) {
            view.removeView(`object` as View)
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view === `object`
        }

    }

    override fun getLayoutId(): Int {
        return R.layout.item_test_group
    }

    override fun onBindViewHolder(viewHolder: ViewHolder) {
        val view = viewHolder.getView<RecyclerView>(R.id.rv_images)
        val vp_content = viewHolder.getView<ViewPager>(R.id.vp_content)

        view.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        if (view.adapter == null) {
            view.adapter = adapter;
        }
        val imageList = listOf(
                ImageBean("1", true),
                ImageBean("1"),
                ImageBean("1"),
                ImageBean("1"),
                ImageBean("1"),
                ImageBean("1")
        )
        val createItems = ItemHelperFactory.createItems(imageList)
        adapter.itemManager.replaceAllItem(createItems)
        adapter.setOnItemClickListener { viewHolder, position ->
            vp_content.currentItem = position
        }
        vp_content.adapter = pagerAdapter;
        vp_content.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {
            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
            }

            override fun onPageSelected(p0: Int) {
                adapter.getData().forEach {
                    (it as? ImageItem)?.apply {
                        data?.isSelect = it == adapter.getData(p0)
                    }
                }
                adapter.notifyDataSetChanged()
                view.smoothScrollToPosition(p0)
            }
        })
    }
}


@TreeDataType(iClass = ImageItem::class)
data class ImageBean(
        val url: String = "",
        var isSelect: Boolean = false
)

class ImageItem : TreeItem<ImageBean>() {
    override fun getLayoutId(): Int {
        return R.layout.item_image;
    }

    override fun onBindViewHolder(viewHolder: ViewHolder) {
        val context = viewHolder.itemView.context
        if (data?.isSelect==true) {
            viewHolder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
        } else {
            viewHolder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.white))
        }
    }

    override fun getItemOffsets(outRect: Rect, layoutParams: RecyclerView.LayoutParams, position: Int) {
        super.getItemOffsets(outRect, layoutParams, position)
        outRect.left = 10;
        outRect.right = 10;
        outRect.top = 10;
        outRect.bottom = 10;
    }

    override fun onClick(viewHolder: ViewHolder) {
        super.onClick(viewHolder)
    }
}
