package com.yara.ai.ui

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.yara.ai.R
import com.yara.ai.data.ApiKeyManager
import com.yara.ai.data.MemoryRepository
import com.yara.ai.engine.EmotionEngine
import com.yara.ai.engine.ModeSelector
import com.yara.ai.engine.NeuralCore
import com.yara.ai.engine.YaraMode

class ChatActivity : AppCompatActivity() {

    private lateinit var apiKeyManager: ApiKeyManager
    private lateinit var memoryRepository: MemoryRepository
    private lateinit var emotionEngine: EmotionEngine
    private lateinit var neuralCore: NeuralCore
    private lateinit var modeSelector: ModeSelector

    private lateinit var tvStatus: TextView
    private lateinit var tvMode: TextView
    private lateinit var etMessage: EditText
    private lateinit var btnSend: ImageButton
    private lateinit var btnSettings: ImageButton
    private lateinit var btnSwitchMode: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        apiKeyManager = ApiKeyManager(this)
        memoryRepository = MemoryRepository(this)
        emotionEngine = EmotionEngine()
        neuralCore = NeuralCore(memoryRepository)
        modeSelector = ModeSelector()

        tvStatus = findViewById(R.id.tvStatus)
        tvMode = findViewById(R.id.tvMode)
        etMessage = findViewById(R.id.etMessage)
        btnSend = findViewById(R.id.btnSend)
        btnSettings = findViewById(R.id.btnSettings)
        btnSwitchMode = findViewById(R.id.btnSwitchMode)

        updateHeader()

        btnSend.setOnClickListener {
            val text = etMessage.text.toString().trim()
            if (text.isNotEmpty()) {
                processMessage(text)
                etMessage.text.clear()
            }
        }

        btnSettings.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }

        btnSwitchMode.setOnClickListener {
            val nextMode = when (modeSelector.currentMode) {
                YaraMode.GIRLFRIEND -> YaraMode.DATA_ANALYST
                YaraMode.DATA_ANALYST -> YaraMode.PERSONAL_ASSISTANT
                YaraMode.PERSONAL_ASSISTANT -> YaraMode.GIRLFRIEND
            }
            modeSelector.switchMode(nextMode)
            updateHeader()
        }
    }

    private fun updateHeader() {
        tvMode.text = "Mode: ${modeSelector.currentMode.displayName}"
        tvStatus.text = "Yara AI • Active Memory & Multi-Engine Ready"
    }

    private fun processMessage(input: String) {
        val activeKey = apiKeyManager.getNextAvailableKey()
        val currentMode = modeSelector.currentMode

        val response = if (activeKey.isNotBlank()) {
            "Yara Cloud (${currentMode.name}): Processed '$input' with active API failover."
        } else {
            neuralCore.generateOfflineResponse(input, currentMode)
        }

        memoryRepository.storeRecentInteraction(input, response)
        val emotionalState = emotionEngine.evaluateResponse(input, currentMode)

        tvStatus.text = "Mood: ${emotionalState.mood} | Emote: ${emotionalState.emoteCode}"
        tvStatus.setBackgroundColor(Color.parseColor(emotionalState.expressionColor))
    }
}
