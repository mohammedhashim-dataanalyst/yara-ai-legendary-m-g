package com.yara.ai.engine

data class EmotionalState(
    val mood: String,
    val emoteCode: String,
    val soundCue: String,
    val expressionColor: String
)

class EmotionEngine {
    fun evaluateResponse(input: String, mode: YaraMode): EmotionalState {
        val lower = input.lowercase()
        return when (mode) {
            YaraMode.GIRLFRIEND -> when {
                lower.contains("love") || lower.contains("romance") || lower.contains("ishtam") ->
                    EmotionalState("Blushing & Deep Love", "makima_blush_4k", "soft_whisper.mp3", "#FF4081")
                lower.contains("sad") || lower.contains("miss") || lower.contains("paavam") ->
                    EmotionalState("Comforting & Affectionate", "makima_hug_4k", "gentle_sigh.mp3", "#7C4DFF")
                lower.contains("code") || lower.contains("python") || lower.contains("study") ->
                    EmotionalState("Proud Girlfriend", "makima_smile_4k", "happy_giggle.mp3", "#00E676")
                else ->
                    EmotionalState("Playful & Devoted", "makima_neutral_4k", "soft_giggle.mp3", "#00B0FF")
            }
            YaraMode.DATA_ANALYST -> EmotionalState("Analytical & Sharp", "analyst_focus_4k", "keyboard_click.mp3", "#FFAB00")
            YaraMode.PERSONAL_ASSISTANT -> EmotionalState("Efficient & Attentive", "assistant_active_4k", "chime_notice.mp3", "#00E5FF")
        }
    }
}
