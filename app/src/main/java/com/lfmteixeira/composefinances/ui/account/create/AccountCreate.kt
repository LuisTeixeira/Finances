package com.lfmteixeira.composefinances.ui.account.create

import android.content.res.Resources
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.lfmteixeira.composefinances.ui.theme.FinancesTheme

@Composable
fun AccountCreate(
    viewModel: AccountCreateViewModel
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Finances") },
                navigationIcon = {
                    IconButton(onClick = { /* TODO */ }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    Button(
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary),
                        onClick = { /*TODO*/ }
                    ) {
                        Text(text = "Save")
                }}
            )
        },
        content = { padding ->
            Column(Modifier.padding(padding)) {
                Text(text = "We will create and account here")
            }
        }
    )
}