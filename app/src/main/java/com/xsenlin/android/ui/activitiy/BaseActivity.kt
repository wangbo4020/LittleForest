package com.xsenlin.android.ui.activitiy

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.xsenlin.android.ui.LifecycleLogDelegate

/**
 * Created by Dylan on 2017/8/31.
 */
open class BaseActivity : AppCompatActivity() {

    protected val mLifecycleLogDelegate: LifecycleLogDelegate

    init {
        mLifecycleLogDelegate = LifecycleLogDelegate("BaseActivity")
    }

    protected fun setUpLifecycleLog(enable : Boolean, tag: String) {
        mLifecycleLogDelegate.logTag = tag
        mLifecycleLogDelegate.lifecycleLog = enable
    }

    override fun onAttachFragment(childFragment: Fragment?) {
        super.onAttachFragment(childFragment)
        mLifecycleLogDelegate.onAttachFragment(childFragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mLifecycleLogDelegate.onCreate(savedInstanceState)
    }

    override fun onRestart() {
        super.onRestart()
        mLifecycleLogDelegate.onRestart()
    }

    override fun onStart() {
        super.onStart()
        mLifecycleLogDelegate.onStart()
    }

    override fun onResume() {
        super.onResume()
        mLifecycleLogDelegate.onResume()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        mLifecycleLogDelegate.onRestoreInstanceState(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mLifecycleLogDelegate.onSaveInstanceState(outState)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        mLifecycleLogDelegate.onNewIntent(intent)
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        mLifecycleLogDelegate.onConfigurationChanged(newConfig)
    }

    override fun onPause() {
        super.onPause()
        mLifecycleLogDelegate.onPause()
    }

    override fun onStop() {
        super.onStop()
        mLifecycleLogDelegate.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mLifecycleLogDelegate.onDestroy()
    }

}