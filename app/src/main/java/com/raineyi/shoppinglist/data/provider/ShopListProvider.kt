package com.raineyi.shoppinglist.data.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import android.util.Log
import com.raineyi.shoppinglist.data.database.ShopListDao
import com.raineyi.shoppinglist.data.mappers.ShopListMapper
import com.raineyi.shoppinglist.domain.ShopItem
import com.raineyi.shoppinglist.presentation.ShopApplication
import javax.inject.Inject

class ShopListProvider : ContentProvider() {

    private val component by lazy {
        (context as ShopApplication).component
    }

    @Inject
    lateinit var shopListDao: ShopListDao

    @Inject
    lateinit var mapper: ShopListMapper

    private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
        addURI("com.raineyi.shoppinglist", "shop_items", GET_SHOP_ITEMS_QUERY)
    }

    override fun onCreate(): Boolean {
        component.inject(this)
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        return when (uriMatcher.match(uri)) {
            GET_SHOP_ITEMS_QUERY -> {
                shopListDao.getShopListCursor()
            }

            else -> {
                null
            }
        }
    }

    override fun getType(uri: Uri): String? {
        TODO("Not yet implemented")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        when (uriMatcher.match(uri)) {
            GET_SHOP_ITEMS_QUERY -> {
                if (values == null) return null
                val id = values.getAsInteger(ID_KEY)
                val name = values.getAsString(NAME_KEY)
                val count = values.getAsInteger(COUNT_KEY)
                val enable = values.getAsBoolean(ENABLE_KEY)
                val shopItem = ShopItem(
                    id = id,
                    name = name,
                    count = count,
                    enable = enable
                )
                shopListDao.addShopItemSync(mapper.mapEntityToDbModel(shopItem))
            }
        }
        return null
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        when (uriMatcher.match(uri)) {
            GET_SHOP_ITEMS_QUERY -> {
                val id = selectionArgs?.get(0)?.toInt() ?: -1
                return shopListDao.deleteShopItemSync(id)
            }
        }
        return 0
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        when (uriMatcher.match(uri)) {
            GET_SHOP_ITEMS_QUERY -> {
                val oldId = selectionArgs?.get(0)?.toInt() ?: -1
                shopListDao.deleteShopItemSync(oldId)

                if (values == null) return 0
                val id = values.getAsInteger(ID_KEY)
                val name = values.getAsString(NAME_KEY)
                val count = values.getAsInteger(COUNT_KEY)
                val enable = values.getAsBoolean(ENABLE_KEY)
                val shopItem = ShopItem(
                    id = id,
                    name = name,
                    count = count,
                    enable = enable
                )
                shopListDao.addShopItemSync(mapper.mapEntityToDbModel(shopItem))
                return 1
            }
        }
        return 0
    }

    companion object {
        private const val GET_SHOP_ITEMS_QUERY = 100
        const val ID_KEY = "id"
        const val NAME_KEY = "name"
        const val COUNT_KEY = "count"
        const val ENABLE_KEY = "enable"
    }
}