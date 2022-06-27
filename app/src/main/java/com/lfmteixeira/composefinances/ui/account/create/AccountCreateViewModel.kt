package com.lfmteixeira.composefinances.ui.account.create

import android.os.Bundle
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.savedstate.SavedStateRegistryOwner
import com.lfmteixeira.composefinances.Graph
import com.lfmteixeira.composefinances.domain.exception.ValidationException
import com.lfmteixeira.composefinances.usecases.account.CreateAccount
import com.lfmteixeira.composefinances.usecases.model.AccountModel
import kotlinx.coroutines.launch

class AccountCreateViewModel(
    val createAccount: CreateAccount = Graph.createAccount,
    val navigateAfterSave: () -> Unit
) : ViewModel() {

    val error: MutableState<ValidationException?> = mutableStateOf(null)

    fun onCreate(model: AccountCreateState) {
        viewModelScope.launch {
            val result = createAccount(
                AccountModel(
                    model.name,
                    model.description,
                    model.initialValue.toDouble()
                )
            )
            if (result.isSuccess) {
                navigateAfterSave()
            } else {
                error.value = result.exceptionOrNull() as ValidationException
            }
        }
    }

    companion object {
        fun provideFactory(
            owner: SavedStateRegistryOwner,
            defaultArgs: Bundle? = null,
            navigateAfterSave: () -> Unit
        ): AbstractSavedStateViewModelFactory =
            object : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(
                    key: String,
                    modelClass: Class<T>,
                    handle: SavedStateHandle
                ): T {
                    return AccountCreateViewModel(navigateAfterSave = navigateAfterSave) as T
                }
            }
    }
}

data class AccountCreateState(
    var name: String = "",
    var description: String = "",
    var initialValue: String = ""
)