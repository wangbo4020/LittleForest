package com.xsenlin.android.ui.activitiy

import android.os.Bundle
import android.support.v4.app.Fragment
import com.xsenlin.android.R
import com.xsenlin.android.ui.StartFragment
import com.xsenlin.android.ui.StartFragmentDelegate

class MainActivity : BaseActivity(), StartFragment {

    companion object {
        val TAG = "MainActivity"
    }

    private val mStartFragmentDelegate: StartFragmentDelegate by lazy { StartFragmentDelegate() }

    init {
//        setUpLifecycleLog(true, TAG)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mStartFragmentDelegate.setup(supportFragmentManager, R.id.fragment_container)
    }

    override fun startFragment(fragment: Fragment, tag: String) {
        mStartFragmentDelegate.startFragment(fragment, tag)
    }
}
