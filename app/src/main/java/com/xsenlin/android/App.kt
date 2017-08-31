package com.xsenlin.android

import android.app.Application
import android.content.res.Configuration

/**
 * Created by Dylan on 2017/8/30.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
    }
}