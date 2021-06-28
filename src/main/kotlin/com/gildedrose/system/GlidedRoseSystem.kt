package com.gildedrose.system

import com.gildedrose.CONJURED
import com.gildedrose.system.controller.GlidedRoseSystemController
import com.gildedrose.Item


abstract class GlidedRoseSystem : GlidedRoseSystemController {

    fun isConjuredItem(itemName :String) = itemName.contains(CONJURED)

    fun downSellIn(item: Item) {
        item.sellIn -= 1
    }

    fun sellInTimeout(item: Item) = item.sellIn < 0

    fun maxQuality(item: Item) {
        item.quality = 50
    }

    fun zeroQuality(item: Item) {
        item.quality = 0
    }
}