package com.xsenlin.android.ui

import android.support.v4.widget.ContentLoadingProgressBar
import android.view.View
import com.xsenlin.android.R
import java.lang.IllegalStateException

/**
 * Created by Dylan on 2017/9/24.
 */
class ContentLoadingDelegate {

    private var mProgressBar: ContentLoadingProgressBar? = null

    fun setup(view: View) {
        mProgressBar = view.findViewById(R.id.content_loading_widget)
        if (mProgressBar == null) {
            throw IllegalStateException("Not found id is content_loading_widget")
        }
    }

    fun hide() {
        throwIfNull()
        mProgressBar!!.hide()
    }

    fun show() {
        throwIfNull()
        mProgressBar!!.show()
    }

    private fun throwIfNull() {
        if (mProgressBar == null) {
            throw IllegalStateException("use before must call setup.")
        }
    }
}