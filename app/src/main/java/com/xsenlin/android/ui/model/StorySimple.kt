package com.xsenlin.android.ui.model

import android.content.res.Resources
import com.xsenlin.android.R

data class StorySimple(val id: Int, var lable: CharSequence, var coverUrl: String) {

    class StorySimpleList(res: Resources) : ModelList<StorySimple>(), TitleLabel {

        override var label: CharSequence = res.getString(R.string.first_story_title)

    }
}