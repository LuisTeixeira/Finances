package com.lfmteixeira.composefinances.ui.account

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lfmteixeira.composefinances.domain.Account

@Composable
fun AccountList(
    modifier: Modifier = Modifier
) {
    val viewModel: AccountListViewModel = viewModel()
    val accounts = viewModel.onAccountListAvailable.observeAsState().value!!

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Finances") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.Add, contentDescription = "New Account")
            }
        },
        content = { padding ->
            LazyColumn(
                contentPadding = padding,
                verticalArrangement = Arrangement.Center
            ) {
                items(accounts, key = {it.uuid}) { account ->
                    AccountItem(
                        account = account,
                        modifier = Modifier.fillParentMaxWidth()
                    )
                }
            }
        }
    )
}

@Composable
private fun AccountItem(
    account: Account,
    modifier: Modifier = Modifier
) {
    ConstraintLayout(modifier = modifier.clickable {  }) {
        val(divider, accountName, accountDescription, accountTotal) = createRefs()

        Divider(
            Modifier.constrainAs(divider) {
                top.linkTo(parent.top)
                centerHorizontallyTo(parent)
                width = Dimension.fillToConstraints
            }
        )

        Text(
            text = account.name,
            style = MaterialTheme.typography.h5,
            modifier = Modifier
                .padding(6.dp)
                .constrainAs(accountName) {
                    start.linkTo(parent.start)
                    top.linkTo(divider.bottom)
                }
        )

        Text(
            text = account.description,
            style = MaterialTheme.typography.subtitle2,
            modifier = Modifier
                .padding(6.dp)
                .constrainAs(accountDescription) {
                    start.linkTo(parent.start)
                    top.linkTo(accountName.bottom)
                }
        )

        Text(
            text = account.getTotal().toString() + "€",
            style = MaterialTheme.typography.h5,
            modifier = Modifier
                .padding(6.dp)
                .constrainAs(accountTotal) {
                    end.linkTo(parent.end)
                }
        )
    }
}