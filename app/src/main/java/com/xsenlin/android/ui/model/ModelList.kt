package com.xsenlin.android.ui.model

import android.util.SparseArray

/**
 * Created by Dylan on 2017/9/9.
 */
open abstract class ModelList<T> {
    protected val infos: SparseArray<T> by lazy {
        SparseArray<T>(0)
    }

    var index: Int = 0

    val count: Int
        get() = infos.size()

    fun get(index: Int): T = infos[index]

    fun add(o: T) {
        infos.put(index++, o)
    }

    fun size(): Int = infos.size()
}