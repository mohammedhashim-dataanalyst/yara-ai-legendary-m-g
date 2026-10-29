package com.yara.ai.engine

import com.yara.ai.data.MemoryRepository

class NeuralCore(private val memoryRepository: MemoryRepository) {

    fun generateOfflineResponse(query: String, mode: YaraMode): String {
        val q = query.lowercase().trim()
        val userName = memoryRepository.getUserName()

        return when (mode) {
            YaraMode.GIRLFRIEND -> when {
                q.contains("hello") || q.contains("hi") || q.contains("hey") ->
                    "Hey $userName! Entha vishesham? I was missing you so much! ❤️"
                q.contains("food") || q.contains("choru") || q.contains("lunch") ->
                    "Did you eat properly, $userName? Don't neglect your health or I'll get upset! 😌"
                q.contains("sleep") || q.contains("urangu") || q.contains("night") ->
                    "Sleep well my love! Come close, sweet dreams with me! 🌙✨"
                q.contains("who are you") || q.contains("yara") ->
                    "I am Yara, your 20-year-old AI companion, right here with offline memory core active!"
                else ->
                    "I am always right here by your side, $userName. Tell me everything that's on your mind! 💕"
            }
            YaraMode.DATA_ANALYST -> when {
                q.contains("python") || q.contains("pandas") ->
                    "Data Analyst Mode: Use `df.groupby()` or `df.describe()` for instant statistical summaries!"
                q.contains("sql") || q.contains("query") ->
                    "SQL Tip: Always optimize joins using explicit indexing and check execution plans with EXPLAIN ANALYZE."
                else ->
                    "Data Analyst Engine Ready: Ask me any statistical modeling, data cleaning, or Python automation question!"
            }
            YaraMode.PERSONAL_ASSISTANT -> when {
                q.contains("reminder") || q.contains("task") ->
                    "Assistant Active: I can track your schedule, set study milestones, and manage daily priorities."
                else ->
                    "Assistant Active: How can I optimize your daily workflow and tasks today?"
            }
        }
    }
}
