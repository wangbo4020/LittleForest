package com.xsenlin.android.ui.fragment

import android.os.Bundle
import android.support.annotation.IdRes
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.util.SparseArray
import android.view.View
import com.xsenlin.android.R
import com.xsenlin.android.ui.BottomNavigationViewHelper

/**
 * Created by Dylan on 2017/12/18.
 * 功能性Fragment，不可参与任何业务逻辑
 */

abstract class BottomNavigationFragment : BaseFragment(), BottomNavigationView.OnNavigationItemSelectedListener {

    private val mFragments = SparseArray<Fragment>()
    private val mNaviBottom: BottomNavigationView by lazy { view!!.findViewById(R.id.navi_bottom) as BottomNavigationView  }

    override fun onAttachFragment(childFragment: Fragment) {
        super.onAttachFragment(childFragment)
        // Activity在后台被回收后，再次进入现场恢复的Fragment
        mFragments.put(idOf(childFragment), childFragment)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        BottomNavigationViewHelper.disableShiftMode(mNaviBottom)
        mNaviBottom.setOnNavigationItemSelectedListener(this)

        // 现场恢复上次显示的Fragment
        val menu = mNaviBottom.menu
        if (menu.size() > 0) {

            var selectedId = menu.getItem(0).itemId
            if (savedInstanceState != null) {
                selectedId = savedInstanceState.getInt("SelectedId")
            }
            mNaviBottom.selectedItemId = selectedId
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("SelectedId", mNaviBottom.selectedItemId)
    }

    /**
     * 显示一个Fragment，隐藏多个Fragment
     */
    protected fun toggleFragment(@IdRes id: Int): Boolean {

        var frgmt = mFragments.get(id)

        if (frgmt == null) {
            frgmt = create(id)
            mFragments.put(id, frgmt)
        }

        if (frgmt != null && frgmt.isVisible) {
            return false
        }

        val fm = getChildFragmentManager()
        val trans = fm.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        if (frgmt != null) {
            if (frgmt.isAdded) {
                trans.show(frgmt)
            } else {
                trans.add(R.id.fragment_container, frgmt)
            }
        }

        for (i in 0 .. mFragments.size()) {
            val f = mFragments.valueAt(i)
            if (f !== frgmt && f != null && f.isAdded) {
                trans.hide(f)
            }
        }

        trans.commit()
        return true
    }

    /**
     * 请根据 id 创建Fragment，id 来自layout中的app:menu属性
     */
    abstract fun create(@IdRes id: Int): Fragment?

    /**
     * Fragment所在位置
     */
    abstract fun idOf(fragment: Fragment): Int
}
