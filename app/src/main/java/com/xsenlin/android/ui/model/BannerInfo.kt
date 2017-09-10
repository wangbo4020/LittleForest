package com.xsenlin.android.ui.model

data class BannerInfo(val id: Int, var type: Int, var summary: CharSequence?, var imageUrl: String, var locationUrl: String?) {

    class BannerInfoList : ModelList<BannerInfo>() {

        fun getBannerImageUrls(): List<String> {
            val list = ArrayList<String>(size())
            for (i in 0..(size() - 1)) {
                list.add(infos.get(i).imageUrl)
            }
            return list
        }
    }
}