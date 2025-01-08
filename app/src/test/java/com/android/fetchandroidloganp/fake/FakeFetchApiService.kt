package com.android.fetchandroidloganp.fake

import com.android.fetchandroidloganp.model.Item
import com.android.fetchandroidloganp.network.FetchApiService
import retrofit2.Response

class FakeFetchApiService: FetchApiService {
    override fun getFetchItemList(): Response<List<Item>> {
        return FakeDataSource.itemList
    }
}