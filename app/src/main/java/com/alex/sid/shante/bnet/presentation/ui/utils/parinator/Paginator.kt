package com.alex.sid.shante.bnet.presentation.ui.utils.parinator

interface Paginator<Key,Item> {
    suspend fun loadNextItems()
    suspend fun reset()
}