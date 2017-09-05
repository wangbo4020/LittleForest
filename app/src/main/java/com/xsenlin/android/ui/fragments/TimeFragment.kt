package com.xsenlin.android.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xsenlin.android.R

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