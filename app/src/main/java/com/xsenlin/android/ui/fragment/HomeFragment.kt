package com.xsenlin.android.ui.fragment

import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tmall.ultraviewpager.UltraViewPager
import com.tmall.ultraviewpager.transformer.UltraScaleTransformer
import com.xsenlin.android.R
import com.xsenlin.android.databinding.ItemHomeBinding
import com.xsenlin.android.ui.StartFragment


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

    private val mData: Array<String> by lazy {
        arrayOf("file:///android_asset/demo/card_0.webp",
                "file:///android_asset/demo/card_1.webp",
                "file:///android_asset/demo/card_2.webp",
                "file:///android_asset/demo/card_3.webp")
    }

    private val mDiscreteWidget by lazy { view!!.findViewById(R.id.ultra_viewpager) as UltraViewPager }
    private val mAdapter: PagerAdapter by lazy { HomeAdapter(layoutInflater, mData) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mDiscreteWidget!!.adapter = mAdapter
        mDiscreteWidget.setPageTransformer(false, UltraScaleTransformer(1f))
    }

    inner class HomeAdapter(val inflater: LayoutInflater, val data: Array<String>) : PagerAdapter() {
        override fun isViewFromObject(view: View, `object`: Any) = view == `object`

        override fun getCount() = data?.size

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val inflate = ItemHomeBinding.inflate(inflater, container, true)
            inflate.data = data[position]
            inflate.image.tag = data[position]
            inflate.image.setOnClickListener {
                val act = activity
                if (act is StartFragment) {
                    act.startFragment(ClassFragment.newInstance(), ClassFragment.TAG)
                }
            }
            return inflate.root
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View)
        }
    }
}