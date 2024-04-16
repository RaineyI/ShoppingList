package com.raineyi.shoppinglist.di

import android.app.Application
import android.content.ContentProvider
import com.raineyi.shoppinglist.data.provider.ShopListProvider
import com.raineyi.shoppinglist.presentation.MainActivity
import com.raineyi.shoppinglist.presentation.ShopItemFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun inject(activity: MainActivity)
    fun inject(fragment: ShopItemFragment)
    fun inject(provider: ShopListProvider)

    @Component.Factory
    interface Factory {
        fun create (
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}