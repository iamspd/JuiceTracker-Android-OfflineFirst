package com.example.roompractice.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "juice")
data class JuiceEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val name: String,
    val description: String,
    val color: String,
    val rating: Int
)
