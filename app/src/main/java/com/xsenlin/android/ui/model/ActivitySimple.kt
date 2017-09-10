package com.xsenlin.android.ui.model

import android.content.res.Resources
import com.xsenlin.android.R

data class ActivitySimple(val id: Int, var type: Int, var locationUrl: String?, var coverUrl: String) {

    class ActivitySimpleList(res: Resources) : ModelList<ActivitySimple>(), TitleLabel {

        override var label: CharSequence = res.getString(R.string.first_activity_title)

    }
}