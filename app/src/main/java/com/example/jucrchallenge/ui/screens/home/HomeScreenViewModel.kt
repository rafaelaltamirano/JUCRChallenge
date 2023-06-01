package com.example.jucrchallenge.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jucrchallenge.MainState
import com.example.jucrchallenge.domain.entities.User
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

    init {

    }

    var state by mutableStateOf(HomeState())
        private set


    private fun setLoading(loading: Boolean) {
        state = state.copy(loading = loading)
    }



}