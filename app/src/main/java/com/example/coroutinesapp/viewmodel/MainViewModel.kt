package com.example.coroutinesapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    var resultState by mutableStateOf("")
        private set

    var countTime1 by mutableStateOf(0)
        private set

    var countTime2 by mutableStateOf(0)
        private set

    private var job: Job? = null

    private suspend fun firstCounter() {
        for (i in 1..5) {
            delay(1000)
            countTime1 = i
        }
    }

    private suspend fun secondCounter() {
        for (i in 1..5) {
            delay(1000)
            countTime2 = i
        }
    }

    fun fetchData() {
        job = viewModelScope.launch {
            firstCounter()
            secondCounter()
            delay(500)
            resultState = "Proceso finalizado"
        }
    }

    fun cancelJobs() {
        job?.cancel()
        countTime1 = 0
        countTime2 = 0
        resultState = "Proceso cancelado"
    }
}
