package com.xsenlin.android.ui.fragment

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.xsenlin.android.R
import com.xsenlin.android.widget.BasisScaleTransformation

/**
 * Created by Dylan on 2017/10/14.
 */
class DetailsFragment: BaseFragment() {

    companion object {
        val TAG = "DetailsFragment"
        fun newInstance():DetailsFragment {
            return DetailsFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view!!, savedInstanceState)
        setupContentLoading(view)
    }

    override fun onResume() {
        super.onResume()
    }
}