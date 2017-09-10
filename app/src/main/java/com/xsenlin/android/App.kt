package com.xsenlin.android

import android.app.Application
import android.content.res.Configuration
import com.github.moduth.blockcanary.BlockCanary
import com.github.moduth.blockcanary.BlockCanaryContext
import com.squareup.leakcanary.LeakCanary

/**
 * Created by Dylan on 2017/8/30.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        setupCanary()
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
    }

    fun setupCanary() {

        if (BuildConfig.DEBUG) {
            BlockCanary.install(this, object : BlockCanaryContext() {
                override fun provideBlockThreshold(): Int = 500
                override fun displayNotification(): Boolean = true
            }).start()

            LeakCanary.install(this)
        }
    }
}