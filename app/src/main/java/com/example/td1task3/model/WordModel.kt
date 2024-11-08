package com.example.td1task3.model

// WordModel.kt

data class WordModel(val originalWord: String) {
    val scrambledWord: String
        get() = originalWord.toCharArray().apply { shuffle() }.concatToString()
}
