package com.lfmteixeira.composefinances.ui.transactions.create

import android.os.Bundle
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistryOwner
import com.lfmteixeira.composefinances.Graph
import com.lfmteixeira.composefinances.usecases.category.GetAllCategories
import com.lfmteixeira.composefinances.usecases.model.TransactionModel
import com.lfmteixeira.composefinances.usecases.transaction.CreateExpense
import com.lfmteixeira.composefinances.usecases.transaction.CreateIncome
import kotlinx.coroutines.launch

class TransactionCreateViewModel(
    val createExpense: CreateExpense = Graph.createExpense,
    val createIncome: CreateIncome = Graph.createIncome,
    val getAllCategories: GetAllCategories = Graph.getAllCategories,
    val accountId: String,
    val navigateAfterSave: () -> Unit
): ViewModel() {

    private val _selectedCategoryId: MutableState<String> = mutableStateOf("")

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
                categoryId = _selectedCategoryId.value,
                description = model.description,
                value = model.value.toDouble(),
            )
            if (model.isExpense){
                createExpense(transactionModel)
            }else{
                createIncome(transactionModel)
            }
            navigateAfterSave()
        }
    }

    fun onCategorySelected(id: String) {
        _selectedCategoryId.value = id
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
                    return TransactionCreateViewModel(navigateAfterSave = navigateAfterSave, accountId = accountId) as T
                }
            }
    }
}

data class CategoryState(
    val id: String,
    val name: String
)

data class TransactionCreateState(
    var name: String = "",
    var description: String = "",
    var value: String = "",
    var categoryId: String = "",
    var isExpense: Boolean = true
)