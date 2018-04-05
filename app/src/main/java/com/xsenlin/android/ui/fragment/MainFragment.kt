package com.xsenlin.android.ui.fragment

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.xsenlin.android.R
import com.xsenlin.android.ui.BottomNavigationViewHelper
import com.xsenlin.android.ui.activitiy.BaseActivity


/**
 * Created by Dylan on 2017/8/31.
 */
class MainFragment : BaseFragment(), BottomNavigationView.OnNavigationItemSelectedListener {

    companion object {
        val TAG = "MainFragment"
    }

    private var mNaviBottom: BottomNavigationView? = null

    private var mFrgmtHome: Fragment? = null
    private var mFrgmtSafe: Fragment? = null
    private var mFrgmtTime: Fragment? = null
    private var mFrgmtMine: Fragment? = null

    init {
//        setupLifecycleLog(true, TAG)
    }

    override fun onAttachFragment(childFragment: Fragment?) {
        super.onAttachFragment(childFragment)

        // 可能是现场恢复的Fragment
        when (childFragment) {
            is HomeFragment -> mFrgmtHome = childFragment
            is SafeFragment -> mFrgmtSafe = childFragment
            is TimeFragment -> mFrgmtTime = childFragment
            is MineFragment -> mFrgmtMine = childFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 如果Fragment未被现场恢复，创建新的实例 需要做null判断
        mFrgmtHome = mFrgmtHome ?: HomeFragment.newInstance()
        mFrgmtSafe = mFrgmtSafe ?: SafeFragment.newInstance()
        mFrgmtTime = mFrgmtTime ?: TimeFragment.newInstance()
        mFrgmtMine = mFrgmtMine ?: MineFragment.newInstance()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
//        setSupportActionBar(toolbar)

        mNaviBottom = view!!.findViewById<BottomNavigationView>(R.id.navi_bottom)
        mNaviBottom!!.setOnNavigationItemSelectedListener(this)
        BottomNavigationViewHelper.disableShiftMode(mNaviBottom!!)

        var selectedId = R.id.navigation_home
        if (savedInstanceState != null) {
            selectedId = savedInstanceState.getInt("SelectedId")
            Log.d(TAG, "${getTagById(selectedId)} restored")
        }
        mNaviBottom!!.selectedItemId = selectedId
        Log.d(TAG, "onViewCreated: Selected " + getTagById(mNaviBottom!!.selectedItemId) + " " + childFragmentManager.fragments.size + " " + savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("SelectedId", mNaviBottom!!.selectedItemId)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_home -> {
                return showFragment(mFrgmtHome!!, mFrgmtSafe!!, mFrgmtTime!!, mFrgmtMine!!)
            }
            R.id.navigation_safe -> {
                return showFragment(mFrgmtSafe!!, mFrgmtHome!!, mFrgmtTime!!, mFrgmtMine!!)
            }
            R.id.navigation_time -> {
                return showFragment(mFrgmtTime!!, mFrgmtHome!!, mFrgmtSafe!!, mFrgmtMine!!)
            }
            R.id.navigation_mine -> {
                return showFragment(mFrgmtMine!!, mFrgmtHome!!, mFrgmtSafe!!, mFrgmtTime!!)
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
        for (f in hides) if (f != fragment && f?.isAdded) trans.hide(f)
        trans.show(fragment).commit()
        Log.d(TAG, "current showing $fragment")
        return true
    }

    fun getTagById(id: Int): String? {
        return when (id) {
            R.id.navigation_home -> HomeFragment.TAG
            R.id.navigation_safe -> SafeFragment.TAG
            R.id.navigation_time -> TimeFragment.TAG
            R.id.navigation_mine -> MineFragment.TAG
            else -> null
        }
    }

}