package com.xsenlin.android.ui.fragment

import android.content.Context
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xsenlin.android.R

/**
 * Created by Dylan on 2017/10/10.
 */
class PurchaseFragment : BaseFragment() {

    companion object {
        val TAG = "PurchaseFragment"

        @JvmStatic
        fun newInstance(): PurchaseFragment {
            return PurchaseFragment()
        }
    }

    private var mTabLayout: TabLayout? = null
    private var mViewPager: ViewPager? = null
    private val mPagerAdapter: PurchasePagerAdapter by lazy { PurchasePagerAdapter(context, childFragmentManager) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_purchase, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            activity.finish()
        }

        mViewPager = view.findViewById(R.id.viewpager)
        mTabLayout = view.findViewById(R.id.tablayout)

        mViewPager!!.adapter = mPagerAdapter
        mTabLayout!!.setupWithViewPager(mViewPager)

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
        mTabLayout?.setupWithViewPager(null)
        super.onDestroyView()
    }

    class PurchasePagerAdapter(val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {

        private val mTitles: Array<String> by lazy {
            context.resources.getStringArray(R.array.purchase_titles)
        }

        override fun getItem(position: Int): Fragment = DemoFragment.newInstance(mTitles[position])

        override fun getCount(): Int = mTitles.size

        override fun getPageTitle(position: Int): CharSequence = mTitles[position]

    }
}
