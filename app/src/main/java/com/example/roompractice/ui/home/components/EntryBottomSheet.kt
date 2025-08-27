package com.example.roompractice.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.graphics.toColorInt
import com.example.roompractice.R
import com.example.roompractice.domain.model.Juice
import com.example.roompractice.ui.home.JuiceFormEvent
import com.example.roompractice.ui.theme.RoomPracticeTheme

@Preview(showBackground = true)
@Composable
fun PreviewEntryBottomSheet() {
    RoomPracticeTheme {
        SheetButtonRow(
            onSaveClick = {},
            onCancelClick = {}
        )
    }
}

@Composable
fun SheetForm(
    juice: Juice?,
    onFormEvent: (JuiceFormEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(R.dimen.large_spacing)
        ),
        modifier = modifier
    ) {
        juice?.let { currentJuice ->
            SheetInputRow(
                fieldValue = currentJuice.name,
                onFieldValueChange = { onFormEvent(JuiceFormEvent.NameChanged(it)) },
                label = stringResource(R.string.label_name),
                onClearClick = { onFormEvent(JuiceFormEvent.NameChanged("")) },
                modifier = Modifier.fillMaxWidth()
            )
            SheetInputRow(
                fieldValue = currentJuice.description,
                onFieldValueChange = { onFormEvent(JuiceFormEvent.DescriptionChanged(it)) },
                label = stringResource(R.string.label_description),
                onClearClick = { onFormEvent(JuiceFormEvent.DescriptionChanged("")) },
                modifier = Modifier.fillMaxWidth()
            )
            ColorPicker(
                selectedColorName = currentJuice.color,
                onColorSelected = { onFormEvent(JuiceFormEvent.ColorChanged(it)) },
                modifier = Modifier.fillMaxWidth()
            )
            SheetInputRow(
                fieldValue = currentJuice.rating.toString(),
                onFieldValueChange = { onFormEvent(JuiceFormEvent.RatingChanged(it)) },
                label = stringResource(R.string.label_rating),
                onClearClick = { onFormEvent(JuiceFormEvent.RatingChanged("")) },
                modifier = Modifier.fillMaxWidth()
            )
            SheetButtonRow(
                onSaveClick = { onFormEvent(JuiceFormEvent.SaveClicked) },
                onCancelClick = { onFormEvent(JuiceFormEvent.CancelClicked) },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColorPicker(
    selectedColorName: String,
    onColorSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = listOf("Red", "Orange", "Yellow", "Green", "Blue")
    var isExpanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = { isExpanded = it },
        modifier = modifier
    ) {
        OutlinedTextField(
            value = selectedColorName,
            onValueChange = {},
            readOnly = true,
            singleLine = true,
            label = { Text(stringResource(R.string.label_color_dropdown_menu)) },
            modifier = Modifier.menuAnchor(MenuAnchorType.PrimaryNotEditable),
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) },
            colors = ExposedDropdownMenuDefaults.textFieldColors()
        )
        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }
        ) {
            colors.forEach { colorName ->
                DropdownMenuItem(
                    text = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Spacer(
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .size(dimensionResource(R.dimen.color_circle_scale))
                                    .background(Color(colorName.toColorInt()))
                            )
                            Spacer(modifier = Modifier.width(dimensionResource(R.dimen.medium_spacing)))
                            Text(colorName)
                        }
                    },
                    onClick = {
                        onColorSelected(colorName)
                        isExpanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun SheetButtonRow(
    onSaveClick: () -> Unit,
    onCancelClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(
            dimensionResource(R.dimen.extra_large_spacing)
        ),
        modifier = modifier
    ) {
        Spacer(modifier = Modifier.weight(1f))
        OutlinedButton(
            onClick = onCancelClick,
            modifier = Modifier.width(dimensionResource(R.dimen.button_scale))
        ) {
            Text(stringResource(R.string.cancel_button_text))
        }
        FilledTonalButton(
            onClick = onSaveClick,
            modifier = Modifier.width(dimensionResource(R.dimen.button_scale))
        ) {
            Text(stringResource(R.string.save_button_text))
        }
    }
}


@Composable
fun SheetHeader(
    title: String,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(R.dimen.large_spacing)
        ),
        modifier = modifier
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(start = dimensionResource(R.dimen.extra_large_spacing))
        )
        HorizontalDivider()
    }
}

@Composable
fun SheetInputRow(
    fieldValue: String,
    onFieldValueChange: (String) -> Unit,
    label: String,
    onClearClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = fieldValue,
        onValueChange = onFieldValueChange,
        label = { Text(label) },
        trailingIcon = {
            IconButton(onClearClick) {
                Icon(
                    imageVector = Icons.Outlined.Cancel,
                    contentDescription = stringResource(R.string.clear_button_content_description)
                )
            }
        },
        modifier = modifier
    )
}