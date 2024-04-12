package com.raineyi.shoppinglist.presentation

import android.app.Application
import com.raineyi.shoppinglist.di.DaggerApplicationComponent

class ShopApplication: Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}