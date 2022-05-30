package com.lfmteixeira.composefinances.ui.transactions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

@Composable
fun TransactionList(
    viewModel: TransactionListViewModel,
    navigateToTransactionDetail: (String) -> Unit
) {
    val transactions = viewModel.onTransactionsAvailable.observeAsState().value!!
    val accountBalance = viewModel.onAccountBalanceAvailable.observeAsState().value!!

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Balance: $accountBalance") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.Add, contentDescription = "New Transaction")
            }
        },
        content = { padding ->
            LazyColumn(
                contentPadding = padding,
                verticalArrangement = Arrangement.Center
            ) {
                items(transactions, key = {it.id}) { transaction ->
                    TransactionItem(
                        transaction = transaction,
                        onClick = navigateToTransactionDetail,
                        modifier = Modifier.fillParentMaxWidth()
                    )
                }
            }
        }
    )
}

@Composable
fun TransactionItem(
    transaction: TransactionViewModel,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    ConstraintLayout(modifier = modifier.clickable { onClick(transaction.id) }) {
        val(divider, transactionDescription, transactionCategory, transactionValue) = createRefs()

        Divider(
            Modifier.constrainAs(divider) {
                top.linkTo(parent.top)
                centerHorizontallyTo(parent)
                width = Dimension.fillToConstraints
            }
        )

        Text(
            text = transaction.description,
            style = MaterialTheme.typography.h5,
            modifier = Modifier
                .padding(6.dp)
                .constrainAs(transactionDescription) {
                    start.linkTo(parent.start)
                    top.linkTo(divider.bottom)
                }
        )

        Text(
            text = transaction.category,
            style = MaterialTheme.typography.subtitle2,
            modifier = Modifier
                .padding(6.dp)
                .constrainAs(transactionCategory) {
                    start.linkTo(parent.start)
                    top.linkTo(transactionDescription.bottom)
                }
        )

        Text(
            text = transaction.value,
            style = MaterialTheme.typography.h5,
            modifier = Modifier
                .padding(6.dp)
                .constrainAs(transactionValue) {
                    end.linkTo(parent.end)
                }
        )
    }
}

