package com.gildedrose.system

import com.gildedrose.Item
import com.gildedrose.MAX_QUALITY

class TicketSystem : GlidedRoseSystem() {
    override fun passOneDay(item: Item) {
        val todayLeftSellIn = item.sellIn
        super.passOneDay(item)
        checkTicket(item, todayLeftSellIn)
    }


    private fun checkTicket(item: Item, sellIn: Int) {
        when {
            sellInTimeout(item) -> zeroQuality(item)
            sellIn <= 5 -> upQuality(item, 3)
            sellIn <= 10 -> upQuality(item, 2)
            else -> upQuality(item, 1)
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