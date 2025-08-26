package com.example.roompractice.ui.home

sealed interface JuiceFormEvent {
    data class NameChanged(val name: String) : JuiceFormEvent
    data class DescriptionChanged(val description: String) : JuiceFormEvent
    data class ColorChanged(val color: String) : JuiceFormEvent
    data class RatingChanged(val rating: String) : JuiceFormEvent
    data object SaveClicked : JuiceFormEvent
    data object CancelClicked : JuiceFormEvent
}