package com.android.fetchandroidloganp.fake

import com.android.fetchandroidloganp.api.APIResult
import com.android.fetchandroidloganp.model.Item
import com.android.fetchandroidloganp.repository.ItemRepository

class FakeItemRepository: ItemRepository {
    override suspend fun getAllItems(): APIResult<List<Item>> {
        return APIResult.Success(FakeFetchApiService().getFetchItemList().body()?: emptyList())
    }
}