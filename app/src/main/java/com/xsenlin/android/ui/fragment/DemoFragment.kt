package com.xsenlin.android.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.xsenlin.android.R

/**
 * Created by Dylan on 2017/9/2.
 */
open class DemoFragment : BaseFragment() {

    companion object {
        val TAG = "DemoFragment"
        val KEY_TEXT = "text"

        @JvmStatic
        fun newInstance(text : CharSequence) : DemoFragment{
            val f = DemoFragment()
            val arguments = Bundle()
            arguments.putCharSequence(KEY_TEXT, text)
            f.arguments = arguments
            return f
        }
    }

    var mTextView : TextView? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_demo, container, false)
        mTextView = rootView.findViewById(android.R.id.text1)
        if (null != arguments) {
            mTextView!!.setText(arguments!!.getString(KEY_TEXT))
        }
        return rootView
    }
}