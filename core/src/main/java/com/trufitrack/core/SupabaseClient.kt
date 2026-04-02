package com.trufitrack.core

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.realtime.Realtime

lateinit var supabaseClient: SupabaseClient
    private set

fun initSupabaseClient(url: String, key: String) {
    supabaseClient = createSupabaseClient(
        supabaseUrl = url,
        supabaseKey = key
    ) {
        install(Auth)
        install(Postgrest)
        install(Realtime)
    }
}
