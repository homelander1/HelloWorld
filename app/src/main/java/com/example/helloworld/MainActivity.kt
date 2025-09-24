package com.example.helloworld

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
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
            // Remove HelloAppTheme wrapper if it doesn't exist
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
    // Array of 5 names to cycle through
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
    // Remove HelloAppTheme wrapper if it doesn't exist
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
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Second Activity Screen",
            modifier = Modifier.padding(16.dp)
        )

        androidx.compose.foundation.layout.Spacer(modifier = Modifier.padding(16.dp))

        Button(
            onClick = {
                onNavigateBack()
            }
        ) {
            Text("Return to Previous Screen")
        }
    }
}