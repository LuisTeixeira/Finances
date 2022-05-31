package com.lfmteixeira.composefinances.ui.account.create

import android.content.res.Resources
import android.text.InputType
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.lfmteixeira.composefinances.ui.theme.FinancesTheme

@Composable
fun AccountCreate(
    viewModel: AccountCreateViewModel
) {

    var accountCreate by remember { mutableStateOf(AccountCreateState())}

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
                    IconButton(
                        onClick = { viewModel.onCreate(accountCreate) }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Check,
                            contentDescription = "Save"
                        )
                }
                }
            )
        },
        content = { padding ->
            Column(
                Modifier
                    .padding(padding)) {
                OutlinedTextField(
                    value = accountCreate.name,
                    onValueChange = { accountCreate = AccountCreateState(name = it, description = accountCreate.description, initialValue = accountCreate.initialValue) },
                    label = {Text("Name")},
                    modifier = Modifier.fillMaxWidth().padding(6.dp)
                )
                OutlinedTextField(
                    value = accountCreate.description,
                    onValueChange = { accountCreate = AccountCreateState(name = accountCreate.name, it, initialValue = accountCreate.initialValue)},
                    label = {Text("Description")},
                    modifier = Modifier.fillMaxWidth().padding(6.dp)
                )
                OutlinedTextField(
                    value = accountCreate.initialValue,
                    onValueChange = { accountCreate = AccountCreateState(name = accountCreate.name, description = accountCreate.description, it)},
                    label = {Text("Initial Value")},
                    modifier = Modifier.fillMaxWidth().padding(6.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
        }
    )
}