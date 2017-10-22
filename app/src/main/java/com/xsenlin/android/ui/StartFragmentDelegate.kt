package com.xsenlin.android.ui

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction

/**
 * Created by Dylan on 2017/10/21.
 */
class StartFragmentDelegate : StartFragment {

    private var mFM: FragmentManager? = null
    private var mContainerId: Int? = null

    fun setup(fm: FragmentManager, containerId: Int) {
        mFM = fm
        mContainerId = containerId
    }

    override fun startFragment(fragment: Fragment, tag: String) {
        mFM!!.beginTransaction().addToBackStack(tag + "_back")
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .add(mContainerId!!, fragment, tag).commit()
    }
}