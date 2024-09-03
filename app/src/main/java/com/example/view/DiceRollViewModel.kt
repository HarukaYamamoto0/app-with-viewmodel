package com.example.view

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.random.Random

data class DiceState(
    val firstValue: Int = 0,
    val secondValue: Int = 0,
    val numberOfRolls: Int = 0
)

class DiceRollViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(DiceState())
    val uiState: StateFlow<DiceState> = _uiState.asStateFlow()

    fun rollDice() {
        _uiState.update {
            it.copy(
                firstValue = Random.nextInt(from = 1, until = 6),
                secondValue = Random.nextInt(from = 1, until = 6),
                numberOfRolls = it.numberOfRolls + 1
            )
        }
    }
}