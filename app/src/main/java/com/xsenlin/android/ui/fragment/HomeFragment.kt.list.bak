package com.xsenlin.android.ui.fragment

import android.content.Context
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.*
import com.xsenlin.android.R
import com.xsenlin.android.ui.activitiy.BaseActivity
import com.xsenlin.android.ui.model.ActivitySimple.ActivitySimpleList

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

    private var mTabLayout: TabLayout? = null
    private var mViewPager: ViewPager? = null
    private val mPagerAdapter: HomePagerAdapter by lazy { HomePagerAdapter(context, childFragmentManager) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view!!, savedInstanceState)

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        mViewPager = view.findViewById(R.id.viewpager)
        mTabLayout = view.findViewById(R.id.tablayout)

        mViewPager!!.adapter = mPagerAdapter
        mTabLayout!!.setupWithViewPager(mViewPager)

        if (savedInstanceState != null) {
            var currentItem = savedInstanceState.getInt("CurrentItem")
            mViewPager!!.setCurrentItem(currentItem)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater!!.inflate(R.menu.menu_home, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item!!.itemId

        return if (id == R.id.action_scan_qr) {
            true
        } else super.onOptionsItemSelected(item)
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

    class HomePagerAdapter(val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {

        private val mCategoryGift: Array<String>

        init {
            mCategoryGift = context.resources.getStringArray(R.array.gift_category)
        }

        override fun getItem(position: Int): Fragment {

            if (position == 0) return FirstFragment.newInstance()
            else if (position == 1) return SecondFragment.newInstance()
            val text : CharSequence = when {
                position == 0 -> context.getString(R.string.large_text)
                position < mCategoryGift.size -> mCategoryGift[position]
                else -> "${position + 1}"
            }
            return DemoFragment.newInstance(text)
        }

        override fun getCount(): Int {
            return mCategoryGift.size
        }

        override fun getPageTitle(position: Int): CharSequence {
            return if (position < mCategoryGift.size) mCategoryGift[position] else "Title ${position + 1}"
        }
    }
}