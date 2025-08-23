package com.example.roompractice.data.repository

import com.example.roompractice.data.dao.JuiceDao
import com.example.roompractice.data.entity.JuiceEntity
import com.example.roompractice.domain.model.Juice
import com.example.roompractice.domain.repository.JuiceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class JuiceRepositoryImpl(
    private val juiceDao: JuiceDao
) : JuiceRepository {
    override suspend fun insertJuice(juice: Juice) {
        juiceDao.insertJuice(juice.toJuiceEntity())
    }

    override suspend fun updateJuice(juice: Juice) {
        juiceDao.updateJuice(juice.toJuiceEntity())
    }

    override suspend fun deleteJuice(juice: Juice) {
        juiceDao.deleteJuice(juice.toJuiceEntity())
    }

    override fun getJuiceById(id: Long): Flow<Juice> {
        return juiceDao.getJuiceById(id).map { it.toJuice() }
    }

    override fun getAllJuice(): Flow<List<Juice>> {
        return juiceDao.getJuiceStream().map { juiceEntityList ->
            juiceEntityList.map { it.toJuice() }
        }
    }
}

private fun Juice.toJuiceEntity(): JuiceEntity {
    return JuiceEntity(
        id = this.id,
        name = this.name,
        description = this.description,
        color = this.color,
        rating = this.rating
    )
}

private fun JuiceEntity.toJuice(): Juice {
    return Juice(
        id = this.id,
        name = this.name,
        description = this.description,
        color = this.color,
        rating = this.rating
    )
}