package com.android.fetchandroidloganp.repository

import com.android.fetchandroidloganp.model.Item
import com.android.fetchandroidloganp.api.APIResult

interface ItemRepository {
    suspend fun getAllItems(): APIResult<List<Item>>
}