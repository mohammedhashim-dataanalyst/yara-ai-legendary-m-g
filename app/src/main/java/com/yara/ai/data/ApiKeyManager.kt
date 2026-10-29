package com.yara.ai.data

import android.content.Context
import android.content.SharedPreferences

class ApiKeyManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("yara_api_prefs", Context.MODE_PRIVATE)

    fun getApiKey(index: Int): String {
        return prefs.getString("api_key_$index", "") ?: ""
    }

    fun setApiKey(index: Int, key: String) {
        prefs.edit().putString("api_key_$index", key).apply()
    }

    fun getActiveKeyIndex(): Int {
        return prefs.getInt("active_key_index", 0)
    }

    fun setActiveKeyIndex(index: Int) {
        prefs.edit().putInt("active_key_index", index).apply()
    }

    fun getNextAvailableKey(): String {
        var currentIndex = getActiveKeyIndex()
        for (i in 0 until 4) {
            val key = getApiKey(currentIndex)
            if (key.isNotBlank()) {
                setActiveKeyIndex(currentIndex)
                return key
            }
            currentIndex = (currentIndex + 1) % 4
        }
        return "" // Uses offline local brain core if no cloud API keys configured
    }
}
