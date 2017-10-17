package com.xsenlin.android.ui.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.xsenlin.android.R
import com.xsenlin.android.ui.ContentLoadingDelegate
import com.xsenlin.android.ui.model.ModelList
import com.xsenlin.android.widget.BasisScaleTransformation
import com.xsenlin.android.widget.ScalePageTransformer

/**
 * Created by Dylan on 2017/8/31.
 */
class HomeFragment : BaseFragment() {

    companion object {
        val TAG = "HomeFragment"

        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    private val mData: ModelList<String> by lazy {
        val list: ModelList<String> = object : ModelList<String>() {}
        list.add("file:///android_asset/demo/card_0.webp")
        list.add("file:///android_asset/demo/card_1.webp")
        list.add("file:///android_asset/demo/card_2.webp")
        list
    }
    private var mViewPager: ViewPager? = null
    private var mPagerAdapter: HomePagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPagerAdapter = HomePagerAdapter(context, getLayoutInflater(savedInstanceState), mData)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view!!, savedInstanceState)

        mViewPager = view.findViewById(R.id.viewpager)
        mViewPager!!.adapter = mPagerAdapter
        mViewPager!!.pageMargin = resources.getDimensionPixelOffset(R.dimen.home_page_margin_horizontal)
        mViewPager!!.offscreenPageLimit = 3
        mViewPager!!.setPageTransformer(true, ScalePageTransformer())

        if (savedInstanceState != null) {
            var currentItem = savedInstanceState.getInt("CurrentItem")
            mViewPager!!.setCurrentItem(currentItem)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("CurrentItem", mViewPager!!.currentItem)
    }

    override fun onDestroyView() {
        mViewPager?.adapter = null
        super.onDestroyView()
    }

    class HomePagerAdapter(val context: Context, inflater: LayoutInflater, val data: ModelList<String>) : PagerAdapter() {

        private val mPagers: Array<View> by lazy {
            // ViewPager只缓存3个View
            arrayOf(inflater.inflate(R.layout.pager_home, null),
                    inflater.inflate(R.layout.pager_home, null),
                    inflater.inflate(R.layout.pager_home, null))
        }
        private val mProgress: ContentLoadingDelegate by lazy { ContentLoadingDelegate() }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val pager = mPagers[position % mPagers.size]
            if (pager.parent == null) {
                container.addView(pager)
            }
            val pageWidth = container.measuredWidth * getPageWidth(position)
            Log.d(TAG, "instantiateItem: position = $position, Container.width = " + container.measuredWidth)
            mProgress.setup(pager)
            mProgress.show()
            Picasso.with(context)
                    .load(data.get(position))
                    .transform(BasisScaleTransformation(data.get(position), pageWidth.toInt()))
                    .into(pager.findViewById<ImageView>(R.id.image), object : Callback.EmptyCallback() {
                        override fun onSuccess() {
                            mProgress.hide()
                        }
                    })
            return pager
        }

        override fun isViewFromObject(view: View?, any: Any?): Boolean = view == any

        override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
            super.destroyItem(container, position, `object`)
        }

        override fun getPageWidth(position: Int): Float {

            return 0.7f
        }

        override fun getCount(): Int = data.count
    }
}