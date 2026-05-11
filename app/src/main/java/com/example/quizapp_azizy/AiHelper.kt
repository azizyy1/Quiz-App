package com.example.quizapp_azizy

import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object AiHelper {
    // IMPORTANT : Remplace par ta clé API de https://aistudio.google.com/
    private const val API_KEY = "AIzaSyDfrWuJqSisdHxednkrK-Y3bWXaZ7DyWuE"
    
    private val model = GenerativeModel(
        modelName = "gemini-1.5-flash",
        apiKey = API_KEY
    )

    interface AiCallback {
        fun onResponse(explanation: String)
        fun onError(error: String)
    }

    @JvmStatic
    fun getExplanation(question: String, answer: String, callback: AiCallback) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val prompt = "Tu es un moniteur d'auto-école. Explique de manière courte et pédagogique pourquoi la réponse '$answer' est la bonne pour la question suivante : '$question'. Sois concis (maximum 2 phrases)."
                val response = withContext(Dispatchers.IO) {
                    model.generateContent(prompt)
                }
                callback.onResponse(response.text ?: "Pas d'explication disponible.")
            } catch (e: Exception) {
                callback.onError(e.localizedMessage ?: "Erreur inconnue")
            }
        }
    }
}
