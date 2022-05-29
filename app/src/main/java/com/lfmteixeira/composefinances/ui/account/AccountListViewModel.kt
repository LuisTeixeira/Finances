package com.lfmteixeira.composefinances.ui.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lfmteixeira.composefinances.domain.Account
import com.lfmteixeira.composefinances.usecases.account.GetAllAccounts
import kotlinx.coroutines.launch

class AccountListViewModel(
    private val getAllAccounts: GetAllAccounts
) : ViewModel() {

    private var _onAccountListAvailable = MutableLiveData<List<Account>>(null)
    var onAccountListAvailable: LiveData<List<Account>> = _onAccountListAvailable

    init {
        viewModelScope.launch {
            var accounts = getAllAccounts()
            _onAccountListAvailable.value = accounts
        }
    }

}