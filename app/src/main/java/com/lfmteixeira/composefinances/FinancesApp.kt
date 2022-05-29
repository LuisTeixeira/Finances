package com.lfmteixeira.composefinances

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lfmteixeira.composefinances.ui.account.AccountList

@Composable
fun FinancesApp(
    appState: FinancesAppState = rememberFinancesAppState()
) {
    NavHost(
        navController = appState.navController,
        startDestination = Screen.AccountList.route
    ) {
        composable(Screen.AccountList.route) { _ ->
            AccountList()
        }
    }
}