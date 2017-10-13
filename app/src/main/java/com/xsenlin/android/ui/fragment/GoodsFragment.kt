package com.xsenlin.android.ui.fragment

import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xsenlin.android.R

/**
 * Created by Dylan on 2017/10/11.
 */
class GoodsFragment : BaseFragment() {

    companion object {
        val TAG = "GoodsFragment"

        fun newInstance(): GoodsFragment {
            return GoodsFragment()
        }
    }

    private var mViewPager: ViewPager? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_goods, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewPager = view!!.findViewById(R.id.viewpager)
    }

    class GoodsPagerAdapter : PagerAdapter() {
        override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun getCount(): Int {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }
}