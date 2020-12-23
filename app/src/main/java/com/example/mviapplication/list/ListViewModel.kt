package com.example.mviapplication.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ListViewModel : ViewModel() {

    val viewState = MutableLiveData<ListViewState>()
    private var state: ListViewState
        get() {
            return viewState.value!!
        }
        set(value) {
            viewState.value = value
        }

    init {
        state = ListViewState(
            isLoading = true,
            items = emptyList()
        )
    }

    fun loadData() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)

            val newWords = getWords()

            state = state.copy(
                isLoading = false,
                items = state.items + newWords
            )
        }
    }

    private suspend fun getWords(): List<String> {
        delay(300L)
        return listOf("Cupcake", "Donut", "Eclair", "Froyo", "Gingerbread", "Honeycomb", "Ice cream sandwich", "Jelly bean", "KitKat", "Lollipop", "Marshmallow", "Nougat", "Oreo", "Pie")
    }

}
