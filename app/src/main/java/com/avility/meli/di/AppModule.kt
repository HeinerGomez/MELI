package com.avility.meli.di

import com.avility.meli.common.Constans
import com.avility.meli.data.remote.MeliApi
import com.avility.meli.data.repository.ProductRepositoryImpl
import com.avility.meli.domain.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Author: Heiner Gómez <agheinerag@gmail.com>
 * Description: Modulo de Dagger Hilt para proveer las dependencias de la api y el repository
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMeliApi(): MeliApi {
        return Retrofit
            .Builder()
            .baseUrl(Constans.BASE_URL_API_MELI)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MeliApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProductRepository(api: MeliApi): ProductRepository {
        return ProductRepositoryImpl(api)
    }
}