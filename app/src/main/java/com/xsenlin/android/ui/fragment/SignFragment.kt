package com.xsenlin.android.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xsenlin.android.R

/**
 * Created by Dylan on 2017/9/24.
 * 登录和注册
 */
class SignFragment : BaseFragment() {

    companion object {
        val TAG = "SignFragment"

        fun newInstance(): SignFragment {
            return SignFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sign, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}