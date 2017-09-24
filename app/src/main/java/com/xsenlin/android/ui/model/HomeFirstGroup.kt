package com.xsenlin.android.ui.model

import android.content.res.Resources
import android.util.Log
import android.util.SparseArray
import com.xsenlin.android.ui.model.ActivitySimple.ActivitySimpleList
import com.xsenlin.android.ui.model.BannerInfo.BannerInfoList
import com.xsenlin.android.ui.model.GoodsSimple.GoodsSimpleList
import com.xsenlin.android.ui.model.StorySimple.StorySimpleList

/**
 * Created by Dylan on 2017/9/9.
 * 首页数据集合
 */
class HomeFirstGroup(var columns: Int, res: Resources) {

    companion object {

        val TYPE_DEFAULT = 0
        val TYPE_HEADER = 1
        val TYPE_TITLE = 2
        val TYPE_ACTIVITY = 3
        val TYPE_STORIES = 4 // story因为是独立列表，下方做了特殊处理
        val TYPE_GOODS = 5
        val TYPE_FOOTER = 6

        fun typeToString(type: Int): String = when (type) {
            TYPE_DEFAULT -> "TYPE_DEFAULT"
            TYPE_HEADER -> "TYPE_HEADER"
            TYPE_TITLE -> "TYPE_TITLE"
            TYPE_ACTIVITY -> "TYPE_ACTIVITY"
            TYPE_STORIES -> "TYPE_STORIES"
            TYPE_GOODS -> "TYPE_GOODS"
            TYPE_FOOTER -> "TYPE_FOOTER"
            else -> "UNKNOW_$type"
        }

        fun createExapme(columns: Int, res: Resources): HomeFirstGroup {
            val data = HomeFirstGroup(columns, res)
            data.banners.add(BannerInfo(0, 0, null, "file:///android_asset/demo/banner_0.png", null))
            data.banners.add(BannerInfo(0, 0, null, "", null))
            data.banners.add(BannerInfo(0, 0, null, "", null))

            data.activities.add(ActivitySimple(1, 0, null, "file:///android_asset/demo/activity_0.png"))
            data.activities.add(ActivitySimple(2, 0, null, "file:///android_asset/demo/activity_1.png"))

            for (i in 0..9) {
                data.stories.add(StorySimple(i * 4 + 1, "爱情树的故事 " + (i * 4 + 1), "file:///android_asset/demo/story_0.png"))
                data.stories.add(StorySimple(i * 4 + 2, "生日树的故事 " + (i * 4 + 2), "file:///android_asset/demo/story_1.png"))
                data.stories.add(StorySimple(i * 4 + 3, "许愿树的故事 " + (i * 4 + 3), "file:///android_asset/demo/story_2.png"))
                data.stories.add(StorySimple(i * 4 + 4, "友情树的故事 " + (i * 4 + 4), "file:///android_asset/demo/story_3.png"))
            }

            for (i in 0..9) {
                data.goods.add(GoodsSimple(i * 4 + 1, "红枫", "" + (i * 4 + 1) + " 散播祝福，友谊长青", "file:///android_asset/demo/goods_0.png"))
                data.goods.add(GoodsSimple(i * 4 + 2, "樱花", "" + (i * 4 + 2) + " 这个季节和樱花最配哦", "file:///android_asset/demo/goods_1.png"))
                data.goods.add(GoodsSimple(i * 4 + 3, "香樟", "" + (i * 4 + 3) + " 年年岁岁，情谊长存", "file:///android_asset/demo/goods_2.png"))
                data.goods.add(GoodsSimple(i * 4 + 4, "合欢花", "" + (i * 4 + 4) + " 孤傲与气节并存乃君子之风骨", "file:///android_asset/demo/goods_3.png"))
            }
            return data
        }
    }

    val banners: BannerInfoList by lazy {
        BannerInfoList()
    }

    val activities: ActivitySimpleList by lazy {
        ActivitySimpleList(res)
    }

    val stories: StorySimpleList by lazy {
        StorySimpleList(res)
    }

    val goods: GoodsSimpleList by lazy {
        GoodsSimpleList(res)
    }

    private val array: Array<ModelList<out Any>> = arrayOf(banners, activities, stories, goods)

    fun getRelativePosition(position: Int): Int {
        val bKey = 0
        val aTitleKey = 1
        val aStartKey = 2
        val aEndKey = 3
        val sTitleKey = 4
        val sStartKey = 5
        val sEndKey = 6
        val gTitleKey = 7
        val gStartKey = 8
        val gEndKey = 9
        val mark = calcPosition(bKey, aTitleKey, aStartKey, aEndKey, sTitleKey, sStartKey, sEndKey, gTitleKey, gStartKey, gEndKey)

        return when (position) {
            mark[bKey] -> 0
            mark[aTitleKey] -> 0
            in mark[aStartKey]..mark[aEndKey] -> (position - mark[aStartKey])
            mark[sTitleKey] -> 0
            in mark[sStartKey]..mark[sEndKey] -> (position - mark[sStartKey])
            mark[gTitleKey] -> 0
            in mark[gStartKey]..mark[gEndKey] -> (position - mark[gStartKey])
            else -> throw NullPointerException("Not found the object $position")
        }

    }

    fun getTypeByPosition(position: Int): Int {
        val bKey = 0
        val aTitleKey = 1
        val aStartKey = 2
        val aEndKey = 3
        val sTitleKey = 4
        val sStartKey = 5
        val sEndKey = 6
        val gTitleKey = 7
        val gStartKey = 8
        val gEndKey = 9
        val mark = calcPosition(bKey, aTitleKey, aStartKey, aEndKey, sTitleKey, sStartKey, sEndKey, gTitleKey, gStartKey, gEndKey)

        return when (position) {
            mark[bKey] -> TYPE_HEADER
            mark[aTitleKey] -> TYPE_TITLE
            in mark[aStartKey]..mark[aEndKey] -> TYPE_ACTIVITY
            mark[sTitleKey] -> TYPE_TITLE
            in mark[sStartKey]..mark[sEndKey] -> TYPE_STORIES
            mark[gTitleKey] -> TYPE_TITLE
            in mark[gStartKey]..mark[gEndKey] -> TYPE_GOODS
            else -> throw IndexOutOfBoundsException("$position")
        }
    }

    fun <T> get(position: Int): T {
        val bKey = 0
        val aTitleKey = 1
        val aStartKey = 2
        val aEndKey = 3
        val sTitleKey = 4
        val sStartKey = 5
        val sEndKey = 6
        val gTitleKey = 7
        val gStartKey = 8
        val gEndKey = 9
        val mark = calcPosition(bKey, aTitleKey, aStartKey, aEndKey, sTitleKey, sStartKey, sEndKey, gTitleKey, gStartKey, gEndKey)

        // TODO Banner为独立处理，如需列表处理，请给Banner加上bStartKey与bEndKey
        return when (position) {
            mark[bKey] -> banners
            mark[aTitleKey] -> activities
            in mark[aStartKey]..mark[aEndKey] -> wrapGet(activities, position - mark[aStartKey])//if (activities.isIndependent) activities else activities.get(position - mark[aStartKey])
            mark[sTitleKey] -> stories
            in mark[sStartKey]..mark[sEndKey] -> wrapGet(stories, position - mark[sStartKey])//if (stories.isIndependent) stories else stories.get(position - mark[sStartKey])
            mark[gTitleKey] -> goods
            in mark[gStartKey]..mark[gEndKey] -> wrapGet(goods, position - mark[gStartKey])//if (goods.isIndependent) goods else goods.get(position - mark[gStartKey])
            else -> throw NullPointerException("Not found the object $position")
        } as T
    }

    fun getChildCountByType(type: Int): Int {
        return when (type) {
            HomeFirstGroup.TYPE_HEADER -> wrapCount(banners)
            HomeFirstGroup.TYPE_TITLE -> {
                var count = 0
                // count title
                for (i in array) {
                    if (i.count <= 0) continue
                    if (i is TitleLabel) count++
                }
                count
            }
            HomeFirstGroup.TYPE_ACTIVITY -> wrapCount(activities)
            HomeFirstGroup.TYPE_STORIES -> wrapCount(stories)
            HomeFirstGroup.TYPE_GOODS -> wrapCount(goods)
//            HomeFirstGroup.TYPE_FOOTER -> 0
            else -> 0
        }
    }

    fun getTotalCount(): Int {
        var count = 0
        for (i in array) {
            if (i.count <= 0) continue
            if (i is TitleLabel) count++
            count += if (i.isIndependent) 1 else i.count
        }
        return count
    }

    /**
     * 计算出各个不同类型对象所对应的区间
     */
    private fun calcPosition(bKey: Int, aTitleKey: Int, aStartKey: Int, aEndKey: Int,
                             sTitleKey: Int, sStartKey: Int, sEndKey: Int,
                             gTitleKey: Int, gStartKey: Int, gEndKey: Int): SparseArray<Int> {
        val headSize = 1

        val bCount = wrapCount(banners)
        val aCount = wrapCount(activities)
        val sCount = wrapCount(stories)
        val gCount = wrapCount(goods)

        val hasBanner = bCount > 0
        val hasActivity = aCount > 0
        val hasStory = sCount > 0
        val hasGoods = gCount > 0

        val aAtom = if (hasActivity) 1 else 0
        val sAtom = if (hasStory) 1 else 0
        val gAtom = if (hasGoods) 1 else 0

        // 根据when的..表达式为包前包后
        val bPos = (if (hasBanner) 1 else 0) - 1

        val aPos = bPos + (if (hasActivity) headSize else 0)

        val aStart = aPos + aAtom
        val aEnd = aStart + aCount - aAtom

        val sPos = aEnd + (if (hasStory) headSize else 0)

        val sStart = sPos + sAtom
        val sEnd = sStart + sCount - sAtom

        val gPos = sEnd + (if (hasGoods) headSize else 0)

        val gStart = gPos + gAtom
        val gEnd = gStart + gCount - gAtom

        val collect = SparseArray<Int>(10)
        collect.put(bKey, bPos)
        collect.put(aTitleKey, aPos)
        collect.put(aStartKey, aStart)
        collect.put(aEndKey, aEnd)
        collect.put(sTitleKey, sPos)
        collect.put(sStartKey, sStart)
        collect.put(sEndKey, sEnd)
        collect.put(gTitleKey, gPos)
        collect.put(gStartKey, gStart)
        collect.put(gEndKey, gEnd)

//        Log.d("HomeFirstGroup", "bPos $bPos aPos $aPos aStart $aStart aEnd $aEnd sPos $sPos sStart $sStart sEnd $sEnd gPos $gPos gStart $gStart gEnd $gEnd")
        return collect
    }

    private fun wrapCount(ml: ModelList<out Any>): Int = if (ml.isIndependent && ml.count > 0) 1 else ml.count

    private fun <D, T : ModelList<out D>> wrapGet(ml: T, position: Int): D = (if (ml.isIndependent) ml else ml.get(position)) as D
}