package com.advaitvedant.openphonics

import android.app.Application
import com.advaitvedant.sync.utils.Sync
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class OpApplication : Application(){
    override fun onCreate(){
        super.onCreate()
        Sync.initialize(context = this)
    }
}