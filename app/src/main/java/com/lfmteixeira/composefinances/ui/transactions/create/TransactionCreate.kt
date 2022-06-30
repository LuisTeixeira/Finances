package com.lfmteixeira.composefinances.ui.transactions.create

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lfmteixeira.composefinances.ui.util.CategoriesDropDownList
import com.lfmteixeira.composefinances.ui.util.IsExpenseToggleButton
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun TransactionCreate(
    navigateBack: () -> Unit,
    viewModel: TransactionCreateViewModel
) {

    var selectedCategoryId by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var value by remember { mutableStateOf("") }
    var isExpense by remember { mutableStateOf(true) }
    var datetime by remember { mutableStateOf<LocalDate>(LocalDate.now()) }
    val dialogState = rememberMaterialDialogState()

    MaterialDialog(
        dialogState = dialogState,
        buttons = {
            positiveButton("Ok")
            negativeButton("Cancel")
        }
    ) {
        datepicker { date ->
            datetime = date
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Create Transaction") },
                navigationIcon = {
                    IconButton(onClick = { navigateBack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            viewModel.onCreate(
                                TransactionCreateState(
                                    isExpense = isExpense,
                                    categoryId = selectedCategoryId,
                                    description = description,
                                    value = value,
                                    date = datetime ?: LocalDate.now()
                                )
                            )
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Check,
                            contentDescription = "Save"
                        )
                    }
                }
            )
        },
        content = { paddingValues ->
            Column(Modifier.padding(paddingValues)) {
                IsExpenseToggleButton(
                    onToggleChange = { isExpense = it },
                    modifier = Modifier.fillMaxWidth()
                )
                Column {
                    CategoriesDropDownList(
                        categories = viewModel.onCategoriesAvailable.value!!,
                        onCategorySelected = { selectedCategoryId = it }
                    )
                    if (!viewModel.error.value?.get("category").isNullOrBlank()) {
                        Text(
                            text = viewModel.error.value?.get("category")!!,
                            color = MaterialTheme.colors.error,
                            style = MaterialTheme.typography.caption,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }
                Column {
                    OutlinedTextField(
                        value = description,
                        onValueChange = { description = it },
                        label = { Text("Description") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(6.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = MaterialTheme.colors.secondary.copy(alpha = ContentAlpha.high),
                            unfocusedBorderColor = MaterialTheme.colors.secondary.copy(alpha = ContentAlpha.disabled),
                            focusedLabelColor = MaterialTheme.colors.secondary.copy(alpha = ContentAlpha.high)
                        )
                    )
                    if (!viewModel.error.value?.get("description").isNullOrBlank()) {
                        Text(
                            text = viewModel.error.value?.get("description")!!,
                            color = MaterialTheme.colors.error,
                            style = MaterialTheme.typography.caption,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }
                Column {
                    OutlinedTextField(
                        value = value,
                        onValueChange = { value = it },
                        label = { Text("Value") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(6.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = MaterialTheme.colors.secondary.copy(alpha = ContentAlpha.high),
                            unfocusedBorderColor = MaterialTheme.colors.secondary.copy(alpha = ContentAlpha.disabled),
                            focusedLabelColor = MaterialTheme.colors.secondary.copy(alpha = ContentAlpha.high)
                        )
                    )
                    if (!viewModel.error.value?.get("value").isNullOrBlank()) {
                        Text(
                            text = viewModel.error.value?.get("value")!!,
                            color = MaterialTheme.colors.error,
                            style = MaterialTheme.typography.caption,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }
                Column {
                    OutlinedTextField(
                        value = datetime?.format(DateTimeFormatter.ISO_DATE) ?: "Date Picker",
                        onValueChange = { },
                        label = { Text("Date") },
                        enabled = false,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(6.dp)
                            .clickable { dialogState.show() },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = MaterialTheme.colors.secondary.copy(alpha = ContentAlpha.high),
                            unfocusedBorderColor = MaterialTheme.colors.secondary.copy(alpha = ContentAlpha.disabled),
                            focusedLabelColor = MaterialTheme.colors.secondary.copy(alpha = ContentAlpha.high)
                        )
                    )
                    if (!viewModel.error.value?.get("dateTime").isNullOrBlank()) {
                        Text(
                            text = viewModel.error.value?.get("dateTime")!!,
                            color = MaterialTheme.colors.error,
                            style = MaterialTheme.typography.caption,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }
            }
        }
    )

}