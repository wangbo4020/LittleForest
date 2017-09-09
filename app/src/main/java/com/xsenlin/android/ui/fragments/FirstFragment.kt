package com.xsenlin.android.ui.fragments

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xsenlin.android.R

/**
 * Created by Dylan on 2017/9/6.
 * 包含Banner
 * in HomeFragment
 */
class FirstFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener {

    companion object {
        val TAG = "FirstFragment"

        fun newInstance() : FirstFragment {
            return FirstFragment()
        }
    }

    private var mSwipeRefresh : SwipeRefreshLayout? = null
    // 大致思路
    // 使用GridLayoutManger的SpanSizeLookup来实现首页复杂滚动布局
    // item_banner, item_tree, item_title, item_horizontal_scrolling
    private var mScrolling : RecyclerView? = null
    private var mLayoutManger : GridLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mLayoutManger = GridLayoutManager(context, 2)
        mLayoutManger!!.spanSizeLookup = object : SpanSizeLookup(){
            override fun getSpanSize(position: Int): Int {
                return when(position) {
                    0 -> 1
                    else -> 2
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_first, container, false)
        mSwipeRefresh = rootView.findViewById(R.id.swipe_refresh_widget)
        mSwipeRefresh!!.setOnRefreshListener(this)

        mScrolling = rootView.findViewById(R.id.recycler_view)
        mScrolling!!.layoutManager = mLayoutManger
        return rootView
    }

    override fun onDestroyView() {
        mSwipeRefresh?.setOnRefreshListener(null)
        mScrolling?.layoutManager = null
        super.onDestroyView()
    }

    override fun onRefresh() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class MultiViewHolder : RecyclerView.ViewHolder {
        companion object {
            val ITEM_TYPE_BANNER = 0
            val ITEM_TYPE_GOODS = 1
            val ITEM_TYPE_ACTIVITIES = 2
            val ITEM_TYPE_STORIES = 3
            val ITEM_TYPE_HEADER = 4
            val ITEM_TYPE_DIVIDER = 5
        }
        constructor(itemView : View) :super(itemView) {

        }

        fun setupBanner(o : Any) {

        }

        fun setupGoods(o : Any) {

        }

        fun setupActivities(o : Any) {

        }

        fun setupStories(o : Any) {

        }

        fun setupHeader(o : Any) {

        }

        fun setupDivider(o : Any) {

        }
    }

    class FirstRecyclerViewAdapter : RecyclerView.Adapter<MultiViewHolder>() {

        override fun getItemViewType(position: Int): Int {
            return super.getItemViewType(position)
        }

        override fun getItemCount(): Int {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onBindViewHolder(holder: MultiViewHolder?, position: Int) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MultiViewHolder {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }
}