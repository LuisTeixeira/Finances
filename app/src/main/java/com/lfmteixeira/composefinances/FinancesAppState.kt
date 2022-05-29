package com.lfmteixeira.composefinances

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
}