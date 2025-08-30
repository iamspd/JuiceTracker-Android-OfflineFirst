package com.example.roompractice

import com.example.roompractice.domain.model.Juice
import com.example.roompractice.domain.repository.JuiceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

class FakeJuiceRepository : JuiceRepository {

    private val juices = MutableStateFlow<LinkedHashMap<Long, Juice>>(linkedMapOf())
    private var nextId = 1L

    override suspend fun insertJuice(juice: Juice) {
        juices.update {
            val newJuice = juice.copy(id = nextId++)
            val currentMap = it.toMutableMap()
            currentMap[newJuice.id] = newJuice
            currentMap as LinkedHashMap<Long, Juice>
        }
    }

    override suspend fun updateJuice(juice: Juice) {
        juices.update {
            val currentMap = it.toMutableMap()
            currentMap[juice.id] = juice
            currentMap as LinkedHashMap<Long, Juice>
        }
    }

    override suspend fun deleteJuice(juice: Juice) {
        juices.update {
            val currentMap = it.toMutableMap()
            currentMap.remove(juice.id)
            currentMap as LinkedHashMap<Long, Juice>
        }
    }

    override fun getJuiceById(id: Long): Flow<Juice> {
        return juices.map { it.getValue(id) }
    }

    override fun getAllJuice(): Flow<List<Juice>> {
        return juices.map { it.values.toList() }
    }
}