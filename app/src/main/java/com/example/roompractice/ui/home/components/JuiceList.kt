package com.example.roompractice.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.graphics.toColorInt
import com.example.roompractice.R
import com.example.roompractice.domain.model.Juice
import com.example.roompractice.ui.components.RatingsBar
import com.example.roompractice.ui.home.JuiceListEvent
import com.example.roompractice.ui.theme.RoomPracticeTheme

@Preview(showBackground = true)
@Composable
fun PreviewListItem() {
    val juice = Juice(
        id = 1L,
        name = "Apple",
        description = "Description of how to make an apple juice.",
        color = "Red",
        rating = 3.5f
    )
    RoomPracticeTheme {
        JuiceListItem(
            juice = juice,
            onJuiceItemClick = {},
            onDeleteIconClick = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewJuiceList() {
    RoomPracticeTheme {
        val juices = List(9) {
            Juice(
                id = it.toLong(),
                name = "Apple $it",
                description = "Juice ingredients",
                color = "Green",
                rating = 4.1f
            )
        }
        JuiceList(
            juices = juices,
            onListEvent = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun JuiceList(
    juices: List<Juice>,
    onListEvent: (JuiceListEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(R.dimen.large_spacing)
        ),
        contentPadding = PaddingValues(
            horizontal = dimensionResource(R.dimen.extra_large_spacing),
            vertical = dimensionResource(R.dimen.extra_large_spacing)
        ),
        modifier = modifier
    ) {
        items(juices, key = { it.id }) { juice ->
            JuiceListItem(
                juice = juice,
                onJuiceItemClick = { onListEvent(JuiceListEvent.JuiceSelected(it)) },
                onDeleteIconClick = { onListEvent(JuiceListEvent.JuiceDeleted(it)) },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun JuiceListItem(
    juice: Juice,
    onJuiceItemClick: (Juice) -> Unit,
    onDeleteIconClick: (Juice) -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    Column(
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(R.dimen.large_spacing)
        ),
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(
                dimensionResource(R.dimen.medium_spacing)
            ),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = { onJuiceItemClick(juice) },
                    onClickLabel = stringResource(R.string.juice_list_item_click_label)
                )
        ) {
            val juiceIconContentDescription = stringResource(
                R.string.juice_image_content_description,
                juice.color,
                juice.name
            )
            Box(
                modifier = Modifier.semantics {
                    contentDescription = juiceIconContentDescription
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_juice_color),
                    contentDescription = null,
                    tint = Color(juice.color.toColorInt()),
                    modifier = Modifier.align(Alignment.Center)
                )
                Image(
                    painter = painterResource(R.drawable.ic_juice_clear),
                    contentDescription = null,
                    modifier = Modifier.size(
                        size = dimensionResource(R.dimen.image_scale)
                    )
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(
                    dimensionResource(R.dimen.small_spacing)
                ),
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = juice.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = juice.description,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1
                )
                RatingsBar(rating = juice.rating)
            }
            IconButton(
                onClick = { onDeleteIconClick(juice) },
                modifier = Modifier.size(
                    dimensionResource(R.dimen.image_scale)
                )
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_delete),
                    contentDescription = stringResource(
                        R.string.delete_button_content_description,
                        juice.name
                    ),
                    tint = MaterialTheme.colorScheme.inverseSurface
                )
            }
        }
        HorizontalDivider(color = DividerDefaults.color.copy(alpha = 0.5f))
    }
}