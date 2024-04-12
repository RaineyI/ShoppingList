package com.raineyi.shoppinglist.di

import android.app.Application
import com.raineyi.shoppinglist.data.database.AppDatabase
import com.raineyi.shoppinglist.data.database.ShopListDao
import com.raineyi.shoppinglist.data.repository.ShoppingListRepositoryImpl
import com.raineyi.shoppinglist.domain.ShopListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {
    @ApplicationScope
    @Binds
    fun bindRepository(impl: ShoppingListRepositoryImpl): ShopListRepository

    companion object {
        @ApplicationScope
        @Provides
        fun provideShopListDao(
            application: Application
        ): ShopListDao {
            return AppDatabase.getInstance(application).shopListDao()
        }
    }
}