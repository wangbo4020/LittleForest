package com.xsenlin.android.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xsenlin.android.R

/**
 * Created by Dylan on 2017/9/6.
 * 包含Banner
 * in HomeFragment
 */
class FirstFragment : BaseFragment() {

    companion object {
        val TAG = "FirstFragment"

        fun newInstance() : FirstFragment {
            return FirstFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_first, container, false)
        return rootView
    }
}