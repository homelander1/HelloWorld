package com.example.helloworld

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import android.content.Intent
import android.util.Log

class MainActivity : ComponentActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate() - Activity створена")

        enableEdgeToEdge()
        setContent {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                MainContent(
                    modifier = Modifier.padding(innerPadding),
                    onNavigateToSecond = {
                        val intent = Intent(this@MainActivity, SecondActivity::class.java)
                        startActivity(intent)
                    }
                )
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() - Activity стає видимою")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() - Activity готова до взаємодії")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() - Activity втрачає фокус")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() - Activity стає невидимою")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart() - Activity перезапускається")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() - Activity знищується")
    }
}

@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    onNavigateToSecond: () -> Unit = {}
) {
    // Array of 4 names to cycle through
    val names = arrayOf("Roman", "Ivan", "Pavlo", "Petro")

    // Remember the current index in the names array
    val currentNameIndex = remember { mutableIntStateOf(0) }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top content - display current name from the array
        Greeting(names[currentNameIndex.intValue])

        // Middle buttons
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    // Cycle to the next name, wrapping around to 0 when reaching the end
                    currentNameIndex.intValue = (currentNameIndex.intValue + 1) % names.size
                    Log.d("MainActivity", "Name changed to: ${names[currentNameIndex.intValue]}")
                }
            ) {
                Text("Change Title")
            }

            androidx.compose.foundation.layout.Spacer(modifier = Modifier.padding(8.dp))

            Button(
                onClick = {
                    Log.d("MainActivity", "Navigating to SecondActivity")
                    onNavigateToSecond()
                }
            ) {
                Text("Go to Second Activity")
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MainContent()
}

// Second Activity
class SecondActivity : ComponentActivity() {

    companion object {
        private const val TAG = "SecondActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate() - SecondActivity створена")

        enableEdgeToEdge()
        setContent {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                SecondContent(
                    modifier = Modifier.padding(innerPadding),
                    onNavigateBack = {
                        Log.d(TAG, "Navigating back to MainActivity")
                        finish() // Закриває поточну Activity і повертається до попередньої
                    }
                )
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() - SecondActivity стає видимою")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() - SecondActivity готова до взаємодії")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() - SecondActivity втрачає фокус")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() - SecondActivity стає невидимою")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart() - SecondActivity перезапускається")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() - SecondActivity знищується")
    }
}

@Composable
fun SecondContent(
    modifier: Modifier = Modifier,
    onNavigateBack: () -> Unit = {}
) {
    // Стан для зберігання введеного тексту
    val inputText = remember { mutableStateOf("") }
    // Стан для зберігання підтвердженого тексту
    val confirmedText = remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Second Activity Screen",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Поле для вводу тексту
        OutlinedTextField(
            value = inputText.value,
            onValueChange = { inputText.value = it },
            label = { Text("Введіть текст") },
            placeholder = { Text("Місце для тексту") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = false,
            maxLines = 3
        )

        // Кнопка для підтвердження вводу
        Button(
            onClick = {
                if (inputText.value.isNotBlank()) {
                    confirmedText.value = inputText.value.trim()
                    inputText.value = "" // Очищаємо поле вводу після підтвердження
                    Log.d("SecondActivity", "Text confirmed: ${confirmedText.value}")
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Підтвердити введення")
        }

        // Місце для виведення підтвердженого тексту
        if (confirmedText.value.isNotEmpty()) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Виведення тексту:",
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    androidx.compose.foundation.layout.Spacer(modifier = Modifier.padding(4.dp))
                    Text(
                        text = confirmedText.value,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
        }

        androidx.compose.foundation.layout.Spacer(modifier = Modifier.weight(1f))

        // Кнопка повернення
        Button(
            onClick = {
                onNavigateBack()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Повернутися до попереднього екрану")
        }
    }
}