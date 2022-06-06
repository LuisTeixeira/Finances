package com.lfmteixeira.composefinances.ui.transactions.create

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.lfmteixeira.composefinances.ui.account.create.AccountCreateState
import com.lfmteixeira.composefinances.ui.theme.util.CategoriesDropDownList
import com.lfmteixeira.composefinances.ui.theme.util.IsExpenseToggleButton

@Composable
fun TransactionCreate(
    navigateBack: () -> Unit,
    viewModel: TransactionCreateViewModel
) {
//    val saver = run {
//        val descriptionKey = "Description"
//        val categoryIdKey = "CategoryId"
//        val valueKey = "Value"
//        val isExpenseKey = "IsExpense"
//        mapSaver(
//            save = { mapOf(descriptionKey to it.description, categoryIdKey to it.categoryId, valueKey to it.value, isExpenseKey to it.isExpense) },
//            restore = { TransactionCreateState(
//                description = it[descriptionKey] as String,
//                categoryId = it[categoryIdKey] as String,
//                value = it[valueKey] as String,
//                isExpense = it[isExpenseKey] as Boolean
//            ) }
//        )
//    }
//
//    var transactionCreateState by rememberSaveable(stateSaver = saver) {
//        mutableStateOf(TransactionCreateState())
//    }

    var selectedCategoryId by remember{ mutableStateOf("")}
    var description by remember { mutableStateOf("") }
    var value by remember { mutableStateOf("") }
    var isExpense by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text("Create Transaction")},
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
                        onClick = { viewModel.onCreate(TransactionCreateState(
                            isExpense = isExpense,
                            categoryId = selectedCategoryId,
                            description = description,
                            value = value
                        )) }
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
                IsExpenseToggleButton(onToggleChange = {isExpense = it}, modifier = Modifier.fillMaxWidth())
                CategoriesDropDownList(
                    categories = viewModel.onCategoriesAvailable.value!!,
                    onCategorySelected = { selectedCategoryId = it }
                )
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = {Text("Description")},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = MaterialTheme.colors.secondary.copy(alpha = ContentAlpha.high),
                        unfocusedBorderColor = MaterialTheme.colors.secondary.copy(alpha = ContentAlpha.disabled),
                        focusedLabelColor = MaterialTheme.colors.secondary.copy(alpha = ContentAlpha.high)
                    )
                )
                OutlinedTextField(
                    value = value,
                    onValueChange = { value = it },
                    label = {Text("Value")},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = MaterialTheme.colors.secondary.copy(alpha = ContentAlpha.high),
                        unfocusedBorderColor = MaterialTheme.colors.secondary.copy(alpha = ContentAlpha.disabled),
                        focusedLabelColor = MaterialTheme.colors.secondary.copy(alpha = ContentAlpha.high)
                    )
                )
            }
        }
    )
    
}