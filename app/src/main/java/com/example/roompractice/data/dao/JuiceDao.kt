package com.example.roompractice.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.roompractice.data.entity.JuiceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface JuiceDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertJuice(juiceEntity: JuiceEntity)

    @Update
    suspend fun updateJuice(juiceEntity: JuiceEntity)

    @Delete
    suspend fun deleteJuice(juiceEntity: JuiceEntity)

    @Query("SELECT * FROM juice WHERE id = :id")
    fun getJuiceById(id: Long): Flow<JuiceEntity>

    @Query("SELECT * FROM juice")
    fun getJuiceStream(): Flow<List<JuiceEntity>>
}