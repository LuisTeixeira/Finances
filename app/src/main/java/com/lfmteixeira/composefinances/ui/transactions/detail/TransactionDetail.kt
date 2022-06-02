package com.lfmteixeira.composefinances.ui.transactions.detail

import android.annotation.SuppressLint
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TransactionDetail(
    viewModel: TransactionDetailViewModel,
    navigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text(text = "Transaction Name")},
                navigationIcon = {
                    IconButton(onClick = {navigateBack()}) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back")
                    }
                }
            )
        },
        content = { _ ->
            Text("This will have the transfer detail")
        }
    )
}