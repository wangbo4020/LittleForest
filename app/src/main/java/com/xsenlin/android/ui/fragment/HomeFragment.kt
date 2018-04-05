package com.xsenlin.android.ui.fragment

import android.graphics.Point
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xsenlin.android.R
import com.xsenlin.android.databinding.ItemHomeBinding
import com.xsenlin.android.ui.StartFragment
import com.yarolegovich.discretescrollview.DiscreteScrollView
import com.yarolegovich.discretescrollview.InfiniteScrollAdapter
import com.yarolegovich.discretescrollview.Orientation
import com.yarolegovich.discretescrollview.transform.ScaleTransformer


/**
 * Created by Dylan on 2017/8/31.
 */
class HomeFragment : BaseFragment(), DiscreteScrollView.OnItemChangedListener<HomeFragment.HomeHolder> {

    companion object {
        val TAG = "HomeFragment"

        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    private val mData: Array<String> by lazy {
        arrayOf("file:///android_asset/demo/card_0.webp",
                "file:///android_asset/demo/card_1.webp",
                "file:///android_asset/demo/card_2.webp",
                "file:///android_asset/demo/card_3.webp")
    }

    private var mDiscreteWidget: DiscreteScrollView? = null
    private var mAdapter: InfiniteScrollAdapter<HomeHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAdapter = InfiniteScrollAdapter.wrap(HomeAdapter(getLayoutInflater(), mData))
        val p = Point()
        activity!!.getWindowManager().defaultDisplay.getSize(p)

        android.util.Log.d(TAG, "screen " + p + ", density " + resources.displayMetrics.density)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mDiscreteWidget = view.findViewById(R.id.discrete_widget)
        mDiscreteWidget!!.setOrientation(Orientation.HORIZONTAL)
        mDiscreteWidget!!.adapter = mAdapter
        mDiscreteWidget!!.setItemTransitionTimeMillis(150)
        mDiscreteWidget!!.setItemTransformer(ScaleTransformer.Builder().setMinScale(0.9f).build())
        mDiscreteWidget!!.addOnItemChangedListener(this)
    }

    override fun onCurrentItemChanged(viewHolder: HomeHolder?, adapterPosition: Int) {
        val position = mAdapter!!.getRealPosition(adapterPosition)
//        android.util.Log.d(TAG, "onCurrentItemChanged " + position)
    }

    override fun onDestroyView() {
//        mDiscreteWidget?.adapter = null
        super.onDestroyView()
    }

    inner class HomeHolder(val binding: ItemHomeBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        override fun onClick(v: View?) {
            val act = activity
            if (act is StartFragment) {
                act.startFragment(ClassFragment.newInstance(), ClassFragment.TAG)
            }
        }

        fun bindTo(data: String) {
            binding.image.tag = data
            binding.data = data
            binding.image.setOnClickListener(this)
        }
    }

    inner class HomeAdapter(val inflater: LayoutInflater, val data: Array<String>) : RecyclerView.Adapter<HomeHolder>() {
//        private val mProgress: ContentLoadingDelegate by lazy { ContentLoadingDelegate() }
        override fun onBindViewHolder(holder: HomeHolder, position: Int) {
//            mProgress.setup(R.id.content_loading_widget, holder.itemView)
//            mProgress.show()
            holder.bindTo(mData[position])
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {
            return HomeHolder(ItemHomeBinding.inflate(inflater))
        }

        override fun getItemCount(): Int = data.size

    }
}