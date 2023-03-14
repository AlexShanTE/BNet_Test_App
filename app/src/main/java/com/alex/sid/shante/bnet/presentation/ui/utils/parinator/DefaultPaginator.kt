package com.alex.sid.shante.bnet.presentation.ui.utils.parinator

class DefaultPaginator<Key, Item>(
    private val initialKey: Key,
    private val onLoadUpdated: (Boolean) -> Unit,
    private val onRequest: suspend (nextKey: Key) -> List<Item>?,
    private val getNextKey: suspend (List<Item>) -> Key,
    private val onError: suspend (Throwable) -> Unit,
    private val onSuccess: suspend (items: List<Item>, newKey: Key) -> Unit
) : Paginator<Key,Item> {

    private var currentKey = initialKey
    private var isMakingRequest = false
    private var items: List<Item> = emptyList()

    override suspend fun loadNextItems() {
        if (isMakingRequest) return
        isMakingRequest = true
        onLoadUpdated(true)
        val result = onRequest(currentKey)
        isMakingRequest = false
        if (result == null) {
            onError(SomethingWentWrongException("Something went wrong"))
            onLoadUpdated(false)
            return
        } else items = result

        currentKey = getNextKey(items)
        onSuccess(items,currentKey)
        onLoadUpdated(false)
    }

    override suspend fun reset() {
        currentKey = initialKey
    }

}

class SomethingWentWrongException(message: String): Throwable()

