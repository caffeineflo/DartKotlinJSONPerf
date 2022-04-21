package com.example.jsonbenchmark

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlin.system.measureTimeMillis
import kotlinx.serialization.*
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView: TextView = findViewById(R.id.textView)
        val button: Button = findViewById(R.id.button)

        button.setOnClickListener {
            textView.text = "JSON Benchmark started"

            val json = Json { coerceInputValues = true }

            val airportsString = resources.openRawResource(R.raw.airports).bufferedReader().use { it.readText() }
            val timeInMillis = measureTimeMillis {
                json.decodeFromString(
                    ListSerializer(AirportElement.serializer()),
                    airportsString
                )
            }

            textView.text = "JSON Serialization took ${timeInMillis} ms"
        }
    }
}