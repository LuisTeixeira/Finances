package com.lfmteixeira.composefinances.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.material.MaterialTheme

@Composable
fun FinancesTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = FinancesColors,
        typography = FinancesTypography,
        shapes = FinancesShapes,
        content = content
    )
}