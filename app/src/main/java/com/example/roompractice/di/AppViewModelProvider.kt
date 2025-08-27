package com.example.roompractice.di

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.roompractice.ui.home.HomeViewModel
import com.example.roompractice.utils.JuiceApplication

object AppViewModelProvider {
    val Factory: ViewModelProvider.Factory = viewModelFactory {
        initializer {
            val application = this.JuiceApplication()
            HomeViewModel(
                addJuiceUseCase = application.appContainer.addJuiceUseCase,
                deleteJuiceUseCase = application.appContainer.deleteJuiceUseCase,
                getAllJuiceUseCase = application.appContainer.getAllJuicesUseCase,
                updateJuiceUseCase = application.appContainer.updateJuiceUseCase
            )
        }
    }
}