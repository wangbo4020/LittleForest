package com.xsenlin.android.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xsenlin.android.R

/**
 * Created by Dylan on 2017/8/31.
 */
class MineFragment : BaseFragment() {

    companion object {
        val TAG = "MineFragment"

        fun newInstance(): MineFragment {
            return MineFragment()
        }
    }

    private val mSignFragment: SignFragment by lazy { SignFragment.newInstance() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_mine, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // FIXME 根据条件判断，显示SignFragment or InfoFragment
        childFragmentManager.beginTransaction()
                .add(R.id.fragment_container, mSignFragment, SignFragment.TAG)
                .show(mSignFragment).commitNow()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }
}