package com.yara.ai.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.yara.ai.R
import com.yara.ai.data.ApiKeyManager
import com.yara.ai.data.MemoryRepository

class SettingsActivity : AppCompatActivity() {

    private lateinit var apiKeyManager: ApiKeyManager
    private lateinit var memoryRepository: MemoryRepository
    private lateinit var etUserName: EditText
    private lateinit var etKey0: EditText
    private lateinit var etKey1: EditText
    private lateinit var etKey2: EditText
    private lateinit var etKey3: EditText
    private lateinit var btnSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        apiKeyManager = ApiKeyManager(this)
        memoryRepository = MemoryRepository(this)

        etUserName = findViewById(R.id.etUserName)
        etKey0 = findViewById(R.id.etKey0)
        etKey1 = findViewById(R.id.etKey1)
        etKey2 = findViewById(R.id.etKey2)
        etKey3 = findViewById(R.id.etKey3)
        btnSave = findViewById(R.id.btnSave)

        etUserName.setText(memoryRepository.getUserName())
        etKey0.setText(apiKeyManager.getApiKey(0))
        etKey1.setText(apiKeyManager.getApiKey(1))
        etKey2.setText(apiKeyManager.getApiKey(2))
        etKey3.setText(apiKeyManager.getApiKey(3))

        btnSave.setOnClickListener {
            memoryRepository.setUserName(etUserName.text.toString().trim())
            apiKeyManager.setApiKey(0, etKey0.text.toString().trim())
            apiKeyManager.setApiKey(1, etKey1.text.toString().trim())
            apiKeyManager.setApiKey(2, etKey2.text.toString().trim())
            apiKeyManager.setApiKey(3, etKey3.text.toString().trim())
            Toast.makeText(this, "Memory & Multi-API Pool saved successfully!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
