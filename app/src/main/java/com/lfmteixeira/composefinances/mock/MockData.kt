package com.lfmteixeira.composefinances.mock

import com.lfmteixeira.composefinances.Graph
import com.lfmteixeira.composefinances.usecases.model.AccountModel
import com.lfmteixeira.composefinances.usecases.model.TransactionModel
import java.time.LocalDate

suspend fun createMockData() {

    val accountIds = createAccounts()

    val categories = createCategories()

    createBankTransactions(accountIds[0], categories)
    createCashTransactions(accountIds[1], categories)

}

private suspend fun createCashTransactions(accountId: String, categories: Map<String, String>) {
    var transactionModel = TransactionModel(
        accountId = accountId,
        categoryId = categories["Other"]!!,
        description = "Withdraw",
        value = 100.0,
        date = LocalDate.now()
    )
    Graph.createIncome(transactionModel)
    transactionModel = TransactionModel(
        accountId = accountId,
        categoryId = categories["Groceries"]!!,
        description = "Groceries Lidl 31/05",
        value = 22.43,
        date = LocalDate.now()
    )
    Graph.createExpense(transactionModel)
}

private suspend fun createBankTransactions(accountId: String, categories: Map<String, String>) {
    var transactionModel = TransactionModel(
        accountId = accountId,
        categoryId = categories["Salary"]!!,
        description = "Salary May",
        value = 2555.44,
        date = LocalDate.now()
    )
    Graph.createIncome(transactionModel)
    transactionModel = TransactionModel(
        accountId = accountId,
        categoryId = categories["Rent"]!!,
        description = "Rent May",
        value = 1225.0,
        date = LocalDate.now()
    )
    Graph.createExpense(transactionModel)
    transactionModel = TransactionModel(
        accountId = accountId,
        categoryId = categories["Groceries"]!!,
        description = "Groceries Rewe 20/05",
        value = 82.30,
        date = LocalDate.now()
    )
    Graph.createExpense(transactionModel)
    transactionModel = TransactionModel(
        accountId = accountId,
        categoryId = categories["Other"]!!,
        description = "Transfer Portugal",
        value = 100.0,
        date = LocalDate.now()
    )
    Graph.createExpense(transactionModel)
}

private suspend fun createAccounts(): List<String> {
    val accountIds = mutableListOf<String>()
    var accountModel = AccountModel(
        name = "Bank account",
        description = "My bank Account"
    )
    accountIds.add(Graph.createAccount(accountModel).getOrThrow().uuid)

    accountModel = AccountModel(
        name = "Cash",
        description = "My Cash Money"
    )
    accountIds.add(Graph.createAccount(accountModel).getOrThrow().uuid)

    return accountIds
}

private suspend fun createCategories(): Map<String, String> {
    val categoriesMap = mutableMapOf<String, String>()
    categoriesMap["Salary"] = Graph.createCategory("Salary").getOrThrow().uuid
    categoriesMap["Rent"] = Graph.createCategory("Rent").getOrThrow().uuid
    categoriesMap["Groceries"] = Graph.createCategory("Groceries").getOrThrow().uuid
    categoriesMap["Other"] = Graph.createCategory("Other").getOrThrow().uuid
    return categoriesMap
}

