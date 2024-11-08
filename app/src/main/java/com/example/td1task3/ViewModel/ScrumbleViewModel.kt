package com.example.td1task3.ViewModel


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.td1task3.model.WordModel

class ScrumbleViewModel : ViewModel() {
    private val wordsList = listOf("android", "compose", "kotlin", "viewmodel", "scramble")
    private var currentWordIndex = 0

    // État de l'application
    var currentWord = mutableStateOf(WordModel(wordsList[currentWordIndex]).scrambledWord)
    var userGuess = mutableStateOf("")
    var score = mutableStateOf(0)
    var progress = mutableStateOf("0/${wordsList.size}")

    // Méthode pour vérifier si la réponse est correcte
    fun checkGuess() {
        if (userGuess.value.equals(wordsList[currentWordIndex], ignoreCase = true)) {
            score.value += 10
            nextWord()
        } else {
            // Affichez un message ou gérez la réponse incorrecte ici
        }
    }

    // Méthode pour passer au mot suivant
    private fun nextWord() {
        currentWordIndex++
        if (currentWordIndex < wordsList.size) {
            currentWord.value = WordModel(wordsList[currentWordIndex]).scrambledWord
            progress.value = "${currentWordIndex + 1}/${wordsList.size}"
            userGuess.value = ""
        } else {
            // Réinitialiser le jeu ou afficher un message de fin de jeu
            currentWordIndex = 0
            score.value = 0
            progress.value = "0/${wordsList.size}"
            currentWord.value = WordModel(wordsList[currentWordIndex]).scrambledWord
        }
    }

    // Méthode pour sauter le mot actuel sans marquer de points
    fun skipWord() {
        nextWord()
    }
}
