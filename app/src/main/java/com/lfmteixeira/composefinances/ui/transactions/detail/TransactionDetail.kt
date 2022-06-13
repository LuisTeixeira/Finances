package com.lfmteixeira.composefinances.ui.transactions.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TransactionDetail(
    viewModel: TransactionDetailViewModel,
    navigateBack: () -> Unit
) {

    val state = viewModel.state

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "${state.value.description}") },
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
        content = { padding ->
            ConstraintLayout(
                Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                val (description, amount, category, date) = createRefs()
                Text(
                    text = state.value.description,
                    style = MaterialTheme.typography.h4,
                    modifier = Modifier.constrainAs(description) {
                        start.linkTo(parent.start)
                    }
                )
                Text(
                    text = state.value.amount,
                    style = MaterialTheme.typography.h4,
                    modifier = Modifier.constrainAs(amount) {
                        end.linkTo(parent.end)
                    }
                )
                Text(
                    text = state.value.category,
                    style = MaterialTheme.typography.subtitle2,
                    modifier = Modifier.constrainAs(category) {
                        start.linkTo(parent.start)
                        top.linkTo(description.bottom)
                    }
                )
                Text(
                    text = state.value.dateTime,
                    style = MaterialTheme.typography.subtitle2,
                    modifier = Modifier.constrainAs(date) {
                        end.linkTo(parent.end)
                        top.linkTo(amount.bottom)
                    }
                )
            }
        }
    )
}