package com.xsenlin.android.ui.fragment

import android.content.Context
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bigkoo.convenientbanner.holder.Holder
import com.xsenlin.android.R
import com.xsenlin.android.databinding.*
import com.xsenlin.android.ui.model.*
import com.xsenlin.android.ui.model.BannerInfo.BannerInfoList
import java.lang.RuntimeException

/**
 * Created by Dylan on 2017/9/6.
 * 包含Banner
 * in HomeFragment
 */
class FirstFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener {

    companion object {
        val TAG = "FirstFragment"

        fun newInstance(): FirstFragment {
            return FirstFragment()
        }
    }

    val GRID_COLUMNS by lazy {
        resources.getInteger(R.integer.first_grid_columns)
    }

    private var mSwipeRefresh: SwipeRefreshLayout? = null
    // 大致思路
    // 使用GridLayoutManger的SpanSizeLookup来实现首页复杂滚动布局
    // item_banner, item_tree, item_title, item_horizontal_scrolling
    private var mScrolling: RecyclerView? = null
    private var mLayoutManger: GridLayoutManager? = null
    private var mAdapter: HomeFirstAdapter? = null

    private val mData by lazy { HomeFirstGroup.createExapme(GRID_COLUMNS, resources) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAdapter = HomeFirstAdapter(getLayoutInflater(savedInstanceState), mData)
        mLayoutManger = GridLayoutManager(context, GRID_COLUMNS)
        mLayoutManger!!.spanSizeLookup = HomeFirstSpanSizeLookup(mData)

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_first, container, false)
        mSwipeRefresh = rootView.findViewById(R.id.swipe_refresh_widget)
        mSwipeRefresh!!.setOnRefreshListener(this)

        mScrolling = rootView.findViewById(R.id.recycler_view)
        mScrolling!!.adapter = mAdapter
        mScrolling!!.layoutManager = mLayoutManger
        return rootView
    }

    override fun onDestroyView() {
        mSwipeRefresh?.setOnRefreshListener(null)
        mScrolling?.layoutManager = null
        mScrolling?.adapter = null
        super.onDestroyView()
    }

    override fun onRefresh() {
        mSwipeRefresh!!.isRefreshing = false
    }

    inner open class MultiViewHolder<D : Any>(val binding: ViewDataBinding, val viewType: Int) : RecyclerView.ViewHolder(binding.root) {

        var inflater: LayoutInflater? = null

        constructor(binding: ViewDataBinding, viewType: Int, inflater: LayoutInflater) : this(binding, viewType) {
            this.inflater = inflater
        }

        inner class LocalImageHolderView : Holder<Integer> {
            override fun createView(context: Context): View {
                val imageView = ImageView(context)
                imageView!!.scaleType = ImageView.ScaleType.FIT_XY
                return imageView
            }

            override fun UpdateUI(context: Context, position: Int, data: Integer?) {
//            imageView!!.setImageResource(data!!)
            }
        }

        open fun bindTo(position: Int, any: D, count: Int) {
            if (binding is ItemBannerBinding) {
                val list = any as BannerInfoList
                binding.banners = list
                setupItemBanner(binding)
            } else if (binding is ItemTitleLabelBinding) {
                binding.title = any as TitleLabel
            } else {
                val curCol = position % GRID_COLUMNS
                val columns = GRID_COLUMNS
                val horizontalStart = curCol == 0
                val horizontalEnd = curCol == GRID_COLUMNS - 1

                if (binding is ItemActivityBinding) {
                    binding.columns = columns
                    binding.horizontalStart = horizontalStart
                    binding.horizontalEnd = horizontalEnd
                    binding.activity = any as ActivitySimple
                } else if (binding is ItemStoryBinding) {
                    binding.columns = columns
                    binding.horizontalStart = horizontalStart
                    binding.horizontalEnd = horizontalEnd
                    binding.stories = any as StorySimple.StorySimpleList
                    setupItemStory(binding)
                } else if (binding is ItemGoodsBinding) {
                    binding.columns = columns
                    binding.horizontalStart = horizontalStart
                    binding.horizontalEnd = horizontalEnd
                    binding.goods = any as GoodsSimple
                }
            }
        }

        fun setupItemBanner(binding: ItemBannerBinding) {

            //自定义你的Holder，实现更多复杂的界面，不一定是图片翻页，其他任何控件翻页亦可。
//                binding.convevinetBanner.setPages(
//                        CBViewHolderCreator<LocalImageHolderView>() {
//                            override fun  createHolder():LocalImageHolderView {
//                                return LocalImageHolderView()
//                            }
//                        }, list.getBannerImageUrls())
            //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
//                        .setPageIndicator(arrayOf(R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused))
            //设置指示器的方向
//                        .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)

            //设置翻页的效果，不需要翻页效果可用不设
            //.setPageTransformer(Transformer.DefaultTransformer);    集成特效之后会有白屏现象，新版已经分离，如果要集成特效的例子可以看Demo的点击响应。
//        convenientBanner.setManualPageable(false);//设置不能手动影响

        }

        private var mStoryAdapter: StoryAdapter? = null
        private var mStoryLayoutManager: LinearLayoutManager? = null

        fun setupItemStory(binding: ItemStoryBinding) {
            if (mStoryAdapter == null && mStoryLayoutManager == null) {
                mStoryAdapter = StoryAdapter(inflater!!, binding.stories!!)
                mStoryLayoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)

                binding.recyclerView.adapter = mStoryAdapter
                binding.recyclerView.layoutManager = mStoryLayoutManager
                binding.recyclerView.setHasFixedSize(true)
            }

        }

        inner class StoryAdapter(val inflater: LayoutInflater, val data: StorySimple.StorySimpleList) : RecyclerView.Adapter<StoryHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): StoryHolder = StoryHolder(ItemStoryActualBinding.inflate(inflater, parent, false))

            override fun getItemCount(): Int = data.count

            override fun onBindViewHolder(holder: StoryHolder, position: Int) {
                holder.bindTo(position, data.get(position), data.count)
            }

        }

        inner class StoryHolder(binding: ItemStoryActualBinding) : MultiViewHolder<StorySimple>(binding, HomeFirstGroup.TYPE_STORIES) {
            override fun bindTo(position: Int, story: StorySimple, count: Int) {
                if (!(binding is ItemStoryActualBinding)) throw RuntimeException("No story")
                binding.story = story
                binding.horizontalStart = position == 0
                binding.horizontalEnd = position == count - 1
            }
        }
    }

    inner class HomeFirstAdapter(val inflater: LayoutInflater, var data: HomeFirstGroup) :
            RecyclerView.Adapter<MultiViewHolder<Any>>() {

        override fun getItemViewType(position: Int): Int = data?.getTypeByPosition(position)

        override fun getItemCount(): Int = data?.getTotalCount()

        override fun onBindViewHolder(holder: MultiViewHolder<Any>, position: Int) {
            holder.bindTo(data.getRelativePosition(position), data.get(position), data.getActualCountByType(holder.viewType))
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MultiViewHolder<Any>? {
            when (viewType) {
                HomeFirstGroup.TYPE_HEADER -> return MultiViewHolder(ItemBannerBinding.inflate(inflater, parent, false), viewType)
                HomeFirstGroup.TYPE_TITLE -> return MultiViewHolder(ItemTitleLabelBinding.inflate(inflater, parent, false), viewType)
                HomeFirstGroup.TYPE_ACTIVITY -> return MultiViewHolder(ItemActivityBinding.inflate(inflater, parent, false), viewType)
                HomeFirstGroup.TYPE_STORIES -> return MultiViewHolder(ItemStoryBinding.inflate(inflater, parent, false), viewType, inflater)
                HomeFirstGroup.TYPE_GOODS -> return MultiViewHolder(ItemGoodsBinding.inflate(inflater, parent, false), viewType)
                else -> throw NullPointerException("Not found the type $viewType")
            }
        }
    }

    inner class HomeFirstSpanSizeLookup(var refer: HomeFirstGroup) : SpanSizeLookup() {
        override fun getSpanSize(position: Int): Int = when (refer.getTypeByPosition(position)) {
            HomeFirstGroup.TYPE_HEADER, HomeFirstGroup.TYPE_TITLE, HomeFirstGroup.TYPE_STORIES -> GRID_COLUMNS
            else -> 1
        }

    }
}