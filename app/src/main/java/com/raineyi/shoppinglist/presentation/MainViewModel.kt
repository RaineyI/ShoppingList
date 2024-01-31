package com.raineyi.shoppinglist.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.raineyi.shoppinglist.data.ShoppingListRepositoryImpl
import com.raineyi.shoppinglist.domain.DeleteShopItemUseCase
import com.raineyi.shoppinglist.domain.EditShopItemUseCase
import com.raineyi.shoppinglist.domain.GetShopListUseCase
import com.raineyi.shoppinglist.domain.ShopItem

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ShoppingListRepositoryImpl(application)

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()

    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }

    fun changeEnableState(shopItem: ShopItem) {
        val newItem = shopItem.copy(enable = !shopItem.enable)
        editShopItemUseCase.editShopItem(newItem)
    }
}