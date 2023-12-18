package com.raineyi.shoppinglist.domain

data class ShopItem(
    val id: Int,
    var name: String,
    var count: Int,
    val enable: Boolean) {
}
