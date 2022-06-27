package com.lfmteixeira.composefinances.ui.account.create

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun AccountCreate(
    navigateBack: () -> Unit,
    viewModel: AccountCreateViewModel
) {
    val context = LocalContext.current
    val saver = run {
        val nameKey = "Name"
        val descriptionKey = "Description"
        val initialValueKey = "InitialValue"
        mapSaver(
            save = {
                mapOf(
                    nameKey to it.name,
                    descriptionKey to it.description,
                    initialValueKey to it.initialValue
                )
            },
            restore = {
                AccountCreateState(
                    it[nameKey] as String,
                    it[descriptionKey] as String,
                    it[initialValueKey] as String
                )
            }
        )
    }

    var accountCreate by rememberSaveable(stateSaver = saver) {
        mutableStateOf(AccountCreateState())
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Finances") },
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
                    .padding(padding)
            ) {
                OutlinedTextField(
                    value = accountCreate.name,
                    onValueChange = {
                        accountCreate = AccountCreateState(
                            name = it,
                            description = accountCreate.description,
                            initialValue = accountCreate.initialValue
                        )
                    },
                    label = { Text("Name") },
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
                    value = accountCreate.description,
                    onValueChange = {
                        accountCreate = AccountCreateState(
                            name = accountCreate.name,
                            it,
                            initialValue = accountCreate.initialValue
                        )
                    },
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
                OutlinedTextField(
                    value = accountCreate.initialValue,
                    onValueChange = {
                        accountCreate = AccountCreateState(
                            name = accountCreate.name,
                            description = accountCreate.description,
                            it
                        )
                    },
                    label = { Text("Initial Value") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = MaterialTheme.colors.secondary.copy(alpha = ContentAlpha.high),
                        unfocusedBorderColor = MaterialTheme.colors.secondary.copy(alpha = ContentAlpha.disabled),
                        focusedLabelColor = MaterialTheme.colors.secondary.copy(alpha = ContentAlpha.high)
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
        }
    )
}