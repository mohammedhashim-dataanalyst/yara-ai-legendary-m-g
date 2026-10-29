package com.yara.ai.engine

enum class YaraMode(val displayName: String) {
    GIRLFRIEND("Girlfriend Persona (Makima 20yo)"),
    DATA_ANALYST("Data Analyst & Python Tutor"),
    PERSONAL_ASSISTANT("Smart Task Assistant")
}

class ModeSelector {
    var currentMode: YaraMode = YaraMode.GIRLFRIEND

    fun switchMode(mode: YaraMode) {
        currentMode = mode
    }
}
