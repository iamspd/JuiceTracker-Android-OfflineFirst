package com.example.roompractice.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.roompractice.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBottomSheet(
    topAppBar: @Composable () -> Unit,
    scaffoldState: BottomSheetScaffoldState,
    sheetContent: @Composable () -> Unit,
    mainContent: @Composable (PaddingValues) -> Unit,
    modifier: Modifier = Modifier
) {
    BottomSheetScaffold(
        topBar = topAppBar,
        scaffoldState = scaffoldState,
        sheetContent = {
            Column(
                verticalArrangement = Arrangement.spacedBy(
                    dimensionResource(R.dimen.large_spacing)
                ),
                modifier = Modifier.padding(
                    dimensionResource(R.dimen.large_spacing)
                )
            ) { sheetContent() }
        },
        content = mainContent,
        modifier = modifier
    )
}