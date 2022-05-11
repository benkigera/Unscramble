package com.example.android.unscramble.ui.game

import android.util.Log
import androidx.lifecycle.ViewModel

private val TAG = GameViewModel::class.java.simpleName

class GameViewModel : ViewModel() {


    //declare all word list and current word
    private var wordsList: MutableList<String> = mutableListOf()
    private lateinit var currentWord: String

    //add backing property to score variable
    private var _score = 0
    val score: Int
        get() = _score


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

    /*
   * Returns true if the current word count is less than MAX_NO_OF_WORDS.
   * Updates the next world
   * */

    fun nextWord(): Boolean {
        return if (currentWordCount < MAX_NO_OF_WORDS) {
            getNextWord()
            true
        } else false
    }

    private fun increaseScore() {
        _score += SCORE_INCREASE
    }

    fun isUserWordCorrect(playerWord: String): Boolean {
        if (playerWord.equals(currentWord, true)) {
            increaseScore()
            return true
        }
        return false
    }


}