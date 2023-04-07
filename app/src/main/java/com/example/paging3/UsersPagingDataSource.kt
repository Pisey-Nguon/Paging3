package com.example.paging3

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.coroutines.awaitObjectResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UsersPagingDataSource :
    PagingSource<Int, UserData>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserData> {
        return try {
            val pageNumber = params.key ?: 1
            val result = withContext(Dispatchers.IO){
                Fuel.get("https://reqres.in/api/users?page=$pageNumber").awaitObjectResult(GsonDeserializer(UserResponse::class.java))
            }
            result.fold(
                success = {
                    LoadResult.Page(
                        data = it.data,
                        prevKey = null,
                        nextKey = pageNumber.plus(1)
                    )
                },
                failure = {
                    LoadResult.Error(it)
                }
            )
        }catch (e:Exception){
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, UserData>): Int? {
        return null
    }
}