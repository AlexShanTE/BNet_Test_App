package com.alex.sid.shante.bnet.presentation.ui.details

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alex.sid.shante.bnet.di.IoDispatcher
import com.alex.sid.shante.bnet.domain.repositories.BNetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: BNetRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _state = MutableStateFlow(DetailsScreenState())
    val state: StateFlow<DetailsScreenState> = _state.asStateFlow()

    fun getDetails(id: Int) {
        viewModelScope.launch(dispatcher) {
            try {
                val drugs = repository.getDrugsById(id)
                _state.update { state ->
                    state.copy(drugs = drugs)
                }
            } catch (e: Throwable) {
                val message = e.message ?: e.toString()
            }
        }
    }

    fun makeToast(context: Context, message: String) {
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
    }
}