package com.xsenlin.android.widget

import android.support.v4.view.ViewPager
import android.view.View

/**
 * Created by Dylan on 2017/10/17.
 */

class ScalePageTransformer(val minScale: Float, val maxScale: Float) : ViewPager.PageTransformer {

    constructor() : this(0.8f, 1f)

    override fun transformPage(page: View, position: Float) {
        var position = position

        if (position < -1) {
            position = -1f
        } else if (position > 1) {
            position = 1f
        }

        val tempScale = if (position < 0) 1 + position else 1 - position

        val slope = (maxScale - minScale) / 1
        //一个公式
        val scaleValue = minScale + tempScale * slope
        page.scaleX = scaleValue
        page.scaleY = scaleValue
    }
}
