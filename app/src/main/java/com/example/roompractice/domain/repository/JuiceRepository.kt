package com.example.roompractice.domain.repository

import com.example.roompractice.domain.model.Juice
import kotlinx.coroutines.flow.Flow

interface JuiceRepository {
    suspend fun insertJuice(juice: Juice)
    suspend fun updateJuice(juice: Juice)
    suspend fun deleteJuice(juice: Juice)
    fun getJuiceById(id: Long): Flow<Juice>
    fun getAllJuice(): Flow<List<Juice>>
}