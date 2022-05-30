package com.lfmteixeira.composefinances.ui.transactions

import android.os.Bundle
import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistryOwner
import com.lfmteixeira.composefinances.Graph
import com.lfmteixeira.composefinances.domain.Transaction
import com.lfmteixeira.composefinances.usecases.transaction.GetTransactionsForAccount
import kotlinx.coroutines.launch

class TransactionListViewModel(
    private val getTransactionsForAccount: GetTransactionsForAccount = Graph.getTransactionsForAccount,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val accountId: String = savedStateHandle.get<String>("accountId")!!
    private var _onTransactionsAvailable = MutableLiveData<List<Transaction>>(null)
    var onTransactionsAvailable: LiveData<List<Transaction>> = _onTransactionsAvailable


    init {
        viewModelScope.launch {
            var transactions = getTransactionsForAccount(accountId)
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