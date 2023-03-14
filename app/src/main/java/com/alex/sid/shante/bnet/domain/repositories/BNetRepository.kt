package com.alex.sid.shante.bnet.domain.repositories

import com.alex.sid.shante.bnet.domain.models.Drugs

interface BNetRepository {

    suspend fun getDrugsById(id:Int) : Drugs

    suspend fun getDrugs(search: String?, offset: Int, limit: Int) : List<Drugs>

}