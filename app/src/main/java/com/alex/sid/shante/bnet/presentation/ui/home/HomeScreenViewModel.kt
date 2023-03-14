package com.alex.sid.shante.bnet.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alex.sid.shante.bnet.domain.repositories.HomeRepository
import com.alex.sid.shante.bnet.presentation.ui.utils.parinator.DefaultPaginator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HomeScreenState())
    val state: StateFlow<HomeScreenState> = _state.asStateFlow()

    private val defaultLimit = 10

    private val paginator = DefaultPaginator(
        initialKey = state.value.offset,
        onLoadUpdated = { _state.update { state -> state.copy(isLoading = it) } },
        onRequest = { offset ->
            repository.getDrugs(
                search = state.value.searchRequest,
                offset = offset,
                limit = defaultLimit
            )
        },
        getNextKey = {
            _state.update { state -> state.copy(offset = state.offset + defaultLimit) }
            state.value.offset
        },
        onError = { _state.update { state -> state.copy(error = it.localizedMessage) } },
        onSuccess = { items, newKey ->
            _state.update { state ->
                state.copy(
                    items = state.items + items,
                    offset = newKey,
                    endReached = items.isEmpty(),
                )
            }
        }
    )

    init {
        loadNextItems()
    }

    fun loadNextItems() {
        viewModelScope.launch {
            paginator.loadNextItems()
        }
    }

    private fun reset() {
        viewModelScope.launch {
            paginator.reset()
        }
    }

    fun updateSearchField(request: String) {
        reset()
        _state.update { state ->
            state.copy(
                isLoading = false,
                items = emptyList(),
                searchRequest = request,
                error = null,
                endReached = false,
                offset = 0
            )
        }
        loadNextItems()
    }
}