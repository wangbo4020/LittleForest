package com.xsenlin.android.ui.fragment

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xsenlin.android.R
import com.xsenlin.android.databinding.ItemClassBinding
import com.xsenlin.android.ui.StartFragment

/**
 * Created by Dylan on 2017/10/21.
 */
class ClassFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener {

    companion object {
        val TAG = "ClassFragment"

        @JvmStatic
        fun newInstance(): ClassFragment {
            val f = ClassFragment()
            return f
        }
    }

    private val mData: Array<String> by lazy {
        arrayOf(
                "file:///android_asset/demo/class_header.webp",
                "file:///android_asset/demo/class_0.webp",
                "file:///android_asset/demo/class_1.webp",
                "file:///android_asset/demo/class_2.webp",
                "file:///android_asset/demo/class_3.webp")
    }
    private var mRecyclerView: RecyclerView? = null
    private var mSwipeRefresh: SwipeRefreshLayout? = null
    private val mLayoutManager: RecyclerView.LayoutManager by lazy { LinearLayoutManager(context) }
    private var mAdapter: ClassAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAdapter = ClassAdapter(context!!, getLayoutInflater(), mData)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_class, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view!!, savedInstanceState)

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener({ fragmentManager!!.popBackStack() })

        setupContentLoading(view)
        hideContentLoading()

        mSwipeRefresh = view!!.findViewById(R.id.swipe_refresh_widget)
        mSwipeRefresh!!.setOnRefreshListener(this)

        mRecyclerView = view.findViewById(R.id.recycler_view)
        mRecyclerView!!.layoutManager = mLayoutManager
        mRecyclerView!!.adapter = mAdapter

    }

    override fun onRefresh() {
        mSwipeRefresh!!.isRefreshing = false
    }

    override fun onDestroyView() {
        mSwipeRefresh?.setOnRefreshListener(null)
        mRecyclerView?.layoutManager = null
        mRecyclerView?.adapter = null
        super.onDestroyView()
    }

    inner class ClassHolder(val binding: ItemClassBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {


        //        private val ivCover: ImageView by lazy { itemView.findViewById<ImageView>(R.id.iv_cover) }
//        private val tvName: ImageView by lazy { itemView.findViewById<ImageView>(R.id.tv_name) }
//        private val tvLabel: ImageView by lazy { itemView.findViewById<ImageView>(R.id.tv_label) }
//        private val tvPrice: ImageView by lazy { itemView.findViewById<ImageView>(R.id.tv_price) }
//        private val btnGoto: ImageView by lazy { itemView.findViewById<ImageView>(R.id.btn_goto) }
        init {
        }

        fun bindTo(first: Boolean, data: String) {
            binding.isFirst = first
            binding.data = data
            binding.image.tag = data
            binding.image.setOnClickListener(this)
            Handler().post({
                android.util.Log.d("AppBindingAdapter", "post width " + (binding.image.width))
            })
        }

        override fun onClick(v: View) {
            val act = activity
            val delegate = if (act is StartFragment) act else null
            val tag = v.tag
            if (tag is String) {
                if (tag.contains("header")) {
                } else {
                    val dotIndex = tag.lastIndexOf(".")
                    val purcCoverUrl = "file:///android_asset/demo/purc_${tag.substring(dotIndex - 1, dotIndex)}.webp"
                    delegate?.startFragment(PurchaseFragment.newInstance(purcCoverUrl), PurchaseFragment.TAG)
                }
            }
        }
    }

    inner class ClassAdapter(val context: Context, val inflater: LayoutInflater, val data: Array<String>) : RecyclerView.Adapter<ClassHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassHolder = ClassHolder(ItemClassBinding.inflate(inflater, parent, false))

        override fun onBindViewHolder(holder: ClassHolder, position: Int) {
            holder.bindTo(position == 0, data.get(position))
        }

        override fun getItemCount(): Int = data.size

    }
}