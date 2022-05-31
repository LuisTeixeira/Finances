package com.lfmteixeira.composefinances.ui.account.create

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner

class AccountCreateViewModel(
    val navigateAfterSave: () -> Unit
): ViewModel() {
    val state: AccountCreateState = AccountCreateState()

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
    val name: String = "",
    val description: String = "",
    val initialValue: String = ""
)