package com.example.roompractice.utils

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.JuiceApplication

fun CreationExtras.JuiceApplication() =
    this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as JuiceApplication