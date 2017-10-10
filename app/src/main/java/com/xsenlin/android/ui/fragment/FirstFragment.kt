package com.xsenlin.android.ui.fragment

import android.content.Context
import android.content.res.Resources
import android.databinding.ViewDataBinding
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
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
    private var mAdapter: HomeFirstAdapter? = null
    private val mLayoutManger: GridLayoutManager by lazy { GridLayoutManager(context, GRID_COLUMNS) }
    private val mDecoration: HomeFirstItemDecoration by lazy { HomeFirstItemDecoration(resources) }

    private val mData by lazy { HomeFirstGroup.createExapme(GRID_COLUMNS, resources) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = getLayoutInflater(savedInstanceState)
        mAdapter = HomeFirstAdapter(inflater, mData)
        mLayoutManger.spanSizeLookup = HomeFirstSpanSizeLookup(mData)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mSwipeRefresh = view!!.findViewById(R.id.swipe_refresh_widget)
        mSwipeRefresh!!.setOnRefreshListener(this)

        mScrolling = view!!.findViewById(R.id.recycler_view)
        mScrolling!!.addItemDecoration(mDecoration)
        mScrolling!!.adapter = mAdapter
        mScrolling!!.layoutManager = mLayoutManger
    }

    override fun onDestroyView() {
        mSwipeRefresh?.setOnRefreshListener(null)
        mScrolling?.removeItemDecoration(mDecoration)
        mScrolling?.layoutManager = null
        mScrolling?.adapter = null
        super.onDestroyView()
    }

    override fun onRefresh() {
        mSwipeRefresh!!.isRefreshing = false
    }

    inner open class MultiViewHolder(val binding: ViewDataBinding, val viewType: Int) : RecyclerView.ViewHolder(binding.root) {

        private var mAdapter: IndependentAdapter<IndependentHolder>? = null
        private val mLayoutManager: LinearLayoutManager by lazy { LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false) }

        init {
            if (binding is ItemBannerBinding) {
                setupItemBanner(binding)
            }
        }

        constructor(binding: ViewDataBinding, viewType: Int, inflater: LayoutInflater) : this(binding, viewType) {
            if (binding is ItemStoryBinding) {
                mAdapter = IndependentAdapter<IndependentHolder>(inflater, viewType)
                setupItemStory(binding)
            }
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

        open fun bindTo(any: Any, count: Int) {
            if (binding is ItemBannerBinding) {
                val list = any as BannerInfoList
                binding.banners = list
            } else if (binding is ItemTitleLabelBinding) {
                binding.title = any as TitleLabel
            } else if (binding is ItemActivityBinding) {
                binding.activity = any as ActivitySimple
            } else if (binding is ItemStoryBinding) {
                mAdapter!!.data = any as StorySimple.StorySimpleList
            } else if (binding is ItemGoodsBinding) {
                binding.goods = any as GoodsSimple
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

        fun setupItemStory(binding: ItemStoryBinding) {
            binding.recyclerView.adapter = mAdapter
            binding.recyclerView.layoutManager = mLayoutManager
            binding.recyclerView.addItemDecoration(mDecoration)
            binding.recyclerView.setHasFixedSize(true)
        }
    }

    inner class HomeFirstAdapter(val inflater: LayoutInflater, var data: HomeFirstGroup) :
            RecyclerView.Adapter<MultiViewHolder>() {

        override fun getItemViewType(position: Int): Int = data?.getTypeByPosition(position)

        override fun getItemCount(): Int = data?.getTotalCount()

        override fun onBindViewHolder(holder: MultiViewHolder, position: Int) {
            holder.bindTo(data.get(position), data.getChildCountByType(holder.viewType))
        }

        override fun onViewRecycled(holder: MultiViewHolder) {
            super.onViewRecycled(holder)
//            Log.d(TAG, "onViewRecycled " + Integer.toHexString(holder.hashCode()) + " parentType " + HomeFirstGroup.typeToString(holder.viewType))
        }

        override fun onViewAttachedToWindow(holder: MultiViewHolder) {
            super.onViewAttachedToWindow(holder)
//            Log.d(TAG, "onViewAttachedToWindow " + Integer.toHexString(holder.hashCode()) + " parentType " + HomeFirstGroup.typeToString(holder.viewType))
        }

        override fun onViewDetachedFromWindow(holder: MultiViewHolder) {
//            Log.d(TAG, "onViewDetachedFromWindow " + Integer.toHexString(holder.hashCode()) + " parentType " + HomeFirstGroup.typeToString(holder.viewType))
            super.onViewDetachedFromWindow(holder)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MultiViewHolder {
            val holder: MultiViewHolder = when (viewType) {
                HomeFirstGroup.TYPE_HEADER -> MultiViewHolder(ItemBannerBinding.inflate(inflater, parent, false), viewType)
                HomeFirstGroup.TYPE_TITLE -> MultiViewHolder(ItemTitleLabelBinding.inflate(inflater, parent, false), viewType)
                HomeFirstGroup.TYPE_ACTIVITY -> MultiViewHolder(ItemActivityBinding.inflate(inflater, parent, false), viewType)
                HomeFirstGroup.TYPE_STORIES -> MultiViewHolder(ItemStoryBinding.inflate(inflater, parent, false), viewType, inflater)
                HomeFirstGroup.TYPE_GOODS -> MultiViewHolder(ItemGoodsBinding.inflate(inflater, parent, false), viewType)
                else -> throw NullPointerException("Not found the parentType $viewType")
            }
//            Log.d(TAG, "onCreateViewHolder " + Integer.toHexString(holder.hashCode()) + " parentType " + HomeFirstGroup.typeToString(viewType))
            return holder
        }
    }

    // 适用于独立列表的Adapter，List in List
    inner class IndependentAdapter<H : MultiViewHolder>(val inflater: LayoutInflater, val parentType: Int) : RecyclerView.Adapter<H>() {
        var data: StorySimple.StorySimpleList? = null

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): H {
            return when (parentType) {
                HomeFirstGroup.TYPE_STORIES -> IndependentHolder(ItemStoryActualBinding.inflate(inflater, parent, false))
                else -> throw NullPointerException("Not found the parentType $parentType")
            } as H
        }

        override fun getItemCount(): Int = data!!.count

        override fun onBindViewHolder(holder: H, position: Int) {
            holder.bindTo(data!!.get(position), data!!.count)
        }
    }

    inner class IndependentHolder(binding: ItemStoryActualBinding) : MultiViewHolder(binding, HomeFirstGroup.TYPE_STORIES) {
        override fun bindTo(any: Any, count: Int) {
            if ((binding is ItemStoryActualBinding)) {
                binding.story = any as StorySimple
            }
        }
    }

    inner class HomeFirstSpanSizeLookup(var refer: HomeFirstGroup) : SpanSizeLookup() {
        override fun getSpanSize(position: Int): Int = when (refer.getTypeByPosition(position)) {
            HomeFirstGroup.TYPE_HEADER, HomeFirstGroup.TYPE_TITLE, HomeFirstGroup.TYPE_STORIES -> GRID_COLUMNS
            else -> 1
        }
    }

    /**
     * 使用下面
     */
    class HomeFirstItemDecoration(val res: Resources) : RecyclerView.ItemDecoration() {

        val marginHorizontal by lazy { res.getDimensionPixelOffset(R.dimen.first_item_margin_horizontal) }
        val intervalHorizontal by lazy { res.getDimensionPixelOffset(R.dimen.first_item_interval_horizontal) }
        val intervalVertical by lazy { res.getDimensionPixelOffset(R.dimen.first_item_interval_vertical) }

        private val mTempOutRect: Rect by lazy {
            Rect()
        }

        override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
            super.onDrawOver(c, parent, state)
        }

        override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
            super.onDraw(c, parent, state)
            val childCount = parent.childCount;

            val dividerPaint = Paint()
            for (i in 0..childCount - 1) {
                val view = parent.getChildAt(i);

                // 将分割距离用Item的背景填充
                // 暂时只实现ColorDrawable的Item
                val background = view.background

                if (background is ColorDrawable) {

                    mTempOutRect.set(0, 0, 0, 0)
                    getItemOffsets(mTempOutRect, view, parent, state)

                    val left   = view.left   - mTempOutRect.left
                    val right  = view.right  + mTempOutRect.right
                    val top    = view.top    - mTempOutRect.top
                    val bottom = view.bottom + mTempOutRect.bottom

                    dividerPaint.color = background.color
                    c.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), dividerPaint);
                }
            }
        }

        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            super.getItemOffsets(outRect, view, parent, state)

            val absolutePosition = (view.layoutParams as RecyclerView.LayoutParams).viewLayoutPosition // 获取Item的position

            val adapter = parent.adapter
            val viewType = adapter.getItemViewType(absolutePosition)

            // 过滤ItemType
            if (viewType != HomeFirstGroup.TYPE_ACTIVITY &&
                    viewType != HomeFirstGroup.TYPE_GOODS &&
                    viewType != HomeFirstGroup.TYPE_DEFAULT) {
                return
            }

            val position =// Relative position
                    if (adapter is HomeFirstAdapter) {
                        adapter.data.getRelativePosition(absolutePosition)
                    } else if (adapter is IndependentAdapter) {
                        absolutePosition
                    } else 0
            val layoutManager = parent.layoutManager

            if (layoutManager is GridLayoutManager) {
//                val relativeCount = if (adapter is HomeFirstAdapter) adapter.data.getChildCountByType(viewType) else 0
                val columns = layoutManager.spanCount
                val curCol = position % columns
                val horizontalStart = curCol == 0
                val horizontalEnd = curCol == columns - 1
//                val verticalStart = relativePosition < columns
//                val verticalEnd = relativePosition > relativeCount - columns
                // 计算间隔总和，再把间隔平摊到每个Item
                val extTotal = marginHorizontal * 2
                val intTotal = intervalHorizontal * (columns - 1)
                val avgTotal = (extTotal + intTotal) / columns
                if (horizontalStart) {
                    outRect.left = marginHorizontal
                    outRect.right = (avgTotal - marginHorizontal)
                } else if (horizontalEnd) {
                    outRect.left = (avgTotal - marginHorizontal)
                    outRect.right = marginHorizontal
                } else {
                    outRect.left = (avgTotal / 2)
                    outRect.right = (avgTotal / 2)
                }
            } else if (layoutManager is LinearLayoutManager) {
                // Start and End区别对待
                val horizontalStart = position == 0
                val horizontalEnd = position == adapter.itemCount - 1
                outRect.left = if (horizontalStart) marginHorizontal else intervalHorizontal / 2
                outRect.right = if (horizontalEnd) marginHorizontal else intervalHorizontal / 2
            }

            outRect.bottom = intervalVertical
        }
    }
}