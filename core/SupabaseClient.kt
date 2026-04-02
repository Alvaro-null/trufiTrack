// core/SupabaseClient.kt
package com.trufitrack.core

import com.trufitrack.app.BuildConfig
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.realtime.Realtime

val supabaseClient = createSupabaseClient(
    supabaseUrl = BuildConfig.SUPABASE_URL,
    supabaseKey = BuildConfig.SUPABASE_KEY
) {
    install(Auth)
    install(Postgrest)
    install(Realtime)
}