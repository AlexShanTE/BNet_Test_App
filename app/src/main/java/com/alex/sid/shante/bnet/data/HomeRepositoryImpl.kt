package com.alex.sid.shante.bnet.data

import com.alex.sid.shante.bnet.domain.models.Drugs
import com.alex.sid.shante.bnet.domain.repositories.HomeRepository

class HomeRepositoryImpl(
    private val bNetApi: BNetApi
) : HomeRepository {
    override suspend fun getDrugsById(id: Int): Drugs {
        return bNetApi.getDrugsById(id)
    }

    override suspend fun getDrugs(search: String?, offset: Int, limit: Int): List<Drugs> {
            return bNetApi.getDrugs(search = search, offset = offset, limit = limit)
    }

}