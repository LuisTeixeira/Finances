package com.lfmteixeira.composefinances.ui.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lfmteixeira.composefinances.Graph
import com.lfmteixeira.composefinances.domain.Account
import com.lfmteixeira.composefinances.usecases.account.GetAllAccounts
import kotlinx.coroutines.launch

class AccountListViewModel(
    private val getAllAccounts: GetAllAccounts = Graph.getAllAccounts
) : ViewModel() {

    private var _onAccountListAvailable = MutableLiveData<List<AccountViewModel>>(null)
    var onAccountListAvailable: LiveData<List<AccountViewModel>> = _onAccountListAvailable

    init {
        reload()
    }

    fun reload() {
        viewModelScope.launch {
            var accounts = getAllAccounts().map { account -> AccountViewModel(account.uuid, account.name, account.description, account.getTotal().toString() + "€") }.toList()
            _onAccountListAvailable.value = accounts
        }
    }

}

class AccountViewModel(
    val id: String,
    val name: String,
    val description: String,
    val value: String
)