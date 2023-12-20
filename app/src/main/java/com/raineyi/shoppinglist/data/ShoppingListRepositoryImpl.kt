package com.raineyi.shoppinglist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.raineyi.shoppinglist.domain.ShopItem
import com.raineyi.shoppinglist.domain.ShopListRepository
import java.lang.RuntimeException

object ShoppingListRepositoryImpl: ShopListRepository {

    private val shopList = mutableListOf<ShopItem>()

    private var autoIncrementId = 0
    val shopListLD = MutableLiveData<List<ShopItem>>()

    init {
         for(i in 0 until 10) {
             val item = ShopItem("Name $i", i, true)
             addShopItem(item)
         }
    }
    override fun getShopList(): LiveData<List<ShopItem>> {
        return shopListLD
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        return shopList.find{
            it.id == shopItemId
        } ?: throw RuntimeException("Element with id $shopItemId not found")
    }

    override fun addShopItem(shopItem: ShopItem) {
        if(shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
        updateList()
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        updateList()
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldElement = getShopItem(shopItem.id)
        shopList.remove(oldElement)
        addShopItem(shopItem)
    }

    private fun updateList() {
        shopListLD.value = shopList.toList()
    }
}