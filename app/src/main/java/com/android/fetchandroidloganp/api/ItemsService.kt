package com.android.fetchandroidloganp.api

import com.android.fetchandroidloganp.model.Item
import com.android.fetchandroidloganp.util.Endpoints
import retrofit2.Response
import retrofit2.http.GET

interface ItemsService {
    @GET(Endpoints.HIRING)
    suspend fun getAllItems(): Response<List<Item>>
}
