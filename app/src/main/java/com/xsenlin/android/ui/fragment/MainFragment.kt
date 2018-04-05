package com.xsenlin.android.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.xsenlin.android.R


/**
 * Created by Dylan on 2017/8/31.
 */
class MainFragment : BottomNavigationFragment() {

    companion object {
        val TAG = "MainFragment"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return toggleFragment(item.itemId)
    }

    override fun create(id: Int): Fragment? {
        return when (id) {
            R.id.navigation_home -> HomeFragment.newInstance()
            R.id.navigation_safe -> SafeFragment.newInstance()
            R.id.navigation_time -> TimeFragment.newInstance()
            R.id.navigation_mine -> MineFragment.newInstance()
            else -> null
        }
    }

    override fun idOf(fragment: Fragment): Int {
        return when (fragment) {
            is HomeFragment -> R.id.navigation_home
            is SafeFragment -> R.id.navigation_safe
            is TimeFragment -> R.id.navigation_time
            is MineFragment -> R.id.navigation_mine
            else -> throw RuntimeException("unknown fragment")
        }
    }
}