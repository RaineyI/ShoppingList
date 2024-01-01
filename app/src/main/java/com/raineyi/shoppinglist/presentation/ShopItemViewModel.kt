package com.raineyi.shoppinglist.presentation

import androidx.lifecycle.ViewModel
import com.raineyi.shoppinglist.data.ShoppingListRepositoryImpl
import com.raineyi.shoppinglist.domain.AddShopItemUseCase
import com.raineyi.shoppinglist.domain.EditShopItemUseCase
import com.raineyi.shoppinglist.domain.GetShopItemUseCase
import com.raineyi.shoppinglist.domain.ShopItem
import java.lang.Exception

class ShopItemViewModel : ViewModel() {

    private val repository = ShoppingListRepositoryImpl
    private val getShopItemUseCase = GetShopItemUseCase(repository)
    private val addShopItemUseCase = AddShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    fun getShopItem(shopItemId: Int) {
        val item = getShopItemUseCase.getShopItem(shopItemId)

    }

    fun addShopItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name, count)
        if(fieldsValid) {
            val shopItem = ShopItem(name, count, true)
            addShopItemUseCase.addShopItem(shopItem)
        }
    }

    fun editShopItem(shopItem: ShopItem, inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name, count)
        if (fieldsValid) {
            val shopItem = ShopItem(name, count, true)
            editShopItemUseCase.editShopItem(shopItem)
        }
    }

    private fun parseName(inputName: String?): String {
        return inputName?.trim() ?: ""
    }

    private fun parseCount(inputCount: String?)  = inputCount?.trim()?.toIntOrNull() ?: 0

    private fun validateInput(name: String, count: Int): Boolean {
        var result = true
        if (name.isBlank())  {
            //TODO: Show error input name
            result = false
        }
        if (count <= 0) {
            //TODO: Show error input count
            result = false
        }
        return result
    }
}