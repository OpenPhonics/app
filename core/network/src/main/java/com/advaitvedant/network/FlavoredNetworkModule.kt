package com.advaitvedant.network

import com.advaitvedant.network.retrofit.RetrofitOpNetwork
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface FlavoredNetworkModule {

    @Binds
    fun binds(impl: RetrofitOpNetwork): OpNetworkDataSource
}
