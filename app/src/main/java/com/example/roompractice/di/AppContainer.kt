package com.example.roompractice.di

import com.example.roompractice.domain.repository.JuiceRepository
import com.example.roompractice.domain.usecase.AddJuiceUseCase
import com.example.roompractice.domain.usecase.DeleteJuiceUseCase
import com.example.roompractice.domain.usecase.GetAllJuicesUseCase
import com.example.roompractice.domain.usecase.UpdateJuiceUseCase

interface AppContainer {
    val juiceRepository: JuiceRepository
    val addJuiceUseCase: AddJuiceUseCase
    val deleteJuiceUseCase: DeleteJuiceUseCase
    val getAllJuicesUseCase: GetAllJuicesUseCase
    val updateJuiceUseCase: UpdateJuiceUseCase
}