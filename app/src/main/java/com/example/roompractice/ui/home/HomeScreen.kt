package com.example.roompractice.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roompractice.R
import com.example.roompractice.di.AppViewModelProvider
import com.example.roompractice.ui.components.AppBottomSheet
import com.example.roompractice.ui.home.components.FAB
import com.example.roompractice.ui.home.components.JuiceList
import com.example.roompractice.ui.home.components.SheetForm
import com.example.roompractice.ui.home.components.SheetHeader
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val homeViewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
    val homeUiState by homeViewModel.homeUiState.collectAsStateWithLifecycle()
    val currentJuice by homeViewModel.currentJuice.collectAsStateWithLifecycle()
    val ratingInput by homeViewModel.ratingInput.collectAsStateWithLifecycle()
    val isSaveButtonEnabled by homeViewModel.isSaveButtonEnabled.collectAsStateWithLifecycle()

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.Hidden,
            skipHiddenState = false
        )
    )

    val scope = rememberCoroutineScope()
    val sheetTitle = if (currentJuice?.id == 0L || currentJuice == null) {
        stringResource(R.string.bottom_sheet_title_new_juice)
    } else {
        stringResource(R.string.bottom_sheet_title_update_juice)
    }

    LaunchedEffect(Unit) {
        homeViewModel.dismissBottomSheet.collect {
            scope.launch {
                bottomSheetScaffoldState.bottomSheetState.hide()
            }
        }
    }
    LaunchedEffect(bottomSheetScaffoldState.bottomSheetState.isVisible) {
        if (!bottomSheetScaffoldState.bottomSheetState.isVisible) {
            homeViewModel.onSheetDismissed()
        }
    }

    AppBottomSheet(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            SheetHeader(
                title = sheetTitle,
                modifier = Modifier.fillMaxWidth()
            )
            SheetForm(
                juice = currentJuice,
                onFormEvent = { homeViewModel.onFormEvent(it) },
                ratingInput = ratingInput,
                isSaveButtonEnabled = isSaveButtonEnabled,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.windowInsetsBottomHeight(WindowInsets.navigationBars))
        },
        mainContent = {
            Scaffold(
                topBar = {
                    CenterAlignedTopAppBar(
                        title = { Text(stringResource(R.string.top_app_bar_title)) },
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                floatingActionButton = {
                    FAB(
                        onClick = {
                            homeViewModel.onAddNewJuice()
                            scope.launch { bottomSheetScaffoldState.bottomSheetState.expand() }
                        }
                    )
                }
            ) { innerPadding ->
                if (homeUiState.isJuiceListEmpty) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    ) {
                        Text(
                            text = stringResource(R.string.home_screen_juice_list_empty_text),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                } else {
                    JuiceList(
                        juices = homeUiState.juices,
                        contentPadding = innerPadding,
                        onListEvent = { event ->
                            homeViewModel.onListEvent(event)
                            if (event is JuiceListEvent.JuiceSelected) {
                                scope.launch { bottomSheetScaffoldState.bottomSheetState.expand() }
                            }
                        },
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(
                                horizontal = dimensionResource(R.dimen.extra_large_spacing),
                                vertical = dimensionResource(R.dimen.medium_spacing)
                            )
                    )
                }
            }
        }
    )
}