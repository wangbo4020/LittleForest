package com.xsenlin.android.ui.fragments

import android.content.Context
import android.os.Bundle
import android.support.design.internal.BottomNavigationItemView
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.xsenlin.android.R

/**
 * Created by Dylan on 2017/8/31.
 */
class MainFragment : BaseFragment(), BottomNavigationView.OnNavigationItemSelectedListener {

    companion object {
        val TAG = "MainFragment";
    }

    private var mNaviBottom: BottomNavigationView? = null

    private var mCurrentTag : String? = null
    private var mFrgmtHome: Fragment? = null
    private var mFrgmtFind: Fragment? = null
    private var mFrgmtMine: Fragment? = null

    init {
        Log.d(TAG, "init: ")
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        Log.d(TAG, "setUserVisibleHint: " + isVisibleToUser)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        Log.d(TAG, "onAttach")
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: " + savedInstanceState)

        mFrgmtHome = HomeFragment.newInstance()
        mFrgmtMine = MineFragment.newInstance()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView = inflater!!.inflate(R.layout.fragment_main, container, false)
        Log.d(TAG, "onCreateView: " + savedInstanceState)
        mNaviBottom = rootView.findViewById<BottomNavigationView>(R.id.navi_bottom)
        if (savedInstanceState != null) {
            mCurrentTag = savedInstanceState.getString("CurrentTag")
            Log.d(TAG, "$mCurrentTag Restored")
            childFragmentManager.beginTransaction()
                    .add(R.id.main_content, mFrgmtMine, MineFragment.TAG)
                    .add(R.id.main_content, mFrgmtHome, HomeFragment.TAG)
                    .commitNow()


            showFragment(childFragmentManager.findFragmentByTag(mCurrentTag), mCurrentTag!!, mFrgmtHome!!, mFrgmtMine!!)
        } else {
            showFragment(mFrgmtHome!!,HomeFragment.TAG, mFrgmtMine!!)
        }

        mNaviBottom!!.setOnNavigationItemSelectedListener(this)
        return rootView
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG, "onActivityCreated: " + savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        Log.d(TAG, "onPause")
        super.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState")
        outState.putString("CurrentTag", mCurrentTag)
        Log.d(TAG, "$mCurrentTag Saved")
    }

    override fun onStop() {
        Log.d(TAG, "onStop")
        super.onStop()
    }

    override fun onDestroyView() {
        Log.d(TAG, "onDestroyView")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy")
        super.onDestroy()
    }

    override fun onDetach() {
        Log.d(TAG, "onDetach")
        super.onDetach()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_home -> {
                return showFragment(mFrgmtHome!!, HomeFragment.TAG, mFrgmtMine!!)
            }
            R.id.navigation_find -> {
                return true
            }
            R.id.navigation_mine -> {
                return showFragment(mFrgmtMine!!, MineFragment.TAG, mFrgmtHome!!)
            }
        }
        return false
    }

    fun showFragment(fragment: Fragment, tag : String, vararg hides: Fragment): Boolean {
        if (fragment!!.isVisible) return false

        val trans = childFragmentManager.beginTransaction()
        if (!fragment!!.isAdded) {
            trans.add(R.id.main_content, fragment, tag)
        }
        for (f in hides) trans.hide(f)
        trans.show(fragment).commit()
        mCurrentTag = fragment.tag
        Log.d(TAG, "Commint $fragment")
        return true
    }

}