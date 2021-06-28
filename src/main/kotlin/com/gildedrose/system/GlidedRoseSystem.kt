package com.gildedrose.system

import com.gildedrose.CONJURED
import com.gildedrose.system.controller.GlidedRoseSystemController
import com.gildedrose.Item
import com.gildedrose.MAX_QUALITY
import com.gildedrose.MIN_QUALITY


abstract class GlidedRoseSystem : GlidedRoseSystemController {

    fun isConjuredItem(itemName :String) = itemName.contains(CONJURED)

    override fun passOneDay(item: Item) {
        downSellIn(item)
    }

    fun downSellIn(item: Item) {
        item.sellIn -= 1
    }

    fun sellInTimeout(item: Item) = item.sellIn < 0

    fun maxQuality(item: Item) {
        item.quality = MAX_QUALITY
    }

    fun zeroQuality(item: Item) {
        item.quality = MIN_QUALITY
    }
}
