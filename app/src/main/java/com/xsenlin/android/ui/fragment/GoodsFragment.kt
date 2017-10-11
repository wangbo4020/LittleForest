package com.xsenlin.android.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xsenlin.android.R

/**
 * Created by Dylan on 2017/10/11.
 */
class GoodsFragment : BaseFragment() {

    companion object {
        val TAG = "GoodsFragment"

        fun newInstance(): GoodsFragment {
            return GoodsFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_goods, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}