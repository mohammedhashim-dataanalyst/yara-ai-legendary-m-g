package com.yara.ai.data

import android.content.Context
import android.content.SharedPreferences

class MemoryRepository(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("yara_memory_brain", Context.MODE_PRIVATE)

    fun saveFact(key: String, value: String) {
        prefs.edit().putString(key.lowercase().trim(), value).apply()
    }

    fun getFact(key: String): String? {
        return prefs.getString(key.lowercase().trim(), null)
    }

    fun getUserName(): String {
        return prefs.getString("user_name", "Kannan") ?: "Kannan"
    }

    fun setUserName(name: String) {
        prefs.edit().putString("user_name", name).apply()
    }

    fun storeRecentInteraction(query: String, response: String) {
        val history = prefs.getString("chat_history", "") ?: ""
        val updated = "$history\nUser: $query\nYara: $response"
        prefs.edit().putString("chat_history", updated.takeLast(2000)).apply()
    }
}
