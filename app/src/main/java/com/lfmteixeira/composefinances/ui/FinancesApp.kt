package com.lfmteixeira.composefinances.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lfmteixeira.composefinances.mock.createMockData
import com.lfmteixeira.composefinances.ui.account.AccountList
import com.lfmteixeira.composefinances.ui.transactions.TransactionList
import com.lfmteixeira.composefinances.ui.transactions.TransactionListViewModel
import kotlinx.coroutines.runBlocking

@Composable
fun FinancesApp(
    appState: FinancesAppState = rememberFinancesAppState()
) {
    runBlocking {
        createMockData()
    }
    NavHost(
        navController = appState.navController,
        startDestination = Screen.AccountList.route
    ) {
        composable(Screen.AccountList.route) { navBackStackEntry ->
            AccountList(
                navigateToTransactions = { accountId ->
                    appState.navigateToAccountTransactions(accountId, navBackStackEntry)
                }
            )
        }
        composable(Screen.AccountTransactions.route) { navBackStackEntry ->
            val transactionsListViewModel: TransactionListViewModel = viewModel(
                factory = TransactionListViewModel.provideFactory(
                    owner = navBackStackEntry,
                    defaultArgs = navBackStackEntry.arguments
                )
            )
            TransactionList(viewModel = transactionsListViewModel, navigateToTransactionDetail = {/* TODO */})
        }
    }
}