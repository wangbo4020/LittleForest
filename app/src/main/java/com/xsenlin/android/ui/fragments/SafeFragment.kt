package com.xsenlin.android.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xsenlin.android.R

/**
 * Created by Dylan on 2017/8/31.
 */
class SafeFragment : DemoFragment() {

    companion object {
        val TAG = "SafeFragment"

        fun newInstance(): SafeFragment {
            val f = SafeFragment()
            f.arguments = Bundle()
            f.arguments.putCharSequence(KEY_TEXT, TAG)
            return f
        }
    }
}