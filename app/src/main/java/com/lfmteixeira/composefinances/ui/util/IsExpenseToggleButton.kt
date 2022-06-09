package com.lfmteixeira.composefinances.ui.util

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun IsExpenseToggleButton(
    onToggleChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val selectedTint = MaterialTheme.colors.secondary
    val unselectedTint = MaterialTheme.colors.background

    val options = listOf("Expense", "Income")
    var selection by remember{ mutableStateOf(true)}

    Row(modifier = modifier
        .height(IntrinsicSize.Min)
        .border(BorderStroke(1.dp, MaterialTheme.colors.secondary))) {

        val expenseBackground = if (selection) selectedTint else unselectedTint

        Row(modifier = Modifier
            .background(expenseBackground)
            .weight(1f)
            .padding(vertical = 6.dp, horizontal = 8.dp)
            .toggleable(
                value = selection,
                onValueChange = { selected ->
                    selection = selected
                    onToggleChange(selected)
                }
            ),
            horizontalArrangement = Arrangement.Center
        ) {
                Text(text = options[0])
        }

        Divider(
            color = MaterialTheme.colors.secondary,
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
        )

        val incomeBackground = if (!selection) selectedTint else unselectedTint

        Row(modifier = Modifier
            .background(incomeBackground)
            .weight(1f)
            .padding(vertical = 6.dp, horizontal = 8.dp)
            .toggleable(
                value = !selection,
                onValueChange = { selected ->
                    selection = !selected
                    onToggleChange(!selected)
                }
            ),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = options[1])
        }
    }
}