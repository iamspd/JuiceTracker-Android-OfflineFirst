package com.example.roompractice.data.mapper

import com.example.roompractice.data.entity.JuiceEntity
import com.example.roompractice.domain.model.Juice

internal fun Juice.toJuiceEntity(): JuiceEntity {
    return JuiceEntity(
        id = this.id,
        name = this.name,
        description = this.description,
        color = this.color,
        rating = this.rating
    )
}

internal fun JuiceEntity.toJuice(): Juice {
    return Juice(
        id = this.id,
        name = this.name,
        description = this.description,
        color = this.color,
        rating = this.rating
    )
}