package com.example.android.unscramble.ui.game

import android.util.Log
import androidx.lifecycle.ViewModel

private val TAG = GameViewModel::class.java.simpleName

class GameViewModel : ViewModel() {



    //declare all word list and current word
    private var wordsList: MutableList<String> = mutableListOf()
    private lateinit var currentWord: String

    private var _currentWordCount = 0
    private val currentWordCount: Int
        get() = _currentWordCount


    private lateinit var _currentScrambledWord: String
    val currentScrambledWord: String
        get() = _currentScrambledWord




    init {
        Log.d(TAG, "GameViewModel created!")
        getNextWord()
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "GameViewModel destroyed!")
    }





    /**
     * Updates currentWord and currentScrambledWord with the next word.
     * */
    private fun getNextWord() {
        currentWord = allWordsList.random()
        val tempWord = currentWord.toCharArray()
        tempWord.shuffle()

        while (String(tempWord).equals(currentWord, false)) {
            tempWord.shuffle()
        }
        if (wordsList.contains(currentWord)) {
            getNextWord()
        } else {
            _currentScrambledWord = String(tempWord)
            ++_currentWordCount
            wordsList.add(currentWord)
        }
    }



}