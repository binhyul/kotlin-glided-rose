package com.gildedrose


class AgedItemSystem : GlidedRoseSystem() {
    override fun passOneDay(item: Item) {
        downSellIn(item)
        if (sellInTimeout(item)) {
            upQuality(item, 2)
        } else {
            upQuality(item, 1)
        }
    }

    private fun upQuality(item: Item, upPoint: Int) {
        if (item.quality + upPoint <= 50) {
            item.quality += upPoint
        } else {
            maxQuality(item)
        }
    }
}