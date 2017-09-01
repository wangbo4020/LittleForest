package com.xsenlin.android.ui.fragments

import android.app.UiModeManager
import android.content.Context
import android.content.Context.UI_MODE_SERVICE
import android.content.res.Configuration
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.*
import android.widget.TextView
import com.xsenlin.android.R
import com.xsenlin.android.ui.activities.BaseActivity

/**
 * Created by Dylan on 2017/8/31.
 */
class HomeFragment : BaseFragment() {

    companion object {
        val TAG = "HomeFragment"

        fun newInstance() : HomeFragment {
            return HomeFragment()
        }
    }

    private var mTabLayout : TabLayout? = null
    private var mViewPager : ViewPager? = null

    fun isTv(context : Context) : Boolean {

        return ((context.getSystemService(UI_MODE_SERVICE) as UiModeManager).currentModeType ==
                Configuration.UI_MODE_TYPE_TELEVISION)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView = inflater!!.inflate(R.layout.fragment_home, container, false)

        val toolbar = rootView.findViewById<Toolbar>(R.id.toolbar)
        (activity as BaseActivity).setSupportActionBar(toolbar)

        (rootView.findViewById<TextView>(android.R.id.text1)).setText(
                (if (isTv(context)) "Running on a TV Device" else "Running on a non-TV Device"))

        mViewPager = rootView.findViewById(R.id.viewpager)
        mTabLayout = rootView.findViewById(R.id.tablayout)
        mTabLayout!!.setupWithViewPager(mViewPager)

        if (savedInstanceState != null) {
            var currentItem = savedInstanceState.getInt("CurrentItem")
            mViewPager!!.setCurrentItem(currentItem)
        }
        return rootView
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

        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("CurrentItem", mViewPager!!.currentItem)
    }
}