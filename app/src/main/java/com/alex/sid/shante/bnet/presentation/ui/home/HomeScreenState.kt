package com.alex.sid.shante.bnet.presentation.ui.home

import com.alex.sid.shante.bnet.domain.models.Drugs

data class HomeScreenState(
    val isLoading: Boolean = false,
    val items: List<Drugs> = emptyList(),
    val searchRequest: String = "",
    val error: String? = null,
    val endReached: Boolean = false,
    val offset:Int = 0
)