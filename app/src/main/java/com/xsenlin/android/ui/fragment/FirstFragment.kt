package com.xsenlin.android.ui.fragment

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bigkoo.convenientbanner.holder.Holder
import com.xsenlin.android.R
import com.xsenlin.android.databinding.ItemActivityBinding
import com.xsenlin.android.databinding.ItemBannerBinding
import com.xsenlin.android.databinding.ItemGoodsBinding
import com.xsenlin.android.databinding.ItemTitleLabelBinding
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

    inner class MultiViewHolder(val binding: ViewDataBinding, val viewType: Int) : RecyclerView.ViewHolder(binding.root) {

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

        fun bindTo(position: Int, any: Any) {
            if (binding is ItemBannerBinding) {
                val list = any as BannerInfoList
                binding.banners = list
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

            } else if (binding is ItemTitleLabelBinding) {
                binding.title = any as TitleLabel
            } else if (binding is ItemActivityBinding) {
                var curCol = position % GRID_COLUMNS
                binding.columns = GRID_COLUMNS
                binding.horizontalStart = curCol == 0
                binding.horizontalEnd = curCol == GRID_COLUMNS - 1
                binding.activity = any as ActivitySimple
            } else if (binding is ItemGoodsBinding) {
                var curCol = position % GRID_COLUMNS
                binding.columns = GRID_COLUMNS
                binding.horizontalStart = curCol == 0
                binding.horizontalEnd = curCol == GRID_COLUMNS - 1
                binding.goods = any as GoodsSimple
            }
        }
    }

    inner class HomeFirstAdapter(val inflater: LayoutInflater, var data: HomeFirstGroup) :
            RecyclerView.Adapter<MultiViewHolder>() {

        override fun getItemViewType(position: Int): Int = data?.getTypeByPosition(position)

        override fun getItemCount(): Int = data?.getTotalCount()

        override fun onBindViewHolder(holder: MultiViewHolder, position: Int) {
            holder.bindTo(data.getRelativePosition(position), data.get(position))
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MultiViewHolder? {
            when (viewType) {
                HomeFirstGroup.TYPE_BANNER -> return MultiViewHolder(ItemBannerBinding.inflate(inflater, parent, false), viewType)
                HomeFirstGroup.TYPE_TITLE -> return MultiViewHolder(ItemTitleLabelBinding.inflate(inflater, parent, false), viewType)
                HomeFirstGroup.TYPE_ACTIVITIES -> return MultiViewHolder(ItemActivityBinding.inflate(inflater, parent, false), viewType)
                HomeFirstGroup.TYPE_GOODS -> return MultiViewHolder(ItemGoodsBinding.inflate(inflater, parent, false), viewType)
            }

            var layoutResId = when (viewType) {
                HomeFirstGroup.TYPE_STORIES -> R.layout.item_story
                else -> throw NullPointerException("Not found the type $viewType")
            }
            return MultiViewHolder(DataBindingUtil.inflate(inflater, layoutResId, parent, false), viewType)
        }
    }

    inner class HomeFirstSpanSizeLookup(var refer: HomeFirstGroup) : SpanSizeLookup() {
        override fun getSpanSize(position: Int): Int = when(refer.getTypeByPosition(position)) {
            HomeFirstGroup.TYPE_BANNER, HomeFirstGroup.TYPE_TITLE -> GRID_COLUMNS
            else -> 1
        }

    }
}