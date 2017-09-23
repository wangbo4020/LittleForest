package com.xsenlin.android.widget

import android.graphics.Bitmap
import android.graphics.Matrix
import com.squareup.picasso.Transformation

/**
 * Created by Dylan on 2017/9/10.
 */

class BasisScaleTransformation(private val key: String, private val targetSize: Int) : Transformation {

    private var basis: Int? = BASIS_WIDTH

    init {
        if (targetSize <= 0) {
            throw IllegalArgumentException("targetSize must be > 0")
        }
    }

    constructor(key: String, targetSize: Int, basis: Int) : this(key, targetSize) {
        this.basis = basis
    }

    override fun transform(source: Bitmap): Bitmap {
        // 获得图片的宽高
        val width = source.width
        val height = source.height
//        Log.d(TAG, "source " + source.width + "x" + source.height + " targetSize " + this.targetSize)

        if (basis == BASIS_WIDTH) {
            if (targetSize == width)
                return source
        } else {
            if (targetSize == height)
                return source
        }

        // 计算缩放比例
        val ratio = targetSize.toFloat() / if (basis == BASIS_WIDTH) width else height
        //        float scaleWidth = ((float) newWidth) / width;
        //        float scaleHeight = ((float) newHeight) / height;
        // 取得想要缩放的matrix参数
        val matrix = Matrix()
        matrix.postScale(ratio, ratio)
        // 得到新的图片
        val output = Bitmap.createBitmap(source, 0, 0, width, height, matrix, true)
        if (source != output && !source.isRecycled) {
            source.recycle()
        }
//        Log.d(TAG, "output " + output.width + "x" + output.height + " ratio " + ratio)
        return output
    }

    override fun key(): String {
        return "basis:$basis|targetSize:$targetSize|$key"
    }

    companion object {

        val TAG = "BSTrans"

        val BASIS_WIDTH = 0
        val BASIS_HEGHT = 1
    }
}
