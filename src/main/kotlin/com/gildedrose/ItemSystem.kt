package com.gildedrose


class ItemSystem : GlidedRoseSystem() {
    override fun passOneDay(item: Item) {
        downSellIn(item)
        if (sellInTimeout(item)) {
            downQuality(item, 2)
        } else {
            downQuality(item, 1)
        }
    }

    private fun downQuality(item: Item, downPoint: Int) {
        if (item.quality > downPoint) {
            item.quality -= downPoint
        } else {
            zeroQuality(item)
        }
    }
}
