package com.alex.sid.shante.bnet.data

import com.alex.sid.shante.bnet.domain.models.Drugs
import retrofit2.http.GET
import retrofit2.http.Query

interface BNetApi {

    @GET("api/ppp/item")
    suspend fun getDrugsById(
        @Query("id") id:Int
    ) : Drugs

    @GET("api/ppp/index")
    suspend fun getDrugs(
        @Query("search") search: String?,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ) : List<Drugs>

}