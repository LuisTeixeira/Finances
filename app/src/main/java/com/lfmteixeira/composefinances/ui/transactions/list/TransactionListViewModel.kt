package com.lfmteixeira.composefinances.ui.transactions

import android.os.Bundle
import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistryOwner
import com.lfmteixeira.composefinances.Graph
import com.lfmteixeira.composefinances.usecases.account.GetAccount
import com.lfmteixeira.composefinances.usecases.transaction.GetTransactionsForAccount
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter

class TransactionListViewModel(
    private val getTransactionsForAccount: GetTransactionsForAccount = Graph.getTransactionsForAccount,
    private val getAccount: GetAccount = Graph.getAccount,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val accountId: String = savedStateHandle.get<String>("accountId")!!

    private val dateTimeFormatter = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss")

    private var _onAccountBalanceAvailable = MutableLiveData<String>(null)
    var onAccountBalanceAvailable: LiveData<String> = _onAccountBalanceAvailable

    private var _onTransactionsAvailable = MutableLiveData<List<TransactionViewModel>>(null)
    var onTransactionsAvailable: LiveData<List<TransactionViewModel>> = _onTransactionsAvailable


    init {
        reload()
    }

    fun reload() {
        viewModelScope.launch {
            var account = getAccount(accountId)
            _onAccountBalanceAvailable.value = account.getTotal().toString() + "€"
            var transactions = getTransactionsForAccount(accountId).map { transaction ->
                TransactionViewModel(
                    transaction.uuid,
                    transaction.description,
                    transaction.category.name,
                    transaction.getValueString() + "€",
                    transaction.dateTime.format(dateTimeFormatter)
                )
            }.toList()
            _onTransactionsAvailable.value = transactions
        }
    }

    companion object {
        fun provideFactory(
            owner: SavedStateRegistryOwner,
            defaultArgs: Bundle? = null
        ): AbstractSavedStateViewModelFactory =
            object : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(
                    key: String,
                    modelClass: Class<T>,
                    handle: SavedStateHandle
                ): T {
                    return TransactionListViewModel(savedStateHandle = handle) as T
                }
            }
    }
}

data class TransactionViewModel(
    val id: String,
    val description: String,
    val category: String,
    val value: String,
    val dateTime: String
)