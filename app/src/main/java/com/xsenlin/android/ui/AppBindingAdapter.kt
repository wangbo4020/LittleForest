package com.xsenlin.android.ui

import android.databinding.BindingAdapter
import android.support.constraint.ConstraintLayout
import android.support.v4.view.MarginLayoutParamsCompat
import android.support.v4.view.ViewCompat
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.xsenlin.android.widget.BasisScaleTransformation

/**
 * Created by Dylan on 2017/9/10.
 */

object AppBindingAdapter {

    val printLog = false
    val TAG = "AppBindingAdapter"

    @JvmStatic
    val TRANSFORM_BASIS_SCALE = 1

    @JvmStatic
    @BindingAdapter("android:layout_marginLeft")
    fun setLayoutMarginLeft(view: View, marginLeft: Float) {
        if (printLog) Log.d(TAG, "marginLeft " + marginLeft)
        (view.layoutParams as ViewGroup.MarginLayoutParams).leftMargin = marginLeft.toInt()
    }

    @JvmStatic
    @BindingAdapter("android:layout_marginStart")
    fun setLayoutMarginStart(view: View, marginStart: Float) {
        if (printLog) Log.d(TAG, "marginStart " + marginStart)
        MarginLayoutParamsCompat.setMarginStart(view.layoutParams as ViewGroup.MarginLayoutParams, marginStart.toInt())
    }

    @JvmStatic
    @BindingAdapter("android:layout_marginRight")
    fun setLayoutMarginRight(view: View, marginRight: Float) {
        if (printLog) Log.d(TAG, "marginRight " + marginRight)
        (view.layoutParams as ViewGroup.MarginLayoutParams).rightMargin = marginRight.toInt()
    }

    @JvmStatic
    @BindingAdapter("android:layout_marginEnd")
    fun setLayoutMarginEnd(view: View, marginEnd: Float) {
        if (printLog) Log.d(TAG, "marginEnd " + marginEnd)
        MarginLayoutParamsCompat.setMarginEnd(view.layoutParams as ViewGroup.MarginLayoutParams, marginEnd.toInt())
    }

    @JvmStatic
    @BindingAdapter("android:paddingLeft")
    fun setPaddingLeft(view: View, paddingLeft: Float) {
        if (printLog) Log.d(TAG, "marginLeft " + paddingLeft)
        view.setPadding(paddingLeft.toInt(), view.paddingTop, view.paddingRight, view.paddingBottom)
    }

    @JvmStatic
    @BindingAdapter("android:paddingStart")
    fun setPaddingStart(view: View, paddingStart: Float) {
        if (printLog) Log.d(TAG, "paddingStart " + paddingStart)
        ViewCompat.setPaddingRelative(view, paddingStart.toInt(), view.paddingTop, ViewCompat.getPaddingEnd(view), view.paddingBottom)
    }

    @JvmStatic
    @BindingAdapter("android:paddingRight")
    fun setPaddingRight(view: View, paddingRight: Float) {
        if (printLog) Log.d(TAG, "setPaddingRight " + paddingRight)
        view.setPadding(view.paddingLeft, view.paddingTop, paddingRight.toInt(), view.paddingBottom)
    }

    @JvmStatic
    @BindingAdapter("android:paddingEnd")
    fun setPaddingEnd(view: View, paddingEnd: Float) {
        if (printLog) Log.d(TAG, "setPaddingEnd " + paddingEnd)
        ViewCompat.setPaddingRelative(view, ViewCompat.getPaddingStart(view), view.paddingTop, paddingEnd.toInt(), view.paddingBottom)
    }

    @JvmStatic
    @BindingAdapter("app:layout_constraintHorizontal_bias")
    fun setLayoutConstraintHorizontalBias(view: View, horizontalBias: Float) {
        if (printLog) Log.d(TAG, "setLayoutConstraintHorizontalBias " + horizontalBias)
        var lp = view.layoutParams
        if (lp is ConstraintLayout.LayoutParams)
            lp.horizontalBias = horizontalBias
    }

    @JvmStatic
    @BindingAdapter("app:layout_constraintLeft_toLeftOf")
    fun setLayoutConstraintLeftToLeftOf(view: View, leftToLeft: Int) {
        if (printLog) Log.d(TAG, "setLayoutConstraintLeftToLeftOf " + leftToLeft)
        var lp = view.layoutParams
        if (lp is ConstraintLayout.LayoutParams)
            lp.leftToLeft = leftToLeft
    }

    @JvmStatic
    @BindingAdapter("app:layout_constraintRight_toRightOf")
    fun setLayoutConstraintRightToRightOf(view: View, rightToRight: Int) {
        if (printLog) Log.d(TAG, "setLayoutConstraintRightToRightOf " + rightToRight)
        var lp = view.layoutParams
        if (lp is ConstraintLayout.LayoutParams)
            lp.rightToRight = rightToRight
    }

    @JvmStatic
    @BindingAdapter("android:src")
    fun setImageUrl(view: ImageView, url: String) {
        if (printLog) Log.d(TAG, "setImageUrl " + url + " " + view.width + " " + view.measuredWidth + " " + url)
        Picasso.with(view.context).load(url).into(view)
    }

    @JvmStatic
    @BindingAdapter("android:src", "bind:transform")
    fun setImageUrlTransform(view: ImageView, url: String, trans: Int) {
        if (printLog) Log.d(TAG, "setImageUrlTransform " + trans + " " + url + " " + view.width + " " + view.measuredWidth + " " + url)

        val targetSize = view.width
        val creator = Picasso.with(view.context).load(url)
        when (trans) {
            TRANSFORM_BASIS_SCALE -> if (targetSize > 0) creator.transform(BasisScaleTransformation(url, targetSize))
        }
        creator.into(view)
    }

    @JvmStatic
    @BindingAdapter("android:tag")
    fun setTag(view: View, tag: Any) {
        if (printLog) Log.d(TAG, "setTag " + tag)
        view.tag = tag
    }

    /**
     * 计算 {@see com.xsenlin.android.ui.fragment.FirstHomeFragment } 布局的边距
     */
    @JvmStatic
    @BindingAdapter("bind:layout_columns", "bind:layout_marginExternal", "bind:layout_marginInternal", "bind:layout_horizontalStart", "bind:layout_horizontalEnd")
    fun setGridLayoutMargin(view: View, columns: Int, marginSpace: Float, internalSpace: Float, horizontalStart: Boolean, horizontalEnd: Boolean) {
        if (printLog) Log.d(TAG, "setGridLayoutMargin columns = $columns, marginSpace = $marginSpace, internalSpace = $internalSpace, horizontalStart = $horizontalStart, horizontalEnd = $horizontalEnd")
        val lp = view.layoutParams
        if (lp is ViewGroup.MarginLayoutParams) {
            val extTotal = marginSpace * 2
            val intTotal = internalSpace * (columns - 1)
            val avgTotal = (extTotal + intTotal) / columns
            if (horizontalStart) {
                lp.leftMargin = marginSpace.toInt()
                lp.rightMargin = (avgTotal - marginSpace).toInt()
            } else if (horizontalEnd) {
                lp.leftMargin = (avgTotal - marginSpace).toInt()
                lp.rightMargin = marginSpace.toInt()
            } else {
                lp.leftMargin = (avgTotal / 2).toInt()
                lp.rightMargin = (avgTotal / 2).toInt()
            }
//            Log.d(TAG, "setGridLayoutMargin columns = $columns, marginSpace = $marginSpace, internalSpace = $internalSpace, horizontalStart = $horizontalStart, horizontalEnd = $horizontalEnd, leftMargin = ${lp.leftMargin}, rightMargin = ${lp.rightMargin}")
        }
    }
}
