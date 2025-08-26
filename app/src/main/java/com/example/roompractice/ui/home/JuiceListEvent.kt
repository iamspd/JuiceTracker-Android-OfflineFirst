package com.example.roompractice.ui.home

import com.example.roompractice.domain.model.Juice

sealed interface JuiceListEvent {
    data class JuiceSelected(val juice: Juice) : JuiceListEvent
    data class JuiceDeleted(val juice: Juice) : JuiceListEvent
}