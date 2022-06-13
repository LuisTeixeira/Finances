package com.lfmteixeira.composefinances.ui.transactions.detail

import android.os.Bundle
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.savedstate.SavedStateRegistryOwner
import com.lfmteixeira.composefinances.Graph
import com.lfmteixeira.composefinances.usecases.transaction.GetTransaction
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter

class TransactionDetailViewModel(
    private val getTransaction: GetTransaction = Graph.getTransaction,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val dateTimeFormatter = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss")

    private val transactionId: String = savedStateHandle.get<String>("transactionId")!!

    val state: MutableState<TransactionDetailState> = mutableStateOf(TransactionDetailState())

    init {
        viewModelScope.launch {
            val transaction = getTransaction(transactionId)
            state.value = TransactionDetailState(
                description = transaction.description,
                category = transaction.category.toString(),
                amount = transaction.getValueString() + "€",
                dateTime = transaction.dateTime.format(dateTimeFormatter)
            )
        }
    }

    companion object {
        fun provideFactory(
            owner: SavedStateRegistryOwner,
            defaultArgs: Bundle? = null
        ): AbstractSavedStateViewModelFactory =
            object : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel?> create(
                    key: String,
                    modelClass: Class<T>,
                    handle: SavedStateHandle
                ): T {
                    return TransactionDetailViewModel(savedStateHandle = handle) as T
                }
            }
    }
}

data class TransactionDetailState(
    val description: String = "",
    val category: String = "",
    val amount: String = "",
    val dateTime: String = ""
)