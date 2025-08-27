package com.example.roompractice.di

import android.content.Context
import com.example.roompractice.data.database.JuiceDatabase
import com.example.roompractice.data.repository.JuiceRepositoryImpl
import com.example.roompractice.domain.repository.JuiceRepository
import com.example.roompractice.domain.usecase.AddJuiceUseCase
import com.example.roompractice.domain.usecase.DeleteJuiceUseCase
import com.example.roompractice.domain.usecase.GetAllJuicesUseCase
import com.example.roompractice.domain.usecase.UpdateJuiceUseCase

class AppContainerImpl(
    private val context: Context
) : AppContainer {
    override val juiceRepository: JuiceRepository by lazy {
        JuiceRepositoryImpl(JuiceDatabase.getDatabase(context).juiceDao())
    }
    override val addJuiceUseCase: AddJuiceUseCase by lazy {
        AddJuiceUseCase(juiceRepository)
    }
    override val deleteJuiceUseCase: DeleteJuiceUseCase by lazy {
        DeleteJuiceUseCase(juiceRepository)
    }
    override val getAllJuicesUseCase: GetAllJuicesUseCase by lazy {
        GetAllJuicesUseCase(juiceRepository)
    }
    override val updateJuiceUseCase: UpdateJuiceUseCase by lazy {
        UpdateJuiceUseCase(juiceRepository)
    }
}