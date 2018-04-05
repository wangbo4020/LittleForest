package com.xsenlin.android.ui

import android.support.annotation.IdRes
import android.view.View

/**
 * Created by Dylan on 2017/11/23.
 *
 *
 * Copy from [android.support.v4.widget.ContentLoadingProgressBar]
 */
class ContentLoadingDelegate : View.OnAttachStateChangeListener {

    internal var mMinDelay = MIN_DELAY

    internal var mMinShowTime = MIN_SHOW_TIME

    internal var mListener: OnContentLoadingListener? = null

    internal var mStartTime = -1L

    internal var mPostedHide = false

    internal var mPostedShow = false

    internal var mDismissed = false

    private val mDelayedHide = Runnable {
        mPostedHide = false
        mStartTime = -1
        setVisible(false)
    }

    private val mDelayedShow = Runnable {
        mPostedShow = false
        if (!mDismissed) {
            mStartTime = System.currentTimeMillis()
            setVisible(true)
        }
    }

    private var mContentLoading: View? = null

    fun setup(@IdRes id: Int, view: View) {
        mContentLoading = view.findViewById(id)
        throwIfNull()
        mContentLoading!!.addOnAttachStateChangeListener(this)
    }

    fun setConfig(minDelay: Long, minShowTime: Long, listener: OnContentLoadingListener) {
        mMinDelay = minDelay
        mMinShowTime = minShowTime
        mListener = listener
    }

    fun unset() {
        throwIfNull()
        mContentLoading!!.removeOnAttachStateChangeListener(this)
        mContentLoading = null
    }

    /**
     * Hide the progress view if it is visible. The progress view will not be
     * hidden until it has been shown for at least a minimum show time. If the
     * progress view was not yet visible, cancels showing the progress view.
     */
    fun hide() {
        throwIfNull()

        mDismissed = true
        removeCallbacks(mDelayedShow)
        val diff = System.currentTimeMillis() - mStartTime
        if (diff >= mMinShowTime || mStartTime == -1L) {
            // The progress spinner has been shown long enough
            // OR was not shown yet. If it wasn't shown yet,
            // it will just never be shown.
            setVisible(false)
        } else {
            // The progress spinner is shown, but not long enough,
            // so put a delayed message in to hide it when its been
            // shown long enough.
            if (!mPostedHide) {
                postDelayed(mDelayedHide, mMinShowTime - diff)
                mPostedHide = true
            }
        }
    }

    /**
     * Show the progress view after waiting for a minimum delay. If
     * during that time, hide() is called, the view is never made visible.
     */
    fun show() {
        throwIfNull()

        // Reset the start time.
        mStartTime = -1
        mDismissed = false
        removeCallbacks(mDelayedHide)
        if (!mPostedShow) {
            postDelayed(mDelayedShow, mMinDelay)
            mPostedShow = true
        }
    }

    protected fun setVisible(visible: Boolean) {
        if (visible) {
            mContentLoading!!.visibility = View.VISIBLE
        } else {
            mContentLoading!!.visibility = View.GONE
        }

        if (mListener != null) {
            mListener!!.onContentLoadingChanged(this, visible)
        }
    }

    private fun postDelayed(action: Runnable, delayMillis: Long) {
        mContentLoading!!.postDelayed(action, delayMillis)
    }

    private fun removeCallbacks(action: Runnable) {
        mContentLoading!!.removeCallbacks(action)
    }

    override fun onViewAttachedToWindow(v: View) {
        removeCallbacks()
    }

    override fun onViewDetachedFromWindow(v: View) {
        removeCallbacks()
    }

    private fun removeCallbacks() {
        removeCallbacks(mDelayedHide)
        removeCallbacks(mDelayedShow)
    }

    private fun throwIfNull() {
        if (mContentLoading == null) {
            throw IllegalStateException("use before must call setup.")
        }
    }

    interface OnContentLoadingListener {

        fun onContentLoadingChanged(delegate: ContentLoadingDelegate, visible: Boolean)
    }

    companion object {

        val MIN_SHOW_TIME = 500L // ms
        val MIN_DELAY = 500L // ms
    }
}
