package com.xsenlin.android.ui.fragment

import android.os.Bundle

/**
 * Created by Dylan on 2017/8/31.
 */
class TimeFragment : DemoFragment() {


    companion object {
        val TAG = "TimeFragment"

        fun newInstance(): TimeFragment {
            val f = TimeFragment()
            f.arguments = Bundle()
            f.arguments.putCharSequence(KEY_TEXT, TAG)
            return f
        }
    }
}