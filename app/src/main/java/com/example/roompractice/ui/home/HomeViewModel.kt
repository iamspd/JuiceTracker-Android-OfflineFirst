package com.example.roompractice.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roompractice.domain.model.Juice
import com.example.roompractice.domain.usecase.AddJuiceUseCase
import com.example.roompractice.domain.usecase.DeleteJuiceUseCase
import com.example.roompractice.domain.usecase.GetAllJuicesUseCase
import com.example.roompractice.domain.usecase.UpdateJuiceUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val addJuiceUseCase: AddJuiceUseCase,
    private val updateJuiceUseCase: UpdateJuiceUseCase,
    private val deleteJuiceUseCase: DeleteJuiceUseCase,
    getAllJuiceUseCase: GetAllJuicesUseCase
) : ViewModel() {

    val homeUiState: StateFlow<HomeUiState> = getAllJuiceUseCase()
        .map { juiceList ->
            HomeUiState(
                isJuiceListEmpty = juiceList.isEmpty(),
                juices = juiceList
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = HomeUiState()
        )

    private val _currentJuice = MutableStateFlow<Juice?>(null)
    val currentJuice: StateFlow<Juice?> = _currentJuice.asStateFlow()

    fun onJuiceSelected(juice: Juice) {
        _currentJuice.update { juice }
    }

    fun deleteJuice(juice: Juice) {
        viewModelScope.launch {
            deleteJuiceUseCase(juice)
        }
    }

    fun saveJuice() {
        viewModelScope.launch {
            _currentJuice.value?.let { juice ->
                if (juice.id == 0L) {
                    addJuiceUseCase(juice)
                } else {
                    updateJuiceUseCase(juice)
                }
            }
        }
    }
}