package com.xsenlin.android.ui.fragments

import android.os.Bundle
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
        val TAG = "MainFragment"
    }

    private var mNaviBottom: BottomNavigationView? = null

    private var mFrgmtHome: Fragment? = null
    private var mFrgmtFind: Fragment? = null
    private var mFrgmtMine: Fragment? = null

    init {
        setUpLifecycleLog(true, TAG)
    }

    override fun onAttachFragment(childFragment: Fragment?) {
        super.onAttachFragment(childFragment)

        // 可能是现场恢复的Fragment
        when (childFragment) {
            is HomeFragment -> mFrgmtHome = childFragment
            is MineFragment -> mFrgmtMine = childFragment
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 如果Fragment被现场恢复，将不创建新的对象 需要做null判断
        if (mFrgmtHome == null) mFrgmtHome = HomeFragment.newInstance()
        if (mFrgmtMine == null) mFrgmtMine = MineFragment.newInstance()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView = inflater!!.inflate(R.layout.fragment_main, container, false)
        mNaviBottom = rootView.findViewById<BottomNavigationView>(R.id.navi_bottom)
        mNaviBottom!!.setOnNavigationItemSelectedListener(this)

        var selectedId = R.id.navigation_home
        if (savedInstanceState != null) {
            selectedId = savedInstanceState.getInt("SelectedId")
            Log.d(TAG, "${getTagById(selectedId)} restored")
        }
        mNaviBottom!!.selectedItemId = selectedId
        Log.d(TAG, "onCreateView: Selected " + getTagById(mNaviBottom!!.selectedItemId) + " " + childFragmentManager.fragments.size + " " + savedInstanceState)
        return rootView
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("SelectedId", mNaviBottom!!.selectedItemId)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_home -> {
                return showFragment(mFrgmtHome!!, mFrgmtMine!!)
            }
            R.id.navigation_find -> {
                return false
            }
            R.id.navigation_mine -> {
                return showFragment(mFrgmtMine!!, mFrgmtHome!!)
            }
        }
        Log.d(TAG, "onNavigationItemSelected: ${getTagById(item.itemId)}")
        return false
    }

    fun showFragment(fragment: Fragment, vararg hides: Fragment): Boolean {
        if (fragment.isVisible) return false

        val trans = childFragmentManager.beginTransaction()
        if (!fragment.isAdded) {
            trans.add(R.id.main_content, fragment)
        }
        for (f in hides) if (f?.isAdded) trans.hide(f)
        trans.show(fragment).commit()
        Log.d(TAG, "current showing $fragment")
        return true
    }

    fun getTagById(id: Int): String? {
        when (id) {
            R.id.navigation_home -> {
                return HomeFragment.TAG
            }
            R.id.navigation_find -> {
                return "FindFragment"
            }
            R.id.navigation_mine -> {
                return MineFragment.TAG
            }
            else -> {
                return null
            }
        }
    }

}