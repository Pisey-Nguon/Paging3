package com.example.paging3

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.coroutines.awaitObjectResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class ProductsPagingDataSource :
    PagingSource<Int, ProductData>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductData> {
        return try {
            val limit = params.loadSize
            val offset = params.key ?: 0
            val result = withContext(Dispatchers.IO){
                delay(1000)
                Fuel.get("http://94.74.81.121:3005/api/client/product/list", listOf("limit" to limit,"offset" to offset))
                    .awaitObjectResult(GsonDeserializer(ProductResponse::class.java))
            }
            result.fold(
                success = {
                    println(it.meta)
                    LoadResult.Page(
                        data = it.data,
                        prevKey = if (offset == 0) null else offset - limit,
                        nextKey = if (it.data.isEmpty()) null else offset + limit
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

    override fun getRefreshKey(state: PagingState<Int, ProductData>): Int? {
        return state.anchorPosition
    }
}