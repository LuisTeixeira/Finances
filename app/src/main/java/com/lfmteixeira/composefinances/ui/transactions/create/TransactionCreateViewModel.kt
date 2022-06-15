package com.lfmteixeira.composefinances.ui.transactions.create

import android.os.Bundle
import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistryOwner
import com.lfmteixeira.composefinances.Graph
import com.lfmteixeira.composefinances.usecases.category.GetAllCategories
import com.lfmteixeira.composefinances.usecases.model.TransactionModel
import com.lfmteixeira.composefinances.usecases.transaction.CreateExpense
import com.lfmteixeira.composefinances.usecases.transaction.CreateIncome
import kotlinx.coroutines.launch
import java.time.LocalDate


class TransactionCreateViewModel(
    val createExpense: CreateExpense = Graph.createExpense,
    val createIncome: CreateIncome = Graph.createIncome,
    val getAllCategories: GetAllCategories = Graph.getAllCategories,
    val accountId: String,
    val navigateAfterSave: () -> Unit
) : ViewModel() {
    private var _onCategoriesAvailable = MutableLiveData<List<CategoryState>>(null)
    public val onCategoriesAvailable: LiveData<List<CategoryState>> = _onCategoriesAvailable


    init {
        viewModelScope.launch {
            _onCategoriesAvailable.value = getAllCategories().map { category ->
                CategoryState(
                    id = category.uuid,
                    name = category.name
                )
            }.toList()
        }
    }

    fun onCreate(model: TransactionCreateState) {
        viewModelScope.launch {
            val transactionModel = TransactionModel(
                accountId = accountId,
                categoryId = model.categoryId,
                description = model.description,
                value = model.value.toDouble(),
                date = model.date
            )
            if (model.isExpense) {
                createExpense(transactionModel)
            } else {
                createIncome(transactionModel)
            }
            navigateAfterSave()
        }
    }

    companion object {
        fun provideFactory(
            owner: SavedStateRegistryOwner,
            defaultArgs: Bundle? = null,
            accountId: String,
            navigateAfterSave: () -> Unit
        ): AbstractSavedStateViewModelFactory =
            object : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(
                    key: String,
                    modelClass: Class<T>,
                    handle: SavedStateHandle
                ): T {
                    return TransactionCreateViewModel(
                        navigateAfterSave = navigateAfterSave,
                        accountId = accountId
                    ) as T
                }
            }
    }
}

data class CategoryState(
    val id: String,
    val name: String
)

data class TransactionCreateState(
    var description: String = "",
    var value: String = "",
    var categoryId: String = "",
    var isExpense: Boolean = true,
    var date: LocalDate = LocalDate.now()
)