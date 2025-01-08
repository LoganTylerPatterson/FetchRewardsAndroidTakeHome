package com.android.fetchandroidloganp.repository

import android.util.Log
import com.android.fetchandroidloganp.api.ItemsService
import com.android.fetchandroidloganp.model.Item
import com.android.fetchandroidloganp.api.APIResult
import com.android.fetchandroidloganp.util.Constants.BASE_URL
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ItemRepositoryImpl private constructor(
    private val itemsService: ItemsService
) : ItemRepository {

    override suspend fun getAllItems(): APIResult<List<Item>> {
        return try {
            val response = itemsService.getAllItems()
            if (response.isSuccessful && response.body() != null) {
                APIResult.Success(data = response.body()!!)
            } else {
                val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
                APIResult.Error(message = errorObj.getString("message"))
            }
        } catch (exception: JSONException) {
            Log.e(TAG, "getAllItems: ${exception.message}")
            APIResult.Error(message = "Something Went Wrong!")
        } catch (exception: Exception) {
            Log.e(TAG, "getAllItems: ${exception.message}")
            APIResult.Error(message = "Something Went Wrong!")
        }
    }

    companion object {
        private const val TAG = "ItemRepositoryImpl"
        private var instance: ItemRepositoryImpl? = null

        fun getInstance(): ItemRepositoryImpl {
            val service = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ItemsService::class.java)

            if (instance == null) {
                instance = ItemRepositoryImpl(service)
            }
            return instance!!
        }
    }
}
