package com.xsenlin.android.ui.model

/**
 * Created by Dylan on 2017/9/8.
 */
class TabItemInfoList : ModelList<TabItemInfoList.TabItemInfo>() {

    data class TabItemInfo(val id: Int, val tag: String, val label: CharSequence) {

    }
}