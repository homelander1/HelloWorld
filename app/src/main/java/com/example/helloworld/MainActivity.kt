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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // Remove HelloAppTheme wrapper if it doesn't exist
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                MainContent(modifier = Modifier.padding(innerPadding))
            }
        }
    }
}

@Composable
fun MainContent(modifier: Modifier = Modifier) {
    // Array of 5 names to cycle through
    val names = arrayOf("Roman", "Ivan", "Pavlo", "Dmytro")

    // Remember the current index in the names array
    val currentNameIndex = remember { mutableIntStateOf(0) }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top content - display current name from the array
        Greeting(names[currentNameIndex.intValue])

        // Bottom button
        Button(
            onClick = {
                // Cycle to the next name, wrapping around to 0 when reaching the end
                currentNameIndex.intValue = (currentNameIndex.intValue + 1) % names.size
                println("Button clicked! Current name: ${names[currentNameIndex.intValue]}")
            }
        ) {
            Text("Change Title")
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