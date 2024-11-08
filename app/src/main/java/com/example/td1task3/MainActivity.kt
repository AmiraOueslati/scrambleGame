package com.example.td1task3


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.td1task3.ViewModel.ScrumbleViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: ScrumbleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScrumbleGame(viewModel)
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScrumbleGame(viewModel: ScrumbleViewModel) {
    val currentWord by viewModel.currentWord
    val userGuess by viewModel.userGuess
    val score by viewModel.score
    val progress by viewModel.progress

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Unscramble") }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = "Progress: $progress", style = MaterialTheme.typography.bodyMedium)
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(text = currentWord, style = MaterialTheme.typography.headlineSmall)
                    Text(text = "Unscramble the word using all the letters.", style = MaterialTheme.typography.bodyMedium)
                }
            }

            OutlinedTextField(
                value = userGuess,
                onValueChange = { viewModel.userGuess.value = it },
                label = { Text("Enter your word") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = { viewModel.checkGuess() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Submit")
            }

            OutlinedButton(
                onClick = { viewModel.skipWord() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Skip")
            }

            Text(text = "Score: $score", style = MaterialTheme.typography.headlineMedium)
        }
    }
}
