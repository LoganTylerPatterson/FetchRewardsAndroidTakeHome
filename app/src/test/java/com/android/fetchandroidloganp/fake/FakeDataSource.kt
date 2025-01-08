package com.android.fetchandroidloganp.fake

import com.android.fetchandroidloganp.model.Item
import retrofit2.Response

object FakeDataSource {
    private const val one = 1
    private const val two = 2
    private const val name1 = "114"
    private const val name2 = "115"

    val itemList: Response<List<Item>> = Response.success(listOf(
        Item(
            id = one,
            listId = one,
            name = name1
        ),
        Item(
            id = two,
            listId = two,
            name = name2
        )
    ))
}