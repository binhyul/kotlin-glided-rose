package com.gildedrose.system

import com.gildedrose.Item
import com.gildedrose.MAX_QUALITY


class AgedItemSystem : GlidedRoseSystem() {
    override fun passOneDay(item: Item) {
        super.passOneDay(item)
        if (sellInTimeout(item)) {
            upQuality(item, 2)
        } else {
            upQuality(item, 1)
        }
    }

    private fun upQuality(item: Item, upPoint: Int) {
        val qualityPoint = if(isConjuredItem(item.name)){
            upPoint *2
        }else{
            upPoint
        }

        if (item.quality + qualityPoint <= MAX_QUALITY) {
            item.quality += qualityPoint
        } else {
            maxQuality(item)
        }
    }
}