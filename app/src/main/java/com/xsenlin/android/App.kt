package com.xsenlin.android

import android.app.Application
import android.content.res.Configuration
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration



/**
 * Created by Dylan on 2017/8/30.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        setupImageLoader()
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
    }

    // 初始化ImageLoader
    fun setupImageLoader() {
        val configuration = ImageLoaderConfiguration .createDefault(this)
        //Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(configuration)
    }
}