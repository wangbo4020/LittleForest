package com.xsenlin.android.ui.fragment

import android.os.Bundle
import android.os.Handler
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v4.widget.ContentLoadingProgressBar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.xsenlin.android.R
import com.xsenlin.android.widget.BasisScaleTransformation
import java.util.*

/**
 * Created by Dylan on 2017/10/11.
 */
class GoodsFragment : BaseFragment() {

    companion object {
        val TAG = "GoodsFragment"
        val KEY_COVER_URLS = "cover_urls"

        fun newInstance(coverUrls: Array<String>): GoodsFragment {
            val fragment = GoodsFragment()
            val arguments = Bundle()
            arguments.putStringArray(KEY_COVER_URLS, coverUrls)
            fragment.arguments = arguments
            android.util.Log.d("III", "covers " + Arrays.toString(coverUrls))
            return fragment
        }
    }

    private var mViewPager: ViewPager? = null
    private var mAdapter: GoodsPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAdapter = GoodsPagerAdapter(getLayoutInflater())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_goods, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewPager = view!!.findViewById(R.id.viewpager)
        mViewPager!!.adapter = mAdapter
    }

    override fun onDestroyView() {
        mViewPager?.adapter = null
        super.onDestroyView()
    }

    inner class GoodsPagerAdapter(val inflater: LayoutInflater) : PagerAdapter() {

        val mHandler by lazy { Handler() }
        val mCovers by lazy { arguments!!.getStringArray(KEY_COVER_URLS) }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val pager = inflater.inflate(R.layout.pager_goods, container, false)
            container.addView(pager)
            val image = pager.findViewById<ImageView>(R.id.image)
            image.tag = mCovers[position]

            mHandler.post {
                android.util.Log.d("III", "instantiateItem load " + mCovers[position] + ", height " + container.height)
                Picasso.get()
                        .load(mCovers[position])
                        .transform(BasisScaleTransformation(image.tag as String, container.height, BasisScaleTransformation.BASIS_HEGHT))
                        .into(image, object : Callback.EmptyCallback() {
                    override fun onSuccess() {
                        pager.findViewById<ContentLoadingProgressBar>(R.id.content_loading_widget).visibility = View.GONE
                    }
                })
            }
            return pager
        }

        override fun destroyItem(container: ViewGroup, position: Int, any: Any) {
            container.removeView(any as View)
        }

        override fun isViewFromObject(view: View, any: Any): Boolean = view == any

        override fun getCount(): Int = mCovers.size

    }
}