package com.example.roompractice

import app.cash.turbine.test
import com.example.roompractice.domain.usecase.AddJuiceUseCase
import com.example.roompractice.domain.usecase.DeleteJuiceUseCase
import com.example.roompractice.domain.usecase.GetAllJuicesUseCase
import com.example.roompractice.domain.usecase.UpdateJuiceUseCase
import com.example.roompractice.ui.home.HomeViewModel
import com.example.roompractice.ui.home.JuiceFormEvent
import com.example.rules.TestDispatcherRule
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {

    @get:Rule
    val testDispatcherRule = TestDispatcherRule()

    private lateinit var fakeJuiceRepository: FakeJuiceRepository
    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        fakeJuiceRepository = FakeJuiceRepository()
        viewModel = HomeViewModel(
            addJuiceUseCase = AddJuiceUseCase(fakeJuiceRepository),
            getAllJuiceUseCase = GetAllJuicesUseCase(fakeJuiceRepository),
            updateJuiceUseCase = UpdateJuiceUseCase(fakeJuiceRepository),
            deleteJuiceUseCase = DeleteJuiceUseCase(fakeJuiceRepository)
        )
    }

    @Test
    fun homeViewModel_initializeViewModel_juiceListIsEmpty() = runTest {
        val initialState = viewModel.homeUiState.value
        assertTrue("The flag is true.", initialState.isJuiceListEmpty)
        assertTrue("List is empty.", initialState.juices.isEmpty())
    }

    @Test
    fun homeViewModel_addNewJuice_juiceSuccessfullyAdded() = runTest {

        viewModel.homeUiState.test {

            awaitItem()

            viewModel.onAddNewJuice()
            assertNotNull("The current juice value is not empty.", viewModel.currentJuice.value)
            assertEquals("", viewModel.currentJuice.value?.name)

            viewModel.onFormEvent(JuiceFormEvent.NameChanged("Apple Juice"))
            viewModel.onFormEvent(JuiceFormEvent.DescriptionChanged("Fresh"))
            viewModel.onFormEvent(JuiceFormEvent.RatingChanged("4.5"))

            assertEquals("Apple Juice", viewModel.currentJuice.value?.name)
            assertEquals("4.5", viewModel.ratingInput.value)

            viewModel.onFormEvent(JuiceFormEvent.SaveClicked)
            val homeUiState = awaitItem()

            assertFalse("Juice list should not be empty.", homeUiState.juices.isEmpty())
            assertEquals(1, homeUiState.juices.size)
            assertEquals("Apple Juice", homeUiState.juices.first().name)
            assertEquals(4.5f, homeUiState.juices.first().rating)
        }
    }
}