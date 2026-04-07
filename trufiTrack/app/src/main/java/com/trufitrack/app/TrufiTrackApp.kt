package com.trufitrack.app

import android.app.Application
import com.trufitrack.core.initSupabaseClient
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TrufiTrackApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initSupabaseClient(BuildConfig.SUPABASE_URL, BuildConfig.SUPABASE_KEY)
    }
}
