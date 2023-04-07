package com.example.paging3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import kotlinx.coroutines.flow.Flow

class MainViewModel:ViewModel() {
    val users: Flow<PagingData<UserData>> =
        Pager(config = PagingConfig(pageSize = 2, prefetchDistance = 2),
            pagingSourceFactory = { UsersPagingDataSource() }
        ).flow.cachedIn(viewModelScope)
}