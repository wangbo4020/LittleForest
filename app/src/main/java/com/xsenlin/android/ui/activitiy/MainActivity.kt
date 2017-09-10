package com.xsenlin.android.ui.activitiy

import android.os.Bundle
import com.xsenlin.android.R

class MainActivity : BaseActivity() {

    companion object {
        val TAG = "MainActivity"
    }

    init {
//        setUpLifecycleLog(true, TAG)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}
