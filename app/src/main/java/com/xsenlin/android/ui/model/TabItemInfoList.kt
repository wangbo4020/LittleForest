package com.xsenlin.android.ui.model

import android.util.SparseArray

/**
 * Created by Dylan on 2017/9/8.
 */
class TabItemInfoList {

    val infos: SparseArray<TabItemInfo> = SparseArray(0)

    val count: Int
        get() = infos.size()

    data class TabItemInfo(val id: Int, val tag: String, val label: CharSequence) {

    }
}