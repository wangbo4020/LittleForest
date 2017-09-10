package com.xsenlin.android.ui.model

import android.content.res.Resources
import com.xsenlin.android.R

data class GoodsSimple(val id: Int, var name: CharSequence, var label: CharSequence, var imageUrl: String) {

    class GoodsSimpleList(res: Resources) : ModelList<GoodsSimple>(), TitleLabel {

        override var label: CharSequence = res.getString(R.string.first_goods_title)
    }
}