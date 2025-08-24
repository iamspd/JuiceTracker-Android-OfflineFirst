package com.example.roompractice.domain.usecase

import com.example.roompractice.domain.model.Juice
import com.example.roompractice.domain.repository.JuiceRepository
import kotlinx.coroutines.flow.Flow

class GetAllJuicesUseCase(
    private val juiceRepository: JuiceRepository
) {
    operator fun invoke(): Flow<List<Juice>> {
        return juiceRepository.getAllJuice()
    }
}