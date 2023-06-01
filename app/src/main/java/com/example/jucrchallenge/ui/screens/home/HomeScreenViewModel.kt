package com.example.jucrchallenge.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.jucrchallenge.domain.entities.Car
import com.example.jucrchallenge.ui.ViewModelWithStatus
import com.example.jucrchallenge.usecases.HomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject


@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val homeUseCase: HomeUseCase
) : ViewModelWithStatus() {

    var state by mutableStateOf(HomeState())
        private set

    init {
        requestCar()
    }

    private fun setLoading(loading: Boolean) {
        state = state.copy(loading = loading)
    }

    private fun setCar(car: Car) {
        state = state.copy(car = car)
    }


    private fun requestCar() = viewModelScope.launch {
        try {
            setLoading(true)
            withContext(Dispatchers.IO) {
                homeUseCase.getCar()
            }.also { setCar(it) }
        } catch (e: Exception) {
            handleNetworkError(e)
        } finally {
            setLoading(false)
        }
    }

}