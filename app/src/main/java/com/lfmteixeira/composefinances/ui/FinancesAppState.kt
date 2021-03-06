package com.lfmteixeira.composefinances.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

sealed class Screen(val route: String) {
    object AccountList: Screen("accountList")
    object AccountCreate: Screen("accountCreate")
    object AccountTransactions: Screen("transactions/{accountId}") {
        fun createRoute(accountId: String) = "transactions/$accountId"
    }
    object TransactionDetail: Screen("transaction/{transactionId}") {
        fun createRoute(transactionId: String) = "transaction/$transactionId"
    }
    object TransactionCreate: Screen("transaction/create")
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

    val selectedAccountId = mutableStateOf("")

    fun navigateToAccountTransactions(accountId: String, from: NavBackStackEntry) {
        if (from.lifecycleIsResumed()) {
            selectedAccountId.value = accountId
            navController.navigate(Screen.AccountTransactions.createRoute(selectedAccountId.value))
        }
    }

    fun navigateAfterTransactionSave(from: NavBackStackEntry) {
        if (from.lifecycleIsResumed()) {
            navController.navigate(Screen.AccountTransactions.createRoute(selectedAccountId.value)) {
                popUpTo(Screen.AccountList.route) {
                    inclusive = false
                }
            }
        }
    }

    fun navigateAfterAccountSave(from: NavBackStackEntry) {
        if (from.lifecycleIsResumed()) {
            navController.navigate(Screen.AccountList.route) {
                popUpTo(Screen.AccountList.route) {
                    inclusive = true
                }
            }
        }
    }

    fun navigateToTransactionDetail(transactionId: String, from: NavBackStackEntry) {
        if (from.lifecycleIsResumed()) {
            navController.navigate(Screen.TransactionDetail.createRoute(transactionId))
        }
    }

    fun navigateToCreateAccount(from: NavBackStackEntry) {
        if (from.lifecycleIsResumed()) {
            navController.navigate(Screen.AccountCreate.route)
        }
    }

    fun navigateToCreateTransaction(from: NavBackStackEntry) {
        if (from.lifecycleIsResumed()) {
            navController.navigate(Screen.TransactionCreate.route)
        }
    }

    fun navigateBack() {
        navController.popBackStack()
    }
}

private fun NavBackStackEntry.lifecycleIsResumed(): Boolean {
    return this.lifecycle.currentState == Lifecycle.State.RESUMED
}