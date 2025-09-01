package com.example.roompractice.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.roompractice.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBottomSheet(
    scaffoldState: BottomSheetScaffoldState,
    sheetContent: @Composable () -> Unit,
    mainContent: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            Column(
                verticalArrangement = Arrangement.spacedBy(
                    dimensionResource(R.dimen.large_spacing)
                ),
                modifier = Modifier.padding(
                    start = dimensionResource(R.dimen.extra_large_spacing),
                    end = dimensionResource(R.dimen.extra_large_spacing),
                )
            ) { sheetContent() }
        },
        sheetContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        modifier = modifier
    ) {
        mainContent()
    }
}