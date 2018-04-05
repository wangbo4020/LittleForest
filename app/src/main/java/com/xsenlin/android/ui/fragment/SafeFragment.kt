package com.xsenlin.android.ui.fragment

import android.os.Bundle

/**
 * Created by Dylan on 2017/8/31.
 */
class SafeFragment : DemoFragment() {

    companion object {
        val TAG = "SafeFragment"

        fun newInstance(): SafeFragment {
            val f = SafeFragment()
            val arguments = Bundle()
            arguments.putCharSequence(KEY_TEXT, TAG)
            f.arguments = arguments
            return f
        }
    }
}