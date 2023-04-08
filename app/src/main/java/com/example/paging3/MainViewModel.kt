package com.example.paging3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import kotlinx.coroutines.flow.Flow

class MainViewModel:ViewModel() {
    val products: Flow<PagingData<ProductData>> =
        Pager(config = PagingConfig(pageSize = 10, initialLoadSize = 10),
            pagingSourceFactory = { ProductsPagingDataSource() }
        ).flow.cachedIn(viewModelScope)
}