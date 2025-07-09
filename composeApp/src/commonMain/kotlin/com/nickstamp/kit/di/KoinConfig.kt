package com.nickstamp.kit.di

import androidx.compose.runtime.Composable

@Composable
expect fun KoinConfig(content: @Composable () -> Unit)