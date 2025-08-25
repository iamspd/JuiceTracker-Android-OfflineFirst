package com.example.roompractice.ui.home.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.StarHalf
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.roompractice.ui.theme.RoomPracticeTheme
import kotlin.math.ceil
import kotlin.math.floor

@Preview(showBackground = true)
@Composable
fun PreviewRatingsBar() {
    RoomPracticeTheme {
        RatingsBar(
            modifier = Modifier.fillMaxWidth(),
            rating = 3.5f
        )
    }
}

@Composable
fun RatingsBar(
    modifier: Modifier = Modifier,
    rating: Float,
    stars: Int = 5,
    color: Color = MaterialTheme.colorScheme.secondary,
) {
    val fullStars = floor(rating).toInt()
    val halfStars = ceil(rating - fullStars).toInt()
    val emptyStars = stars - fullStars - halfStars

    Row(modifier = modifier) {
        repeat(fullStars) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = null,
                tint = color
            )
        }
        repeat(halfStars) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.StarHalf,
                contentDescription = null,
                tint = color
            )
        }
        repeat(emptyStars) {
            Icon(
                imageVector = Icons.Filled.StarBorder,
                contentDescription = null,
                tint = color
            )
        }
    }
}