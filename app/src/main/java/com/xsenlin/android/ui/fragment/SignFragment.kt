package com.xsenlin.android.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.xsenlin.android.R

/**
 * Created by Dylan on 2017/9/24.
 * 登录和注册
 */
class SignFragment : BaseFragment() {

    companion object {
        @JvmStatic
        val TAG = "SignFragment"

        fun newInstance(): SignFragment {
            return SignFragment()
        }
    }

    private var etPhone: EditText? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sign, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view!!, savedInstanceState)
        etPhone = view.findViewById(R.id.et_phone)
    }

    override fun onResume() {
        super.onResume()
        etPhone!!.requestFocus()
//        val imm: InputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//        imm.showSoftInput(etPhone, InputMethodManager.SHOW_FORCED)
    }
}