package com.gildedrose


abstract class GlidedRoseSystem : GlidedRoseSystemController {

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
