package com.lfmteixeira.composefinances.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

sealed class Screen(val route: String) {
    object AccountList: Screen("accountList")
    object AccountTransactions: Screen("transactions/{accountId}") {
        fun createRoute(accountId: String) = "transactions/$accountId"
    }
}

@Composable
fun rememberFinancesAppState(
    navController: NavHostController = rememberNavController(),
) = remember(navController) {
    FinancesAppState(navController)
}

class FinancesAppState(
    val navController: NavHostController
) {

    fun navigateToAccountTransactions(accountId: String, from: NavBackStackEntry) {
        if (from.lifecycleIsResumed()) {
            navController.navigate(Screen.AccountTransactions.createRoute(accountId))
        }
    }
}

private fun NavBackStackEntry.lifecycleIsResumed(): Boolean {
    return this.lifecycle.currentState == Lifecycle.State.RESUMED
}