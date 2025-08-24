package com.example.roompractice.domain.usecase

import com.example.roompractice.domain.model.Juice
import com.example.roompractice.domain.repository.JuiceRepository

class AddJuiceUseCase(
    private val juiceRepository: JuiceRepository
) {
    suspend operator fun invoke(juice: Juice) {
        juiceRepository.insertJuice(juice)
    }
}