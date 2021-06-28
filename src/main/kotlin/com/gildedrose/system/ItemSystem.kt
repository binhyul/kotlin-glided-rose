package com.gildedrose.system

import com.gildedrose.Item


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
        val qualityPoint = if(isConjuredItem(item.name)){
            downPoint *2
        }else{
            downPoint
        }


        if (item.quality > qualityPoint) {
            item.quality -= qualityPoint
        } else {
            zeroQuality(item)
        }
    }
}
