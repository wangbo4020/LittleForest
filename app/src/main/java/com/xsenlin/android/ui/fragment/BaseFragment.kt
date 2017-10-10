package com.xsenlin.android.ui.fragment

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xsenlin.android.ui.ContentLoadingDelegate
import com.xsenlin.android.ui.LifecycleLogDelegate
import com.xsenlin.android.ui.activitiy.BaseActivity

/**
 * Created by Dylan on 2017/8/31.
 */
open class BaseFragment : Fragment() {

    protected val mLifecycleLogDelegate: LifecycleLogDelegate by lazy { LifecycleLogDelegate("BaseFragment") }
    protected val mContentLoadingDelegate: ContentLoadingDelegate by lazy { ContentLoadingDelegate() }

    protected fun setupLifecycleLog(enable: Boolean, tag: String) {
        mLifecycleLogDelegate.logTag = tag
        mLifecycleLogDelegate.lifecycleLog = enable
    }

    protected fun setSupportActionBar(@Nullable toolbar: Toolbar) {
        (activity as BaseActivity).setSupportActionBar(toolbar)
    }

    /* - - - - - - - - - - - - - - - ContentLoadingDelegate Start - - - - - - - - - - - - - - - - */
    protected fun setupContentLoading(view: View) {
        mContentLoadingDelegate.setup(view)
    }

    protected fun showContentLoading() {
        mContentLoadingDelegate.show()
    }

    protected fun hideContentLoading() {
        mContentLoadingDelegate.hide()
    }
    /* * * * * * * * * * * * * * * * * ContentLoadingDelegate End * * * * * * * * * * * * * * * * */

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        mLifecycleLogDelegate.setUserVisibleHint(isVisibleToUser)
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        mLifecycleLogDelegate.onConfigurationChanged(newConfig)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mLifecycleLogDelegate.onAttach(context)
    }

    override fun onAttachFragment(childFragment: Fragment?) {
        super.onAttachFragment(childFragment)
        mLifecycleLogDelegate.onAttachFragment(childFragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mLifecycleLogDelegate.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mLifecycleLogDelegate.onCreateView(savedInstanceState)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mLifecycleLogDelegate.onViewCreated(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mLifecycleLogDelegate.onActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        mLifecycleLogDelegate.onStart()
    }

    override fun onResume() {
        super.onResume()
        mLifecycleLogDelegate.onResume()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mLifecycleLogDelegate.onSaveInstanceState(outState)
    }

    override fun onPause() {
        super.onPause()
        mLifecycleLogDelegate.onPause()
    }

    override fun onStop() {
        super.onStop()
        mLifecycleLogDelegate.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mLifecycleLogDelegate.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
        mLifecycleLogDelegate.onDestroy()
    }

    override fun onDetach() {
        super.onDetach()
        mLifecycleLogDelegate.onDetach()
    }
}