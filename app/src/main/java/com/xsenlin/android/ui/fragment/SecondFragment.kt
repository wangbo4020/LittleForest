package com.xsenlin.android.ui.fragment

import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.LayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xsenlin.android.R

/**
 * Created by Dylan on 2017/9/24.
 */
class SecondFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener {

    companion object {
        val TAG = "SecondFragment"

        fun newInstance(): SecondFragment {
            return SecondFragment()
        }
    }

    private var mSwipeRefresh: SwipeRefreshLayout? = null
    private var mRecyclerView: RecyclerView? = null
    private val mLayoutManager: LayoutManager by lazy { LinearLayoutManager(context) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupContentLoading(view)
        hideContentLoading()
        mSwipeRefresh = view.findViewById(R.id.swipe_refresh_widget)
        mSwipeRefresh!!.setOnRefreshListener(this)

        mRecyclerView = view.findViewById(R.id.recycler_view)
        mRecyclerView!!.layoutManager = mLayoutManager
    }

    override fun onDestroyView() {
        mSwipeRefresh?.setOnRefreshListener(null)
        mRecyclerView?.layoutManager = null
        mRecyclerView?.adapter = null
        super.onDestroyView()
    }

    override fun onRefresh() {
    }

    class SecondAdapter : RecyclerView.Adapter<SecondHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SecondHolder {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onBindViewHolder(holder: SecondHolder, position: Int) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun getItemCount(): Int {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }

    class SecondHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}