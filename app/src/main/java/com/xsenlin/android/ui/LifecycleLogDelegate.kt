package com.xsenlin.android.ui

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log

/**
 * Created by Dylan on 2017/9/1.
 */
class LifecycleLogDelegate(var lifecycleLog: Boolean, var logTag: String) {

    constructor(tag: String) : this(false, tag)

    fun setUserVisibleHint(isVisibleToUser: Boolean) {
        if (lifecycleLog) Log.d(logTag, "setUserVisibleHint: $isVisibleToUser")
    }

    fun onAttach(context: Context?) {
        if (lifecycleLog) Log.d(logTag, "onAttach")
    }

    fun onAttachFragment(childFragment: Fragment?) {
        if (lifecycleLog) Log.d(logTag, "onAttachFragment: $childFragment")
    }

    fun onCreate(savedInstanceState: Bundle?) {
        if (lifecycleLog) Log.d(logTag, "onCreate: " + savedInstanceState)
    }

    fun onCreateView(savedInstanceState: Bundle?) {
        if (lifecycleLog) Log.d(logTag, "onCreateView: " + savedInstanceState)
    }

    fun onActivityCreated(savedInstanceState: Bundle?) {
        if (lifecycleLog) Log.d(logTag, "onActivityCreated: " + savedInstanceState)
    }

    fun onRestart() {
        if (lifecycleLog) Log.d(logTag, "onRestart")
    }

    fun onStart() {
        if (lifecycleLog) Log.d(logTag, "onStart")
    }

    fun onResume() {
        if (lifecycleLog) Log.d(logTag, "onResume")
    }

    fun onSaveInstanceState(outState: Bundle) {
        if (lifecycleLog) Log.d(logTag, "onSaveInstanceState: $outState")
    }

    fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        if (lifecycleLog) Log.d(logTag, "onRestoreInstanceState: " + savedInstanceState)
    }

    fun onConfigurationChanged(newConfig: Configuration?) {
        if (lifecycleLog) Log.d(logTag, "onConfigurationChanged: " + newConfig)
    }

    fun onNewIntent(intent: Intent?) {
        if (lifecycleLog) Log.d(logTag, "onNewIntent: " + intent)
    }

    fun onPause() {
        if (lifecycleLog) Log.d(logTag, "onPause")
    }

    fun onStop() {
        if (lifecycleLog) Log.d(logTag, "onStop")
    }

    fun onDestroyView() {
        if (lifecycleLog) Log.d(logTag, "onDestroyView")
    }

    fun onDestroy() {
        if (lifecycleLog) Log.d(logTag, "onDestroy")
    }

    fun onDetach() {
        if (lifecycleLog) Log.d(logTag, "onDetach")
    }
}