package com.xsenlin.android.ui.fragments

import android.os.Bundle
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
class FirstFragment : BaseFragment() {

    companion object {
        val TAG = "FirstFragment"

        fun newInstance() : FirstFragment {
            return FirstFragment()
        }
    }

    // 大致思路
    // 使用GridLayoutManger的SpanSizeLookup来实现首页复杂滚动布局
    // item_banner, item_tree, item_title, item_horizontal_scrolling
    private var mScrolling : RecyclerView? = null
    private val mLayoutManger : RecyclerView.LayoutManager by lazy {
        val glm = GridLayoutManager(context, 2)
        glm.spanSizeLookup = object : SpanSizeLookup(){
            override fun getSpanSize(position: Int): Int {
                return when(position) {
                    0 -> 1
                    else -> 2
                }
            }
        }
        glm
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_first, container, false)
        mScrolling = rootView.findViewById(R.id.recycler_view)
        return rootView
    }
}