package com.android.fetchandroidloganp.network

import com.android.fetchandroidloganp.model.Item
import retrofit2.Response
import retrofit2.http.GET

interface FetchApiService {
    @GET("hiring.json")
    fun getFetchItemList(): Response<List<Item>>
}