package com.example.roompractice.ui.home

import com.example.roompractice.domain.model.Juice

data class HomeUiState(
    val isJuiceListEmpty: Boolean = true,
    val juices: List<Juice> = emptyList()
)
