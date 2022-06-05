package com.lfmteixeira.composefinances.ui.transactions.create

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.lfmteixeira.composefinances.ui.theme.util.CategoriesDropDownList

@Composable
fun TransactionCreate(
    navigateBack: () -> Unit,
    viewModel: TransactionCreateViewModel
) {
    
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
                }
            )
        },
        content = { paddingValues ->  
            Column(Modifier.padding(paddingValues)) {
                CategoriesDropDownList(
                    categories = viewModel.onCategoriesAvailable.value!!,
                    onCategorySelected = viewModel::onCategorySelected
                )
            }
        }
    )
    
}