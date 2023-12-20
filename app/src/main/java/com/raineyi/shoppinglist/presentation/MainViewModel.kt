package com.raineyi.shoppinglist.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.raineyi.shoppinglist.data.ShoppingListRepositoryImpl
import com.raineyi.shoppinglist.domain.DeleteShopItemUseCase
import com.raineyi.shoppinglist.domain.EditShopItemUseCase
import com.raineyi.shoppinglist.domain.GetShopListUseCase
import com.raineyi.shoppinglist.domain.ShopItem

class MainViewModel : ViewModel() {

    private val repository = ShoppingListRepositoryImpl

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