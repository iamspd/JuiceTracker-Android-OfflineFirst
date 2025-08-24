package com.example.roompractice.domain.usecase

import com.example.roompractice.domain.model.Juice
import com.example.roompractice.domain.repository.JuiceRepository

class UpdateJuiceUseCase(
    private val juiceRepository: JuiceRepository
) {
    suspend operator fun invoke(juice: Juice) {
        juiceRepository.updateJuice(juice)
    }
}